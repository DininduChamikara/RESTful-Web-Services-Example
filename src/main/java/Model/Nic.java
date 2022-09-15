package Model;

import java.util.Calendar;

public class Nic {

    private String nic;
    private String birthdate;
    private int age;
    private String gender;
    private String errMsg;

    public Nic() {
    }

    public Nic(String nic, String birthdate, int age, String gender) {
        this.nic = nic;
        this.birthdate = birthdate;
        this.age = age;
        this.gender = gender;
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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public static Nic getNicData(User user) {

        System.out.println("getNicData Started");

        Nic nic = new Nic();

        String nicNum = user.getNic();
        boolean validNic = false;
        boolean newId = false;

        int month[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        String sex;
        int year = 0;
        int y;  // variable to calculate the year
        int md = 0; // variable for calculate month and data
        int tempMd; // temporary do changes in variable md
        int mon = 0;  // variable for month
        int day;    // variable for date

        int currentYear = Calendar.getInstance().get(Calendar.YEAR); // to get current year
        int age;

        String birthdate;

        String err_Msg = "";

        try {

            System.out.println(nicNum);

            if (nicNum.length() == 10 || nicNum.length() == 12) {
                validNic = true;
            } else {
                err_Msg = "Invalid NIC number";
            }

            if (nicNum.length() == 12 && validNic == true) {
                newId = true;
            } else if (nicNum.length() == 10 && validNic == true) {
                newId = false;
            }

            if (newId == false && validNic == true) {

                y = Integer.parseInt(nicNum.toString().substring(0, 2));
                md = Integer.parseInt(nicNum.toString().substring(2, 5));

                year = 1900 + y;

            } else if (newId == true && validNic == true) {
                y = Integer.parseInt(nicNum.toString().substring(0, 4));
                md = Integer.parseInt(nicNum.toString().substring(4, 7));

                year = y;
            }
            
            if(md>366 && md<501){
                validNic = false;
                err_Msg = "Invalid NIC number";
            }
            
            if(md>866){
                validNic = false;
                err_Msg = "Invalid NIC number";
            }

            if (md > 500) {
                md = md - 500;
                sex = "female";
            } else {
                sex = "male";
            }

            tempMd = md;
            // month
            for (int i = 0; i < 12; i++) {
                if (tempMd <= month[i]) {
                    mon = i + 1;
                    break;
                } else {
                    tempMd = tempMd - month[i];
                }
            }

            for (int i = 0; i < mon - 1; i++) {
                md = md - month[i];
            }
            day = md;

            birthdate = day + "/" + mon + "/" + year;
            age = currentYear - year;

            System.out.println("age is " + age);

            if (validNic) {
                nic.setNic(nicNum);
                nic.setBirthdate(birthdate);
                nic.setAge(age);
                nic.setGender(sex);
                nic.setErrMsg(err_Msg);
            }else{
                nic.setNic("");
                nic.setBirthdate("");
                nic.setAge(0);
                nic.setGender("");
                nic.setErrMsg(err_Msg);
            }

            System.out.println("nic age is " + nic.getAge());

        } catch (Exception e) {
            System.out.println("The Error is : " + e.getMessage());
        }

        System.out.println("nic number is (from getNicData method) " + nic.getNic());

        return nic;
    }
}
