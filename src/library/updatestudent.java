package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;
public class updatestudent {
public static void updatestudent() {
	try {
	String sql;
	Connection con=DBConnection.getConnection();
	Scanner sc=new Scanner(System.in);
	System.out.print("enter roll no:");
	int roll_no=sc.nextInt();
	while(true) {
	System.out.println("1.update name");
	System.out.println("2.update dapartment");
	System.out.println("3.update email");
	System.out.println("4.exit");
	System.out.println("enter chioce:");
	int choice=sc.nextInt();
	sc.nextLine();
	switch(choice) {
		case 1:
			System.out.println("enter new name:");
			String newname=sc.nextLine();
			sql="update students set name=? where roll_no=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,newname);
		ps.setInt(2,roll_no);
		int rows=ps.executeUpdate();
		if(rows>0) {
		System.out.println("newname sucessfully added");
		}
		else {
			System.out.println("roll no not found");
		}
		break;
		case 2:
			System.out.println("enter new department");
			String newdepartment=sc.nextLine();
			sql="update students set department=? where roll_no=?";
			PreparedStatement ps1=con.prepareStatement(sql);
			ps1.setString(1, newdepartment);
			ps1.setInt(2, roll_no);
			int rows1=ps1.executeUpdate();
			if(rows1>0) {
			System.out.println("new department successfully changed");
			}
			else {
				System.out.println("rollno is not found");
			}
			break;
		case 3:
			System.out.println("enter new email");
			String newemail=sc.next();
			sql="update students set email=? where roll_no=?";
			PreparedStatement ps2=con.prepareStatement(sql);
			ps2.setString(1, newemail);
			ps2.setInt(2, roll_no);
			int rows3=ps2.executeUpdate();
			if(rows3>0) {
			System.out.println("new email successfull changed");
			}
			else {
				System.out.println("roll no is not found");
			}
			break;
		case 4:
			return;
			default:
				System.out.println("invalid choice");
	}
	}
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
}
