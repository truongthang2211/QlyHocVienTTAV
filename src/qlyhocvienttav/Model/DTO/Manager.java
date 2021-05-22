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
    Manager(String manager_ID,String fullName, String sex, String dateOfBirth, String nationality, String address, String email, String phoneNumber)
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
    
    
}
