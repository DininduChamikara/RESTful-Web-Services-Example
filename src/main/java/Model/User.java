package Model;

import Utilities.DBconnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

public class User {

    private String id;
    private String fullname;
    private String address;
    private String nationality;
    private String nic;
    private String birthdate;
    private int age;
    private String gender;
    private Date lastUpdate;
    private int activeState;

    public User() {
    }

    public User(String id, String fullname, String address, String nationality, String nic, String birthdate, int age, String gender) {
        this.id = id;
        this.fullname = fullname;
        this.address = address;
        this.nationality = nationality;
        this.nic = nic;
        this.birthdate = birthdate;
        this.age = age;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getActiveState() {
        return activeState;
    }

    public void setActiveState(int activeState) {
        this.activeState = activeState;
    }

    
    
    public static List<User> find() {
        List<User> users = new ArrayList<User>();
        User user;
        String sql = "SELECT * FROM nic_register.users_new WHERE activeState=1";
        ResultSet resultSet;

        try {
            Connection con = DBconnection.createConnection();
            Statement stmt = con.createStatement();
            resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getString("id"));
                user.setFullname(resultSet.getString("fullname"));
                user.setAddress(resultSet.getString("address"));
                user.setNationality(resultSet.getString("nationality"));
                user.setNic(resultSet.getString("nic"));
                user.setBirthdate(resultSet.getString("birthdate"));
                user.setAge(resultSet.getInt("age"));
                user.setGender(resultSet.getString("gender"));
                user.setLastUpdate(resultSet.getDate("lastUpdate"));
                users.add(user);
                System.out.println(user);
            }

        } catch (Exception e) {
            System.out.println("The Error is " + e.getMessage());
        }

        return users;
    }

    public static void save(User user) {

        String sql = "INSERT INTO nic_register.users_new(fullname, address, nationality, nic, birthdate, age, gender, lastUpdate, activeState) VALUES(?,?,?,?,?,?,?,?,?)";

        String fullname = user.getFullname();
        String address = user.getAddress();
        String nationality = user.getNationality();
        String nicNumber = user.getNic();
        String birthdate = user.getBirthdate();
        int age = user.getAge();
        String gender = user.getGender();
        
        long millis = System.currentTimeMillis();       
        System.out.println("long variable value is "+ millis);
        Date currentDate = new java.sql.Date(millis);    
        
        int activeState = 1;
        

        try {
            Connection con = DBconnection.createConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, fullname);
            ps.setString(2, address);
            ps.setString(3, nationality);
            ps.setString(4, nicNumber);
            ps.setString(5, birthdate);
            ps.setInt(6, age);
            ps.setString(7, gender);
            ps.setDate(8, currentDate);
            ps.setInt(9, activeState);

            ps.executeUpdate();
            System.out.println("Successfully saved the values in db from User.java");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static User getUserData(User user) {

        User u = new User();
        String id = user.getId();
        ResultSet resultSet;

        String sql = "SELECT * FROM nic_register.users_new WHERE id='" + id + "';";

        try {

            Connection con = DBconnection.createConnection();
            Statement stmt = con.createStatement();
            resultSet = stmt.executeQuery(sql);

            resultSet.next();

            u.setId(resultSet.getString("id"));
            u.setFullname(resultSet.getString("fullname"));
            u.setAddress(resultSet.getString("address"));
            u.setNationality(resultSet.getString("nationality"));
            u.setNic(resultSet.getString("nic"));
            u.setBirthdate(resultSet.getString("birthdate"));
            u.setAge(resultSet.getInt("age"));
            u.setGender(resultSet.getString("gender"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return u;
    }

    public static void edit(User user) {

        String sql = "UPDATE nic_register.users_new SET fullname=?, address=?, nationality=?, nic=?, birthdate=?, age=?, gender=?, lastUpdate=?  WHERE id=?";

        String id = user.getId();
        String fullname = user.getFullname();
        String address = user.getAddress();
        String nationality = user.getNationality();
        String nicNumber = user.getNic();
        String birthdate = user.getBirthdate();
        int age = user.getAge();
        String gender = user.getGender();
        
        long millis = System.currentTimeMillis();       
        System.out.println("long variable value is "+ millis);
        Date currentDate = new java.sql.Date(millis);    

        try {
            Connection con = DBconnection.createConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, fullname);
            ps.setString(2, address);
            ps.setString(3, nationality);
            ps.setString(4, nicNumber);
            ps.setString(5, birthdate);
            ps.setInt(6, age);
            ps.setString(7, gender);
            ps.setDate(8, currentDate);
            ps.setString(9, id);

            ps.executeUpdate();
            System.out.println("Successfully updated the values in db from User.java");

        } catch (Exception e) {
            System.out.println("error from edit user is "+e.getMessage());
        }
    }

    public static void deleteUserData(User user) {


        try {

            String id = user.getId();

            System.out.println("id is from deleteuserdata " + id);
            
            Connection con;
            con = DBconnection.createConnection();

//            String sql = "DELETE FROM nic_register.users_new WHERE id=?";
            String sql = "UPDATE nic_register.users_new SET activeState='0' WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("The Error is : " + e.getMessage());
        }

    }

}
