/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Model.DTO;

/**
 *
 * @author Thang
 */
public class Student extends Personal_Info{
    String student_id, class_id,course_id,course_name;
    double amountOfFee;

    public Student(String student_id, String class_id, String course_id, String course_name, double amountOfFee, String fullName, String sex, String dateOfBirth, String nationality, String address, String email, String phoneNumber) {
        super(fullName, sex, dateOfBirth, nationality, address, email, phoneNumber);
        this.student_id = student_id;
        this.class_id = class_id;
        this.course_id = course_id;
        this.course_name = course_name;
        this.amountOfFee = amountOfFee;
    }

    public Student(String student_id, String class_id, String course_id, String fullName, String sex, String dateOfBirth, String nationality, String address, String email, String phoneNumber) {
        super(fullName, sex, dateOfBirth, nationality, address, email, phoneNumber);
        this.student_id = student_id;
        this.class_id = class_id;
        this.course_id = course_id;
    }

    

    public Student() {
    }
    
    public Student(String student_id) {
        this.student_id = student_id;
    }

    public Student(String student_id, String course_name, double amountOfFee, String fullName) {
        super(fullName);
        this.student_id = student_id;
        this.course_name = course_name;
        this.amountOfFee = amountOfFee;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public double getAmountOfFee() {
        return amountOfFee;
    }

    public void setAmountOfFee(double amountOfFee) {
        this.amountOfFee = amountOfFee;
    }

    
    
    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
    
    

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    @Override
    public boolean checkContain(String str) {
         if (
            fullName.toUpperCase().contains(str.toUpperCase())==true ||
                sex.toUpperCase().contains(str.toUpperCase())==true ||
                dateOfBirth.toUpperCase().contains(str.toUpperCase())==true ||
                nationality.toUpperCase().contains(str.toUpperCase())==true ||
                address.toUpperCase().contains(str.toUpperCase())==true ||
                email.toUpperCase().contains(str.toUpperCase())==true ||
                phoneNumber.toUpperCase().contains(str.toUpperCase())==true||
                class_id.toUpperCase().contains(str.toUpperCase())==true ||
                course_id.toUpperCase().contains(str.toUpperCase())==true ||
                course_name.toUpperCase().contains(str.toUpperCase())==true ||
                String.valueOf(amountOfFee).toUpperCase().contains(str.toUpperCase())==true
        ){
   
            
            return true;
        }
        else{
            return false;
        }
    }
    
}
