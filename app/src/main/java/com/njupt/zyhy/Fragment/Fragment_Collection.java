package com.njupt.zyhy.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.njupt.zyhy.Adapter.MsgAdapter;
import com.njupt.zyhy.Fragment_Exhibition_collect;
import com.njupt.zyhy.Fragment_Home_seach;
import com.njupt.zyhy.Fragment_collection_detail;
import com.njupt.zyhy.R;
import com.njupt.zyhy.bean.InitSDK;
import com.njupt.zyhy.bean.Msg;
import com.njupt.zyhy.bean.MsgLab;
import com.njupt.zyhy.bmob.restapi.Bmob;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Collection#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Collection extends Fragment implements AdapterView.OnItemClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView deng;

    List<CityItem> cityList;
    GridView gridView;
    ImageView imageView;

    private GridView mLvMsgList;
    private List<Msg> mDatas = new ArrayList<>();
    private MsgAdapter mAdapter;

    private ArrayList<String> C_id,C_Class,C_Voice,C_Name,C_Introduce,C_Pic1,C_Pic2,C_Pic3;

    public Fragment_Collection() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Collection.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Collection newInstance(String param1, String param2) {
        Fragment_Collection fragment = new Fragment_Collection();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__collection, container, false);

        gridView = (GridView) view.findViewById(R.id.grid);
        /**
         * ?????????????????????
         */
        C_id = new ArrayList<String>();
        C_Class = new ArrayList<String>();
        C_Voice = new ArrayList<String>();
        C_Name = new ArrayList<String>();
        C_Introduce = new ArrayList<String>();
        C_Pic1 = new ArrayList<String>();
        C_Pic2 = new ArrayList<String>();
        C_Pic3 = new ArrayList<String>();

        /** ???????????? */
        inindate();

        setData();
        setGridView();
        imageView = (ImageView) view.findViewById(R.id.imageView6);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Home_seach.class);
                startActivity(intent);
            }
        });

        // ????????????
        deng = (ImageView) view.findViewById(R.id.imageView17);
        deng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Exhibition_collect.class);
                startActivity(intent);
            }
        });

        mLvMsgList = view.findViewById(R.id.id_lv_msgList);

        // ??????????????????
        mDatas.addAll(MsgLab.generateMockList(C_Name,C_Pic1));

        mAdapter = new MsgAdapter(getActivity(), mDatas);
        mLvMsgList.setAdapter(mAdapter);
        // ?????????gridview???item???????????????
        mLvMsgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemClick", mDatas.get(position).getTitle());
                Intent intent = new Intent(getActivity(), Fragment_collection_detail.class);
                intent.putExtra("C_Name", C_Name.get(position));
                intent.putExtra("C_Voice", C_Voice.get(position));
                intent.putExtra("C_Introduce", C_Introduce.get(position));
                intent.putExtra("C_Pic1", C_Pic1.get(position));
                intent.putExtra("C_Pic2", C_Pic2.get(position));
                intent.putExtra("C_Pic3", C_Pic3.get(position));
                startActivity(intent);
            }
        });

        return view;
    }

    /**????????????GirdView?????????????????????*/
    private void setGridView() {
        int size = cityList.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 2) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // ??????GirdView????????????,?????????????????????
        gridView.setHorizontalSpacing(5); // ???????????????????????????
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // ???????????????=???????????????

        GridViewAdapter adapter = new GridViewAdapter(getActivity().getApplicationContext(),
                cityList);
        gridView.setAdapter(adapter);
        // ?????????gridview???item???????????????
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // ??????????????????
                    inindate2(cityList.get(position).getCityName(),position);
                    mDatas.clear();
                    mDatas.addAll(MsgLab.generateMockList(C_Name, C_Pic1));
                    mAdapter.notifyDataSetChanged();
                    Log.e("onItemClick", cityList.get(position).getCityName());

                } else {
                    // ??????????????????
                    inindate2(cityList.get(position).getCityName(),position);
                    mDatas.clear();
                    mDatas.addAll(MsgLab.generateMockList(C_Name, C_Pic1));
                    mAdapter.notifyDataSetChanged();
                    Log.e("onItemClick", cityList.get(position).getCityName());
                }
            }
        });

    }

    /**??????GirdView ???????????????*/
    public class GridViewAdapter extends BaseAdapter {
        Context context;
        List<CityItem> list;
        public GridViewAdapter(Context _context, List<CityItem> _list) {
            this.list = _list;
            this.context = _context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.hroizontal_list_item, null);
            TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);
            CityItem city = list.get(position);
            tvCity.setText(city.getCityName());
            return convertView;
        }
    }
    // ????????????
    public class CityItem {
        private String cityName;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    /**??????????????????*/
    private void setData() {
        cityList = new ArrayList<CityItem>();
        CityItem item = new CityItem();
        item.setCityName("??????");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("????????????");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("???????????????");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("????????????");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("????????????");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("??????");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("????????????");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("????????????");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("????????????");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("??????");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("????????????");
        cityList.add(item);
        item = new CityItem();
        item.setCityName("??????");
        cityList.add(item);
        cityList.addAll(cityList);
    }

    private void inindate(){
        InitSDK.Initbmob();
        String re,id;
        String Class,Voice,Name,Introduce,Pic1,Pic2,Pic3;;
        re = Bmob.findAll("Collection");
        JSONObject jsonObject = JSON.parseObject(re);
        //??????????????????????????????
        String status = jsonObject.getString("results");
        if (status!=null){
            //??????????????????json???,???????????? content???json???????????????????????????json??????
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            System.out.println(jsonArray);

            for(int i =0;i < jsonArray.size(); i++) {
                JSONObject jsonFirst = jsonArray.getJSONObject(i);

                //????????????json?????????
                id = jsonFirst.getString("id");
                if (id != null) {
                    C_id.add(id);
                }
                //????????????json?????????
                Class = jsonFirst.getString("Class");
                if (Class != null) {
                    C_Class.add(Class);
                }
                //????????????json?????????
                Voice = jsonFirst.getString("Voice");
                if (Voice != null) {

                    C_Voice.add(Voice);
                }
                //????????????json?????????
                Name = jsonFirst.getString("Name");
                if (Name != null) {

                    C_Name.add(Name);
                }
                //????????????json?????????
                Introduce = jsonFirst.getString("Introduce");
                if (Introduce != null) {

                    C_Introduce.add(Introduce);
                }
                //????????????json?????????
                Pic1 = jsonFirst.getString("Pic1");
                if (Pic1 != null) {

                    C_Pic1.add(Pic1);
                }
                //????????????json?????????
                Pic2 = jsonFirst.getString("Pic2");
                if (Pic2 != null) {

                    C_Pic2.add(Pic2);
                }
                //????????????json?????????
                Pic3 = jsonFirst.getString("Pic3");
                if (Pic3 != null) {

                    C_Pic3.add(Pic3);
                }
            }
        }
    }
    private void inindate2(String where,int postion){
        C_id.clear();
        C_Class.clear();
        C_Voice.clear();
        C_Name.clear();
        C_Introduce.clear();
        C_Pic1.clear();
        C_Pic2.clear();
        C_Pic3.clear();

        if (postion == 0){
            inindate();
        }else{
            InitSDK.Initbmob();
            String re,id;
            String Class,Voice,Name,Introduce,Pic1,Pic2,Pic3;;
            String json = "{"+'"'+"Class"+'"'+":";
            String JSon = '"'+where+'"'+"}";

            re = Bmob.findAll("Collection",json+JSon);
            JSONObject jsonObject = JSON.parseObject(re);
            //??????????????????????????????
            String status = jsonObject.getString("results");
            if (status!=null){
                //??????????????????json???,???????????? content???json???????????????????????????json??????
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                System.out.println(jsonArray);

                for(int i =0;i < jsonArray.size(); i++) {
                    JSONObject jsonFirst = jsonArray.getJSONObject(i);

                    //????????????json?????????
                    id = jsonFirst.getString("id");
                    if (id != null) {
                        C_id.add(id);
                    }
                    //????????????json?????????
                    Class = jsonFirst.getString("Class");
                    if (Class != null) {
                        C_Class.add(Class);
                    }
                    //????????????json?????????
                    Voice = jsonFirst.getString("Voice");
                    if (Voice != null) {

                        C_Voice.add(Voice);
                    }
                    //????????????json?????????
                    Name = jsonFirst.getString("Name");
                    if (Name != null) {

                        C_Name.add(Name);
                    }
                    //????????????json?????????
                    Introduce = jsonFirst.getString("Introduce");
                    if (Introduce != null) {

                        C_Introduce.add(Introduce);
                    }
                    //????????????json?????????
                    Pic1 = jsonFirst.getString("Pic1");
                    if (Pic1 != null) {

                        C_Pic1.add(Pic1);
                    }
                    //????????????json?????????
                    Pic2 = jsonFirst.getString("Pic2");
                    if (Pic2 != null) {

                        C_Pic2.add(Pic2);
                    }
                    //????????????json?????????
                    Pic3 = jsonFirst.getString("Pic3");
                    if (Pic3 != null) {

                        C_Pic3.add(Pic3);
                    }
                }
            }
        }
    }

}