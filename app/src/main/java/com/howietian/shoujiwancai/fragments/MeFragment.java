package com.howietian.shoujiwancai.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import com.howietian.shoujiwancai.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {

  Toolbar tb_me;
  public MeFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_me,container,false);
    tb_me = (Toolbar) view.findViewById(R.id.tb_me);
    tb_me.setTitle("我的");
    tb_me.setTitleTextColor(getResources().getColor(R.color.white));
    ((AppCompatActivity)getActivity()).setSupportActionBar(tb_me);
    return view;
  }
}
