package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;
public class issuebook {
public static void issuebook() {
	try {
	Connection con=DBConnection.getConnection();
	Scanner sc=new Scanner(System.in);
	System.out.println("enter roll no:");
	int roll_no=sc.nextInt();
	sc.nextLine();
	String sql="select*from students where roll_no=?";
	PreparedStatement ps=con.prepareStatement(sql);
	ps.setInt(1, roll_no);
	ResultSet rs=ps.executeQuery();
	if(rs.next()) {
		System.out.println("student found");
	}
	else {
		System.out.println("student is not found");
	}
	System.out.println("enter book title:");
	String title=sc.nextLine();
	String sql1="select*from books where title=?";
	PreparedStatement ps1=con.prepareStatement(sql1);
	ps1.setString(1, title);
	ResultSet rs1=ps1.executeQuery();
	if(rs1.next()) {
		int bookid=rs1.getInt("id");
		int available_quantity=rs1.getInt("available_quantity");
		System.out.println("book is found");
		System.out.println("book id:"+bookid);
		System.out.println("quantity:"+available_quantity);
	if(available_quantity>0) {
		String sql2="insert into issued_books(roll_no,book_id,book_title,issue_date) values(?,?,?,curdate())";
		PreparedStatement ps2=con.prepareStatement(sql2);
		ps2.setInt(1, roll_no);
		ps2.setInt(2, bookid);
		ps2.setString(3, title);
		ps2.executeUpdate();
		String sql3="update books set available_quantity=available_quantity-1 where id=?";
		PreparedStatement ps3=con.prepareStatement(sql3);
		ps3.setInt(1,bookid);
		ps3.executeUpdate();
		System.out.println("book issued successfully");
	}
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
