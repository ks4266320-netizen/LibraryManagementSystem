package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class searchbook {
public static void searchbook() {
	Scanner sc=new Scanner(System.in);
	System.out.print("enter book title:");
	String title=sc.nextLine();
	try {
		Connection con=DBConnection.getConnection();
		String sql="select*from books where title like ?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,"%"+title+"%");
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
		System.out.println("Book ID : " + rs.getInt("id"));
        System.out.println("Title : " + rs.getString("title"));
        System.out.println("Author : " + rs.getString("author"));
        System.out.println("Category : " + rs.getString("category"));
        System.out.println("Publisher : " + rs.getString("publisher"));
        System.out.println("Quantity : " + rs.getInt("quantity"));
        System.out.println("Available Quantity : " + rs.getInt("available_quantity"));
	}
	else {
		System.out.println("book is not found");
	}
		con.close();
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
}
