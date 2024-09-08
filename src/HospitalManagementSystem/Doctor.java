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
            System.out.println("+----------+------------+--------------------+-----------------------------+");
            System.out.println("  Id       | Doctor Id  | Name               | Specialization              |");
            System.out.println("+----------+------------+--------------------+-----------------------------+");
            while (resultSet.next()){
                int Id = resultSet.getInt("Id");
                String Name = resultSet.getString("Name");
                String Specialization = resultSet.getString("Specialization");
                System.out.printf("%10s |%10s  |%18s   |%27s    |\n",Id, Id,Name,Specialization);
                System.out.println("+----------+------------+--------------------+-----------------------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public boolean GetDoctorById(int Id) {
        String Query = "Select * from doctors where Id=?";
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
