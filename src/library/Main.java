
package library;
import java.util.Scanner;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	while(true) {
    		System.out.println("1.add book");
    		System.out.println("2.view book");
    		System.out.println("3.search book");
    		System.out.println("4.update book");
    		System.out.println("5.delete book");
    		System.out.println("6.add students");
    		System.out.println("7.view students");
    		System.out.println("8.update student");
    		System.out.println("9.delete student");
    		System.out.println("10.issue book");
    		System.out.println("11.return book");
    		System.out.println("12.view issue books");
    		System.out.println("13.exit");
    	int choice=sc.nextInt();
switch(choice) {
case 1:book.addBook();
break;
case 2:viewbooks.viewbooks();
break;
case 3:searchbook.searchbook();
break;
case 4:
	updatebook.updatebook();
	break;
case 5:
	deletebook.deletebook();
	break;
case 6:
	addstudents.addstudents();
	break;
case 7:
	viewstudents.viewstudents();
	break;
case 8:
	updatestudent.updatestudent();
	break;
case 9:
	deletestudent.deletestudent();
	break;
case 10:
	issuebook.issuebook();
	break;
case 11:
	returnbook.returnbook();
	break;
case 12:
	viewissuedbooks.viewissuedbooks();
	break;
case 13:System.out.println("exit from the program");
	return;
default:System.out.println("invalid choice");
    	}
    	}
}
}
