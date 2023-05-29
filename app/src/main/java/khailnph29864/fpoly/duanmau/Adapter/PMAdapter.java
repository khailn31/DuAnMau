package khailnph29864.fpoly.duanmau.Adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import khailnph29864.fpoly.duanmau.DAO.LoaiSachDAO;
import khailnph29864.fpoly.duanmau.DAO.PhieuMuonDAO;
import khailnph29864.fpoly.duanmau.DAO.SachDAO;
import khailnph29864.fpoly.duanmau.DAO.ThanhVienDAO;
import khailnph29864.fpoly.duanmau.Model.LoaiSach;
import khailnph29864.fpoly.duanmau.Model.PhieuMuon;
import khailnph29864.fpoly.duanmau.Model.Sach;
import khailnph29864.fpoly.duanmau.Model.ThanhVien;
import khailnph29864.fpoly.duanmau.R;

public class PMAdapter extends RecyclerView.Adapter<PMAdapter.DbVH> {
    private ArrayList<PhieuMuon> list;
    private PhieuMuonDAO dao;
    private SachDAO sachDAO;
    private ThanhVienDAO thanhVienDAO;
    private Context context;
    private final Calendar myCalendar = Calendar.getInstance();


    public PMAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new PhieuMuonDAO(context);
        sachDAO = new SachDAO(context);
        thanhVienDAO = new ThanhVienDAO(context);

    }

    @NonNull
    @Override
    public DbVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_qlpm, parent, false);

        return new DbVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DbVH holder, int position) {
        PhieuMuon phieuMuon = list.get(position);
        holder.tv1.setText(String.valueOf(position + 1 + "."));
        ThanhVien thanhVien = thanhVienDAO.getByID(String.valueOf(phieuMuon.getId_tv()));
        holder.tv2.setText(thanhVien.getName_tv().toString());
        Sach sach = sachDAO.getByID(String.valueOf(phieuMuon.getId_sach()));
        holder.tv3.setText(sach.getName_sach());
        if (phieuMuon.getStatus() == 1) {
            holder.tv4.setText("Đã Trả Sách");
        } else {
            holder.tv4.setText("Chưa trả");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfor(list.get(position));
            }
        });
        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Del(phieuMuon.getId_pm());
            }
        });
        holder.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(list.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    class DbVH extends RecyclerView.ViewHolder {
        private TextView tv1, tv2, tv3, tv4;
        private ImageView btndel, btnUp;

        public DbVH(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv_id_pm);
            tv2 = itemView.findViewById(R.id.tv_name_tv_pm);
            tv3 = itemView.findViewById(R.id.tv_name_s_pm);
            tv4 = itemView.findViewById(R.id.tv_status_pm);
            btndel = itemView.findViewById(R.id.del_s);
            btnUp = itemView.findViewById(R.id.update_s);
        }
    }

    public void setData(ArrayList<PhieuMuon> lst) {
        this.list = lst;
        notifyDataSetChanged();
    }

    private void showInfor(PhieuMuon phieuMuon) {
        Sach sach = sachDAO.getByID(String.valueOf(phieuMuon.getId_sach()));
        ThanhVien thanhVien = thanhVienDAO.getByID(String.valueOf(phieuMuon.getId_tv()));
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog_infor_pm);
        TextView tv1, tv2, tv3, tv4, tv5, tv6;
        Button btn1;
        tv1 = dialog.findViewById(R.id.tv_infor_id_pm);
        tv1.setText(phieuMuon.getId_pm() + "");
        tv2 = dialog.findViewById(R.id.tv_infor_name_tv_pm);
        tv2.setText(thanhVien.getName_tv());
        tv3 = dialog.findViewById(R.id.tv_infor_name_s_pm);
        tv3.setText(sach.getName_sach());
        tv4 = dialog.findViewById(R.id.tv_infor_price_s_pm);
        tv4.setText(sach.getPrice_sach() + "");
        tv5 = dialog.findViewById(R.id.tv_infor_date_pm);
        tv5.setText(phieuMuon.getDate());
        tv6 = dialog.findViewById(R.id.tv_infor_status_pm);
        if (phieuMuon.getStatus() == 0) {
            tv6.setText("Chưa trả");
        } else {
            tv6.setText("Đã trả");
        }

        btn1 = dialog.findViewById(R.id.btn_infor_close_pm);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void Del(int id) {
        AlertDialog.Builder dialogDL = new AlertDialog.Builder(context);
        dialogDL.setMessage("ban co muon xoa khong");
        dialogDL.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogDL.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dao.delete(id) > 0) {
                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    list = dao.getAllData();
                    setData(list);
                } else {
                    Toast.makeText(context, "Xoa k thanh cong", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();

            }
        });
        dialogDL.show();
    }

    private void update(PhieuMuon phieuMuon, int id)  {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog_add_pm);
        TextView tv1, tv2;
        Spinner sp1, sp2;
        CheckBox ckb;
        Button btn1, btn2, btn3;

        tv2 = dialog.findViewById(R.id.tv_add_s_price_pm);
        tv2.setText(list.get(id).getPrice() + "");
        tv1 = dialog.findViewById(R.id.tv_add_date_pm);
        tv1.setText(list.get(id).getDate());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            myCalendar.setTime(sdf.parse(phieuMuon.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sp1 = dialog.findViewById(R.id.sp_add_tv_pm);
        sp2 = dialog.findViewById(R.id.sp_add_s_pm);
        btn1 = dialog.findViewById(R.id.btnSave_add_pm);
        btn2 = dialog.findViewById(R.id.btnCacel_add_pm);
        btn3 = dialog.findViewById(R.id.btn_date_picker);
        ckb = dialog.findViewById(R.id.ckb_status);
        if (list.get(id).getStatus() == 1) {
            ckb.setChecked(true);
        } else {
            ckb.setChecked(false);
        }
        ArrayList<ThanhVien> list_tv = thanhVienDAO.getAllData();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, thanhVienDAO.name());
        sp1.setAdapter(adapter1);
        int spIndex1 = 0;
        for (ThanhVien thanhVien : list_tv) {
            if (thanhVien.getId_tv() == phieuMuon.getId_tv()) {
                sp1.setSelection(spIndex1);
                break;
            }
            spIndex1++;
        }
        ArrayList<Sach> list_s = sachDAO.getAllData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, sachDAO.name());
        sp2.setAdapter(adapter);
        int spIndex = 0;
        for (Sach sach : list_s) {
            if (sach.getId_sach() == phieuMuon.getId_sach()) {
                sp2.setSelection(spIndex);

                break;
            }
            spIndex++;
        }


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
            DatePickerDialog datePickerDialog = new DatePickerDialog(context, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });


        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                phieuMuon.setId_tv(list_tv.get(position).getId_tv());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                phieuMuon.setId_sach(list_s.get(position).getId_sach());
               tv2.setText(list_s.get(position).getPrice_sach()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        phieuMuon.setPrice(Integer.parseInt(tv2.getText().toString()));
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ckb.isChecked()) {
                    phieuMuon.setStatus(1);
                } else {
                    phieuMuon.setStatus(0);
                }
                if (dao.update(phieuMuon) > 0) {
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    list = dao.getAllData();
                    setData(list);
                } else {
                    Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
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
}
