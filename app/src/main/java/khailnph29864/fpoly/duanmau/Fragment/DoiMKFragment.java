package khailnph29864.fpoly.duanmau.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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


public class DoiMKFragment extends Fragment {
    TextInputEditText edPassOld, edPass, edRePass;
    Button btnSave, btnCancel;
    ThuThuDAO dao;

    public DoiMKFragment() {
        // Required empty public constructor
    }


    public static DoiMKFragment newInstance() {
        DoiMKFragment fragment = new DoiMKFragment();
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
        return inflater.inflate(R.layout.fragment_doi_m_k, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edPassOld=view.findViewById(R.id.edPassOld);
        edPass=view.findViewById(R.id.edPassChange);
        edRePass=view.findViewById(R.id.edRePassChange);
        btnSave=view.findViewById(R.id.btnSaveUserChange);
        btnCancel=view.findViewById(R.id.btnCancelUserChange);
        dao=new ThuThuDAO(getContext());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPass.setText("");
                edPassOld.setText("");
                edRePass.setText("");

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user=pref.getString("USERNAME","");
                if(validate()>0){
                    ThuThu thuThu=dao.getByID(user);
                    thuThu.setPass_tt(edPass.getText().toString());
                    if(dao.update(thuThu)>0){
                        Toast.makeText(getContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPass.setText("");
                        edPassOld.setText("");
                        edRePass.setText("");
                        Intent i=new Intent(getContext(), MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(getContext(), "Thay doi mat khau that bai", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Thay doi mat khau that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public int validate(){
        int check=1;
        if(edPassOld.getText().length()==0||edPass.getText().length()==0||edRePass.getText().length()==0){
            Toast.makeText(getContext(), "không được để trông", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld=pref.getString("PASSWORD","");
            String pass=edPassOld.getText().toString();
            String passN=edPass.getText().toString();
            String repass=edRePass.getText().toString();
            if (!passOld.equals(pass)){
                Toast.makeText(getContext(), "Mật khẩu sai", Toast.LENGTH_SHORT).show();
           check=-1;
            }
            if(!passN.equals(repass)){
                Toast.makeText(getContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            check=-1;}
        }
        return check;
    }

}