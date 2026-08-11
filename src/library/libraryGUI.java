package library;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class libraryGUI {
public static void main(String args[]) {
	JFrame frame=new JFrame("librarymanagement system");
	frame.setSize( 600,400);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JButton addbookbutton=new JButton("add book");
	addbookbutton.setBounds(50,50,150,40);
	addbookbutton.addActionListener(e -> {
		 JFrame addFrame = new JFrame("Add Book");
		    addFrame.setSize(400, 300);
		    addFrame.setLayout(null);
		JLabel titleLabel=new JLabel("book title");
		titleLabel.setBounds(40,40,100,30);
		addFrame.add(titleLabel);
		 JTextField titleField = new JTextField();
		    titleField.setBounds(140, 40, 180, 30);
		    addFrame.add(titleField);
		    addFrame.setVisible(true);
		    JLabel authorLabel = new JLabel("Author:");
		    authorLabel.setBounds(40, 80, 100, 30);
		    addFrame.add(authorLabel);

		    JTextField authorField = new JTextField();
		    authorField.setBounds(140, 80, 180, 30);
		    addFrame.add(authorField);
		    JLabel categoryLabel = new JLabel("Category:");
		    categoryLabel.setBounds(40, 120, 100, 30);
		    addFrame.add(categoryLabel);

		    JTextField categoryField = new JTextField();
		    categoryField.setBounds(140, 120, 180, 30);
		    addFrame.add(categoryField);
		    JLabel publisherLabel = new JLabel("Publisher:");
		    publisherLabel.setBounds(40, 160, 100, 30);
		    addFrame.add(publisherLabel);

		    JTextField publisherField = new JTextField();
		    publisherField.setBounds(140, 160, 180, 30);
		    addFrame.add(publisherField);
		    JLabel quantityLabel = new JLabel("Quantity:");
		    quantityLabel.setBounds(40, 200, 100, 30);
		    addFrame.add(quantityLabel);

		    JTextField quantityField = new JTextField();
		    quantityField.setBounds(140, 200, 180, 30);
		    addFrame.add(quantityField);
		    JButton saveButton = new JButton("Add Book");
		    saveButton.setBounds(140, 240, 180, 40);
		    addFrame.add(saveButton);
		    saveButton.addActionListener(event ->{
		    			    	String title = titleField.getText();
		    String author = authorField.getText();
		    String category = categoryField.getText();
		    String publisher = publisherField.getText();
		    String quantityText = quantityField.getText();

		    if (quantityText.isEmpty()) {
		        JOptionPane.showMessageDialog(addFrame, "Please enter quantity");
		        return;
		    }
		    try {

                // Convert quantity from String to int
                int quantity = Integer.parseInt(quantityText);

                // Connect to database
                Connection con = DBConnection.getConnection();

                // SQL query
                String sql = "INSERT INTO books "
                        + "(title, author, category, publisher, quantity, available_quantity) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";

                // Prepare query
                PreparedStatement ps = con.prepareStatement(sql);

                // Set values
                ps.setString(1, title);
                ps.setString(2, author);
                ps.setString(3, category);
                ps.setString(4, publisher);
                ps.setInt(5, quantity);
                ps.setInt(6, quantity);

                // Execute INSERT
                int rows = ps.executeUpdate();

                // Check result
                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            addFrame,
                            "Book Added Successfully!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            addFrame,
                            "Book Not Added"
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        addFrame,
                        "Quantity must be a number"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        addFrame,
                        ex.getMessage()
                );
            }

        });

        // Show Add Book window
        addFrame.setVisible(true);

    });
	JButton viewbookbutton = new JButton("View Books");
    viewbookbutton.setBounds(50, 110, 150, 40);
    frame.add(viewbookbutton);

    viewbookbutton.addActionListener(e -> {

        JFrame viewFrame = new JFrame("View Books");

        viewFrame.setSize(900, 400);

        String[] columns = {
                "ID",
                "Title",
                "Author",
                "Category",
                "Publisher",
                "Quantity",
                "Available"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM books";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String category = rs.getString("category");
                String publisher = rs.getString("publisher");
                int quantity = rs.getInt("quantity");
                int available = rs.getInt("available_quantity");

                model.addRow(new Object[]{
                        id,
                        title,
                        author,
                        category,
                        publisher,
                        quantity,
                        available
                });
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    viewFrame,
                    ex.getMessage()
            );
        }

        JScrollPane scrollPane =
                new JScrollPane(table);

        viewFrame.add(scrollPane);

        viewFrame.setVisible(true);
    });
 // =========================
 // SEARCH BOOK BUTTON
 // =========================

 JButton searchbookbutton = new JButton("Search Book");
 searchbookbutton.setBounds(50, 170, 150, 40);

 searchbookbutton.addActionListener(e -> {

     JFrame searchFrame = new JFrame("Search Book");
     searchFrame.setSize(500, 350);
     searchFrame.setLayout(null);

     JLabel titleLabel = new JLabel("Enter Book Title:");
     titleLabel.setBounds(40, 40, 130, 30);
     searchFrame.add(titleLabel);

     JTextField titleField = new JTextField();
     titleField.setBounds(170, 40, 200, 30);
     searchFrame.add(titleField);

     JButton searchButton = new JButton("Search");
     searchButton.setBounds(170, 80, 100, 35);
     searchFrame.add(searchButton);

     searchButton.addActionListener(event -> {

         String title = titleField.getText();

         if (title.isEmpty()) {
             JOptionPane.showMessageDialog(
                     searchFrame,
                     "Please enter book title"
             );
             return;
         }

         String[] columns = {
                 "ID",
                 "Title",
                 "Author",
                 "Category",
                 "Publisher",
                 "Quantity",
                 "Available"
         };

         DefaultTableModel model =
                 new DefaultTableModel(columns, 0);

         JTable table = new JTable(model);

         try {

             Connection con = DBConnection.getConnection();

             String sql = "SELECT * FROM books WHERE title LIKE ?";

             PreparedStatement ps =
                     con.prepareStatement(sql);

             ps.setString(1, "%" + title + "%");

             ResultSet rs = ps.executeQuery();

             boolean found = false;

             while (rs.next()) {

                 found = true;

                 int id = rs.getInt("id");
                 String bookTitle = rs.getString("title");
                 String author = rs.getString("author");
                 String category = rs.getString("category");
                 String publisher = rs.getString("publisher");
                 int quantity = rs.getInt("quantity");
                 int available = rs.getInt("available_quantity");

                 model.addRow(new Object[]{
                         id,
                         bookTitle,
                         author,
                         category,
                         publisher,
                         quantity,
                         available
                 });
             }

             if (!found) {

                 JOptionPane.showMessageDialog(
                         searchFrame,
                         "Book not found"
                 );

                 return;
             }

         } catch (Exception ex) {

             JOptionPane.showMessageDialog(
                     searchFrame,
                     ex.getMessage()
             );

             return;
         }

         JScrollPane scrollPane =
                 new JScrollPane(table);

         scrollPane.setBounds(20, 130, 450, 150);

         searchFrame.add(scrollPane);

         searchFrame.revalidate();
         searchFrame.repaint();
     });

     searchFrame.setVisible(true);
 });

 frame.add(searchbookbutton);
