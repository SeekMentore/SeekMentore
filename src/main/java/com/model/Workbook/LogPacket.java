package commons.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import commons.constants.LogPacketAndCodeConstants;
import commons.constants.WorkbookConstants;
import commons.exceptions.ApplicationException;
import commons.interfaces.AppComparator;
import commons.interfaces.AppModel;
import commons.model.Workbook.WorkbookHeader;
import commons.model.Workbook.WorkbookRecord;
import commons.model.Workbook.WorkbookRecord.TypeOfMismatchEnum;
import commons.model.Workbook.WorkbookReport;
import commons.model.comparators.LogPacketComparator;
import commons.utils.ApplicationUtility;
import commons.utils.LoggerUtils;
import commons.utils.PrintFormatterUtils;

public class LogPacket implements AppModel, Serializable, LogPacketAndCodeConstants {

	private static final long serialVersionUID = -947132405067993007L;
	
	private List<LogCode> logCodes;
	private Long timeToExecuteInMilliseconds;
	private LogPacketComparator logPacketComparator;
	private List<Payload> payloadList;
	private List<ToolOutputFile> toolOutputFiles;
	private String toolName;
	private Map<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>> logCodeDescriptionsMap;
	private TargetLevelComparatorInfo targetLevelComparatorInfo;
	
	public TargetLevelComparatorInfo getTargetLevelComparatorInfo() {
		return targetLevelComparatorInfo;
	}

	/**
	 * Constructors
	 */
	// To be only used by local method for copying the object
	private LogPacket() {
		this.logPacketComparator = new LogPacketComparator();
	}
	
	private void setTargetLevelComparatorInfo() {
		targetLevelComparatorInfo = new TargetLevelComparatorInfo();
		// Preserve Target Information
		targetLevelComparatorInfo.setAllPayloadNames(this.getAllPayloadNames());
		targetLevelComparatorInfo.setLogCodesSize(this.getLogCodes().size());
		targetLevelComparatorInfo.setLogPacketComparatorCompareeLogPacketLogCodesSize(this.getLogPacketComparator().getCompareeLogPacket().getLogCodes().size());
		targetLevelComparatorInfo.setLogPacketComparatorMissingLogCodesSize(this.getLogPacketComparator().getMissingLogCodes().size());
		targetLevelComparatorInfo.setLogPacketComparatorExtraLogCodesSize(this.getLogPacketComparator().getExtraLogCodes().size());
		targetLevelComparatorInfo.setTimeToExecuteInMilliseconds(this.getTimeToExecuteInMilliseconds() / 1000);
		targetLevelComparatorInfo.setLogPacketComparatorCompareeLogPacketTimeToExecuteInMilliseconds(this.getLogPacketComparator().getCompareeLogPacket().getTimeToExecuteInMilliseconds() / 1000);
		targetLevelComparatorInfo.setLogPacketComparatorDifferenceIntimeToExecuteInSeconds(this.getLogPacketComparator().getDifferenceIntimeToExecuteInSeconds());
	}
	
	private void selfDesctructorbyRetainingOnlyTargetLevelInformationAndNullyingAllOtherInfo() {
		// Self Destruct all Irrelevant Information
		logCodes = null;
		timeToExecuteInMilliseconds = null;
		logPacketComparator = null;
		payloadList = null;
		toolOutputFiles = null;
		toolName = null;
		logCodeDescriptionsMap = null;
	}
	
	public LogPacket(
			String toolName,
			Set<Payload> payloadSet,
			Set<ToolOutputFile> toolOutputFilesSet
	) {
		this.logCodes = new LinkedList<LogCode>();
		this.logCodeDescriptionsMap = new LinkedHashMap<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>>();
		this.toolName = toolName;
		this.payloadList = payloadSet.stream().collect(Collectors.toList());
		this.toolOutputFiles = toolOutputFilesSet.stream().collect(Collectors.toList());
		this.logPacketComparator = new LogPacketComparator();
	}
	
	public LogPacket(
			Set<LogCode> logCodesSet, 
			String toolName,
			Set<Payload> payloadSet,
			Set<ToolOutputFile> toolOutputFilesSet
	) {
		this.logCodes = logCodesSet.stream().collect(Collectors.toList());
		this.logCodeDescriptionsMap = new LinkedHashMap<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>>();
		this.toolName = toolName;
		this.payloadList = payloadSet.stream().collect(Collectors.toList());
		this.toolOutputFiles = toolOutputFilesSet.stream().collect(Collectors.toList());
		this.logPacketComparator = new LogPacketComparator();
	}
	
