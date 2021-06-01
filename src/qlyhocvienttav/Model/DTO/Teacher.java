package qlyhocvienttav.Model.DTO;

import java.util.Locale;

public class Teacher extends Personal_Info{
    String teacherId;
    public Teacher(String teacher_id, String fullName, String sex, String dateOfBirth, String nationality, String address, String email, String phoneNumber){
        super(fullName, sex, dateOfBirth, nationality, address, email, phoneNumber);
        this.teacherId=teacher_id;
    }
    public String getTeacherId(){
      return teacherId;
    }
    public void setTeacherId(String _teacherId){
        this.teacherId=_teacherId;
    }
    public boolean checkContain(String str){

        if (teacherId.toUpperCase().contains(str.toUpperCase()) == true ||
            fullName.toUpperCase().contains(str.toUpperCase())==true ||
                sex.toUpperCase().contains(str.toUpperCase())==true ||
                dateOfBirth.toUpperCase().contains(str.toUpperCase())==true ||
                nationality.toUpperCase().contains(str.toUpperCase())==true ||
                address.toUpperCase().contains(str.toUpperCase())==true ||
                email.toUpperCase().contains(str.toUpperCase())==true ||
                phoneNumber.toUpperCase().contains(str.toUpperCase())==true

        ){
            return true;
        }
        else{
            return false;
        }
    }

}

