package khailnph29864.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khailnph29864.fpoly.duanmau.Database.DbHelper;
import khailnph29864.fpoly.duanmau.Model.LoaiSach;
import khailnph29864.fpoly.duanmau.Model.ThuThu;

public class LoaiSachDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;

    public LoaiSachDAO(Context context) {
        DbHelper helper = new DbHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public long insert(LoaiSach ob) {
        ContentValues values = new ContentValues();
        values.put("name_loaisach", ob.getName_loaisach());

        return sqLiteDatabase.insert("tbl_loaisach", null, values);
    }

    public int update(LoaiSach ob) {
        ContentValues values = new ContentValues();
        values.put("name_loaisach", ob.getName_loaisach());
        return sqLiteDatabase.update("tbl_loaisach", values, "id_loaisach=?", new String[]{String.valueOf(ob.getId_loaisach())});
    }

    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_loaisach", "id_loaisach=?", new String[]{String.valueOf(ID)});
    }

    public ArrayList<LoaiSach> getData(String sql, String... SelectAvgs) {
        ArrayList<LoaiSach> lst = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            LoaiSach ob = new LoaiSach();
            ob.setId_loaisach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_loaisach"))));
            ob.setName_loaisach(cursor.getString(cursor.getColumnIndex("name_loaisach")));
            lst.add(ob);
        }
        return lst;
    }

    public ArrayList<LoaiSach> getAllData() {
        String sql = "SELECT * FROM tbl_loaisach";
        return getData(sql);
    }

    public LoaiSach getByID(String id) {
        String sql = "SELECT * FROM tbl_loaisach  where id_loaisach=?";
        return getData(sql, id).get(0);
    }
    public ArrayList<String> getName(String sql,String...SelectAvgs){
        ArrayList<String> lst=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,SelectAvgs);
        while (cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex("name_loaisach"));
            lst.add(name);
        }
        return lst;

    }
    public ArrayList<String> name(){
        String name="SELECT name_loaisach FROM tbl_loaisach";
        return getName(name);
    }
}
