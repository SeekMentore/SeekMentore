import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.model.components.AssignmentAttendance;
import com.model.workbook.WorkbookReport;
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
		workbookReport.createSheet("Attendance", list, AssignmentAttendance.class, "SALES_REPORT_TEST");
		byte[] content =  WorkbookUtils.createWorkbook(workbookReport);
		//FileUtils.generateArbitraryFile(content, "C:\\Users\\shantanu\\Desktop\\attendance.xlsx");
		FileUtils.generateArbitraryFile(content, "C:\\Users\\smukherjee\\Desktop\\src\\attendance.xlsx");
	}

}
