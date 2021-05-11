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
    String student_id, class_id,course_id;
    public Student(String student_id, String course_id, String fullName, String sex, String dateOfBirth,
                   String nationality, String address, String email, String phoneNumber) {
        super(fullName, sex, dateOfBirth, nationality, address, email, phoneNumber);
        this.student_id = student_id;
        this.course_id = course_id;
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
    
}
