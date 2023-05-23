package khailnph29864.fpoly.duanmau.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khailnph29864.fpoly.duanmau.Database.DbHelper;
import khailnph29864.fpoly.duanmau.Model.PhieuMuon;
import khailnph29864.fpoly.duanmau.Model.ThanhVien;

public class PhieuMuonDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;

    public PhieuMuonDAO(Context context) {
        DbHelper helper = new DbHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public long insert(PhieuMuon ob) {
        ContentValues values = new ContentValues();
        values.put("id_tv",ob.getId_tv());
        values.put("id_sach",ob.getId_sach());
//        values.put("id_tt",ob.getId_tt());
        values.put("date",ob.getDate());
        values.put("price",ob.getPrice());
        values.put("status",ob.getStatus());
        return sqLiteDatabase.insert("tbl_pm", null, values);
    }

    public int update(PhieuMuon ob) {
        ContentValues values = new ContentValues();
        values.put("id_tv",ob.getId_tv());
        values.put("id_sach",ob.getId_sach());
//        values.put("id_tt",ob.getId_tt());
        values.put("date",ob.getDate());
        values.put("price",ob.getPrice());
        values.put("status",ob.getStatus());
        return sqLiteDatabase.update("tbl_pm", values, "id_pm=?", new String[]{String.valueOf(ob.getId_pm())});
    }

    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_pm", "id_pm=?", new String[]{String.valueOf(ID)});
    }

    @SuppressLint("Range")
    public ArrayList<PhieuMuon> getData(String sql, String... SelectAvgs) {
        ArrayList<PhieuMuon> lst = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            PhieuMuon ob = new PhieuMuon();
            ob.setId_pm(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_pm"))));
            ob.setId_tv(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_tv"))));
            ob.setId_sach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_sach"))));
//            ob.setId_tt(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_tt"))));
            ob.setDate(cursor.getString(cursor.getColumnIndex("date")));
            ob.setStatus(Integer.parseInt(cursor.getString(cursor.getColumnIndex("status"))));
            lst.add(ob);
        }
        return lst;
    }

    public ArrayList<PhieuMuon> getAllData() {
        String sql = "SELECT * FROM tbl_pm";
        return getData(sql);
    }

    public PhieuMuon getByID(String id) {
        String sql = "SELECT * FROM tbl_pm  where id_pm=?";
        return getData(sql, id).get(0);
    }
}
