package khailnph29864.fpoly.duanmau.Model;

import java.util.Date;

public class PhieuMuon {
    private int id_pm;
    private int id_tv;
    private int id_sach;
    private int id_tt;
    private String date;
    private int price;
    private int status;

    public int getId_pm() {
        return id_pm;
    }

    public void setId_pm(int id_pm) {
        this.id_pm = id_pm;
    }

    public int getId_tv() {
        return id_tv;
    }

    public void setId_tv(int id_tv) {
        this.id_tv = id_tv;
    }

    public int getId_sach() {
        return id_sach;
    }

    public void setId_sach(int id_sach) {
        this.id_sach = id_sach;
    }

    public int getId_tt() {
        return id_tt;
    }

    public void setId_tt(int id_tt) {
        this.id_tt = id_tt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