	public LogPacket(
			Set<LogCode> logCodesSet, 
			Map<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>> logCodeDescriptionsSetMap, 
			String toolName,
			Set<Payload> payloadSet,
			Set<ToolOutputFile> toolOutputFilesSet
	) {
		this.logCodes = logCodesSet.stream().collect(Collectors.toList());
		this.logCodeDescriptionsMap = logCodeDescriptionsSetMap;
		this.toolName = toolName;
		this.payloadList = payloadSet.stream().collect(Collectors.toList());
		this.toolOutputFiles = toolOutputFilesSet.stream().collect(Collectors.toList());
		this.logPacketComparator = new LogPacketComparator();
	}
	/**
	 * Constructors
	 */
	
	public void addLogCode(LogCode logCode) {
		if (this.logCodes.contains(logCode)) {
			this.logCodes.get(this.logCodes.indexOf(logCode)).mergeLogCode(logCode);
		} else {
			this.logCodes.add(logCode);
		}
	}
	
	public void addLogCode(Set<LogCode> logCodes) {
		for (final LogCode logCode : logCodes) {
			addLogCode(logCode);
		}
	}
	
	public ToolOutputFile getToolOutputFileByFileDescriptionEnum(final ToolOutputFileEnum toolOutputFileEnum) {
		for(final ToolOutputFile toolOutputFile : this.toolOutputFiles) {
			if (toolOutputFileEnum.equals(toolOutputFile.getToolOutputFileEnum()))
				return toolOutputFile;
		}
		throw new ApplicationException("No tool output file found for specified Enum.");
	}
	
	public String getAllPayloadNames() {
		final StringBuilder allPayloadNames = new StringBuilder(EMPTY_STRING);
		for(final Payload payload : this.payloadList) {
			allPayloadNames.append(payload.getName()).append(SEMICOLON).append(WHITESPACE);
		}
		return allPayloadNames.toString();
	}
	
