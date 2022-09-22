
package Model;

import Utilities.DBconnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsersCount {
    private int maleUsersCount;
    private int femaleUsersCount;
    private int deletedUserCount;

    public UsersCount() {
    }

    public UsersCount(int maleUsersCount, int femaleUsersCount, int deletedUserCount) {
        this.maleUsersCount = maleUsersCount;
        this.femaleUsersCount = femaleUsersCount;
        this.deletedUserCount = deletedUserCount;
    }

    public int getMaleUsersCount() {
        return maleUsersCount;
    }

    public void setMaleUsersCount(int maleUsersCount) {
        this.maleUsersCount = maleUsersCount;
    }

    public int getFemaleUsersCount() {
        return femaleUsersCount;
    }

    public void setFemaleUsersCount(int femaleUsersCount) {
        this.femaleUsersCount = femaleUsersCount;
    }

    public int getDeletedUserCount() {
        return deletedUserCount;
    }

    public void setDeletedUserCount(int deletedUserCount) {
        this.deletedUserCount = deletedUserCount;
    }
    
    public UsersCount getUserCount(){
        UsersCount uc = new UsersCount();
        
        String sqlMaleCount = "SELECT COUNT(gender) FROM nic_register.users_new WHERE gender='male' AND activeState=1";
        String sqlFemaleCount = "SELECT COUNT(gender) FROM nic_register.users_new WHERE gender='female' AND activeState=1";
        String sqlDeletedCount = "SELECT COUNT(gender) FROM nic_register.users_new WHERE activeState=0";
        
        try {
            Connection con = DBconnection.createConnection();
            Statement stmt = con.createStatement();
            
            ResultSet resultMale = stmt.executeQuery(sqlMaleCount);
            resultMale.next();
            uc.setMaleUsersCount(Integer.parseInt(resultMale.getString(1)));
            
            ResultSet resultFemale = stmt.executeQuery(sqlFemaleCount);
            resultFemale.next();
            uc.setFemaleUsersCount(Integer.parseInt(resultFemale.getString(1)));
            
            ResultSet resultDeleted = stmt.executeQuery(sqlDeletedCount);
            resultDeleted.next();
            uc.setDeletedUserCount(Integer.parseInt(resultDeleted.getString(1)));
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return uc;
    }
}
