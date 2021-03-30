/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlyhocvienttav.Model;

/**
 *
 * @author Thang
 */
public class HocVien {
    private String Ho,Ten,QuocTich;

    public HocVien(String Ho, String Ten, String QuocTich) {
        this.Ho = Ho;
        this.Ten = Ten;
        this.QuocTich = QuocTich;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String Ho) {
        this.Ho = Ho;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getQuocTich() {
        return QuocTich;
    }

    public void setQuocTich(String QuocTich) {
        this.QuocTich = QuocTich;
    }
}
