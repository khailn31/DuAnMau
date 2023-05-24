package khailnph29864.fpoly.duanmau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import khailnph29864.fpoly.duanmau.DAO.LoaiSachDAO;
import khailnph29864.fpoly.duanmau.DAO.ThanhVienDAO;
import khailnph29864.fpoly.duanmau.Model.LoaiSach;
import khailnph29864.fpoly.duanmau.Model.ThanhVien;
import khailnph29864.fpoly.duanmau.R;

public class LSAdapter extends RecyclerView.Adapter<LSAdapter.DbVH> {
    private ArrayList<LoaiSach> list;

    private Context context;
    private LoaiSachDAO dao;

    public LSAdapter(ArrayList<LoaiSach> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new LoaiSachDAO(context);
    }

    @NonNull
    @Override
    public DbVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_qlls, parent, false);

        return new DbVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DbVH holder, int position) {
       LoaiSach loaiSach=list.get(position);
        holder.tv1.setText(String.valueOf(position+1));
        holder.tv2.setText(loaiSach.getName_loaisach());

        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Del(loaiSach.getId_loaisach());
            }
        });

        holder.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(loaiSach);
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
        private TextView tv1, tv2;
        private ImageView btndel,btnUp;

        public DbVH(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv_id_ls);
            tv2 = itemView.findViewById(R.id.tv_name_ls);
            btndel = itemView.findViewById(R.id.del_ls);
            btnUp=itemView.findViewById(R.id.update_ls);
        }
    }

    public void setData(ArrayList<LoaiSach> lst){
        this.list=lst;
        notifyDataSetChanged();
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
    private void update(LoaiSach loaiSach){
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog_add_ls);
        TextInputEditText ed1;
        Button btn1,btn2;
        TextView tv1;
        tv1=dialog.findViewById(R.id.title_dialog_ls);
        tv1.setText("Cập nhật thông tin");
        ed1=dialog.findViewById(R.id.ed_add_ls_name);ed1.setText(loaiSach.getName_loaisach());

        btn1=dialog.findViewById(R.id.btnSave_add_ls);
        btn2=dialog.findViewById(R.id.btnCacel_add_ls);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSach loaiSach1=new LoaiSach();
                loaiSach1.setId_loaisach(loaiSach.getId_loaisach());
                loaiSach1.setName_loaisach(ed1.getText().toString());

                if(dao.update(loaiSach1)>0){
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
