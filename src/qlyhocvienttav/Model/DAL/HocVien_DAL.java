/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Model.DAL;

import qlyhocvienttav.Model.DTO.HocVien;

/**
 *
 * @author Thang Nguyen Anh
 */
public class HocVien_DAL extends DBConnection{
    public HocVien_DAL(){
    };
    
    
    public boolean Insert(HocVien hv){
        OpenConnection();
        String sql = "INSERT INTO HocVien (FULLNAME, EMAIL, GHICHU) VALUES ("+ hv.getHoTen()+ ","+ hv.getEmail()+","+ hv.getGhichu()+")";
        return true;
    }
}
