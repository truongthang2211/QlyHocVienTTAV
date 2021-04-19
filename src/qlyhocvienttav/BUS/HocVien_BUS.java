/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.BUS;

import qlyhocvienttav.Model.DAL.HocVien_DAL;
import qlyhocvienttav.Model.DTO.HocVien;

/**
 *
 * @author Thang Nguyen Anh
 */
public class HocVien_BUS {
    
    private HocVien_DAL hv_dal = new HocVien_DAL();
    
    public boolean Insert(HocVien hv){
        return hv_dal.Insert(hv);
    }
}
