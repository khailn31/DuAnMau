package khailnph29864.fpoly.duanmau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import khailnph29864.fpoly.duanmau.DAO.LoaiSachDAO;
import khailnph29864.fpoly.duanmau.DAO.SachDAO;
import khailnph29864.fpoly.duanmau.DAO.ThanhVienDAO;
import khailnph29864.fpoly.duanmau.Model.LoaiSach;
import khailnph29864.fpoly.duanmau.Model.Sach;
import khailnph29864.fpoly.duanmau.Model.ThanhVien;
import khailnph29864.fpoly.duanmau.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.DbVH> {
    private ArrayList<Sach> list;
    private LoaiSachDAO lsdao;
    private Context context;
    private SachDAO dao;

    public SachAdapter(ArrayList<Sach> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new SachDAO(context);
        lsdao=new LoaiSachDAO(context);
    }

    @NonNull
    @Override
    public DbVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_qls, parent, false);

        return new DbVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DbVH holder, int position) {
       Sach sach=list.get(position);
        holder.tv1.setText(String.valueOf(position+1+"."));
        holder.tv2.setText(sach.getName_sach());
        holder.tv3.setText(String.valueOf(sach.getPrice_sach()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfor(sach);
            }
        });
        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Del(sach.getId_sach());
            }
        });

        holder.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(sach);
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
        private TextView tv1, tv2,tv3;
        private ImageView btndel,btnUp;

        public DbVH(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv_id_s);
            tv2 = itemView.findViewById(R.id.tv_name_s);
            tv3=itemView.findViewById(R.id.tv_price_s);
            btndel = itemView.findViewById(R.id.del_s);
            btnUp=itemView.findViewById(R.id.update_s);
        }
    }

    public void setData(ArrayList<Sach> lst){
        this.list=lst;
        notifyDataSetChanged();
    }
    private void showInfor(Sach sach){
        LoaiSach loaiSach=lsdao.getByID(String.valueOf(sach.getId_loaisach()));
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog_infor_s);
        TextView tv1,tv2,tv3,tv4;
        Button btn1;
        tv1=dialog.findViewById(R.id.tv_infor_id_s);
        tv2=dialog.findViewById(R.id.tv_infor_name_s);
        tv4=dialog.findViewById(R.id.tv_infor_price_s);
        tv3=dialog.findViewById(R.id.tv_infor_ls_s);
        tv1.setText(String.valueOf(sach.getId_sach()));
        tv2.setText(sach.getName_sach());
        tv3.setText(loaiSach.getName_loaisach());
        tv4.setText(String.valueOf(sach.getPrice_sach()));
        btn1=dialog.findViewById(R.id.btn_infor_close_s);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void Del(int id){
        AlertDialog.Builder dialogDL=new AlertDialog.Builder(context);
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
                if(dao.delete(id)>0){
                    Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    list=dao.getAllData();
                    setData(list);
                }
                else {
                    Toast.makeText(context, "Xoa k thanh cong", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();

            }
        });
        dialogDL.show();
    }
    private void update(Sach sach){
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog_add_s);
        TextInputEditText ed1,ed2;
        Spinner sp;
        sp=dialog.findViewById(R.id.sp_add_s_ls);

        ArrayList<LoaiSach> lsList=lsdao.getAllData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, lsdao.name());
        sp.setAdapter(adapter);
        int spIndex=0;
        for(LoaiSach ls:lsList){
            if(sach.getId_loaisach()==ls.getId_loaisach()){
                sp.setSelection(spIndex);
                break;
            }
            spIndex++;
        }
        Button btn1,btn2;
        TextView tv1;
        tv1=dialog.findViewById(R.id.title_dialog_s);
        tv1.setText("Cập nhật thông tin");
        ed1=dialog.findViewById(R.id.ed_add_s_name);ed1.setText(sach.getName_sach());
        ed2=dialog.findViewById(R.id.ed_add_s_price);ed2.setText(String.valueOf(sach.getPrice_sach()));
        btn1=dialog.findViewById(R.id.btnSave_add_s);
        btn2=dialog.findViewById(R.id.btnCacel_add_s);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach sach1=new Sach();
               sach1.setId_sach(sach.getId_sach());
                sach1.setName_sach(ed1.getText().toString());
                sach1.setPrice_sach(Integer.parseInt(ed2.getText().toString()));
                sach1.setId_loaisach(sach.getId_loaisach());
                if(dao.update(sach1)>0){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    list=dao.getAllData();
                    setData(list);
                }else{
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
