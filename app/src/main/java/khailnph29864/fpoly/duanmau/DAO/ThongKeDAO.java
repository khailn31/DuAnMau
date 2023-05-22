package khailnph29864.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khailnph29864.fpoly.duanmau.Database.DbHelper;
import khailnph29864.fpoly.duanmau.Model.Sach;
import khailnph29864.fpoly.duanmau.Model.ThanhVien;
import khailnph29864.fpoly.duanmau.Model.Top;

public class ThongKeDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;

    public ThongKeDAO(Context context) {
        DbHelper helper = new DbHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }
    public ArrayList<Top> getTop(){
        String sqlTop="SELECT id_sach, cout(id_sach) as soLuong FROM tbl_pm GROUP BY id_sach " +
                "ORDER BY soLuong DESC LIMIT 10";
        ArrayList<Top> lst=new ArrayList<Top>();
        SachDAO sachDAO=new SachDAO(context);
        Cursor c=sqLiteDatabase.rawQuery(sqlTop,null);
        while (c.moveToNext()){
            Top top=new Top();
            Sach sach=sachDAO.getByID(c.getString(c.getColumnIndex("id_sach")));
            top.setTenSach(sach.getName_sach());
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            lst.add(top);
        }
        return lst;
    }
    public int getDoanhThu(String tuNgay,String denNgay){
        String sql="SELECT SUM(tienThue) as doanhThu FROM tbl_pm WHERE ngay BETWEEN ? AND?";
        ArrayList<Integer> list=new ArrayList<>();
        Cursor c=sqLiteDatabase.rawQuery(sql,new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }


}
