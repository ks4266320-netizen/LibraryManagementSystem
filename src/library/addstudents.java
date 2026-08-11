package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
public class addstudents {
	public static void addstudents() {
Scanner sc=new Scanner(System.in);
System.out.println("enter name:");
String name=sc.nextLine();
System.out.println("enter roll_no:");
int roll_no=sc.nextInt();
sc.nextLine();
System.out.println("enter department:");
String department=sc.nextLine();
System.out.println("enter email:");
String email=sc.nextLine();
try {
	Connection con=DBConnection.getConnection();
    String sql="insert into students(name,roll_no,department,email)values(?,?,?,?)";
    PreparedStatement ps=con.prepareStatement(sql);
    ps.setString(1,name);
    ps.setInt(2,roll_no);
    ps.setString(3, department);
    ps.setString(4,email);
    int rows=ps.executeUpdate();
    if(rows>0){
    	System.out.println("added successfully");
    }
    else {
    	System.out.println("not added");
    }
}
    catch(Exception e) {
    	System.out.println(e);
    }
	}
}