	@Override
	public void comparator(final AppModel appModel) {
		LoggerUtils.logInfoSteps("					Starting Comparision of Log Packet");
		final LogPacket logPacket = (LogPacket) appModel;
		this.logPacketComparator.setComparorLogPacket(this);
		this.logPacketComparator.setCompareeLogPacket(logPacket);
		this.logPacketComparator.startNewCommentsLine();
		final List<LogCode> tempLogCodesHolder = new LinkedList<LogCode>();
		
		// Making a copy of the LogCodes List with supplied argument LogPacket
		tempLogCodesHolder.addAll(logPacket.logCodes);
		// Getting Missing LogCodes List
		tempLogCodesHolder.removeAll(this.logCodes);
		LoggerUtils.logInfoSteps("						Getting Missing Log Codes from Summary");
		this.logPacketComparator.addMissingLogCodes(tempLogCodesHolder);
		
		// Clearing temporary LogCodeHolder
		tempLogCodesHolder.clear();
		
		// Making a copy of the LogCodes List with calling LogPacket
		tempLogCodesHolder.addAll(this.logCodes);
		// Getting Extra LogCodes List
		tempLogCodesHolder.removeAll(logPacket.logCodes);
		LoggerUtils.logInfoSteps("						Getting Extra Log Codes from Summary");
		this.logPacketComparator.addExtraLogCodes(tempLogCodesHolder);
		
		// Fetching LogCodes List present in both LogPackets
		final List<LogCode> logCodesPresentInBoth = new LinkedList<LogCode>();
		logCodesPresentInBoth.addAll(this.logCodes);
		// Removing all the Extra LogCodes
		logCodesPresentInBoth.removeAll(tempLogCodesHolder);
		LoggerUtils.logInfoSteps("						Getting Compared Log Codes from Summary");
		this.logPacketComparator.addComparedLogCodes(logCodesPresentInBoth);
		
		// Clearing temporary LogCodeHolder
		tempLogCodesHolder.clear();
		logCodesPresentInBoth.clear();
		
		// Comparing individual LogCodes which were present in both LogPackets
		LoggerUtils.logInfoSteps("						Comparing Counts of Compared Log Codes from Summary");
		for(final LogCode logCode : this.logPacketComparator.getComparedLogCodes()) {
			final LogCode toBeComparedLogCode = logPacket.logCodes.get(logPacket.logCodes.indexOf(logCode));
			logCode.comparator(toBeComparedLogCode);
		}
		
		// Comparing time of execution for both LogPackets
		LoggerUtils.logInfoSteps("						Comparing Difference in Time for Execution");
		this.logPacketComparator.setDifferenceIntimeToExecuteInMilliseconds(this.timeToExecuteInMilliseconds - logPacket.timeToExecuteInMilliseconds);
		this.logPacketComparator.setDifferenceIntimeToExecuteInSeconds(this.logPacketComparator.getDifferenceIntimeToExecuteInMilliseconds() / 1000);
		
		// Comparing LogCodeDescriptionsMap
		// Getting Missing LogCodeDescriptionsMap List
		LoggerUtils.logInfoSteps("						Getting Missing Log Codes Packet Details");
		for (Map.Entry<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>> entry : logPacket.getLogCodeDescriptionsMap().entrySet()) {
			final ToolOutputFileEnum toolOutputFileEnum = entry.getKey();
			if (this.logCodeDescriptionsMap.containsKey(toolOutputFileEnum)) {
				// If the Key is present then it is a Compared LogCodeDescription
				this.logPacketComparator.addComparedLogCodeDescriptionsMap(toolOutputFileEnum, this.logCodeDescriptionsMap.get(toolOutputFileEnum));
			} else {
				// If the Key is not present then it is a Missing LogCodeDescription
				this.logPacketComparator.addMissingLogCodeDescriptionsMap(toolOutputFileEnum, entry.getValue());
			}
		}
		
		LoggerUtils.logInfoSteps("						Getting Extra Log Codes Packet Details");
		// Getting Extra LogCodeDescriptionsMap List
		for (Map.Entry<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>> entry : this.logCodeDescriptionsMap.entrySet()) {
			final ToolOutputFileEnum toolOutputFileEnum = entry.getKey();
			if (!logPacket.getLogCodeDescriptionsMap().containsKey(toolOutputFileEnum)) {
				// If the Key is not present then it is a Extra LogCodeDescription
				this.logPacketComparator.addExtraLogCodeDescriptionsMap(toolOutputFileEnum, entry.getValue());
			}
		}
		
		LoggerUtils.logInfoSteps("						Getting Compared Log Codes Packet Details");
		LoggerUtils.logInfoSteps("						Comparing Individual Records of Compared Log Codes Packets");
		// Comparing individual LogCodeDescription List which were present in both LogCodeDescriptionsMap
		for (Map.Entry<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>> entry : this.logPacketComparator.getComparedLogCodeDescriptionsMap().entrySet()) {
			// Syriana List
			final List<LogCodeDescription> comparorLogCodeDescriptionList = ApplicationUtility.getCompleteLogCodeDescriptionListOutOfMap(entry.getValue(), false);;
			// Apex List Map
			final Map<String, List<LogCodeDescription>> compareeLogCodeDescriptionListMap = logPacket.getLogCodeDescriptionsMap().get(entry.getKey());
			 
			for (Integer iterator = 0; iterator < comparorLogCodeDescriptionList.size(); iterator++) {
				boolean isContextHit = false;
				final LogCodeDescription comparorLogCodeDescription = comparorLogCodeDescriptionList.get(iterator);
				
				final LogCodeDescription compareeLogCodeDescriptionFinder = ApplicationUtility.getLogCodeDescriptionFromMapBasedOnContextAndPopOutObjectAfterFetch(compareeLogCodeDescriptionListMap, comparorLogCodeDescription.contextCriteria());
				if (null != compareeLogCodeDescriptionFinder) {
					// If True that means it is a Hit based on Context
					isContextHit = true;
					comparorLogCodeDescription.comparator(compareeLogCodeDescriptionFinder);
				}
				if (isContextHit) {
					// If Hit based on Context found remove first List Item from Apex List Map
					compareeLogCodeDescriptionListMap.get(comparorLogCodeDescription.contextCriteria()).remove(0);
					if (compareeLogCodeDescriptionListMap.get(comparorLogCodeDescription.contextCriteria()).isEmpty()) {
						compareeLogCodeDescriptionListMap.put(comparorLogCodeDescription.contextCriteria(), null);
						compareeLogCodeDescriptionListMap.remove(comparorLogCodeDescription.contextCriteria());
					}
				} else {
					// Else Set Syriana Item as Extra
					comparorLogCodeDescription.setIsExtraLogCodeDescriptionRecord(true);
				}
			}
			
			for (Map.Entry<String, List<LogCodeDescription>> mapEntry : compareeLogCodeDescriptionListMap.entrySet()) {
				if (null != mapEntry.getValue() && !mapEntry.getValue().isEmpty()) {
					// If Object is Not NUll it means it was not encountered in Syriana List, i.e. it is a Missing Line
					for (Integer iterator = 0; iterator < mapEntry.getValue().size(); iterator++) {
						final LogCodeDescription compareeLogCodeDescriptionFinder = mapEntry.getValue().get(iterator);
						if (null != compareeLogCodeDescriptionFinder) {
							compareeLogCodeDescriptionFinder.setIsMissingLogCodeDescriptionRecord(true);
						}
					}
					// Add it at the end of Compared List Object
					entry.getValue().put(mapEntry.getKey(), mapEntry.getValue());
				}
			}
		}
		
		this.logPacketComparator.addCommentsToList();
		LoggerUtils.logInfoSteps("					Finalizing Comparision of Log Packet");
	}
	