//=========================
//UPDATE BOOK BUTTON
//=========================

JButton updatebookbutton = new JButton("Update Book");
updatebookbutton.setBounds(50, 230, 150, 40);

updatebookbutton.addActionListener(e -> {

  JFrame updateFrame = new JFrame("Update Book");
  updateFrame.setSize(450, 400);
  updateFrame.setLayout(null);

  // Book ID
  JLabel idLabel = new JLabel("Book ID:");
  idLabel.setBounds(40, 30, 100, 30);
  updateFrame.add(idLabel);

  JTextField idField = new JTextField();
  idField.setBounds(140, 30, 180, 30);
  updateFrame.add(idField);

  // Title
  JLabel titleLabel = new JLabel("Title:");
  titleLabel.setBounds(40, 80, 100, 30);
  updateFrame.add(titleLabel);

  JTextField titleField = new JTextField();
  titleField.setBounds(140, 80, 180, 30);
  updateFrame.add(titleField);

  // Author
  JLabel authorLabel = new JLabel("Author:");
  authorLabel.setBounds(40, 120, 100, 30);
  updateFrame.add(authorLabel);

  JTextField authorField = new JTextField();
  authorField.setBounds(140, 120, 180, 30);
  updateFrame.add(authorField);

  // Category
  JLabel categoryLabel = new JLabel("Category:");
  categoryLabel.setBounds(40, 160, 100, 30);
  updateFrame.add(categoryLabel);

  JTextField categoryField = new JTextField();
  categoryField.setBounds(140, 160, 180, 30);
  updateFrame.add(categoryField);

  // Publisher
  JLabel publisherLabel = new JLabel("Publisher:");
  publisherLabel.setBounds(40, 200, 100, 30);
  updateFrame.add(publisherLabel);

  JTextField publisherField = new JTextField();
  publisherField.setBounds(140, 200, 180, 30);
  updateFrame.add(publisherField);

  // Quantity
  JLabel quantityLabel = new JLabel("Quantity:");
  quantityLabel.setBounds(40, 240, 100, 30);
  updateFrame.add(quantityLabel);

  JTextField quantityField = new JTextField();
  quantityField.setBounds(140, 240, 180, 30);
  updateFrame.add(quantityField);

  // Update Button
  JButton updateButton = new JButton("Update");
  updateButton.setBounds(140, 290, 180, 40);
  updateFrame.add(updateButton);

  // Update button action
  updateButton.addActionListener(event -> {

      String idText = idField.getText();
      String title = titleField.getText();
      String author = authorField.getText();
      String category = categoryField.getText();
      String publisher = publisherField.getText();
      String quantityText = quantityField.getText();

      // Check empty fields
      if (idText.isEmpty()
              || title.isEmpty()
              || author.isEmpty()
              || category.isEmpty()
              || publisher.isEmpty()
              || quantityText.isEmpty()) {

          JOptionPane.showMessageDialog(
                  updateFrame,
                  "Please fill all fields"
          );

          return;
      }

      try {

          int id = Integer.parseInt(idText);
          int quantity = Integer.parseInt(quantityText);

          Connection con = DBConnection.getConnection();

          // First check whether book exists
          String checkSql =
                  "SELECT * FROM books WHERE id=?";

          PreparedStatement checkPs =
                  con.prepareStatement(checkSql);

          checkPs.setInt(1, id);

          ResultSet rs = checkPs.executeQuery();

          if (!rs.next()) {

              JOptionPane.showMessageDialog(
                      updateFrame,
                      "Book ID not found"
              );

              return;
          }

          // Get old quantity and available quantity
          int oldQuantity = rs.getInt("quantity");
          int oldAvailable = rs.getInt("available_quantity");

          // Calculate issued books
          int issuedBooks = oldQuantity - oldAvailable;

          // New available quantity
          int newAvailable = quantity - issuedBooks;

          if (newAvailable < 0) {

              JOptionPane.showMessageDialog(
                      updateFrame,
                      "Quantity cannot be less than issued books"
              );

              return;
          }

          // Update book
          String sql =
                  "UPDATE books SET title=?, author=?, category=?, "
                  + "publisher=?, quantity=?, available_quantity=? "
                  + "WHERE id=?";

          PreparedStatement ps =
                  con.prepareStatement(sql);

          ps.setString(1, title);
          ps.setString(2, author);
          ps.setString(3, category);
          ps.setString(4, publisher);
          ps.setInt(5, quantity);
          ps.setInt(6, newAvailable);
          ps.setInt(7, id);

          int rows = ps.executeUpdate();

          if (rows > 0) {

              JOptionPane.showMessageDialog(
                      updateFrame,
                      "Book Updated Successfully!"
              );

          } else {

              JOptionPane.showMessageDialog(
                      updateFrame,
                      "Book Not Updated"
              );
          }

      } catch (NumberFormatException ex) {

          JOptionPane.showMessageDialog(
                  updateFrame,
                  "ID and Quantity must be numbers"
          );

      } catch (Exception ex) {

          JOptionPane.showMessageDialog(
                  updateFrame,
                  ex.getMessage()
          );
      }
  });

  updateFrame.setVisible(true);
});

