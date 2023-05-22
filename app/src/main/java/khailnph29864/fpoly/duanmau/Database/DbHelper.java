package khailnph29864.fpoly.duanmau.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "DuAnMau";
    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static final String Table_thuthu= "CREATE TABLE " +
            "tbl_tt(" +
            "id_tt INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name_tt TEXT NOT NULL," +
            "pass_tt TEXT NOT NULL)";
    public static final String insert_tt="Insert into tbl_tt(name_tt,pass_tt) values" +
            "('khai','123')";
    public static final String Table_thanhvien="CREATE TABLE " +
            "tbl_tv(" +
            "id_tv INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name_tv TEXT NOT NULL," +
            "year_tv TEXT NOT NULL)";
    public static final String insert_tv="Insert into tbl_tv(name_tv,year_tv) values" +
            "('le ngoc khai','2001')";

    public static final String Table_loaisach="CREATE TABLE " +
            "tbl_loaisach(" +
            "id_loaisach INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name_loaisach TEXT NOT NULL)";
    public static final String Table_sach="CREATE TABLE " +
            "tbl_sach(" +
            "id_sach INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name_sach TEXT NOT NULL," +
            "price_sach INTEGER NOT NULL," +
            "id_loaisach INTEGER," +
            "FOREIGN KEY (id_loaisach) REFERENCES tbl_loaisach (id_loaisach))";
    public static final String Table_phieumuon="CREATE TABLE " +
            "tbl_pm(" +
            "id_pm INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_tv INTEGER," +
            "id_sach INTEGER," +
            "id_tt INTEGER," +
            "date TEXT NOT NULL," +
            "price INTEGER NOT NULL," +
            "status INTEGER NOT NULL," +
            "FOREIGN KEY (id_tv) REFERENCES tbl_tv (id_tv)," +
            "FOREIGN KEY (id_sach) REFERENCES tbl_sach (id_sach)," +
            "FOREIGN KEY (id_tt) REFERENCES tbl_tt (id_tt)) ";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Table_thuthu);
        db.execSQL(Table_thanhvien);
        db.execSQL(Table_loaisach);
        db.execSQL(Table_sach);
        db.execSQL(Table_phieumuon);

        db.execSQL(insert_tt);
        db.execSQL(insert_tv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_tt");
        db.execSQL("DROP TABLE IF EXISTS tbl_tv");
        db.execSQL("DROP TABLE IF EXISTS tbl_loaisach");
        db.execSQL("DROP TABLE IF EXISTS tbl_sach");
        db.execSQL("DROP TABLE IF EXISTS tbl_pm");
        onCreate(db);
    }
}
