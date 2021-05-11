package qlyhocvienttav.Model.DTO;

public class Course {
    String course_id;
    String courseName;
    long feeOfCourse;
    String dateStart;
    String dateEnd;

    public Course(String course_id, String courseName, long feeOfCourse, String dateStart, String dayEnd) {
        this.course_id = course_id;
        this.courseName = courseName;
        this.feeOfCourse = feeOfCourse;
        this.dateStart = dateStart;
        this.dateEnd = dayEnd;
    }


    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getFeeOfCourse() {
        return feeOfCourse;
    }

    public void setFeeOfCourse(long feeOfCourse) {
        this.feeOfCourse = feeOfCourse;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDayEnd(String dayEnd) {
        this.dateEnd = dayEnd;
    }


}
