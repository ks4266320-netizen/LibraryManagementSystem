
package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class book {

    public static void addBook() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Publisher: ");
        String publisher = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();

        try {

            Connection con = DBConnection.getConnection();

            // Check if same book already exists
            String checkSql =
                    "SELECT id FROM books " +
                    "WHERE title=? " +
                    "AND author=? " +
                    "AND category=? " +
                    "AND publisher=?";

            PreparedStatement checkPs =
                    con.prepareStatement(checkSql);

            checkPs.setString(1, title);
            checkPs.setString(2, author);
            checkPs.setString(3, category);
            checkPs.setString(4, publisher);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {

                // Same book found
                int bookId = rs.getInt("id");

                String updateSql =
                        "UPDATE books " +
                        "SET quantity = quantity + ?, " +
                        "available_quantity = available_quantity + ? " +
                        "WHERE id=?";

                PreparedStatement updatePs =
                        con.prepareStatement(updateSql);

                updatePs.setInt(1, quantity);
                updatePs.setInt(2, quantity);
                updatePs.setInt(3, bookId);

                int rows = updatePs.executeUpdate();

                if (rows > 0) {
                    System.out.println(
                            "Book already exists. Quantity increased!"
                    );
                }

            } else {

                // Book does not exist
                String insertSql =
                        "INSERT INTO books " +
                        "(title, author, category, publisher, quantity, available_quantity) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

                PreparedStatement insertPs =
                        con.prepareStatement(insertSql);

                insertPs.setString(1, title);
                insertPs.setString(2, author);
                insertPs.setString(3, category);
                insertPs.setString(4, publisher);
                insertPs.setInt(5, quantity);
                insertPs.setInt(6, quantity);

                int rows = insertPs.executeUpdate();

                if (rows > 0) {
                    System.out.println(
                            "New Book Added Successfully!"
                    );
                }
            }

            con.close();

        } catch (Exception e) {

            System.out.println(e);
        }
    }
}