frame.add(updatebookbutton);
//=========================
//DELETE BOOK BUTTON
//=========================

JButton deletebookbutton = new JButton("Delete Book");
deletebookbutton.setBounds(50, 290, 150, 40);

deletebookbutton.addActionListener(e -> {

 JFrame deleteFrame = new JFrame("Delete Book");
 deleteFrame.setSize(400, 220);
 deleteFrame.setLayout(null);

 // Book ID label
 JLabel idLabel = new JLabel("Book ID:");
 idLabel.setBounds(40, 40, 100, 30);
 deleteFrame.add(idLabel);

 // Book ID field
 JTextField idField = new JTextField();
 idField.setBounds(140, 40, 180, 30);
 deleteFrame.add(idField);

 // Delete button
 JButton deleteButton = new JButton("Delete");
 deleteButton.setBounds(140, 90, 180, 40);
 deleteFrame.add(deleteButton);

 // Delete button action
 deleteButton.addActionListener(event -> {

     String idText = idField.getText();

     // Check empty ID
     if (idText.isEmpty()) {

         JOptionPane.showMessageDialog(
                 deleteFrame,
                 "Please enter Book ID"
         );

         return;
     }

     try {

         int id = Integer.parseInt(idText);

         Connection con = DBConnection.getConnection();

         // First check whether book exists
         String checkSql =
                 "SELECT * FROM books WHERE id=?";

         PreparedStatement checkPs =
                 con.prepareStatement(checkSql);

         checkPs.setInt(1, id);

         ResultSet rs = checkPs.executeQuery();

         if (!rs.next()) {

             JOptionPane.showMessageDialog(
                     deleteFrame,
                     "Book ID not found"
             );

             return;
         }

         // Confirm deletion
         int choice = JOptionPane.showConfirmDialog(
                 deleteFrame,
                 "Are you sure you want to delete this book?",
                 "Confirm Delete",
                 JOptionPane.YES_NO_OPTION
         );

         if (choice != JOptionPane.YES_OPTION) {
             return;
         }

         // Delete book
         String sql =
                 "DELETE FROM books WHERE id=?";

         PreparedStatement ps =
                 con.prepareStatement(sql);

         ps.setInt(1, id);

         int rows = ps.executeUpdate();

         if (rows > 0) {

             JOptionPane.showMessageDialog(
                     deleteFrame,
                     "Book Deleted Successfully!"
             );

             idField.setText("");

         } else {

             JOptionPane.showMessageDialog(
                     deleteFrame,
                     "Book Not Deleted"
             );
         }

     } catch (NumberFormatException ex) {

         JOptionPane.showMessageDialog(
                 deleteFrame,
                 "Book ID must be a number"
         );

     } catch (Exception ex) {

         JOptionPane.showMessageDialog(
                 deleteFrame,
                 ex.getMessage()
         );
     }
 });

 deleteFrame.setVisible(true);
});

