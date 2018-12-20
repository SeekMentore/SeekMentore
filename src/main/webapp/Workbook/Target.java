package commons.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import commons.constants.ApplicationConstants;
import commons.constants.LogPacketAndCodeConstants;
import commons.constants.WorkbookConstants;
import commons.interfaces.AppComparator;
import commons.interfaces.AppModel;
import commons.model.Workbook.WorkbookHeader;
import commons.model.Workbook.WorkbookRecord;
import commons.model.Workbook.WorkbookReport;
import commons.utils.ApplicationUtility;
import commons.utils.FileHandlerUtility;
import commons.utils.LoggerUtils;
import commons.utils.WorkbookUtils;

public class Target implements AppModel, Serializable, LogPacketAndCodeConstants {

	private static final long serialVersionUID = -8074116114663876812L;
	private String name;
	private String directoryPath;
	private List<LogPacket> logPackets;
	
	private Target () {}
	
	public Target (
			String name, 
			String directoryPath
	) {
		this.name = name;
		this.directoryPath = directoryPath;
		logPackets = new LinkedList<LogPacket>();
	}
	
	public Target (
		String name, 
		String directoryPath,
		Set<LogPacket> logPacketsSet
	) {
		this.name = name;
		this.directoryPath = directoryPath;
		this.logPackets = logPacketsSet.stream().collect(Collectors.toList());
	}
	
	public void addLogPacket(LogPacket logPacket) {
		this.logPackets.add(logPacket);
	}
	
	public void addLogPacket(Set<LogPacket> logPackets) {
		for (final LogPacket logPacket : logPackets) {
			addLogPacket(logPacket);
		}
	}
	
	public Target getACopy() {
		final Target newInstance = new Target();
		newInstance.name =  this.name;
		newInstance.directoryPath =  this.directoryPath;
		newInstance.logPackets = new LinkedList<LogPacket>();
		for(final LogPacket logPacket : this.logPackets) {
			newInstance.logPackets.add(logPacket.getACopy());
		}
		return newInstance;
	}
	
	public void processTarget() throws IOException, Exception {
		final List<File> payloadFileList  = FileHandlerUtility.listFilesInDirectory(this.directoryPath + BACKWARD_SLASH + this.name, ApplicationUtility.controlConfiguration.getTargetConfiguration().getPayloadFileExtensionsToBeProcessed());
		for (final File payloadFile : payloadFileList) {
			final Payload payload = new Payload(payloadFile.getName(), payloadFile.getAbsolutePath());
			final Set<Payload> payloadSet = new HashSet<Payload>();
			payloadSet.add(payload);
			LoggerUtils.logInfoSteps("			Processing Payload >> " + ApplicationConstants.INVERTED_COMMA + payload.getName() + ApplicationConstants.INVERTED_COMMA);
			final LogPacket apexLogPacket = apex.com.parsers.Parser.init(ApplicationUtility.controlConfiguration.getPathConfiguration().getApexToolName(), payloadSet, payload.runPayloadOnApexAndReturnToolOutputFilesSet(this));
			final LogPacket syrianaLogPacket = syriana.com.parsers.Parser.init(ApplicationUtility.controlConfiguration.getPathConfiguration().getSyrianaToolName(), payloadSet, payload.runPayloadOnSyrianaAndReturnToolOutputFilesSet(this));
			LoggerUtils.logInfoSteps("				Comparing the Output Generated from " + ApplicationConstants.INVERTED_COMMA + ApplicationUtility.controlConfiguration.getPathConfiguration().getSyrianaToolName() + ApplicationConstants.INVERTED_COMMA + " with " + ApplicationConstants.INVERTED_COMMA + ApplicationUtility.controlConfiguration.getPathConfiguration().getApexToolName() + ApplicationConstants.INVERTED_COMMA);
			syrianaLogPacket.setTimeToExecuteInMilliseconds(payload.getExecutionTimeInMillisecondsForSyriana());
			apexLogPacket.setTimeToExecuteInMilliseconds(payload.getExecutionTimeInMillisecondsForApex());
			syrianaLogPacket.comparator(apexLogPacket);
			addLogPacket(syrianaLogPacket);
			LoggerUtils.logInfoSteps("				Creating Comparison report for payload");
			final byte[] reportBytes = WorkbookUtils.createReport(syrianaLogPacket);
			final String directoryPath = ApplicationUtility.controlConfiguration.getPathConfiguration().getOutputFolderPath() 
					+ BACKWARD_SLASH 
					+ ApplicationUtility.controlConfiguration.getExecutionTimeString()
					+ BACKWARD_SLASH
					+ ApplicationUtility.controlConfiguration.getPathConfiguration().getReportOutputFolderName() 
					+ BACKWARD_SLASH 
					+ this.getName();
			
			final String fileName = payload.getName() + ApplicationConstants.PERIOD + WorkbookConstants.REPORT_FILE_EXTENSIONS;
			LoggerUtils.logInfoSteps("				Writing report for payload");
			FileHandlerUtility.createDirectoryIfNotExists(directoryPath);
			FileHandlerUtility.writeBytesToFile(reportBytes, directoryPath, fileName);
		}
	}
	
	@Override
	public String toString() {
		final StringBuilder description = new StringBuilder(EMPTY_STRING);
		return description.toString();
	}

	@Override
	public AppComparator returnModelComparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkbookReport getWorkbookReportData() {
		final WorkbookReport workbookReport = new WorkbookReport(7000);
		{
			final List<WorkbookHeader> headers = new LinkedList<WorkbookHeader>();
			headers.add(new WorkbookHeader(new Object[] { 
									"PAYLOAD_NAME", 
									"TOTAL_LOG _CODES_IN_SYRIANA", 
									"TOTAL_LOG_CODES_IN_APEX", 
									"TOTAL_MISSING_LOG_CODES", 
									"TOTAL_EXTRA_LOG_CODES",
									"EXECUTION_TIME_FOR_SYRIANA_(SECONDS)",
									"EXECUTION_TIME_FOR_APEX_(SECONDS)",
									"DIFFERNCE_IN_TIME_OF_EXECUTION_(SYRIANA - APEX)"
							}));
			final List<WorkbookRecord> records = new LinkedList<WorkbookRecord>();
			for (LogPacket logPacket : logPackets) {
				records.add(new WorkbookRecord(new Object[] { 
										logPacket.getTargetLevelComparatorInfo().getAllPayloadNames(),
										logPacket.getTargetLevelComparatorInfo().getLogCodesSize(),
										logPacket.getTargetLevelComparatorInfo().getLogPacketComparatorCompareeLogPacketLogCodesSize(),
										logPacket.getTargetLevelComparatorInfo().getLogPacketComparatorMissingLogCodesSize(),
										logPacket.getTargetLevelComparatorInfo().getLogPacketComparatorExtraLogCodesSize(),
										logPacket.getTargetLevelComparatorInfo().getTimeToExecuteInMilliseconds(),
										logPacket.getTargetLevelComparatorInfo().getLogPacketComparatorCompareeLogPacketTimeToExecuteInMilliseconds(),
										logPacket.getTargetLevelComparatorInfo().getLogPacketComparatorDifferenceIntimeToExecuteInSeconds()
								}));	
				// GC logPacket after it is done
				logPacket = null;
			}
			// GC logPacket after it is done
			logPackets = null;
			workbookReport.createSheet("Summary", headers, records, 1, 1);
		}
		return workbookReport;
	}

	@Override
	public void comparator(AppModel appModel) {
		// TODO Auto-generated method stub
	}

	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LogPacket> getLogPackets() {
		return logPackets;
	}

}
