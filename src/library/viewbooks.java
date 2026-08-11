package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class viewbooks {
public static void viewbooks() {
	try {
		Connection con=DBConnection.getConnection();
		String sql="select*from books";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			System.out.println("----------------------");
			System.out.println("books id:"+rs.getInt("id"));
			System.out.println("title:"+rs.getString("title"));
			System.out.println("Author:"+rs.getString("category"));
			System.out.println("publisher:"+rs.getString("publisher"));
			System.out.println("quantity:"+rs.getInt("quantity"));
			System.out.println("available:"+rs.getString("available_quantity"));
		}
		con.close();
		}
	catch(Exception e) {
		System.out.println(e);
	}
}
}