frame.add(deletebookbutton);
//=========================
//ADD STUDENT BUTTON
//=========================

JButton addstudentbutton = new JButton("Add Student");
addstudentbutton.setBounds(250, 50, 150, 40);

addstudentbutton.addActionListener(e -> {

 JFrame studentFrame = new JFrame("Add Student");
 studentFrame.setSize(450, 350);
 studentFrame.setLayout(null);

 // Name
 JLabel nameLabel = new JLabel("Name:");
 nameLabel.setBounds(40, 40, 100, 30);
 studentFrame.add(nameLabel);

 JTextField nameField = new JTextField();
 nameField.setBounds(150, 40, 200, 30);
 studentFrame.add(nameField);

 // Roll No
 JLabel rollLabel = new JLabel("Roll No:");
 rollLabel.setBounds(40, 90, 100, 30);
 studentFrame.add(rollLabel);

 JTextField rollField = new JTextField();
 rollField.setBounds(150, 90, 200, 30);
 studentFrame.add(rollField);

 // Department
 JLabel departmentLabel = new JLabel("Department:");
 departmentLabel.setBounds(40, 140, 100, 30);
 studentFrame.add(departmentLabel);

 JTextField departmentField = new JTextField();
 departmentField.setBounds(150, 140, 200, 30);
 studentFrame.add(departmentField);

 // Email
 JLabel emailLabel = new JLabel("Email:");
 emailLabel.setBounds(40, 190, 100, 30);
 studentFrame.add(emailLabel);

 JTextField emailField = new JTextField();
 emailField.setBounds(150, 190, 200, 30);
 studentFrame.add(emailField);

 // Add Student button
 JButton saveButton = new JButton("Add Student");
 saveButton.setBounds(150, 240, 200, 40);
 studentFrame.add(saveButton);

 // Save button action
 saveButton.addActionListener(event -> {

     String name = nameField.getText();
     String rollText = rollField.getText();
     String department = departmentField.getText();
     String email = emailField.getText();

     // Check empty fields
     if (name.isEmpty()
             || rollText.isEmpty()
             || department.isEmpty()
             || email.isEmpty()) {

         JOptionPane.showMessageDialog(
                 studentFrame,
                 "Please fill all fields"
         );

         return;
     }

     try {

         int rollNo = Integer.parseInt(rollText);

         Connection con = DBConnection.getConnection();

         // Insert student
         String sql =
                 "INSERT INTO students "
                 + "(name, roll_no, department, email) "
                 + "VALUES (?, ?, ?, ?)";

         PreparedStatement ps =
                 con.prepareStatement(sql);

         ps.setString(1, name);
         ps.setInt(2, rollNo);
         ps.setString(3, department);
         ps.setString(4, email);

         int rows = ps.executeUpdate();

         if (rows > 0) {

             JOptionPane.showMessageDialog(
                     studentFrame,
                     "Student Added Successfully!"
             );

             // Clear fields
             nameField.setText("");
             rollField.setText("");
             departmentField.setText("");
             emailField.setText("");

         } else {

             JOptionPane.showMessageDialog(
                     studentFrame,
                     "Student Not Added"
             );
         }

     } catch (NumberFormatException ex) {

         JOptionPane.showMessageDialog(
                 studentFrame,
                 "Roll No must be a number"
         );

     } catch (Exception ex) {

         JOptionPane.showMessageDialog(
                 studentFrame,
                 ex.getMessage()
         );
     }
 });

 studentFrame.setVisible(true);
});