	// Copying the current object into new object to provide to comparator
	public LogPacket getACopy() {
		final LogPacket newInstance = new LogPacket();
		newInstance.timeToExecuteInMilliseconds =  this.timeToExecuteInMilliseconds;
		newInstance.logCodes = new LinkedList<LogCode>();
		newInstance.toolName = this.toolName;
		newInstance.payloadList = new LinkedList<Payload>();
		for(final Payload payload : this.payloadList) {
			newInstance.payloadList.add(payload.getACopy());
		}
		newInstance.toolOutputFiles = new LinkedList<ToolOutputFile>();
		newInstance.logCodeDescriptionsMap = new LinkedHashMap<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>>();
		for(final LogCode logCode : this.logCodes) {
			newInstance.logCodes.add(logCode.getACopy());
		}
		for(final ToolOutputFile toolOutputFile : this.toolOutputFiles) {
			newInstance.toolOutputFiles.add(toolOutputFile.getACopy());
		}
		for (Map.Entry<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>> entry : logCodeDescriptionsMap.entrySet()) {
			final Map<String, List<LogCodeDescription>> newMapValue = new LinkedHashMap<String, List<LogCodeDescription>>();
			for (Map.Entry<String, List<LogCodeDescription>> mapEntry : entry.getValue().entrySet()) {
				final List<LogCodeDescription> newListValue = new LinkedList<LogCodeDescription>();
				for(final LogCodeDescription logCodeDescription : mapEntry.getValue()) {
					newListValue.add(logCodeDescription.getACopy());
				}
				newMapValue.put(mapEntry.getKey(), newListValue);
			}
		    newInstance.logCodeDescriptionsMap.put(entry.getKey(), newMapValue);
		}
		return newInstance;
	}
	
	@Override
	public String toString() {
		final StringBuilder description = new StringBuilder(EMPTY_STRING);
		PrintFormatterUtils.appendStartAndEndToken(description);
		PrintFormatterUtils.appendHeader(description, this.toolName + " Log Packet Details");
		PrintFormatterUtils.appendLabelAndData(description, "Time To Execute In Milliseconds", this.timeToExecuteInMilliseconds);
		PrintFormatterUtils.appendTableWithHeaderAndData(description, "Payload Details", this.payloadList);
		PrintFormatterUtils.appendTableWithHeaderAndData(description, "Tool Output File Details", this.toolOutputFiles);
		PrintFormatterUtils.appendTableWithHeaderAndData(description, "Log Codes Details", this.logCodes);
		PrintFormatterUtils.appendHeader(description, "Log Code Descriptions");
		for (Map.Entry<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>> entry : logCodeDescriptionsMap.entrySet()) {
			for (Map.Entry<String, List<LogCodeDescription>> mapEntry : entry.getValue().entrySet()) {
				PrintFormatterUtils.appendTableWithHeaderAndData(description, entry.getKey().toString(), mapEntry.getValue());
			}
		}
		PrintFormatterUtils.appendStartAndEndToken(description);
		return description.toString();
	}
	
	@Override
	public AppComparator returnModelComparator() {
		return logPacketComparator;
	}

	public List<LogCode> getLogCodes() {
		return logCodes;
	}

