package khailnph29864.fpoly.duanmau.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import khailnph29864.fpoly.duanmau.DAO.ThuThuDAO;
import khailnph29864.fpoly.duanmau.MainActivity;
import khailnph29864.fpoly.duanmau.Model.ThuThu;
import khailnph29864.fpoly.duanmau.R;


public class TaoTKFragment extends Fragment {
    Button btn1,btn2;
    TextInputEditText ed1,ed2,ed3,ed4;
    ThuThuDAO dao;

    public TaoTKFragment() {
        // Required empty public constructor
    }


    public static TaoTKFragment newInstance() {
        TaoTKFragment fragment = new TaoTKFragment();

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
        return inflater.inflate(R.layout.fragment_tao_t_k, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn1=view.findViewById(R.id.btnSaveUser_new);
        btn2=view.findViewById(R.id.btnCancelUser_new);
        ed1=view.findViewById(R.id.ed_ttk_name);
        ed2=view.findViewById(R.id.ed_ttk_user);
        ed3=view.findViewById(R.id.ed_ttk_pass);
        ed4=view.findViewById(R.id.ed_ttk_repass);
        dao=new ThuThuDAO(getContext());

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed4.setText("");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu thuThu=new ThuThu();
                thuThu.setName_tt(ed1.getText().toString());
                thuThu.setUser_tt(ed2.getText().toString());
                thuThu.setPass_tt(ed3.getText().toString());
                if(validate()>0){
                    if(dao.insert(thuThu)>0){
                        Toast.makeText(getContext(), "thêm tai khoan thanh cong", Toast.LENGTH_SHORT).show();
                        ed1.setText("");
                        ed2.setText("");
                        ed3.setText("");
                        ed4.setText("");
                        Intent i=new Intent(getContext(), MainActivity.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(getContext(), "them that bai", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    public int validate(){
        int check=1;
        if(ed1.getText().length()==0||ed2.getText().length()==0||ed3.getText().length()==0
        ||ed4.getText().length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
        check=-1;
        }else{
            String pass=ed3.getText().toString();
            String repass=ed4.getText().toString();
            if(!pass.equals(repass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check=-1;
            }else{
                check=1;
            }

        }
        return check;
    }
}