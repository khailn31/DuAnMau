package khailnph29864.fpoly.duanmau.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import khailnph29864.fpoly.duanmau.Adapter.TvAdapter;
import khailnph29864.fpoly.duanmau.DAO.ThanhVienDAO;
import khailnph29864.fpoly.duanmau.Model.ThanhVien;
import khailnph29864.fpoly.duanmau.R;


public class QuanLyThanhVienFragment extends Fragment {
    RecyclerView recy;
    ThanhVienDAO dao;
    ArrayList<ThanhVien> list;
    TvAdapter adapter;
    FloatingActionButton btn;



    public QuanLyThanhVienFragment() {
        // Required empty public constructor
    }


    public static QuanLyThanhVienFragment newInstance() {
        QuanLyThanhVienFragment fragment = new QuanLyThanhVienFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_thanh_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recy=view.findViewById(R.id.recy_qltv);
        btn=view.findViewById(R.id.flAdd_tv);
        dao=new ThanhVienDAO(getContext());
        list=dao.getAllData();
        adapter=new TvAdapter(list,getContext());
        RecyclerView rc=view.findViewById(R.id.recy_qltv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc.setLayoutManager(linearLayoutManager);
        recy.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.layout_dialog_add_tv);
                TextInputEditText ed1,ed2;
                Button btn1,btn2;
                ed1=dialog.findViewById(R.id.ed_add_tv_name);
                ed2=dialog.findViewById(R.id.ed_add_tv_year);
                btn1=dialog.findViewById(R.id.btnSave_add_tv);
                btn2=dialog.findViewById(R.id.btnCacel_add_tv);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ThanhVien thanhVien=new ThanhVien();
                        thanhVien.setName_tv(ed1.getText().toString());
                        thanhVien.setYear_tv(ed2.getText().toString());
                        if(dao.insert(thanhVien)>0){
                            Toast.makeText(getContext(), "Thêm thành viên thành công", Toast.LENGTH_SHORT).show();
                            list=dao.getAllData();
                            adapter.setData(list);
                        }else{
                            Toast.makeText(getContext(), "Them that bai", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ed1.setText("");
                        ed2.setText("");

                    }
                });
                dialog.show();
            }
        });
    }
}