	public Long getTimeToExecuteInMilliseconds() {
		return timeToExecuteInMilliseconds;
	}

	public LogPacketComparator getLogPacketComparator() {
		return logPacketComparator;
	}

	public void setPayloadList(List<Payload> payloadList) {
		this.payloadList = payloadList;
	}

	public String getToolName() {
		return toolName;
	}
	
	public List<ToolOutputFile> getToolOutputFiles() {
		return toolOutputFiles;
	}

	public Map<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>> getLogCodeDescriptionsMap() {
		return logCodeDescriptionsMap;
	}

	@Override
	public WorkbookReport getWorkbookReportData() {
		setTargetLevelComparatorInfo();
		LoggerUtils.logInfoSteps("					Preparing Report Data from Log Packet");
		final WorkbookReport workbookReport = new WorkbookReport(7000);
		{
			LoggerUtils.logInfoSteps("						Preparing Log Summary Report");
			final List<WorkbookHeader> headers = new LinkedList<WorkbookHeader>();
			headers.add(new WorkbookHeader(new Object[] { "LOG_CODE", "SYRIANA_COUNT", "APEX_COUNT", WorkbookConstants.COLUMN_NAME_MATCH_STATUS, WorkbookConstants.COLUMN_NAME_COMMENTS }));
			LoggerUtils.logInfoSteps("							Missing Log Code Report");
			final List<WorkbookRecord> records = new LinkedList<WorkbookRecord>();
			for (LogCode missingLogCode : logPacketComparator.getMissingLogCodes()) {
				records.add(new WorkbookRecord(new Object[] { missingLogCode.getCode(), "0", missingLogCode.getCount(), "Missing Codes", "" }));
				// GC missingLogCode after it is done
				missingLogCode = null;
			}
			// GC missingLogCodes after it is done
			logPacketComparator.garbageCollectMissingLogCodes();
			LoggerUtils.logInfoSteps("							Extra Log Code Report");
			for (LogCode extraLogCode : logPacketComparator.getExtraLogCodes()) {
				records.add(new WorkbookRecord(new Object[] { extraLogCode.getCode(), extraLogCode.getCount(), "0", "Extra Codes", "" }));
				// GC extraLogCode after it is done
				extraLogCode = null;
			}
			// GC extraLogCodes after it is done
			logPacketComparator.garbageCollectExtraLogCodes();
			LoggerUtils.logInfoSteps("							Compared Log Code Report");
			for (LogCode comparedLogCode : logPacketComparator.getComparedLogCodes()) {
				records.add(new WorkbookRecord(new Object[] { 
										comparedLogCode.getCode(), 
										comparedLogCode.getCount(), 
										comparedLogCode.getLogCodeComparator().getCompareeLogCode().getCount(),
										comparedLogCode.getLogCodeComparator().matchStatus(), 
										comparedLogCode.getLogCodeComparator().comments().get(0) 
									}));
				// GC comparedLogCode after it is done
				comparedLogCode = null;
			}
			// GC comparedLogCodes after it is done
			logPacketComparator.garbageCollectComparedLogCodes();
			workbookReport.createSheet("Log Packet Summary", headers, records, 1, 1);
		}
		LoggerUtils.logInfoSteps("						Preparing Log Code Details Report");
		LoggerUtils.logInfoSteps("							Missing Log Code Details Report");
		for (Map.Entry<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>> entry : logPacketComparator.getMissingLogCodeDescriptionsMap().entrySet()) {
			List<LogCodeDescription> logCodeDescriptionList = ApplicationUtility.getCompleteLogCodeDescriptionListOutOfMap(entry.getValue(), false);
			if (!logCodeDescriptionList.isEmpty()) {
				final Object[] commonHeaderNames = logCodeDescriptionList.get(0).getWorkbookReportHeaderNames();
				final List<WorkbookHeader> headers = new LinkedList<WorkbookHeader>();
				headers.add(new WorkbookHeader(commonHeaderNames, 2));
				headers.add(new WorkbookHeader(ApplicationUtility.addObjectArrays(prepareToolNameInterleaf(commonHeaderNames.length), new Object[] {WorkbookConstants.COLUMN_NAME_MATCH_STATUS, WorkbookConstants.COLUMN_NAME_COMMENTS})));
				final List<WorkbookRecord> records = new LinkedList<WorkbookRecord>();
				for (LogCodeDescription logCodeDescription : logCodeDescriptionList) {
					Object[] comparorData = new Object[0];
					List<Object[]> compareeDataList = logCodeDescription.getWorkbookReportRecordData();
					for (Object[] compareeData : compareeDataList) {
						records.add(new WorkbookRecord(ApplicationUtility.addObjectArrays(ApplicationUtility.mergeTwoComparingObjectsInterleaf(comparorData, compareeData), new Object[] {"Missing Log Code", ""})));
						// GC logCodeDescription after it is done
						compareeData = null;
					}
					// GC comparorData after it is done
					comparorData = null;
					// GC compareeDataList after it is done
					compareeDataList = null;
					// GC logCodeDescription after it is done
					logCodeDescription = null;
				}
				// GC logCodeDescriptionList after it is done
				logCodeDescriptionList = null;
				workbookReport.createSheet(entry.getKey().toString(), headers, records, 1, 1);
			}
			// GC logCodeDescriptionList after it is done
			logCodeDescriptionList = null;
			// GC entry value after it is done
			entry.setValue(null);
		}
		// GC missingLogCodeDescriptionsMap after it is done
		logPacketComparator.garbageCollectMissingLogCodeDescriptionsMap();
		LoggerUtils.logInfoSteps("							Extra Log Code Details Report");
		for (Map.Entry<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>> entry : logPacketComparator.getExtraLogCodeDescriptionsMap().entrySet()) {
			List<LogCodeDescription> logCodeDescriptionList = ApplicationUtility.getCompleteLogCodeDescriptionListOutOfMap(entry.getValue(), false);
			if (!logCodeDescriptionList.isEmpty()) {
				final Object[] commonHeaderNames = logCodeDescriptionList.get(0).getWorkbookReportHeaderNames();
				final List<WorkbookHeader> headers = new LinkedList<WorkbookHeader>();
				headers.add(new WorkbookHeader(commonHeaderNames, 2));
				headers.add(new WorkbookHeader(ApplicationUtility.addObjectArrays(prepareToolNameInterleaf(commonHeaderNames.length), new Object[] {WorkbookConstants.COLUMN_NAME_MATCH_STATUS, WorkbookConstants.COLUMN_NAME_COMMENTS})));
				final List<WorkbookRecord> records = new LinkedList<WorkbookRecord>();
				for (LogCodeDescription logCodeDescription : logCodeDescriptionList) {
					List<Object[]> comparorDataList = logCodeDescription.getWorkbookReportRecordData();
					Object[] compareeData = new Object[0];
					for (Object[] comparorData : comparorDataList) {
						records.add(new WorkbookRecord(ApplicationUtility.addObjectArrays(ApplicationUtility.mergeTwoComparingObjectsInterleaf(comparorData, compareeData), new Object[] {"Extra Log Code", ""})));
						// GC logCodeDescription after it is done
						comparorData = null;
					}
					// GC compareeData after it is done
					compareeData = null;
					// GC comparorDataList after it is done
					comparorDataList = null;
					// GC logCodeDescription after it is done
					logCodeDescription = null;
				}
				// GC logCodeDescriptionList after it is done
				logCodeDescriptionList = null;
				workbookReport.createSheet(entry.getKey().toString(), headers, records, 1, 1);
			}
			// GC logCodeDescriptionList after it is done
			logCodeDescriptionList = null;
			// GC entry value after it is done
			entry.setValue(null);
		}
		// GC extraLogCodeDescriptionsMap after it is done
		logPacketComparator.garbageCollectExtraLogCodeDescriptionsMap();
		LoggerUtils.logInfoSteps("							Compared Log Code Details Report");
		for (Map.Entry<ToolOutputFileEnum, Map<String, List<LogCodeDescription>>> entry : logPacketComparator.getComparedLogCodeDescriptionsMap().entrySet()) {
			List<LogCodeDescription> logCodeDescriptionList = ApplicationUtility.getCompleteLogCodeDescriptionListOutOfMap(entry.getValue(), true);
			if (!logCodeDescriptionList.isEmpty()) {
				final Object[] commonHeaderNames = logCodeDescriptionList.get(0).getWorkbookReportHeaderNames();
				final List<WorkbookHeader> headers = new LinkedList<WorkbookHeader>();
				headers.add(new WorkbookHeader(commonHeaderNames, 2));
				headers.add(new WorkbookHeader(ApplicationUtility.addObjectArrays(prepareToolNameInterleaf(commonHeaderNames.length), new Object[] {WorkbookConstants.COLUMN_NAME_MATCH_STATUS, WorkbookConstants.COLUMN_NAME_COMMENTS})));
				final List<WorkbookRecord> records = new LinkedList<WorkbookRecord>();
				for (LogCodeDescription logCodeDescription : logCodeDescriptionList) {
					List<Object[]> comparorDataList = logCodeDescription.getWorkbookReportRecordData();
					List<Object[]> compareeDataList = null;
					Object[] compareeData = new Object[0];
					if (!logCodeDescription.getIsExtraLogCodeDescriptionRecord()  && !logCodeDescription.getIsMissingLogCodeDescriptionRecord()) {
						compareeDataList = logCodeDescription.returnCodeModelComparator().getCompareeLogCodeDescription().getWorkbookReportRecordData();
					} 
					for (int i = 0; i<comparorDataList.size(); i++) {
						if (!logCodeDescription.getIsExtraLogCodeDescriptionRecord()  && !logCodeDescription.getIsMissingLogCodeDescriptionRecord()) {
							records.add(new WorkbookRecord(ApplicationUtility.addObjectArrays(ApplicationUtility.mergeTwoComparingObjectsInterleaf(comparorDataList.get(i), compareeDataList.get(i)), new Object[] {logCodeDescription.returnCodeModelComparator().matchStatus(), logCodeDescription.returnCodeModelComparator().comments().get(i)}), !logCodeDescription.returnCodeModelComparator().getMatched(), TypeOfMismatchEnum.DATA_MISMATCH));
							// GC compareeDataList.get(i) after it is done
							compareeDataList.set(i, null);
						} else {
							if (logCodeDescription.getIsExtraLogCodeDescriptionRecord()) {
								records.add(new WorkbookRecord(ApplicationUtility.addObjectArrays(ApplicationUtility.mergeTwoComparingObjectsInterleaf(comparorDataList.get(i), compareeData), new Object[] {"Extra Log Code", ""}), true, TypeOfMismatchEnum.EXTRA_RECORD));
							} else if (logCodeDescription.getIsMissingLogCodeDescriptionRecord()) {
								// Reverse the Record Feed order as it is Missing
								records.add(new WorkbookRecord(ApplicationUtility.addObjectArrays(ApplicationUtility.mergeTwoComparingObjectsInterleaf(compareeData, comparorDataList.get(i)), new Object[] {"Missing Log Code", ""}), true, TypeOfMismatchEnum.MISSING_RECORD));
							} 
						}
						// GC comparorDataList.get(i) after it is done
						comparorDataList.set(i, null);
					}
					// GC comparorData after it is done
					compareeData = null;
					// GC comparorDataList after it is done
					comparorDataList = null;
					// GC compareeDataList after it is done
					compareeDataList = null;
					// GC logCodeDescription after it is done
					logCodeDescription = null;
				}
				// GC logCodeDescriptionList after it is done
				logCodeDescriptionList = null;
				workbookReport.createSheet(entry.getKey().toString(), headers, records, 1, 1);
			}
			// GC logCodeDescriptionList after it is done
			logCodeDescriptionList = null;
			// GC entry value after it is done
			entry.setValue(null);
		}
		// GC comparedLogCodeDescriptionsMap after it is done
		logPacketComparator.garbageCollectComparedLogCodeDescriptionsMap();
		LoggerUtils.logInfoSteps("					Finalizing Report Data from Log Packet");
		selfDesctructorbyRetainingOnlyTargetLevelInformationAndNullyingAllOtherInfo();
		return workbookReport;
	}
	
	private Object[] prepareToolNameInterleaf(int length) {
		final Object[] toolNames = new Object[length*2];
		for (int i = 0;i<toolNames.length; i+=2) {
			toolNames[i] = ApplicationUtility.controlConfiguration.getPathConfiguration().getSyrianaToolName().toUpperCase();
			toolNames[i+1] = ApplicationUtility.controlConfiguration.getPathConfiguration().getApexToolName().toUpperCase();
		}
		return toolNames;
	}

	public void setTimeToExecuteInMilliseconds(Long timeToExecuteInMilliseconds) {
		this.timeToExecuteInMilliseconds = timeToExecuteInMilliseconds;
	}
}
