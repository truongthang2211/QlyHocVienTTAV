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
public class Analysis {
    String CourseName,CourseID;
    double Revenue;
    public double jan,feb,march,april,may,jun,july,aug,sep,oc,no,dec;
    public String getCourseName() {
        return CourseName;
    }

    public Analysis(double jan, double feb, double march, double april, double may, double jun, double july, double aug, double sep, double oc, double no, double dec) {
        this.jan = jan;
        this.feb = feb;
        this.march = march;
        this.april = april;
        this.may = may;
        this.jun = jun;
        this.july = july;
        this.aug = aug;
        this.sep = sep;
        this.oc = oc;
        this.no = no;
        this.dec = dec;
    }
    
    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String CourseID) {
        this.CourseID = CourseID;
    }

    public double getRevenue() {
        return Revenue;
    }

    public void setRevenue(double Revenue) {
        this.Revenue = Revenue;
    }

  

    public Analysis(String CourseID, String CourseName, double Revenue) {
        this.CourseName = CourseName;
        this.CourseID = CourseID;
        this.Revenue = Revenue;
    }




    
}
