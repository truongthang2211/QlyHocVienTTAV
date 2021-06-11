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
public class Schedule {
    String ScheduleID, Day ,  Shift , ClassId, ClassName, RoomId, RoomName, TeacherId, TeacherName;

    public Schedule(String ScheduleID, String Day, String Shift, String ClassId, String ClassName, String RoomId, String RoomName, String TeacherId, String TeacherName) {
        this.ScheduleID = ScheduleID;
        this.Day = Day;
        this.Shift = Shift;
        this.ClassId = ClassId;
        this.ClassName = ClassName;
        this.RoomId = RoomId;
        this.RoomName = RoomName;
        this.TeacherId = TeacherId;
        this.TeacherName = TeacherName;
    }

    public Schedule(String ScheduleID,String Day, String Shift, String ClassId, String RoomId, String TeacherId) {
         this.ScheduleID = ScheduleID;
        this.Day = Day;
        this.Shift = Shift;
        this.ClassId = ClassId;
        this.RoomId = RoomId;
        this.TeacherId = TeacherId;
    }

    public String getScheduleID() {
        return ScheduleID;
    }

    public void setScheduleID(String ScheduleID) {
        this.ScheduleID = ScheduleID;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String Day) {
        this.Day = Day;
    }

    public String getShift() {
        return Shift;
    }

    public void setShift(String Shift) {
        this.Shift = Shift;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String ClassId) {
        this.ClassId = ClassId;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String RoomId) {
        this.RoomId = RoomId;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String TeacherId) {
        this.TeacherId = TeacherId;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String TeacherName) {
        this.TeacherName = TeacherName;
    }
    public boolean checkContain(String str){
//          id.toUpperCase().contains(str.toUpperCase()) == true 
//                ||
//    String ScheduleID, Day ,  Shift , ClassId, ClassName, RoomId, RoomName, TeacherId, TeacherName;


        if (
            ScheduleID.toUpperCase().contains(str.toUpperCase())==true ||
                Day.toUpperCase().contains(str.toUpperCase())==true ||
                Shift.toUpperCase().contains(str.toUpperCase())==true ||
                ClassId.toUpperCase().contains(str.toUpperCase())==true ||
                ClassName.toUpperCase().contains(str.toUpperCase())==true ||
                RoomName.toUpperCase().contains(str.toUpperCase())==true ||
                RoomId.toUpperCase().contains(str.toUpperCase())==true ||
                TeacherId.toUpperCase().contains(str.toUpperCase())==true ||
                TeacherName.toUpperCase().contains(str.toUpperCase())==true
                

        ){
   
            
            return true;
        }
        else{
            return false;
        }
    }
}