frame.add(addstudentbutton);
//=========================
//VIEW STUDENTS BUTTON
//=========================

JButton viewstudentbutton = new JButton("View Students");
viewstudentbutton.setBounds(250, 110, 150, 40);

viewstudentbutton.addActionListener(e -> {

 JFrame viewFrame = new JFrame("View Students");
 viewFrame.setSize(750, 400);

 String[] columns = {
         "No",
         "Name",
         "Roll No",
         "Department",
         "Email"
 };

 DefaultTableModel model =
         new DefaultTableModel(columns, 0);

 JTable table = new JTable(model);

 try {

     Connection con = DBConnection.getConnection();

     String sql = "SELECT * FROM students";

     PreparedStatement ps =
             con.prepareStatement(sql);

     ResultSet rs = ps.executeQuery();

     while (rs.next()) {

         int no = rs.getInt("no");
         String name = rs.getString("name");
         int rollNo = rs.getInt("roll_no");
         String department = rs.getString("department");
         String email = rs.getString("email");

         model.addRow(new Object[]{
                 no,
                 name,
                 rollNo,
                 department,
                 email
         });
     }

 } catch (Exception ex) {

     JOptionPane.showMessageDialog(
             viewFrame,
             ex.getMessage()
     );
 }

 JScrollPane scrollPane =
         new JScrollPane(table);

 viewFrame.add(scrollPane);

 viewFrame.setVisible(true);
});

frame.add(viewstudentbutton);
//=========================
//UPDATE STUDENT BUTTON
//=========================

JButton updatestudentbutton = new JButton("Update Student");
updatestudentbutton.setBounds(250, 170, 150, 40);

updatestudentbutton.addActionListener(e -> {

 JFrame updateFrame = new JFrame("Update Student");
 updateFrame.setSize(450, 350);
 updateFrame.setLayout(null);

 // Roll No
 JLabel rollLabel = new JLabel("Roll No:");
 rollLabel.setBounds(40, 30, 100, 30);
 updateFrame.add(rollLabel);

 JTextField rollField = new JTextField();
 rollField.setBounds(150, 30, 200, 30);
 updateFrame.add(rollField);

 // Name
 JLabel nameLabel = new JLabel("New Name:");
 nameLabel.setBounds(40, 80, 100, 30);
 updateFrame.add(nameLabel);

 JTextField nameField = new JTextField();
 nameField.setBounds(150, 80, 200, 30);
 updateFrame.add(nameField);

 // Department
 JLabel departmentLabel = new JLabel("New Department:");
 departmentLabel.setBounds(40, 130, 110, 30);
 updateFrame.add(departmentLabel);

 JTextField departmentField = new JTextField();
 departmentField.setBounds(150, 130, 200, 30);
 updateFrame.add(departmentField);

 // Email
 JLabel emailLabel = new JLabel("New Email:");
 emailLabel.setBounds(40, 180, 100, 30);
 updateFrame.add(emailLabel);

 JTextField emailField = new JTextField();
 emailField.setBounds(150, 180, 200, 30);
 updateFrame.add(emailField);

 // Update button
 JButton updateButton = new JButton("Update");
 updateButton.setBounds(150, 230, 200, 40);
 updateFrame.add(updateButton);

 // Update button action
 updateButton.addActionListener(event -> {

     String rollText = rollField.getText();
     String name = nameField.getText();
     String department = departmentField.getText();
     String email = emailField.getText();

     // Check empty fields
     if (rollText.isEmpty()
             || name.isEmpty()
             || department.isEmpty()
             || email.isEmpty()) {

         JOptionPane.showMessageDialog(
                 updateFrame,
                 "Please fill all fields"
         );

         return;
     }

     try {

         int rollNo = Integer.parseInt(rollText);

         Connection con = DBConnection.getConnection();

         // First check whether student exists
         String checkSql =
                 "SELECT * FROM students WHERE roll_no=?";

         PreparedStatement checkPs =
                 con.prepareStatement(checkSql);

         checkPs.setInt(1, rollNo);

         ResultSet rs = checkPs.executeQuery();

         if (!rs.next()) {

             JOptionPane.showMessageDialog(
                     updateFrame,
                     "Student with this roll no is not found"
             );

             return;
         }

         // Update student
         String sql =
                 "UPDATE students SET name=?, department=?, email=? "
                 + "WHERE roll_no=?";

         PreparedStatement ps =
                 con.prepareStatement(sql);

         ps.setString(1, name);
         ps.setString(2, department);
         ps.setString(3, email);
         ps.setInt(4, rollNo);

         int rows = ps.executeUpdate();

         if (rows > 0) {

             JOptionPane.showMessageDialog(
                     updateFrame,
                     "Student Updated Successfully!"
             );

             rollField.setText("");
             nameField.setText("");
             departmentField.setText("");
             emailField.setText("");

         } else {

             JOptionPane.showMessageDialog(
                     updateFrame,
                     "Student Not Updated"
             );
         }

     } catch (NumberFormatException ex) {

         JOptionPane.showMessageDialog(
                 updateFrame,
                 "Roll No must be a number"
         );

     } catch (Exception ex) {

         JOptionPane.showMessageDialog(
                 updateFrame,
                 ex.getMessage()
         );
     }
 });

 updateFrame.setVisible(true);
});

