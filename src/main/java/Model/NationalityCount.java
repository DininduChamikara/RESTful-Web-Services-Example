/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utilities.DBconnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class NationalityCount {

    private int sinhalaCount;
    private int tamilCount;
    private int englishCount;

    public NationalityCount() {
    }

    public NationalityCount(int sinhalaCount, int tamilCount, int englishCount) {
        this.sinhalaCount = sinhalaCount;
        this.tamilCount = tamilCount;
        this.englishCount = englishCount;
    }

    public int getSinhalaCount() {
        return sinhalaCount;
    }

    public void setSinhalaCount(int sinhalaCount) {
        this.sinhalaCount = sinhalaCount;
    }

    public int getTamilCount() {
        return tamilCount;
    }

    public void setTamilCount(int tamilCount) {
        this.tamilCount = tamilCount;
    }

    public int getEnglishCount() {
        return englishCount;
    }

    public void setEnglishCount(int englishCount) {
        this.englishCount = englishCount;
    }

    public NationalityCount getNationalityCount() {
        NationalityCount nc = new NationalityCount();

        String sqlSinhalaCount = "SELECT COUNT(nationality) FROM nic_register.users_new WHERE nationality='sinhala' AND activeState=1";
        String sqlTamilCount = "SELECT COUNT(nationality) FROM nic_register.users_new WHERE nationality='tamil' AND activeState=1";
        String sqlEnglishCount = "SELECT COUNT(nationality) FROM nic_register.users_new WHERE nationality='english' AND activeState=1";

        try {
            Connection con = DBconnection.createConnection();
            Statement stmt = con.createStatement();
            
            ResultSet resultSinhala = stmt.executeQuery(sqlSinhalaCount);
            resultSinhala.next();
            
            System.out.println("sinhala count is " + resultSinhala.getString(1));
            nc.setSinhalaCount(Integer.parseInt(resultSinhala.getString(1)));
            
            ResultSet resultTamil = stmt.executeQuery(sqlTamilCount);
            resultTamil.next();
            
            System.out.println("tamil count is " + resultTamil.getString(1));
            nc.setTamilCount(Integer.parseInt(resultTamil.getString(1)));
            
            ResultSet resultEnglish = stmt.executeQuery(sqlEnglishCount);
            resultEnglish.next();

            System.out.println("english count is " + resultEnglish.getString(1));
            nc.setEnglishCount(Integer.parseInt(resultEnglish.getString(1)));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return nc;
    }
}
