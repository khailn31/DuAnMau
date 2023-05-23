package khailnph29864.fpoly.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khailnph29864.fpoly.duanmau.Database.DbHelper;
import khailnph29864.fpoly.duanmau.Model.ThanhVien;

public class ThanhVienDAO {
    private final SQLiteDatabase sqLiteDatabase;
    private Context context;

    public ThanhVienDAO(Context context) {
        DbHelper helper = new DbHelper(context);
        sqLiteDatabase = helper.getWritableDatabase();
    }

    public long insert(ThanhVien ob) {
        ContentValues values = new ContentValues();
        values.put("name_tv", ob.getName_tv());
        values.put("year_tv", ob.getYear_tv());
        return sqLiteDatabase.insert("tbl_tv", null, values);
    }

    public int update(ThanhVien ob) {
        ContentValues values = new ContentValues();
        values.put("name_tv", ob.getName_tv());
        values.put("year_tv", ob.getYear_tv());
        return sqLiteDatabase.update("tbl_tv", values, "id_tv=?", new String[]{String.valueOf(ob.getId_tv())});
    }

    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_tv", "id_tv=?", new String[]{String.valueOf(ID)});
    }

    public ArrayList<ThanhVien> getData(String sql, String... SelectAvgs) {
        ArrayList<ThanhVien> lst = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            ThanhVien ob = new ThanhVien();
            ob.setId_tv(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_tv"))));
            ob.setName_tv(cursor.getString(cursor.getColumnIndex("name_tv")));
            ob.setYear_tv(cursor.getString(cursor.getColumnIndex("year_tv")));
            lst.add(ob);
        }
        return lst;
    }

    public ArrayList<ThanhVien> getAllData() {
        String sql = "SELECT * FROM tbl_tv";
        return getData(sql);
    }

    public ThanhVien getByID(String id) {
        String sql = "SELECT * FROM tbl_tv  where id_tv=?";
        return getData(sql, id).get(0);
    }
    public ArrayList<String> getName(String sql,String...SelectAvgs){
        ArrayList<String> lst=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,SelectAvgs);
        while (cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndex("name_tv"));
            lst.add(name);
        }
        return lst;

    }
    public ArrayList<String> name(){
        String name="SELECT name_tv FROM tbl_tv";
        return getName(name);
    }
}
