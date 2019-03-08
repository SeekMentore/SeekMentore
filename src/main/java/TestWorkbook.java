import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.model.components.AssignmentAttendance;
import com.model.workbook.WorkbookCell;
import com.model.workbook.WorkbookCell.TypeOfStyleEnum;
import com.model.workbook.WorkbookRecord;
import com.model.workbook.WorkbookReport;
import com.utils.FileUtils;
import com.utils.WorkbookUtils;

public class TestWorkbook {
	
	public static void main(String args[]) throws InstantiationException, IllegalAccessException, IOException {
		final WorkbookReport workbookReport = new WorkbookReport();
		workbookReport.createSheet("Attendance", getWorkbookRecords());
		workbookReport.setDefaultCellWidth(4500);
		byte[] content =  WorkbookUtils.createWorkbook(workbookReport);
		//FileUtils.generateArbitraryFile(content, "C:\\Users\\shantanu\\Desktop\\attendance.xlsx");
		FileUtils.generateArbitraryFile(content, "C:\\Users\\smukherjee\\Desktop\\src\\attendance.xlsx");
	}
	
	/*public static void main(String args[]) throws IOException {
		URL url = new URL("http://imageserver.seekmentore.com/images/company-logo-complete.png");
		URLConnection connection = url.openConnection();
		InputStream inputStream = connection.getInputStream();
		byte[] bytes = IOUtils.toByteArray(inputStream);
		FileUtils.generateArbitraryFile(bytes, "C:\\Users\\smukherjee\\Desktop\\src\\company-logo-complete.png");
		URL url = new URL("http://imageserver.seekmentore.com/images/company-logo-complete.png");
		BufferedImage img = ImageIO.read(url);
		File file = new File("C:\\Users\\smukherjee\\Desktop\\src\\company-logo-complete.png");
		ImageIO.write(img, "png", file);
	}*/
	
	public static List<WorkbookRecord> getWorkbookRecords() throws InstantiationException, IllegalAccessException {
		final List<WorkbookRecord> workbookRecords = new LinkedList<WorkbookRecord>();
		
		List<WorkbookCell> workbookCells = new LinkedList<WorkbookCell>();
		WorkbookCell workbookCell = new WorkbookCell("", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.BOLD_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_DARK_BLUE, TypeOfStyleEnum.BORDER_THIN_DARK_BLUE}, true, 6, 4);
		workbookCell.setImage("http://imageserver.seekmentore.com/images/company-logo-complete.png", null, null, null, 5, 4, null);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("One Stop Solution to All Tutoring Problems", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.BOLD_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_DARK_BLUE, TypeOfStyleEnum.FONT_COLOR_WHITE, TypeOfStyleEnum.BORDER_THIN_DARK_BLUE}, true, 6, 4);
		workbookCells.add(workbookCell);
		WorkbookRecord workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Tutor Invoice and Attendance Tracker", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.BOLD_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_LIGHT_GREY}, true, 12, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Customer Id - AUBGH-56368-12345-YUGHT", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 6, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Package Id - AUBGH-56368-12345-YUGHT", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 6, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Name", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Mrs Priyanka", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Grade", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("6th", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Contact No", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("9821622998", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Subject", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Science", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Email Id", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("priyanka@parent.com", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.DEFAULT_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_LIGHT_GREY}, true, 6, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.DEFAULT_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_LIGHT_GREY}, true, 6, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Assignment Id - AUBGH-56368-12345-YUGHT", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 6, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Tutor ID - AUBGH-56368-12345-YUGHT", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 6, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Start date", true, TypeOfStyleEnum.BOLD_HEADER_CELL);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("06-Mar-2019", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("End date", true, TypeOfStyleEnum.BOLD_HEADER_CELL);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("06-Mar-2019", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 2, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Name", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Mr Shantanu", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Total Duration", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("25 h", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Contact No", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("9739936482", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Completed Duration", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.FONT_COLOR_GREEN, TypeOfStyleEnum.BOLD_HEADER_CELL}, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("15 h  45 m", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.FONT_COLOR_GREEN, TypeOfStyleEnum.BOLD_HEADER_CELL}, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("Email id", true, TypeOfStyleEnum.BOLD_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("mukherjeeshantanu797@gmail.com", true, TypeOfStyleEnum.DEFAULT_HEADER_CELL, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("Remaining Duration", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.FONT_COLOR_RED, TypeOfStyleEnum.BOLD_HEADER_CELL}, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookCell = new WorkbookCell("9 h  15 m", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.FONT_COLOR_RED, TypeOfStyleEnum.BOLD_HEADER_CELL}, true, 3, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.BOLD_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_LIGHT_GREY}, true, 12, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		final AssignmentAttendance assignmentAttendance = new AssignmentAttendance();
		assignmentAttendance.setEntryDateTimeMillis(1551816000000L);
		assignmentAttendance.setExitDateTimeMillis(1551825300000L);
		assignmentAttendance.setDurationHours(2);
		assignmentAttendance.setDurationMinutes(35);
		assignmentAttendance.setTopicsTaught("Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken Demo taken");
		assignmentAttendance.setIsClassworkProvided("Y");
		assignmentAttendance.setIsHomeworkProvided("Y");
		assignmentAttendance.setIsTestProvided("Y");
		assignmentAttendance.setRecordLastUpdatedMillis(1551729183391L);
		assignmentAttendance.setUpdatedByName("Shantanu Mukherjee");
		assignmentAttendance.setUpdatedByUserType("Employee");
		final List<AssignmentAttendance> list =  new ArrayList<AssignmentAttendance>();
		list.add(assignmentAttendance);
		workbookRecords.addAll(WorkbookUtils.computeHeaderAndRecordsForApplicationWorkbookObjectList(list, AssignmentAttendance.class, "ATTENDANCE_TRACKER_SHEET"));
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("", true, new TypeOfStyleEnum[] {TypeOfStyleEnum.BOLD_HEADER_CELL, TypeOfStyleEnum.SOLID_FOREGROUND_LIGHT_GREY}, true, 12, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("", true, 11, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("1. Payout will be made on every Wednesday, Cut-off day for 'Wednesday-payouts' will be preceding Sunday", true, 12, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("2. Academic Quality, Punctuality and Overall rating columns need to be filled by parent ", true, 12, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("3. Invoice need to be signed by parents and its scanned copy  should reach ' tutorpay@helloclass.com' on or before the cutoff days", true, 12, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("4. Completion of package is mandatory for payout, Helloclass will not be able to process any  partial payments ", true, 12, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("5. Name of scanned image- should be 'Packageid'", true, 12, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("6. In order to have hassle free payout , kindly adhere to above instructions", true, 12, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("7. For timely renewal, kindly get in touch with your assigned relationship manager(3 classes before the completion of package)", true, 12, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
		workbookCells = new LinkedList<WorkbookCell>();
		workbookCell = new WorkbookCell("8. Kindly don’t extend number of sessions or hours, beyond the finalized terms. Helloclass will not be liable to pay for any additional hour", true, 12, 1);
		workbookCells.add(workbookCell);
		workbookRecord = new WorkbookRecord(workbookCells);
		workbookRecords.add(workbookRecord);
		
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
