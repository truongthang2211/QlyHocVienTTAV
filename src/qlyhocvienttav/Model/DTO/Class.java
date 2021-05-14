package qlyhocvienttav.Model.DTO;

public class Class {
    String classId;
    String className;
    int numberOfPeople;
    int maxNumberOfPeople;
    String courseId;

    public Class(String classId, String className, int numberOfPeople, int maxNumberOfPeople, String courseId) {
        this.classId = classId;
        this.className = className;
        this.numberOfPeople = numberOfPeople;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.courseId = courseId;
    }


    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


}
