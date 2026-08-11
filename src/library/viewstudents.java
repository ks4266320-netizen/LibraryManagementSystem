package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class viewstudents {
public static void viewstudents() {
	Connection con=DBConnection.getConnection();
	try {
	String sql="select*from students";
	PreparedStatement ps=con.prepareStatement(sql);
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
		System.out.println("name:"+rs.getString("name"));
		System.out.println("roll no:"+rs.getInt("roll_no"));
		System.out.println("department:"+rs.getString("department"));
		System.out.println("email:"+rs.getString("email"));
	}
	con.close();
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
}
