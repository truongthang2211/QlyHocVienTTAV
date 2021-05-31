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
public class Account extends Personal_Info{
    String username,password,acctype,owner,create_date;

    public Account() {
    }

    public Account(String username, String password, String acctype, String owner, String create_date) {
        this.username = username;
        this.password = password;
        this.acctype = acctype;
        this.owner = owner;
        this.create_date = create_date;
    }

    public Account(String username, String password, String acctype, String owner,String create_date ,String fullName, String sex, String dateOfBirth, String nationality, String address, String email, String phoneNumber) {
        super(fullName, sex, dateOfBirth, nationality, address, email, phoneNumber);
        this.username = username;
        this.password = password;
        this.acctype = acctype;
        this.owner = owner;
        this.create_date = create_date;
    }
    public Account(String username, String password, String acctype, String owner ,String fullName, String sex, String dateOfBirth, String nationality, String address, String email, String phoneNumber) {
        super(fullName, sex, dateOfBirth, nationality, address, email, phoneNumber);
        this.username = username;
        this.password = password;
        this.acctype = acctype;
        this.owner = owner;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAcctype() {
        return acctype;
    }

    public void setAcctype(String acctype) {
        this.acctype = acctype;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
    
}