frame.add(updatestudentbutton);
//=========================
//DELETE STUDENT BUTTON
//=========================

JButton deletestudentbutton = new JButton("Delete Student");
deletestudentbutton.setBounds(250, 230, 150, 40);

deletestudentbutton.addActionListener(e -> {

 JFrame deleteFrame = new JFrame("Delete Student");
 deleteFrame.setSize(400, 220);
 deleteFrame.setLayout(null);

 // Roll No label
 JLabel rollLabel = new JLabel("Roll No:");
 rollLabel.setBounds(40, 40, 100, 30);
 deleteFrame.add(rollLabel);

 // Roll No field
 JTextField rollField = new JTextField();
 rollField.setBounds(140, 40, 180, 30);
 deleteFrame.add(rollField);

 // Delete button
 JButton deleteButton = new JButton("Delete");
 deleteButton.setBounds(140, 90, 180, 40);
 deleteFrame.add(deleteButton);

 // Delete button action
 deleteButton.addActionListener(event -> {

     String rollText = rollField.getText();

     // Check empty field
     if (rollText.isEmpty()) {

         JOptionPane.showMessageDialog(
                 deleteFrame,
                 "Please enter Roll No"
         );

         return;
     }

     try {

         int rollNo = Integer.parseInt(rollText);

         Connection con = DBConnection.getConnection();

         // Check whether student exists
         String checkSql =
                 "SELECT * FROM students WHERE roll_no=?";

         PreparedStatement checkPs =
                 con.prepareStatement(checkSql);

         checkPs.setInt(1, rollNo);

         ResultSet rs = checkPs.executeQuery();

         if (!rs.next()) {

             JOptionPane.showMessageDialog(
                     deleteFrame,
                     "Student with this roll no is not found"
             );

             return;
         }

         // Confirm deletion
         int choice = JOptionPane.showConfirmDialog(
                 deleteFrame,
                 "Are you sure you want to delete this student?",
                 "Confirm Delete",
                 JOptionPane.YES_NO_OPTION
         );

         if (choice != JOptionPane.YES_OPTION) {
             return;
         }

         // Delete student
         String sql =
                 "DELETE FROM students WHERE roll_no=?";

         PreparedStatement ps =
                 con.prepareStatement(sql);

         ps.setInt(1, rollNo);

         int rows = ps.executeUpdate();

         if (rows > 0) {

             JOptionPane.showMessageDialog(
                     deleteFrame,
                     "Student Deleted Successfully!"
             );

             rollField.setText("");

         } else {

             JOptionPane.showMessageDialog(
                     deleteFrame,
                     "Student Not Deleted"
             );
         }

     } catch (NumberFormatException ex) {

         JOptionPane.showMessageDialog(
                 deleteFrame,
                 "Roll No must be a number"
         );

     } catch (Exception ex) {

         JOptionPane.showMessageDialog(
                 deleteFrame,
                 ex.getMessage()
         );
     }
 });

 deleteFrame.setVisible(true);
});

frame.add(deletestudentbutton);
//=========================
//ISSUE BOOK BUTTON
//=========================

JButton issuebookbutton = new JButton("Issue Book");
issuebookbutton.setBounds(450, 50, 150, 40);

