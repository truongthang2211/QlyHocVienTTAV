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
public class Personal_Info {
    String id, fullName, sex,dateOfBirth, nationality,address,email,phoneNumber;
    public Personal_Info( String fullName, String sex, String dateOfBirth, String nationality, String address, String email, String phoneNumber) {
        this.fullName = fullName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Personal_Info() {
    }

    public Personal_Info(String fullName) {
        this.fullName = fullName;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
      public boolean checkContain(String str){
//          id.toUpperCase().contains(str.toUpperCase()) == true 
//                ||
        if (
            fullName.toUpperCase().contains(str.toUpperCase())==true ||
                sex.toUpperCase().contains(str.toUpperCase())==true ||
                dateOfBirth.toUpperCase().contains(str.toUpperCase())==true ||
                nationality.toUpperCase().contains(str.toUpperCase())==true ||
                address.toUpperCase().contains(str.toUpperCase())==true ||
                email.toUpperCase().contains(str.toUpperCase())==true ||
                phoneNumber.toUpperCase().contains(str.toUpperCase())==true

        ){
   
            
            return true;
        }
        else{
            return false;
        }
    }
 
}
