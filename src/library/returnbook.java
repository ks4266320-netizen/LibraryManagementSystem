package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class returnbook {

    public static void returnbook() {

        try {

            Connection con = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Roll No: ");
            int roll_no = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();

            // Check whether the book is issued
            String sql = "SELECT * FROM issued_books WHERE roll_no=? AND book_title=? AND return_date IS NULL";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, roll_no);
            ps.setString(2, title);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int bookId = rs.getInt("book_id");

                // Update return date
                String sql1 = "UPDATE issued_books SET return_date=CURDATE() WHERE roll_no=? AND book_title=? AND return_date IS NULL";

                PreparedStatement ps1 = con.prepareStatement(sql1);

                ps1.setInt(1, roll_no);
                ps1.setString(2, title);

                ps1.executeUpdate();

                // Increase available quantity
                String sql2 = "UPDATE books SET available_quantity=available_quantity+1 WHERE id=?";

                PreparedStatement ps2 = con.prepareStatement(sql2);

                ps2.setInt(1, bookId);

                ps2.executeUpdate();

                System.out.println("Book Returned Successfully!");

            } else {

                System.out.println("No issued book found for this student.");

            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}