package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
    private java.sql.Connection Connection;


    public Doctor(Connection connection){
        this.Connection = connection;

    }


    public void ViewDoctors(){
        String Query = "Select * from Doctors";
        try{
            PreparedStatement preparedStatement = Connection.prepareStatement(Query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctor");
            System.out.println("+------------+--------------------+-----------------------------+");
            System.out.println("| Doctor Id  | Name               | Specialisation              |");
            System.out.println("+------------+--------------------+-----------------------------+");
            while (resultSet.next()){
                int Id = resultSet.getInt("Id");
                String Name = resultSet.getString("Name");
                String Specialisation = resultSet.getString("Specialisation");
                System.out.printf("|%12s|%20s|%29s|\n", Id,Name,Specialisation);
                System.out.println("+------------+--------------------+-----------------------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public boolean GetDoctorById(int Id) {
        String Query = "Select * from Doctors where Id=?";
        try {
            PreparedStatement preparedStatement = Connection.prepareStatement(Query);
            preparedStatement.setInt(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

}
