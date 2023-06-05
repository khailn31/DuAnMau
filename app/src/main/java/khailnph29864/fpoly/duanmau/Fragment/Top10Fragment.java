package khailnph29864.fpoly.duanmau.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import khailnph29864.fpoly.duanmau.Adapter.Top10Adapter;
import khailnph29864.fpoly.duanmau.Adapter.TvAdapter;
import khailnph29864.fpoly.duanmau.DAO.ThanhVienDAO;
import khailnph29864.fpoly.duanmau.DAO.ThongKeDAO;
import khailnph29864.fpoly.duanmau.Model.Top;
import khailnph29864.fpoly.duanmau.R;


public class Top10Fragment extends Fragment {
    RecyclerView recy;
    ThongKeDAO dao;
    Top10Adapter adapter;
    ArrayList<Top> list;

    public Top10Fragment() {
        // Required empty public constructor
    }


    public static Top10Fragment newInstance() {
        Top10Fragment fragment = new Top10Fragment();

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
        return inflater.inflate(R.layout.fragment_top10, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recy = view.findViewById(R.id.recy_top10);
        dao = new ThongKeDAO(getContext());
        list = dao.getTop();
        adapter = new Top10Adapter(list, getContext());
        RecyclerView rc = view.findViewById(R.id.recy_top10);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(linearLayoutManager);
        recy.setAdapter(adapter);


    }
}