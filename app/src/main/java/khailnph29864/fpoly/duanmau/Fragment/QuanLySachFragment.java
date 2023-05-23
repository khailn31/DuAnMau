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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import khailnph29864.fpoly.duanmau.Adapter.SachAdapter;
import khailnph29864.fpoly.duanmau.Adapter.TvAdapter;
import khailnph29864.fpoly.duanmau.DAO.LoaiSachDAO;
import khailnph29864.fpoly.duanmau.DAO.SachDAO;
import khailnph29864.fpoly.duanmau.DAO.ThanhVienDAO;
import khailnph29864.fpoly.duanmau.Model.LoaiSach;
import khailnph29864.fpoly.duanmau.Model.Sach;
import khailnph29864.fpoly.duanmau.Model.ThanhVien;
import khailnph29864.fpoly.duanmau.R;


public class QuanLySachFragment extends Fragment {
    RecyclerView recy;
    SachDAO dao;
    ArrayList<Sach> list;
    LoaiSachDAO lsdao;
    ArrayList<LoaiSach> list_ls;
    SachAdapter adapter;

    FloatingActionButton btn;

    public QuanLySachFragment() {
        // Required empty public constructor
    }


    public static QuanLySachFragment newInstance() {
        QuanLySachFragment fragment = new QuanLySachFragment();

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
        return inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recy=view.findViewById(R.id.recy_qls);
        btn=view.findViewById(R.id.flAdd_S);
        dao=new SachDAO(getContext());
        list=dao.getAllData();
        lsdao=new LoaiSachDAO(getContext());
        list_ls=lsdao.getAllData();


        adapter=new SachAdapter(list,getContext());
        RecyclerView rc=view.findViewById(R.id.recy_qls);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc.setLayoutManager(linearLayoutManager);
        recy.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.layout_dialog_add_s);
                TextInputEditText ed1,ed2;
                Spinner sp;
                Button btn1,btn2;
                sp=dialog.findViewById(R.id.sp_add_s_ls);
                ed1=dialog.findViewById(R.id.ed_add_s_name);
                ed2=dialog.findViewById(R.id.ed_add_s_price);
                btn1=dialog.findViewById(R.id.btnSave_add_s);
                btn2=dialog.findViewById(R.id.btnCacel_add_s);
                 Sach sach=new Sach();
                ArrayAdapter<String> adapter_ls = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, lsdao.name());
                sp.setAdapter(adapter_ls);

                sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        list_ls=lsdao.getAllData();
                        sach.setId_loaisach(list_ls.get(position).getId_loaisach());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sach.setName_sach(ed1.getText().toString());
                        sach.setPrice_sach(Integer.parseInt(ed2.getText().toString()));
                        if(dao.insert(sach)>0){
                            Toast.makeText(getContext(), "Thêm  thành công", Toast.LENGTH_SHORT).show();
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