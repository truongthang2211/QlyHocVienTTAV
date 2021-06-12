/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Model.DTO;

/**
 *
 * @author nvtru
 */
public class Manager extends Personal_Info {
    String manager_ID;
    public Manager(String manager_ID,String fullName, String sex, String dateOfBirth, String nationality, String address, String email, String phoneNumber)
    {
        super(fullName, sex, dateOfBirth, nationality, address, email, phoneNumber);
        this.manager_ID=manager_ID;
    }

    public String getManager_ID() {
        return manager_ID;
    }

    public void setManager_ID(String manager_ID) {
        this.manager_ID = manager_ID;
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
                phoneNumber.toUpperCase().contains(str.toUpperCase())==true

        ){
   
            
            return true;
        }
        else{
            return false;
        }
    }
    
    
}
