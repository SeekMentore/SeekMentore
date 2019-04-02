import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.utils.UUIDGeneratorUtils;
import com.utils.ValidationUtils;

public class DBConnect {
	/*
	
  	feedSubscribeWithUsSerialId();
 	feedSubmitQuerySerialId();	
	feedFindTutorSerialId();
	feedBecomeTutorSerialId();
	feedEmployeeEmailSerialId();
	feedEmployeeContactNumberSerialId();
	feedEmployeeSerialId();
	feedPasswordChangeSerialId();
	feedLogonSerialId();
	feedErrorSerialId();
	feedMailAttachmentSerialId();
	feedMailSerialId();
	feedDocumentSerialId();
	feedTutorEmailIdSerialId();
	feedTutorContactNumberSerialId();
	feedTutorSerialId();
	feedCustomerEmailIdSerialId();
	feedCustomerContactNumberSerialId();
	feedCustomerSerialId();
	feedEnquirySerialId();
	feedTutorMapperSerialId();
	feedDemoSerialId();
	
	*/
	public static void main(String args[]) {
	}
	
	public static void feedSubscribeWithUsSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SUBSCRIBE_WITH_US");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("TENTATIVE_SUBSCRIPTION_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE SUBSCRIBE_WITH_US SET SUBSCRIBE_WITH_US_SERIAL_ID = '"+serailId+"' WHERE TENTATIVE_SUBSCRIPTION_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedSubmitQuerySerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SUBMIT_QUERY");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("QUERY_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE SUBMIT_QUERY SET QUERY_SERIAL_ID = '"+serailId+"' WHERE QUERY_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedFindTutorSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM FIND_TUTOR");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("ENQUIRY_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE FIND_TUTOR SET FIND_TUTOR_SERIAL_ID = '"+serailId+"' WHERE ENQUIRY_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedBecomeTutorSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BECOME_TUTOR");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("TENTATIVE_TUTOR_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE BECOME_TUTOR SET BECOME_TUTOR_SERIAL_ID = '"+serailId+"' WHERE TENTATIVE_TUTOR_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedEmployeeEmailSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE_EMAIL_ID");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("EMPLOYEE_EMAIL_ID_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE EMPLOYEE_EMAIL_ID SET EMPLOYEE_EMAIL_ID_SERIAL_ID = '"+serailId+"' WHERE EMPLOYEE_EMAIL_ID_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedEmployeeContactNumberSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE_CONTACT_NUMBER");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("EMPLOYEE_CONTACT_NUMBER_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE EMPLOYEE_CONTACT_NUMBER SET EMPLOYEE_CONTACT_NUMBER_SERIAL_ID = '"+serailId+"' WHERE EMPLOYEE_CONTACT_NUMBER_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedEmployeeSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("EMPLOYEE_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE EMPLOYEE SET EMPLOYEE_SERIAL_ID = '"+serailId+"' WHERE EMPLOYEE_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedPasswordChangeSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PASSWORD_CHANGE_TRACKER");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("PASSWORD_CHANGE_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE PASSWORD_CHANGE_TRACKER SET PASSWORD_CHANGE_SERIAL_ID = '"+serailId+"' WHERE PASSWORD_CHANGE_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedLogonSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM LOGON_TRACKER");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("LOGON_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE LOGON_TRACKER SET LOGON_SERIAL_ID = '"+serailId+"' WHERE LOGON_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedErrorSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM APP_ERROR_REPORT");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("ERROR_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE APP_ERROR_REPORT SET ERROR_SERIAL_ID = '"+serailId+"' WHERE ERROR_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedMailAttachmentSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM MAIL_ATTACHMENTS");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("ATTACHMENT_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE MAIL_ATTACHMENTS SET ATTACHMENT_SERIAL_ID = '"+serailId+"' WHERE ATTACHMENT_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedMailSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM MAIL_QUEUE");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("MAIL_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE MAIL_QUEUE SET MAIL_SERIAL_ID = '"+serailId+"' WHERE MAIL_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedDocumentSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TUTOR_DOCUMENT");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("DOCUMENT_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE TUTOR_DOCUMENT SET DOCUMENT_SERIAL_ID = '"+serailId+"' WHERE DOCUMENT_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedTutorEmailIdSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM REGISTERED_TUTOR_EMAIL_ID");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("REGISTERED_TUTOR_EMAIL_ID_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE REGISTERED_TUTOR_EMAIL_ID SET REGISTERED_TUTOR_EMAIL_ID_SERIAL_ID = '"+serailId+"' WHERE REGISTERED_TUTOR_EMAIL_ID_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedTutorContactNumberSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM REGISTERED_TUTOR_CONTACT_NUMBER");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("REGISTERED_TUTOR_CONTACT_NUMBER_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE REGISTERED_TUTOR_CONTACT_NUMBER SET REGISTERED_TUTOR_CONTACT_NUMBER_SERIAL_ID = '"+serailId+"' WHERE REGISTERED_TUTOR_CONTACT_NUMBER_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedTutorSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM REGISTERED_TUTOR");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("TUTOR_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE REGISTERED_TUTOR SET TUTOR_SERIAL_ID = '"+serailId+"' WHERE TUTOR_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedCustomerEmailIdSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SUBSCRIBED_CUSTOMER_EMAIL_ID");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("SUBSCRIBED_CUSTOMER_EMAIL_ID_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE SUBSCRIBED_CUSTOMER_EMAIL_ID SET SUBSCRIBED_CUSTOMER_EMAIL_ID_SERIAL_ID = '"+serailId+"' WHERE SUBSCRIBED_CUSTOMER_EMAIL_ID_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedCustomerContactNumberSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SUBSCRIBED_CUSTOMER_CONTACT_NUMBER");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("SUBSCRIBED_CUSTOMER_CONTACT_NUMBER_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE SUBSCRIBED_CUSTOMER_CONTACT_NUMBER SET SUBSCRIBED_CUSTOMER_CONTACT_NUMBER_SERIAL_ID = '"+serailId+"' WHERE SUBSCRIBED_CUSTOMER_CONTACT_NUMBER_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedCustomerSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SUBSCRIBED_CUSTOMER");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("CUSTOMER_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE SUBSCRIBED_CUSTOMER SET CUSTOMER_SERIAL_ID = '"+serailId+"' WHERE CUSTOMER_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedEnquirySerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ENQUIRY");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("ENQUIRY_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE ENQUIRY SET ENQUIRY_SERIAL_ID = '"+serailId+"' WHERE ENQUIRY_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedTutorMapperSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TUTOR_MAPPER");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("TUTOR_MAPPER_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE TUTOR_MAPPER SET TUTOR_MAPPER_SERIAL_ID = '"+serailId+"' WHERE TUTOR_MAPPER_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void feedDemoSerialId() {
		System.out.println("Started");
		final List<String> dbUniqueIdList = new LinkedList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM DEMO");
			while (rs.next()) {
				dbUniqueIdList.add(rs.getString("DEMO_TRACKER_ID"));
			}
			if (ValidationUtils.checkNonEmptyList(dbUniqueIdList)) {
				for (final String dbUniqueId : dbUniqueIdList) {
					final String serailId = UUIDGeneratorUtils.generateSerialGUID();
					stmt.executeUpdate("UPDATE DEMO SET DEMO_SERIAL_ID = '"+serailId+"' WHERE DEMO_TRACKER_ID = '"+dbUniqueId+"'");
					System.out.println(dbUniqueId + "  " + serailId);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Completed");
	}
	
	public static void querySomeTable() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SEEK_MENTORE", "root", "pass");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from DEMO");
			while (rs.next()) {
				System.out.println(rs.getString("DEMO_SERIAL_ID"));				
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
