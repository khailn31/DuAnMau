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

import khailnph29864.fpoly.duanmau.DAO.ThanhVienDAO;
import khailnph29864.fpoly.duanmau.Model.ThanhVien;
import khailnph29864.fpoly.duanmau.R;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.DbVH> {
    private ArrayList<ThanhVien> list;

    private Context context;
    private ThanhVienDAO dao;

    public TvAdapter(ArrayList<ThanhVien> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new ThanhVienDAO(context);
    }

    @NonNull
    @Override
    public DbVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_qltv, parent, false);

        return new DbVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DbVH holder, int position) {
       ThanhVien thanhVien=list.get(position);
        holder.tv1.setText(String.valueOf(position+1));
        holder.tv2.setText(thanhVien.getName_tv());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfor(thanhVien);
            }
        });
        holder.btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Del(list.get(position).getId_tv());
            }
        });

        holder.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(thanhVien);
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
            tv1 = itemView.findViewById(R.id.tv_id_tv);
            tv2 = itemView.findViewById(R.id.tv_name_tv);
            btndel = itemView.findViewById(R.id.del_tv);
            btnUp=itemView.findViewById(R.id.update_tv);
        }
    }

    public void setData(ArrayList<ThanhVien> lst){
        this.list=lst;
        notifyDataSetChanged();
    }
    private void showInfor(ThanhVien thanhVien){
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog_infor_tv);
        TextView tv1,tv2,tv3;
        Button btn1;
        tv1=dialog.findViewById(R.id.tv_infor_id_tv);
        tv2=dialog.findViewById(R.id.tv_infor_name_tv);
        tv3=dialog.findViewById(R.id.tv_infor_year_tv);
        tv1.setText(String.valueOf(thanhVien.getId_tv()));
        tv2.setText(thanhVien.getName_tv());
        tv3.setText(thanhVien.getYear_tv());
        btn1=dialog.findViewById(R.id.btn_infor_close_tv);
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
    private void update(ThanhVien thanhVien){
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.layout_dialog_add_tv);
        TextInputEditText ed1,ed2;
        Button btn1,btn2;
        TextView tv1;
        tv1=dialog.findViewById(R.id.title_dialog_tv);
        tv1.setText("Cập nhật thông tin");
        ed1=dialog.findViewById(R.id.ed_add_tv_name);ed1.setText(thanhVien.getName_tv());
        ed2=dialog.findViewById(R.id.ed_add_tv_year);ed2.setText(thanhVien.getYear_tv());
        btn1=dialog.findViewById(R.id.btnSave_add_tv);
        btn2=dialog.findViewById(R.id.btnCacel_add_tv);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThanhVien thanhVien1=new ThanhVien();
                thanhVien1.setId_tv(thanhVien.getId_tv());
                thanhVien1.setName_tv(ed1.getText().toString());
                thanhVien1.setYear_tv(ed2.getText().toString());
                if(dao.update(thanhVien1)>0){
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
