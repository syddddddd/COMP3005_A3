import java.sql.*;

public class Main {

    // I defined the connection object as a static variable
    public static Connection connection;

    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/Assignment3";
        String user = "postgres";
        String password = "Doritos472!";

        try {
            Class.forName("org.postgresql.Driver");

            // I initialized the connection object
            connection = DriverManager.getConnection(url, user, password);

            // I checked if it connected to the database successfully
            if (connection != null) {
                System.out.println("Connected to PostgreSQL successfully!");
            } else {
                System.out.println("Failed to establish connection.");
            }

            // My four functions for this assignment
            //getAllStudents();
            //addStudent("Tony", "Stark", "tony.stark@example.com", "2023-01-02");
            //updateStudentEmail("1", "test.email@example.com");
            //deleteStudent("4");

            connection.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //Retrieves and displays all records from the students table.
    public static void getAllStudents(){
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet result = statement.getResultSet();

            while (result.next()) {
                System.out.print(result.getString("student_id") + " \t");
                System.out.print(result.getString("first_name") + " \t");
                System.out.print(result.getString("last_name") + " \t");
                System.out.print(result.getString("email") + " \t");
                System.out.println(result.getString("enrollment_date"));
            }
        }
        catch (SQLException e){
            System.out.println("Database not populated");
        }
    }

    // Inserts a new student record into the students table
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ( \'" + first_name + "\' , \'" + last_name + "\' , \'" + email + "\' , \'" + enrollment_date + "\');";
            int rowsInserted = statement.executeUpdate(sql);

            if (rowsInserted > 0){
                System.out.println("New student inserted");
            }
        }
        catch (SQLException e){
            System.out.println("INSERT failed");
        }
    }

    //Updates the email address for a student with the specified student_id
    public static void updateStudentEmail(String student_id, String new_email){
        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE students SET email = \'" + new_email + "\' WHERE student_id = \'" + student_id + "\';";
            statement.executeUpdate(sql);

            System.out.println("Updated email");
        }
        catch (SQLException e){
            System.out.println("UPDATE failed");
        }
    }

    // Deletes the record of the student with the specified student_id
    public static void deleteStudent(String student_id){
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM students WHERE student_id = \'" + student_id + "\';";
            statement.executeUpdate(sql);

            System.out.println("Deleted student");
        }
        catch (SQLException e){
            System.out.println("DELETE failed");
        }
    }
}

