package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
public class deletebook {
	public static void deletebook() {
		searchbook.searchbook();
Scanner sc=new Scanner(System.in);
System.out.println("enter id:");
int id=sc.nextInt();
try {
	Connection con=DBConnection.getConnection();
String sql="delete from books where id =?";
PreparedStatement ps=con.prepareStatement(sql);
ps.setInt(1, id);
int rows=ps.executeUpdate();
if(rows>0) {
	System.out.println("book deleted successfully");
}
else {
	System.out.println("book not found");
}
con.close();
}
catch(Exception e) {
	System.out.println(e);
}
}
}
