package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
public class deletestudent {
public static void deletestudent() {
	try {
	Connection con=DBConnection.getConnection();
	Scanner sc=new Scanner(System.in);
	System.out.println("enter roll no:");
	int roll_no=sc.nextInt();
	String sql="delete from students where roll_no=?";
	PreparedStatement ps=con.prepareStatement(sql);
	ps.setInt(1,roll_no);
	int rows=ps.executeUpdate();
	if(rows>0) {
	System.out.println("deleted successfull");
	}
	else {
		System.out.println("roll no not found");
	}
	con.close();
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
}
