import java.sql.*;
import java.util.*;
import java.util.Date;

class Delivery_Person_Acc_Creation {
    public void createDeliveryPersonAccount(String name, String username, String password, String vehicleType, String phoneNo, String status, String address, String workingHours, String salary) {
        String sql = "INSERT INTO delivery_person (Name, Username, password, Vechile_type, Phone_no, Status, Address, Working_hours, Salary, Created_at, Modified_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = dbconnection.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, name);
            pstm.setString(2, username);
            pstm.setString(3, password);
            pstm.setString(4, vehicleType);
            pstm.setString(5, phoneNo);
            pstm.setString(6, status);
            pstm.setString(7, address);
            pstm.setString(8, workingHours);
            pstm.setString(9, salary);
            pstm.setTimestamp(10, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(11, new Timestamp(new Date().getTime()));

            pstm.executeUpdate();
            System.out.println("Delivery person account created successfully!!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Delivery_Person_Acc_Creation ob = new Delivery_Person_Acc_Creation();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Vehicle Type: ");
        String vehicleType = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNo = scanner.nextLine();

        System.out.print("Enter Status(free/assigned): ");
        String status = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Working Hours: ");
        String workingHours = scanner.nextLine();

        System.out.print("Enter Salary: ");
        String salary = scanner.nextLine();

        ob.createDeliveryPersonAccount(name, username, password, vehicleType, phoneNo, status, address, workingHours, salary);
        scanner.close();
    }
}