issuebookbutton.addActionListener(e -> {

 JFrame issueFrame = new JFrame("Issue Book");
 issueFrame.setSize(450, 300);
 issueFrame.setLayout(null);

 // Roll No
 JLabel rollLabel = new JLabel("Student Roll No:");
 rollLabel.setBounds(40, 40, 120, 30);
 issueFrame.add(rollLabel);

 JTextField rollField = new JTextField();
 rollField.setBounds(170, 40, 180, 30);
 issueFrame.add(rollField);

 // Book Title
 JLabel titleLabel = new JLabel("Book Title:");
 titleLabel.setBounds(40, 90, 120, 30);
 issueFrame.add(titleLabel);

 JTextField titleField = new JTextField();
 titleField.setBounds(170, 90, 180, 30);
 issueFrame.add(titleField);

 // Issue button
 JButton issueButton = new JButton("Issue Book");
 issueButton.setBounds(170, 150, 180, 40);
 issueFrame.add(issueButton);

 // Issue button action
 issueButton.addActionListener(event -> {

     String rollText = rollField.getText();
     String title = titleField.getText();

     // Check empty fields
     if (rollText.isEmpty() || title.isEmpty()) {

         JOptionPane.showMessageDialog(
                 issueFrame,
                 "Please fill all fields"
         );

         return;
     }

     try {

         int rollNo = Integer.parseInt(rollText);

         Connection con = DBConnection.getConnection();

         // =========================
         // CHECK STUDENT
         // =========================

         String studentSql =
                 "SELECT * FROM students WHERE roll_no=?";

         PreparedStatement studentPs =
                 con.prepareStatement(studentSql);

         studentPs.setInt(1, rollNo);

         ResultSet studentRs =
                 studentPs.executeQuery();

         if (!studentRs.next()) {

             JOptionPane.showMessageDialog(
                     issueFrame,
                     "Student with this roll no is not found"
             );

             return;
         }


         // =========================
         // CHECK BOOK
         // =========================

         String bookSql =
                 "SELECT * FROM books WHERE title=?";

         PreparedStatement bookPs =
                 con.prepareStatement(bookSql);

         bookPs.setString(1, title);

         ResultSet bookRs =
                 bookPs.executeQuery();

         if (!bookRs.next()) {

             JOptionPane.showMessageDialog(
                     issueFrame,
                     "Book not found"
             );

             return;
         }


         // =========================
         // GET BOOK DETAILS
         // =========================

         int bookId = bookRs.getInt("id");

         String bookTitle =
                 bookRs.getString("title");

         int availableQuantity =
                 bookRs.getInt("available_quantity");


         // =========================
         // CHECK AVAILABLE QUANTITY
         // =========================

         if (availableQuantity <= 0) {

             JOptionPane.showMessageDialog(
                     issueFrame,
                     "Book is not available"
             );

             return;
         }


         // =========================
         // INSERT INTO ISSUED_BOOKS
         // =========================

         String issueSql =
                 "INSERT INTO issued_books "
                 + "(roll_no, book_id, book_title, issue_date, return_date) "
                 + "VALUES (?, ?, ?, CURDATE(), NULL)";

         PreparedStatement issuePs =
                 con.prepareStatement(issueSql);

         issuePs.setInt(1, rollNo);
         issuePs.setInt(2, bookId);
         issuePs.setString(3, bookTitle);

         int rows =
                 issuePs.executeUpdate();


         // =========================
         // REDUCE AVAILABLE QUANTITY
         // =========================

         if (rows > 0) {

             String updateSql =
                     "UPDATE books "
                     + "SET available_quantity = available_quantity - 1 "
                     + "WHERE id=?";

             PreparedStatement updatePs =
                     con.prepareStatement(updateSql);

             updatePs.setInt(1, bookId);

             updatePs.executeUpdate();


             JOptionPane.showMessageDialog(
                     issueFrame,
                     "Book Issued Successfully!"
             );

             rollField.setText("");
             titleField.setText("");

         }

     } catch (NumberFormatException ex) {

         JOptionPane.showMessageDialog(
                 issueFrame,
                 "Roll No must be a number"
         );

     } catch (Exception ex) {

         JOptionPane.showMessageDialog(
                 issueFrame,
                 ex.getMessage()
         );
     }
 });

 issueFrame.setVisible(true);
});

frame.add(issuebookbutton);
//=========================
//RETURN BOOK BUTTON
//=========================

JButton returnbookbutton = new JButton("Return Book");
returnbookbutton.setBounds(450, 110, 150, 40);

