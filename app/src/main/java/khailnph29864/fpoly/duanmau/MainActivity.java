package khailnph29864.fpoly.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import khailnph29864.fpoly.duanmau.Fragment.DoanhThuFragment;
import khailnph29864.fpoly.duanmau.Fragment.DoiMKFragment;
import khailnph29864.fpoly.duanmau.Fragment.QuanLyLoaiSachFragment;
import khailnph29864.fpoly.duanmau.Fragment.QuanLyPhieuMuonFragment;
import khailnph29864.fpoly.duanmau.Fragment.QuanLySachFragment;
import khailnph29864.fpoly.duanmau.Fragment.QuanLyThanhVienFragment;
import khailnph29864.fpoly.duanmau.Fragment.TaoTKFragment;
import khailnph29864.fpoly.duanmau.Fragment.Top10Fragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawerLayout;
private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,0,0);
        drawerToggle.syncState();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContent, QuanLyPhieuMuonFragment.newInstance());


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.phieuMuon:
                replaceFrag(QuanLyPhieuMuonFragment.newInstance());
                break;
            case R.id.doanhThu:
                replaceFrag(DoanhThuFragment.newInstance());
                break;
            case R.id.loaiSach:
                replaceFrag(QuanLyLoaiSachFragment.newInstance());
                break;
            case R.id.thanhVien:
                replaceFrag(QuanLyThanhVienFragment.newInstance());
                break;
            case R.id.top10:
                replaceFrag(Top10Fragment.newInstance());
                break;
            case R.id.ttk:
                replaceFrag(TaoTKFragment.newInstance());
                break;
            case R.id.sach:
                replaceFrag(QuanLySachFragment.newInstance());
                break;
            case R.id.dmk:
                replaceFrag(DoiMKFragment.newInstance());
                break;
            case R.id.exit:
                finish();
                break;
        }
        return false;
    }
public void replaceFrag(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContent,fragment);
        transaction.commit();
        drawerLayout.close();
}
    @Override
    public void onBackPressed() {
        if(drawerLayout.isOpen()==true){
            drawerLayout.close();
        }else{
            super.onBackPressed();
        }

    }
}