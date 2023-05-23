package khailnph29864.fpoly.duanmau.Fragment;

import android.app.DatePickerDialog;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import khailnph29864.fpoly.duanmau.Adapter.PMAdapter;
import khailnph29864.fpoly.duanmau.Adapter.SachAdapter;
import khailnph29864.fpoly.duanmau.Adapter.TvAdapter;
import khailnph29864.fpoly.duanmau.DAO.LoaiSachDAO;
import khailnph29864.fpoly.duanmau.DAO.PhieuMuonDAO;
import khailnph29864.fpoly.duanmau.DAO.SachDAO;
import khailnph29864.fpoly.duanmau.DAO.ThanhVienDAO;
import khailnph29864.fpoly.duanmau.Model.LoaiSach;
import khailnph29864.fpoly.duanmau.Model.PhieuMuon;
import khailnph29864.fpoly.duanmau.Model.Sach;
import khailnph29864.fpoly.duanmau.Model.ThanhVien;
import khailnph29864.fpoly.duanmau.R;


public class QuanLyPhieuMuonFragment extends Fragment {
    RecyclerView recy;
    PhieuMuonDAO dao;
    ArrayList<PhieuMuon> list;
    PMAdapter adapter;
    SachDAO sachDAO;
    ArrayList<Sach> list_sach;
//    SachAdapter adapter_sach;
    ThanhVienDAO thanhVienDAO;
    ArrayList<ThanhVien> list_tv;
//    TvAdapter adapter_tv;
private final Calendar myCalendar = Calendar.getInstance();
    FloatingActionButton btn;


    public QuanLyPhieuMuonFragment() {
        // Required empty public constructor
    }


    public static QuanLyPhieuMuonFragment newInstance() {
        QuanLyPhieuMuonFragment fragment = new QuanLyPhieuMuonFragment();

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
        return inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recy=view.findViewById(R.id.recy_qlpm);
        btn=view.findViewById(R.id.flAdd_pm);
        dao=new PhieuMuonDAO(getContext());
        list=dao.getAllData();
        sachDAO=new SachDAO(getContext());
        list_sach=sachDAO.getAllData();
        thanhVienDAO=new ThanhVienDAO(getContext());
        list_tv=thanhVienDAO.getAllData();

        adapter=new PMAdapter(list,getContext());
        RecyclerView rc=view.findViewById(R.id.recy_qlpm);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rc.setLayoutManager(linearLayoutManager);
        recy.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(getContext());
                dialog.setContentView(R.layout.layout_dialog_add_pm);

                TextView tv1,tv2;
                Spinner sp1,sp2;
                CheckBox ckb ;
                Button btn1,btn2,btn3;
                sp1=dialog.findViewById(R.id.sp_add_tv_pm);
                tv2=dialog.findViewById(R.id.tv_add_s_price_pm);
                sp2=dialog.findViewById(R.id.sp_add_s_pm);
                btn1=dialog.findViewById(R.id.btnSave_add_pm);
                btn2=dialog.findViewById(R.id.btnCacel_add_pm);
                tv1=dialog.findViewById(R.id.tv_add_date_pm);
                btn3=dialog.findViewById(R.id.btn_date_picker);
                ckb=dialog.findViewById(R.id.ckb_status);
                PhieuMuon phieuMuon=new PhieuMuon();
                ArrayAdapter<String> adapter_s = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, sachDAO.name());
                sp2.setAdapter(adapter_s);

                sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        list_sach=sachDAO.getAllData();
                        phieuMuon.setId_sach(list_sach.get(position).getId_sach());
                        phieuMuon.setPrice(list_sach.get(position).getPrice_sach());
                        tv2.setText(String.valueOf(list_sach.get(position).getPrice_sach()));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                ArrayAdapter<String> adapter_tv = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, thanhVienDAO.name());
                sp1.setAdapter(adapter_tv);

                sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        list_tv=thanhVienDAO.getAllData();
                        phieuMuon.setId_tv(list_tv.get(position).getId_tv());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                btn3.setOnClickListener(view -> {
                    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                            myCalendar.set(Calendar.YEAR, year);
                            myCalendar.set(Calendar.MONTH, monthOfYear);
                            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            tv1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            Date selectedDate = myCalendar.getTime();
                            SimpleDateFormat dateFormatter = new SimpleDateFormat(
                                    "yyyy-MM-dd");
                            phieuMuon.setDate(dateFormatter.format(selectedDate));
                        }
                    };
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                });

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ckb.isChecked()){
                            phieuMuon.setStatus(1);
                        }else{
                            phieuMuon.setStatus(0);
                        }
                        if(dao.insert(phieuMuon)>0){
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
                       dialog.dismiss();


                    }
                });
                dialog.show();
            }
        });
    }
}