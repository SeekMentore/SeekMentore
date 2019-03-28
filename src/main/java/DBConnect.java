import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.utils.UUIDGeneratorUtils;
import com.utils.ValidationUtils;

public class DBConnect {

	public static void main(String args[]) {
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
