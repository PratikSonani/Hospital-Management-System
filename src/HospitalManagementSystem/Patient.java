package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private java.sql.Connection Connection;
    private java.util.Scanner Scanner;

    public Patient(Connection connection,Scanner scanner){
        this.Connection = connection;
        this.Scanner = scanner;
    }

    public void addPatient(){
        System.out.print("Enter the Name");
        String Name = Scanner.next();
        System.out.print("Enter Your Age");
        int Age = Scanner.nextInt();
        System.out.print("Enter Your Gender");
        String Gender = Scanner.next();

        try {String Query = "INSERT INTO Patient(Name,Age,Gender) VALUES(?,?,?)";
            PreparedStatement preparedStatement = Connection.prepareStatement(Query);
            preparedStatement.setString(1,Name);
            preparedStatement.setInt(2,Age);
            preparedStatement.setString(3,Gender);
            int AffectedRows = preparedStatement.executeUpdate();
            if (AffectedRows>0){
                System.out.println("Patient Added Successfully");
            }
            else {
                System.out.println("Patient Failed to Add");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }



    }
    public void ViewPatient(){
        String Query = "Select * from Patient";
        try{
            PreparedStatement preparedStatement = Connection.prepareStatement(Query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patient");
            System.out.println("+------------+--------------------+--------------+---------------+");
            System.out.println("| Patient Id | Name               | Age          | Gender        |");
            System.out.println("+------------+--------------------+--------------+---------------+");
            while (resultSet.next()){
                int Id = resultSet.getInt("Id");
                String Name = resultSet.getString("Name");
                int Age = resultSet.getInt("Age");
                String Gender = resultSet.getString("Gender");
                System.out.printf("|%12s|%20s|%14s|%15s|\n",Id,Id,Name,Age,Gender);
                System.out.println("+------------+--------------------+--------------+---------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public boolean GetPatientById(int Id) {
        String Query = "Select * from Patient where Id=?";
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
