package com.howietian.shoujiwancai.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.howietian.shoujiwancai.R;
import com.howietian.shoujiwancai.entities.MyMsg;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 78421 on 2017/2/19.
 */

public class MsgAdapter extends BaseAdapter{

    Context context;
    List<MyMsg> msgs;
    LayoutInflater inflater;

    public MsgAdapter(Context context, List<MyMsg> msgs){
        this.context=context;
        this.msgs=msgs;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return msgs.size();
    }

    @Override
    public MyMsg getItem(int position) {
        return msgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item_msg,parent,false);
            vh=new ViewHolder(convertView);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        MyMsg myMsg=getItem(position);
        String avatar=myMsg.getAvatar();
        if (position==0){
            vh.avatar.setImageResource(R.mipmap.msg_systemmsg);
        }else if(TextUtils.isEmpty(avatar)){
            vh.avatar.setImageResource(R.mipmap.ic_launcher);
        }else {
            //如果该用户的头像不是空，就用ImageLoader从服务器下载过来，并初始化到相应的控件上
            //这里应该没啥用目前
            ImageLoader.getInstance().displayImage(avatar,vh.avatar);
        }
        if(myMsg.isRead()){
            vh.isread.setVisibility(View.INVISIBLE);
        }else {
            vh.isread.setVisibility(View.VISIBLE);
        }
        vh.name.setText(myMsg.getName());
        vh.time.setText(myMsg.getTime());
        vh.infor.setText(myMsg.getInfor());


        return convertView;
    }

    public class ViewHolder{
        @Bind(R.id.item_msg_avatar)
        ImageView avatar;
        @Bind(R.id.item_msg_infor)
        TextView infor;
        @Bind(R.id.item_msg_isread)
        ImageView isread;
        @Bind(R.id.item_msg_name)
        TextView name;
        @Bind(R.id.item_msg_time)
        TextView time;
        public ViewHolder(View convertView){
            ButterKnife.bind(this,convertView);
        }

    }

}
