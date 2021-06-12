package qlyhocvienttav.Model.DTO;

public class Class {
    String classId;
    String className;
    int numberOfPeople;
    int maxNumberOfPeople;
    double BasicGrade;
    String courseId;

    public Class(String classId, String className, int numberOfPeople, int maxNumberOfPeople, String courseId, double BasicGrade) {
        this.classId = classId;
        this.className = className;
        this.numberOfPeople = numberOfPeople;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.BasicGrade = BasicGrade;
        this.courseId = courseId;
    }

    public double getBasicGrade() {
        return BasicGrade;
    }

    public void setBasicGrade(double BasicGrade) {
        this.BasicGrade = BasicGrade;
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
    
    
    public boolean checkContain(String str){
//          id.toUpperCase().contains(str.toUpperCase()) == true 
//                ||


        if (
            classId.toUpperCase().contains(str.toUpperCase())==true ||
                className.toUpperCase().contains(str.toUpperCase())==true ||
                String.valueOf(numberOfPeople).toUpperCase().contains(str.toUpperCase())==true ||
                String.valueOf(maxNumberOfPeople).toUpperCase().contains(str.toUpperCase())==true ||
                String.valueOf(BasicGrade).toUpperCase().contains(str.toUpperCase())==true ||
                courseId.toUpperCase().contains(str.toUpperCase())==true

        ){
   
            
            return true;
        }
        else{
            return false;
        }
    }

}
