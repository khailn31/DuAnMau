package khailnph29864.fpoly.duanmau.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import khailnph29864.fpoly.duanmau.R;


public class TaoTKFragment extends Fragment {


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
}