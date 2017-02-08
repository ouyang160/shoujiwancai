package com.howietian.shoujiwancai.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class HomeFragment extends Fragment {

  Toolbar tb_home;
  public HomeFragment() {
    // Required empty public constructor
  }


  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view =inflater.inflate(R.layout.fragment_home, container, false);
    tb_home = (Toolbar) view.findViewById(R.id.tb_home);
    tb_home.setTitle("首页");
    tb_home.setTitleTextColor(getResources().getColor(R.color.white));
    ((AppCompatActivity) getActivity()).setSupportActionBar(tb_home);



    return view;
  }
}
