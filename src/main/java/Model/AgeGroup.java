
package Model;

import Utilities.DBconnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AgeGroup {
    private String ageGroup;
    private int count;

    public AgeGroup() {
    }

    public AgeGroup(String ageGroup, int count) {
        this.ageGroup = ageGroup;
        this.count = count;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public static List<AgeGroup> findAgeGroup() {
        List<AgeGroup> ageGroups = new ArrayList<AgeGroup>();
              
        try {
            Connection con = DBconnection.createConnection();
            Statement stmt = con.createStatement();
            
            for(int i=15; i<=95; i=i+10){
                
                String sql = "SELECT COUNT(id) FROM nic_register.users_new WHERE age >" + (i-1) + " AND age < "+ (i+10) +" AND activeState=1";
                ResultSet result = stmt.executeQuery(sql);
                result.next();
                AgeGroup ageGroup = new AgeGroup();
                ageGroup.setAgeGroup(i + " - " + (i+10));
                ageGroup.setCount(result.getInt(1)); 
                
                ageGroups.add(ageGroup);
            }
            
        } catch (Exception e) {
            System.out.println("The Error is " + e.getMessage());
        }

        return ageGroups;
    }
    
    
}
