/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Model.DTO;

/**
 *
 * @author Khang
 */
public class TestSchedule {
    String TestSche_ID,Teacher_ID,Course_ID,Room_ID,Loai_KT,TestDate;
    //Them ca thi
    int Shift;
    public TestSchedule(String TestSche_ID,String Teacher_ID,String Course_ID, String Room_ID, String Loai_KT, String TestDate, int Shift){
        this.TestSche_ID = TestSche_ID;
        this.Teacher_ID = Teacher_ID;
        this.Course_ID = Course_ID;
        this.Room_ID = Room_ID;
        this.Loai_KT = Loai_KT;
        this.TestDate = TestDate;
        this.Shift = Shift;
    }

    public String getTestSche_ID() {
        return TestSche_ID;
    }

    public void setTestSche_ID(String TestSche_ID) {
        this.TestSche_ID = TestSche_ID;
    }

    public String getTeacher_ID() {
        return Teacher_ID;
    }

    public void setTeacher_ID(String Teacher_ID) {
        this.Teacher_ID = Teacher_ID;
    }

    public String getCourse_ID() {
        return Course_ID;
    }
    
    public int getShift(){
        return Shift;
    }
    
    public void setCourse_ID(String Course_ID) {
        this.Course_ID = Course_ID;
    }

    public String getRoom_ID() {
        return Room_ID;
    }

    public void setRoom_ID(String Room_ID) {
        this.Room_ID = Room_ID;
    }

    public String getLoai_KT() {
        return Loai_KT;
    }

    public void setLoai_KT(String Loai_KT) {
        this.Loai_KT = Loai_KT;
    }

    public String getTestDate() {
        return TestDate;
    }

    public void setTestDate(String TestDate) {
        this.TestDate = TestDate;
    }
    
    public void setShift(int Shift){
        this.Shift = Shift;
    }
    
}
