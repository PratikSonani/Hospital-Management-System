package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String Url = "jdbc:mysql://localhost:3306/hospital";
    private static final String UserName = "root";
    private static final String Password = "Pratik2000&";

    public static void main(String[] args) {
        try {
            Class.forName("con.mysql.cj.jdbc.Driver");
//            it is use to connect with database load all necessary drivers

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
                        System.out.println();
                        break;

                    case 2:
                        // View Patient
                        patient.ViewPatient();
                        System.out.println();
                        break;

                    case 3:
                        // View Doctor
                        doctor.ViewDoctors();
                        System.out.println();
                        break;

                    case 4:
                        //Book Appointment
                        BookAppointment(patient,doctor,connection,scanner);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println();
                        return;

                    default:
                        System.out.println("Enter a Valid Input");
                        System.out.println();
                        break;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

public static void BookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner) {
    System.out.println("Enter the Patient Id: ");
    int PatientId = scanner.nextInt();
    System.out.println("Enter the Doctor Id: ");
    int DoctorId = scanner.nextInt();
    System.out.println("Enter the appointment date (YYYY-MM-DD): ");
    String Appointment_Date = scanner.next();

    // Check if the patient and doctor exist
    if (patient.GetPatientById(PatientId) && doctor.GetDoctorById(DoctorId)) {
        // Check if the doctor is available
        if (checkDoctorAval(DoctorId, Appointment_Date, connection)) {
            // Proceed to book the appointment
            String insertAppointmentQuery = "INSERT INTO appointments (Patient_Id, Doctor_Id, Appointment_Date) VALUES (?, ?, ?)";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertAppointmentQuery);
                preparedStatement.setInt(1, PatientId);
                preparedStatement.setInt(2, DoctorId);
                preparedStatement.setString(3, Appointment_Date);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Appointment booked successfully.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Doctor is not available on the selected date.");
        }
    } else {
        System.out.println("Either Doctor or Patient doesn't exist.");
    }
}



    public static boolean checkDoctorAval(int DoctorId, String appointmentDate, Connection connection) {
        String query = "SELECT COUNT(*) FROM appointments WHERE Doctor_Id = ? AND Appointment_Date = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, DoctorId);
            preparedStatement.setString(2, appointmentDate);  // consistent variable name
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 0;  // true if doctor is available
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
