
package Model;

import Utilities.DBconnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;


public class UserJson {
    private String id;
    private String fullname;
    private String address;
    private String nationality;
    private String nic;
    private String birthdate;
    private int age;
    private String gender;
    private String lastUpdate;
    private int activeState;

    public UserJson() {
    }

    public UserJson(String id, String fullname, String address, String nationality, String nic, String birthdate, int age, String gender, String lastUpdate, int activeState) {
        this.id = id;
        this.fullname = fullname;
        this.address = address;
        this.nationality = nationality;
        this.nic = nic;
        this.birthdate = birthdate;
        this.age = age;
        this.gender = gender;
        this.lastUpdate = lastUpdate;
        this.activeState = activeState;
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

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getActiveState() {
        return activeState;
    }

    public void setActiveState(int activeState) {
        this.activeState = activeState;
    }
    
    public static void saveUser(UserJson user) {

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
}
