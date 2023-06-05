package khailnph29864.fpoly.duanmau.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import khailnph29864.fpoly.duanmau.DAO.ThongKeDAO;
import khailnph29864.fpoly.duanmau.Model.LoaiSach;
import khailnph29864.fpoly.duanmau.Model.Sach;
import khailnph29864.fpoly.duanmau.Model.Top;
import khailnph29864.fpoly.duanmau.R;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.DbVH> {
    private ArrayList<Top> list;
    private ThongKeDAO dao;
    private Context context;


    public Top10Adapter(ArrayList<Top> list, Context context) {
        this.list = list;
        this.context = context;
        dao =new ThongKeDAO(context);

    }

    @NonNull
    @Override
    public DbVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_top10, parent, false);

        return new DbVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DbVH holder, int position) {
        Top top = list.get(position);
        holder.tv1.setText(String.valueOf(position + 1 + "."));
        holder.tv2.setText("Tên sách: "+top.getTenSach());
        holder.tv3.setText("Số lượng: "+top.getSoLuong());

    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    class DbVH extends RecyclerView.ViewHolder {
        private TextView tv1, tv2, tv3;


        public DbVH(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv_stt_top10);
            tv2 = itemView.findViewById(R.id.tv_nameS_top10);
            tv3 = itemView.findViewById(R.id.tv_sl_Top10);

        }
    }

    public void setData(ArrayList<Top> lst) {
        this.list = lst;
        notifyDataSetChanged();
    }


}
