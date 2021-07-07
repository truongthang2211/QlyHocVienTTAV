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
public class Score {
    String Score_ID,Student_ID,Student_Name,TestSchedule_ID,KindOfTest;
    float Listening,Writing,Reading,Speaking;
    Score(){}

    public Score( String Student_ID, String Student_Name,String KindOfTest, float Listening, float Writing, float Reading, float Speaking) {
        this.Student_Name = Student_Name;
        this.Student_ID = Student_ID;   
        this.KindOfTest = KindOfTest;
        this.Listening = Listening;
        this.Writing = Writing;
        this.Reading = Reading;
        this.Speaking = Speaking;
    }

    public String getScore_ID() {
        return Score_ID;
    }

    public void setScore_ID(String Score_ID) {
        this.Score_ID = Score_ID;
    }

    public String getStudent_ID() {
        return Student_ID;
    }

    public void setStudent_ID(String Student_ID) {
        this.Student_ID = Student_ID;
    }

    public String getTestSchedule_ID() {
        return TestSchedule_ID;
    }

    public void setTestSchedule_ID(String TestSchedule_ID) {
        this.TestSchedule_ID = TestSchedule_ID;
    }

    public float getListening() {
        return Listening;
    }

    public void setListening(float Listening) {
        this.Listening = Listening;
    }

    public float getWriting() {
        return Writing;
    }

    public void setWriting(float Writing) {
        this.Writing = Writing;
    }

    public float getReading() {
        return Reading;
    }

    public void setReading(float Reading) {
        this.Reading = Reading;
    }

    public float getSpeaking() {
        return Speaking;
    }

    public void setSpeaking(float Speaking) {
        this.Speaking = Speaking;
    }

    public String getStudent_Name() {
        return Student_Name;
    }

    public void setStudent_Name(String Student_Name) {
        this.Student_Name = Student_Name;
    }

    public String getKindOfTest() {
        return KindOfTest;
    }

    public void setKindOfTest(String KindOfTest) {
        this.KindOfTest = KindOfTest;
    }
    
    public boolean checkdata(String str) {        
         if (
            Student_ID.toUpperCase().contains(str.toUpperCase())==true ||
            Student_Name.toUpperCase().contains(str.toUpperCase())==true ||
            String.valueOf(Listening).toUpperCase().contains(str)==true||
            String.valueOf(Writing).toUpperCase().contains(str)==true||
            String.valueOf(Speaking).toUpperCase().contains(str)==true||
            String.valueOf(Reading).toUpperCase().contains(str)==true
                 
        ){    
            return true;
        }
        else{
            return false;
        }
    }
}
