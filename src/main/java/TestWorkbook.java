import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.model.components.AssignmentAttendance;
import com.model.workbook.WorkbookCell;
import com.model.workbook.WorkbookRecord;
import com.model.workbook.WorkbookReport;
import com.model.workbook.WorkbookCell.TypeOfStyleEnum;
import com.utils.FileUtils;
import com.utils.WorkbookUtils;

public class TestWorkbook {
	
	public static void main(String args[]) throws InstantiationException, IllegalAccessException, IOException {
		final AssignmentAttendance assignmentAttendance = new AssignmentAttendance();
		assignmentAttendance.setEntryDateTimeMillis(1551816000000L);
		assignmentAttendance.setExitDateTimeMillis(1551825300000L);
		assignmentAttendance.setDurationHours(2);
		assignmentAttendance.setDurationMinutes(35);
		assignmentAttendance.setTopicsTaught("Demo taken");
		assignmentAttendance.setIsClassworkProvided("Y");
		assignmentAttendance.setIsHomeworkProvided("Y");
		assignmentAttendance.setIsTestProvided("Y");
		assignmentAttendance.setRecordLastUpdatedMillis(1551729183391L);
		assignmentAttendance.setUpdatedByName("Shantanu Mukherjee");
		assignmentAttendance.setUpdatedByUserType("Employee");
		final List<AssignmentAttendance> list =  new ArrayList<AssignmentAttendance>();
		list.add(assignmentAttendance);
		final WorkbookReport workbookReport = new WorkbookReport();
		//workbookReport.createSheet("Attendance", list, AssignmentAttendance.class, "SALES_REPORT_TEST");
		workbookReport.createSheet("Attendance", getWorkbookRecords());
		workbookReport.setDefaultCellWidth(3000);
		byte[] content =  WorkbookUtils.createWorkbook(workbookReport);
		//FileUtils.generateArbitraryFile(content, "C:\\Users\\shantanu\\Desktop\\attendance.xlsx");
		FileUtils.generateArbitraryFile(content, "C:\\Users\\smukherjee\\Desktop\\src\\attendance.xlsx");
	}
	
	public static List<WorkbookRecord> getWorkbookRecords() throws InstantiationException, IllegalAccessException {
		final List<WorkbookRecord> workbookRecords = new LinkedList<WorkbookRecord>();
		
		// 1st ROW
		List<WorkbookCell> workbookCells = new LinkedList<WorkbookCell>();
		WorkbookCell workbookCell = new WorkbookCell("Seek Mentore - Attendance Tracker", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 11, 5);
		workbookCells.add(workbookCell);
		WorkbookRecord workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Tutor Invoice and Attendance", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 11, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Parent's  Details", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 6, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Package Details", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 5, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 6, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 5, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Name", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Mrs Priyanka", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Board/Syllabus", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("ICSE", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Contact No", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("9821622998", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Grade", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("6th", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Email Id", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("priyanka@parent.com", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Subject", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Science", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 6, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Start date", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("06-Mar-2019", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Tutor ID -36482", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 6, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("End date", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("06-Mar-2019", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Name", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Mr Shantanu", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Total Hours", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("25", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Contact No", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("9739936482", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Completed Hours", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("15", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Email id", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("mukherjeeshantanu797@gmail.com", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Completed Minutes", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("45", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 6, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Remaining Hours", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("9", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 6, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Remaining Minutes", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("15", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		final AssignmentAttendance assignmentAttendance = new AssignmentAttendance();
		assignmentAttendance.setEntryDateTimeMillis(1551816000000L);
		assignmentAttendance.setExitDateTimeMillis(1551825300000L);
		assignmentAttendance.setDurationHours(2);
		assignmentAttendance.setDurationMinutes(35);
		assignmentAttendance.setTopicsTaught("Demo taken");
		assignmentAttendance.setIsClassworkProvided("Y");
		assignmentAttendance.setIsHomeworkProvided("Y");
		assignmentAttendance.setIsTestProvided("Y");
		assignmentAttendance.setRecordLastUpdatedMillis(1551729183391L);
		assignmentAttendance.setUpdatedByName("Shantanu Mukherjee");
		assignmentAttendance.setUpdatedByUserType("Employee");
		final List<AssignmentAttendance> list =  new ArrayList<AssignmentAttendance>();
		list.add(assignmentAttendance);
		workbookRecords.addAll(WorkbookUtils.computeHeaderAndRecordsForApplicationWorkbookObjectList(list, AssignmentAttendance.class, "SALES_REPORT_TEST"));
		
		return workbookRecords;
	}
	
	public static List<WorkbookRecord> getWorkbookRecords_save() {
		final List<WorkbookRecord> workbookRecords = new LinkedList<WorkbookRecord>();
		
		// 1st ROW
		List<WorkbookCell> workbookCells = new LinkedList<WorkbookCell>();
		
		WorkbookCell workbookCell = new WorkbookCell("ENTRY_DATE", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 2);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("EXIT_DATE", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 2);
		workbookCells.add(workbookCell);
		WorkbookRecord workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		// 2nd ROW
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("ENTRY_DATE", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 1, 2);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("EXIT_DATE", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 1, 2);
		workbookCells.add(workbookCell);
		//workbookRecord = new WorkbookRecord(workbookCells);
		//workbookRecords.add(workbookRecord);
		
		// 3rd ROW
		//workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("06-Mar-2019", true, TypeOfStyleEnum.SOLID_FOREGROUND_GOLD);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("01:30 AM", true, TypeOfStyleEnum.SOLID_FOREGROUND_GOLD);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);

		// 3rd ROW
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("06-Mar-2019", true, TypeOfStyleEnum.SOLID_FOREGROUND_YELLOW);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("01:30 AM", true, TypeOfStyleEnum.SOLID_FOREGROUND_YELLOW);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		// 3rd ROW
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("06-Mar-2019", true, TypeOfStyleEnum.SOLID_FOREGROUND_LAVENDER);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("01:30 AM", true, TypeOfStyleEnum.SOLID_FOREGROUND_LAVENDER);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		// 3rd ROW
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("06-Mar-2019", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("01:30 AM", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		// 4th ROW
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("06-Mar-2019", true, 2, 3);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("01:30 AM", true, 2, 3);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		return workbookRecords;
	}

}
