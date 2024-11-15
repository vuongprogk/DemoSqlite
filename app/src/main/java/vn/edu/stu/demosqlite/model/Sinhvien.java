package vn.edu.stu.demosqlite.model;

import java.io.Serializable;

public class Sinhvien implements Serializable {
    private int ma;
    private String ten;
    private String lop;

    public Sinhvien(int ma, String ten, String lop) {
        this.ma = ma;
        this.ten = ten;
        this.lop = lop;
    }

    public Sinhvien() {
    }

    public Sinhvien(String ten, String lop) {
        this.ten = ten;
        this.lop = lop;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    @Override
    public String toString() {
        return ma + " - " + ten + " - " + lop;
    }

}
