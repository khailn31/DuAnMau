package khailnph29864.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khailnph29864.fpoly.duanmau.Database.DbHelper;
import khailnph29864.fpoly.duanmau.Model.ThanhVien;
import khailnph29864.fpoly.duanmau.Model.ThuThu;

public class ThuThuDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;

    public ThuThuDAO(Context context) {
        DbHelper helper = new DbHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public long insert(ThuThu ob) {
        ContentValues values = new ContentValues();
        values.put("name_tt", ob.getName_tt());
        values.put("pass_tt", ob.getPass_tt());
        return sqLiteDatabase.insert("tbl_tt", null, values);
    }

    public int update(ThuThu ob) {
        ContentValues values = new ContentValues();
        values.put("name_tt", ob.getName_tt());
        values.put("pass_tt", ob.getPass_tt());
        return sqLiteDatabase.update("tbl_tt", values, "id_tt=?", new String[]{String.valueOf(ob.getId_tt())});
    }

    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_tt", "id_tt=?", new String[]{String.valueOf(ID)});
    }

    public ArrayList<ThuThu> getData(String sql, String... SelectAvgs) {
        ArrayList<ThuThu> lst = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            ThuThu ob = new ThuThu();
            ob.setId_tt(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_tt"))));
            ob.setName_tt(cursor.getString(cursor.getColumnIndex("name_tt")));
            ob.setPass_tt(cursor.getString(cursor.getColumnIndex("pass_tt")));
            lst.add(ob);
        }
        return lst;
    }

    public ArrayList<ThuThu> getAllData() {
        String sql = "SELECT * FROM tbl_tt";
        return getData(sql);
    }

    public ThuThu getByID(String id) {
        String sql = "SELECT * FROM tbl_tt  where name_tt=?";
        return getData(sql, id).get(0);
    }
    public int checkLogin(String id,String password){
        String sql="SELECT * FROM tbl_tt WHERE name_tt=? AND pass_tt=?";
        ArrayList<ThuThu> list=getData(sql,id,password);
        if(list.size()==0){
            return -1;
        }
        return 1;
    }
}
