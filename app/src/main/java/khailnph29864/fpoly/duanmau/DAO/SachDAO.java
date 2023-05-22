package khailnph29864.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khailnph29864.fpoly.duanmau.Database.DbHelper;
import khailnph29864.fpoly.duanmau.Model.LoaiSach;
import khailnph29864.fpoly.duanmau.Model.Sach;

public class SachDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;

    public SachDAO(Context context) {
        DbHelper helper = new DbHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public long insert(Sach ob) {
        ContentValues values = new ContentValues();
        values.put("name_sach", ob.getName_sach());
        values.put("price_sach",ob.getPrice_sach());
        values.put("id_loaisach",ob.getId_loaisach());

        return sqLiteDatabase.insert("tbl_sach", null, values);
    }

    public int update(Sach ob) {
        ContentValues values = new ContentValues();
        values.put("name_sach", ob.getName_sach());
        values.put("price_sach",ob.getPrice_sach());
        values.put("id_loaisach",ob.getId_loaisach());
        return sqLiteDatabase.update("tbl_sach", values, "id_sach=?", new String[]{String.valueOf(ob.getId_sach())});
    }

    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_sach", "id_sach=?", new String[]{String.valueOf(ID)});
    }

    public ArrayList<Sach> getData(String sql, String... SelectAvgs) {
        ArrayList<Sach> lst = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            Sach ob = new Sach();
            ob.setId_sach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_sach"))));
            ob.setPrice_sach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("price_sach"))));
            ob.setId_loaisach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_loaisach"))));
            ob.setName_sach(cursor.getString(cursor.getColumnIndex("name_lsach")));
            lst.add(ob);
        }
        return lst;
    }

    public ArrayList<Sach> getAllData() {
        String sql = "SELECT * FROM tbl_sach";
        return getData(sql);
    }

    public Sach getByID(String id) {
        String sql = "SELECT * FROM tbl_sach  where id_sach=?";
        return getData(sql, id).get(0);
    }
}
