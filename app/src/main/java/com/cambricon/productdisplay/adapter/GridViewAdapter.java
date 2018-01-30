package com.cambricon.productdisplay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cambricon.productdisplay.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangyaling on 18-1-30.
 */

public class GridViewAdapter extends BaseAdapter{
    private Context mContext;
    private List<Map<String, Object>> listItem = new ArrayList<Map<String, Object>>();;
    private int[] gv_image;
    private String[] gv_text;

    public GridViewAdapter(Context context){
        this.mContext=context;
        //this.listItem=listItem;
        gv_image=new int[]{R.drawable.voice,R.drawable.voice,R.drawable.voice,R.drawable.voice};
        gv_text=context.getResources().getStringArray(R.array.grid_view_text);
        for(int i=0;i<gv_text.length;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("image",gv_image[i]);
            map.put("title",gv_text[i]);
            listItem.add(map);
        }
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.gridview_item,null);
        }
        ImageView item_image=view.findViewById(R.id.itemImage);
        TextView item_text=view.findViewById(R.id.itemText);
        Map<String,Object> map=listItem.get(i);
        item_image.setImageResource((Integer) map.get("image"));
        item_text.setText(map.get("title")+"");
        return view;
    }
}
