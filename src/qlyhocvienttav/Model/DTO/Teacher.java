package qlyhocvienttav.Model.DTO;

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

}

