package jdbc.manager;

import jdbc.database.DBConnectionProvider;
import jdbc.model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonManager {

    private Connection conn = DBConnectionProvider.getProvider().getConnection();

    public void addPerson(Person person){

        try {
           String query = "Insert into person (name, surname, email, password) values (?,?,?,?)";
           PreparedStatement stat = conn.prepareStatement(query);
           stat.setString(1, person.getName());
           stat.setString(2, person.getSurname());
           stat.setString(3, person.getEmail());
           stat.setString(4, person.getPassword());
           String sql = "Select name from person where exists (select email from person where " +
                    "email='" + person.getEmail() + "')";
           ResultSet rs = stat.executeQuery(sql);
           if (rs.next()){
               System.out.println("Person already exists!");
           } else {
               stat.execute();
               System.out.println("Adding was succeeded!");
           }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void upDatePerson (Person person){

        try {
        String query = "UPDATE person set name = ?, surname = ?, email = ?, password = ? WHERE email='" + person.getEmail() + "'";
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, person.getName());
            stat.setString(2, person.getSurname());
            stat.setString(3, person.getEmail());
            stat.setString(4, person.getPassword());
            stat.executeUpdate();
            System.out.println("Update was succeeded!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getPerson (String keyword){

        List<String[]> list = new ArrayList<>();
        try {
            String query = "Select name, surname, email from person";
            PreparedStatement stat = conn.prepareStatement(query);
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString(1);
                String surname = rs.getString(2);
                String email = rs.getString(3);
                if (name.contains(keyword) || surname.contains(keyword)) {
                    String[] result = new String[]{name, surname, email};
                    list.add(result);
                    System.out.println(Arrays.toString(result));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAllData (){

        List<String[]> list = new ArrayList<>();

        try {
            String query = "Select * from person";
            PreparedStatement stat = conn.prepareStatement(query);
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()){
                String name = rs.getString(2);
                String surname = rs.getString(3);
                String email = rs.getString(4);
                String[] result = new String[]{name, surname, email};
                list.add(result);
                System.out.println(Arrays.toString(result));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void deleteData (String email){

        try {
            String query = "Delete from person where email='" + email + "'";
            PreparedStatement stat = conn.prepareStatement(query);
            stat.executeUpdate();
            System.out.println("Data was deleted successfully!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAllData (){

        try {
            String query = "Delete from person";
            PreparedStatement stat = conn.prepareStatement(query);
            stat.executeUpdate();
            System.out.println("Database table is empty!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
