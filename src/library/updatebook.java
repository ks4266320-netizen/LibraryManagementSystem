package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
public class updatebook {
public static void updatebook() {
	Scanner sc=new Scanner(System.in);
	System.out.println("enter book id:");
int id=sc.nextInt();
System.out.println("\n===update book======");
System.out.println("1.update title");
System.out.println("2.update author");
System.out.println("3.update category");
System.out.println("4.update publisher");
System.out.println("5.update quantity");
System.out.println("enter your choice:");
int choice=sc.nextInt();
sc.nextLine();
String sql;

try {
	Connection con=DBConnection.getConnection();
	PreparedStatement ps=null;
	switch(choice) {
	case 1:
		System.out.println("enter new title:");
		String newvalue=sc.nextLine();
		sql="update books set title=? where id=?";
		ps=con.prepareStatement(sql);
		ps.setString(1,newvalue);
		ps.setInt(2,id);
		break;
	case 2:
		System.out.println("enter new author:");
		String author=sc.nextLine();
		sql="update books set author=? where id=?";
		ps=con.prepareStatement(sql);
		ps.setString(1, author);
		ps.setInt(2, id);
		break;
	case 3:
		System.out.println("enter new category");
		String category=sc.nextLine();
		sql="update books set category=? where id=?";
		ps=con.prepareStatement(sql);
		ps.setString(1,category);
		ps.setInt(2,id);
		break;
	case 4:
		System.out.println("enter new publisher:");
		String publisher=sc.nextLine();
		sql="update books set publisher=? where id=?";
		ps=con.prepareStatement(sql);
		ps.setString(1, publisher);
		ps.setInt(2, id);
		break;
	case 5:
		System.out.println("enter new quantity:");
		int quantity=sc.nextInt();
		sql="update books set quantity=?,available_quantity=? where id=?";
		ps=con.prepareStatement(sql);
		ps.setInt(1, quantity);
		ps.setInt(2, id);
		break;
		default:
			System.out.println("invalid choice");
			break;
	}
	int rows=ps.executeUpdate();
	if(rows>0) {
		System.out.println("book update successfully");
	}
	else {
		System.out.println("book id not found");
	}
	con.close();
}
catch(Exception e) {
	System.out.println(e);
}
}
}