returnbookbutton.addActionListener(e -> {

 JFrame returnFrame = new JFrame("Return Book");
 returnFrame.setSize(450, 300);
 returnFrame.setLayout(null);

 // Roll No
 JLabel rollLabel = new JLabel("Student Roll No:");
 rollLabel.setBounds(40, 40, 120, 30);
 returnFrame.add(rollLabel);

 JTextField rollField = new JTextField();
 rollField.setBounds(170, 40, 180, 30);
 returnFrame.add(rollField);

 // Book ID
 JLabel bookIdLabel = new JLabel("Book ID:");
 bookIdLabel.setBounds(40, 90, 120, 30);
 returnFrame.add(bookIdLabel);

 JTextField bookIdField = new JTextField();
 bookIdField.setBounds(170, 90, 180, 30);
 returnFrame.add(bookIdField);

 // Return button
 JButton returnButton = new JButton("Return Book");
 returnButton.setBounds(170, 150, 180, 40);
 returnFrame.add(returnButton);

 returnButton.addActionListener(event -> {

     String rollText = rollField.getText();
     String bookIdText = bookIdField.getText();

     // Check empty fields
     if (rollText.isEmpty() || bookIdText.isEmpty()) {

         JOptionPane.showMessageDialog(
                 returnFrame,
                 "Please enter Roll No and Book ID"
         );

         return;
     }

     try {

         int rollNo = Integer.parseInt(rollText);
         int bookId = Integer.parseInt(bookIdText);

         Connection con = DBConnection.getConnection();

         // =========================
         // CHECK ISSUED BOOK
         // =========================

         String checkSql =
                 "SELECT * FROM issued_books "
                 + "WHERE roll_no=? "
                 + "AND book_id=? "
                 + "AND return_date IS NULL";

         PreparedStatement checkPs =
                 con.prepareStatement(checkSql);

         checkPs.setInt(1, rollNo);
         checkPs.setInt(2, bookId);

         ResultSet rs = checkPs.executeQuery();

         if (!rs.next()) {

             JOptionPane.showMessageDialog(
                     returnFrame,
                     "No active issue found for this student and book"
             );

             return;
         }

         // =========================
         // RETURN BOOK
         // =========================

         String returnSql =
                 "UPDATE issued_books "
                 + "SET return_date=CURDATE() "
                 + "WHERE roll_no=? "
                 + "AND book_id=? "
                 + "AND return_date IS NULL";

         PreparedStatement returnPs =
                 con.prepareStatement(returnSql);

         returnPs.setInt(1, rollNo);
         returnPs.setInt(2, bookId);

         int rows =
                 returnPs.executeUpdate();

         if (rows > 0) {

             // =========================
             // INCREASE AVAILABLE QUANTITY
             // =========================

             String updateSql =
                     "UPDATE books "
                     + "SET available_quantity = available_quantity + 1 "
                     + "WHERE id=?";

             PreparedStatement updatePs =
                     con.prepareStatement(updateSql);

             updatePs.setInt(1, bookId);

             updatePs.executeUpdate();

             JOptionPane.showMessageDialog(
                     returnFrame,
                     "Book Returned Successfully!"
             );

             rollField.setText("");
             bookIdField.setText("");

         } else {

             JOptionPane.showMessageDialog(
                     returnFrame,
                     "Book could not be returned"
             );
         }

     } catch (NumberFormatException ex) {

         JOptionPane.showMessageDialog(
                 returnFrame,
                 "Roll No and Book ID must be numbers"
         );

     } catch (Exception ex) {

         JOptionPane.showMessageDialog(
                 returnFrame,
                 ex.getMessage()
         );
     }
 });

 returnFrame.setVisible(true);
});

frame.add(returnbookbutton);
//=========================
//VIEW ISSUED BOOKS BUTTON
//=========================

JButton viewissuedbutton = new JButton("View Issued Books");
viewissuedbutton.setBounds(450, 170, 150, 40);

viewissuedbutton.addActionListener(e -> {

 JFrame viewFrame = new JFrame("View Issued Books");
 viewFrame.setSize(900, 400);
 viewFrame.setLayout(null);

 String[] columns = {
         "Issue ID",
         "Roll No",
         "Book ID",
         "Book Title",
         "Issue Date",
         "Return Date"
 };

 DefaultTableModel model =
         new DefaultTableModel(columns, 0);

 JTable table = new JTable(model);

 try {

     Connection con = DBConnection.getConnection();

     String sql =
             "SELECT * FROM issued_books";

     PreparedStatement ps =
             con.prepareStatement(sql);

     ResultSet rs =
             ps.executeQuery();

     while (rs.next()) {

         int issueId =
                 rs.getInt("issue_id");

         int rollNo =
                 rs.getInt("roll_no");

         int bookId =
                 rs.getInt("book_id");

         String bookTitle =
                 rs.getString("book_title");

         String issueDate =
                 rs.getString("issue_date");

         String returnDate =
                 rs.getString("return_date");

         model.addRow(new Object[]{
                 issueId,
                 rollNo,
                 bookId,
                 bookTitle,
                 issueDate,
                 returnDate
         });
     }

 } catch (Exception ex) {

     JOptionPane.showMessageDialog(
             viewFrame,
             ex.getMessage()
     );
 }

 JScrollPane scrollPane =
         new JScrollPane(table);

 scrollPane.setBounds(
         10,
         10,
         860,
         330
 );

 viewFrame.add(scrollPane);

 viewFrame.setVisible(true);
});

frame.add(viewissuedbutton);
    // Main window layout
    frame.setLayout(null);
	frame.add(addbookbutton);
	frame.add(viewbookbutton);
	frame.setVisible(true);
}
}
