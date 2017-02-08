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
public class ProjectFragment extends Fragment {
@Bind(R.id.tb_project)
  Toolbar tb_project;
  public ProjectFragment() {
    // Required empty public constructor
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_project,container,false);
    tb_project = (Toolbar) view.findViewById(R.id.tb_project);
    tb_project.setTitle("项目");
    tb_project.setTitleTextColor(getResources().getColor(R.color.white));
    ((AppCompatActivity)getActivity()).setSupportActionBar(tb_project);
    return view;
  }
}
