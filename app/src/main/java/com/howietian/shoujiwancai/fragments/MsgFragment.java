package com.howietian.shoujiwancai.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.Bind;
import com.howietian.shoujiwancai.R;
import com.howietian.shoujiwancai.adapters.MsgAdapter;
import com.howietian.shoujiwancai.entities.MyMsg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * A simple {@link Fragment} subclass.
 */
public class MsgFragment extends Fragment {

    private static final int REFRESH_COMPLETE = 0X110;
    private SwipeRefreshLayout mrefreshLayout;
    private ListView mlistView;
    private MsgAdapter mAdapter;
    private List<MyMsg> mDatas = new ArrayList<MyMsg>(Arrays.asList(
            new MyMsg(null,"系统消息","2017-2-20","+++++++++++++++++++++++++++++++++++adfagagaavadfasgasdgasgasdgasdg+++gadsg")
    ));

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case REFRESH_COMPLETE:
                    mDatas.addAll(Arrays.asList(
                            new MyMsg(null,"someone","2017-2-20","adfadsgasdgagdasgdasdgasdgasdgasdfasdfasd+++++++++++++++")
                    ));
                    mAdapter.notifyDataSetChanged();
                    mrefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };







    Toolbar tb_msg;
    public MsgFragment() {
        // Required empty public constructor

    }


    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_msg,container,false);
        tb_msg = (Toolbar) view.findViewById(R.id.tb_msg);


        mlistView= (ListView) view.findViewById(R.id.msg_listview);
        mrefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.refresh_msg);
        mrefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageAtTime(REFRESH_COMPLETE,1000);
            }
        });
        mrefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light));

        mAdapter= new MsgAdapter(getContext(),mDatas);
        mlistView.setAdapter(mAdapter);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDatas.get(position).setRead(true);
                view.findViewById(R.id.item_msg_isread).setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(),"接下来应该跳转到具体信息界面",Toast.LENGTH_LONG).show();
            }
        });



        tb_msg.setTitle("消息");
        tb_msg.setTitleTextColor(getResources().getColor(R.color.white));
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb_msg);
        return view;
    }
}
