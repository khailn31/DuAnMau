package khailnph29864.fpoly.duanmau.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import khailnph29864.fpoly.duanmau.DAO.ThongKeDAO;
import khailnph29864.fpoly.duanmau.R;


public class DoanhThuFragment extends Fragment {
    Button btn1,btn2,btn3;
    TextView tv1,tv2,tv3;
    ThongKeDAO dao;
    private final Calendar myCalendar = Calendar.getInstance();

    public DoanhThuFragment() {
        // Required empty public constructor
    }


    public static DoanhThuFragment newInstance() {
        DoanhThuFragment fragment = new DoanhThuFragment();

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
        return inflater.inflate(R.layout.fragment_doanh_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn1=view.findViewById(R.id.btn_tuNgay);
        btn2=view.findViewById(R.id.btn_denNgay);
        btn3=view.findViewById(R.id.btn_doanhThu);
        tv1=view.findViewById(R.id.tv_tuNgay);
        tv2=view.findViewById(R.id.tv_denNgay);
        tv3=view.findViewById(R.id.tv_doanhThu);
        dao=new ThongKeDAO(getContext());
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        Date selectedDate = myCalendar.getTime();
                        SimpleDateFormat dateFormatter = new SimpleDateFormat(
                                "yyyy-MM-dd");
                       tv1.setText(dateFormatter.format(selectedDate));

                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
      btn2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                      myCalendar.set(Calendar.YEAR, year);
                      myCalendar.set(Calendar.MONTH, monthOfYear);
                      myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                      Date selectedDate = myCalendar.getTime();
                      SimpleDateFormat dateFormatter = new SimpleDateFormat(
                              "yyyy-MM-dd");
                      tv2.setText(dateFormatter.format(selectedDate));

                  }
              };
              DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
              datePickerDialog.show();
          }
      });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay=tv1.getText().toString();
                String denNgay=tv2.getText().toString();
                tv3.setText(dao.getDoanhThu(tuNgay,denNgay)+"");
            }
        });

    }
}