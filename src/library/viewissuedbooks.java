package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class viewissuedbooks {
public static void viewissuedbooks() {
	try {
	Connection con=DBConnection.getConnection();
	String sql="select*from issued_books";
	PreparedStatement ps=con.prepareStatement(sql);
ResultSet rs=ps.executeQuery();
if(rs.next()) {
	System.out.println("roll_no:"+rs.getInt("roll_no"));
	System.out.println("book id:"+rs.getInt("book_id"));
	System.out.println("book title:"+rs.getString("book_title"));
	System.out.println("issue date:"+rs.getDate("issue_date"));
	System.out.println("return date:"+rs.getDate("return_date"));
}
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
}
