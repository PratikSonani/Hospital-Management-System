package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String Url = "jdbc:mysql://localhost:3306/Hospital";
    private static final String UserName = "root";
    private static final String Password = "Pratik2000&";

    public static void main(String[] args) {
        try {
            Class.forName("con.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(Url,UserName,Password);
            Patient patient = new Patient(connection,scanner);
            Doctor doctor = new Doctor(connection);
            while (true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1 . Add Patient");
                System.out.println("2 . View Patient");
                System.out.println("3 . View Doctor");
                System.out.println("4 .  Book Appointment");
                System.out.println("5 . Exit");
                System.out.println("Enter Your Choice");
                int Choice = scanner.nextInt();

                switch (Choice){
                    case 1:
                        // Add Patient
                        patient.addPatient();
                        break;

                    case 2:
                        // View Patient
                        patient.ViewPatient();
                        break;

                    case 3:
                        // View Doctor
                        doctor.ViewDoctors();
                        break;

                    case 4:
                        //Book Appointment

                        break;
                    case 5:
                        return;

                    default:
                        System.out.println("Enter a Valid Input");
                        break;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}