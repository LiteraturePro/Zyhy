package com.njupt.zyhy.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lzj.gallery.library.views.BannerViewPager;
import com.njupt.zyhy.CircleImageView;
import com.njupt.zyhy.Fragment_Home_Dangjiang;
import com.njupt.zyhy.Fragment_Home_bianmin;
import com.njupt.zyhy.Fragment_Home_canguanyuyue;
import com.njupt.zyhy.Fragment_Home_education;
import com.njupt.zyhy.Fragment_Home_inform;
import com.njupt.zyhy.Fragment_Home_quanjing;
import com.njupt.zyhy.Fragment_Home_seach;
import com.njupt.zyhy.Fragment_Home_zhoubianwenchuang;
import com.njupt.zyhy.Fragment_Me_news;
import com.njupt.zyhy.Fragment_collection_detail;
import com.njupt.zyhy.Fragment_exhabition_detail;
import com.njupt.zyhy.Fragment_guide_map;
import com.njupt.zyhy.Fragment_guide_webview;
import com.njupt.zyhy.R;
import com.njupt.zyhy.ScanActivity;
import com.njupt.zyhy.bean.InitSDK;
import com.njupt.zyhy.bmob.restapi.Bmob;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<String> C_id,C_Class,C_Voice,C_Name,C_Introduce,C_Pic1,C_Pic2,C_Pic3;
    private ArrayList<String> Z_Title,Z_Subtitle,Z_Text,Z_Pic1,Z_Pic2,Z_Pic3,Z_Pic4;

    BannerViewPager banner_1,banner_2,banner_3;
    List<String> urlList_wenwu,urlList_book,urlList_home;
    ImageView imageView,scan_imageView,Seach_imageView;
    CircleImageView CircleImageView1,CircleImageView2,CircleImageView3,CircleImageView4,CircleImageView5,CircleImageView6,CircleImageView7,CircleImageView8;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Home newInstance(String param1, String param2) {
        Fragment_Home fragment = new Fragment_Home();
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
        //StatusBarCompat.setStatusBarColor(getActivity(),getResources().getColor(R.color.lastTextColor2));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__home, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         * ??????????????????
         */
        banner_1 = (BannerViewPager) getActivity().findViewById(R.id.banner_1);
        banner_2 = (BannerViewPager) getActivity().findViewById(R.id.banner_2);
        banner_3 = (BannerViewPager) getActivity().findViewById(R.id.banner_3);
        /**
         * ????????????????????????
         */
        imageView = (ImageView) getActivity().findViewById(R.id.ivMsg);
        scan_imageView = (ImageView) getActivity().findViewById(R.id.ivqr);
        Seach_imageView = (ImageView) getActivity().findViewById(R.id.ivSearch);
        /**
         * ???????????????????????????
         */
        C_id = new ArrayList<String>();
        C_Class = new ArrayList<String>();
        C_Voice = new ArrayList<String>();
        C_Name = new ArrayList<String>();
        C_Introduce = new ArrayList<String>();
        C_Pic1 = new ArrayList<String>();
        C_Pic2 = new ArrayList<String>();
        C_Pic3 = new ArrayList<String>();
        /**
         * ???????????????????????????
         */
        Z_Title  = new ArrayList<String>();
        Z_Subtitle  = new ArrayList<String>();
        Z_Text  = new ArrayList<String>();
        Z_Pic1 = new ArrayList<String>();
        Z_Pic2 = new ArrayList<String>();
        Z_Pic3 = new ArrayList<String>();
        Z_Pic4 = new ArrayList<String>();
        /**
         * ????????????
         */
        mParam2 = inindate2();
        mParam1 = inindate();
        urlList_home = new ArrayList<>();
        urlList_home.add("http://bmob.wxiou.cn/2021/06/02/bac9eb2c401894e580853d35f2c6d382.jpg");
        urlList_home.add("http://bmob.wxiou.cn/2021/06/02/dc5963db402a50b780c7f327d5831154.jpg");
        urlList_home.add("http://bmob.wxiou.cn/2021/06/02/4830727f40137b98807e4eaa82e5c13f.jpg");

        urlList_book = new ArrayList<>();
        for(int i = 0; i < Z_Pic1.size(); i++){
            urlList_book.add(Z_Pic1.get(i));
        }
        urlList_wenwu = new ArrayList<>();
        for(int i = 0; i < C_Pic1.size(); i++){
            urlList_wenwu.add(C_Pic2.get(i));
        }
        //???????????????
        banner_1.initBanner(urlList_book, false)//??????3D????????????
                .addPageMargin(10, 80)//??????1page???????????????,??????2??????item?????????????????????
                //.addPointMargin(6)//???????????????
                .addStartTimer(3)//????????????5?????????
                .addPointBottom(7)
                .addRoundCorners(10)//??????
                .finishConfig()//???????????????
                .addBannerListener(new BannerViewPager.OnClickBannerListener() {
                    @Override
                    public void onBannerClick(int position) {
                        Intent intent = new Intent(getActivity(), Fragment_exhabition_detail.class);
                        intent.putExtra("Z_Title", Z_Title.get(position));
                        intent.putExtra("Z_Subtitle", Z_Subtitle.get(position));
                        intent.putExtra("Z_Text", Z_Text.get(position));
                        intent.putExtra("Z_Pic1", Z_Pic1.get(position));
                        intent.putExtra("Z_Pic2", Z_Pic2.get(position));
                        intent.putExtra("Z_Pic3", Z_Pic3.get(position));
                        intent.putExtra("Z_Pic4", Z_Pic4.get(position));
                        startActivity(intent);
                    }
                });
        banner_2.initBanner(urlList_wenwu, true)//??????3D????????????
                .addPageMargin(0, 100)//??????1page???????????????,??????2??????item?????????????????????
                //.addPointMargin(6)//???????????????
                .addStartTimer(3)//????????????5?????????
                .addPointBottom(1)
                .addRoundCorners(10)//??????
                .finishConfig()//???????????????
                .addBannerListener(new BannerViewPager.OnClickBannerListener() {
                    @Override
                    public void onBannerClick(int position) {
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
        banner_3.initBanner(urlList_home, false)//??????3D????????????
                .addPageMargin(0, 0)//?????????
                .addPointMargin(6)//???????????????
                .addStartTimer(5)//????????????5?????????
                .addPointBottom(7)
                .finishConfig()//???????????????
                .addBannerListener(new BannerViewPager.OnClickBannerListener() {
                    @Override
                    public void onBannerClick(int position) {
                        //??????item
                        Log.i("test","--------------00x3");
                    }
                });

        //??????????????????
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Me_news.class);
                startActivity(intent);
            }
        });

        //?????????????????????
        scan_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //IntentIntegrator intentIntegrator = new IntentIntegrator();
                IntentIntegrator intentIntegrator = IntentIntegrator.forSupportFragment(Fragment_Home.this);

                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setCaptureActivity(ScanActivity.class);
                intentIntegrator.setPrompt("??????????????????");//?????????????????????
                intentIntegrator.setCameraId(0);//???????????????????????????
                intentIntegrator.setBeepEnabled(true); //???????????????????????????????????????
                intentIntegrator.setBarcodeImageEnabled(true);//???????????????????????????????????????
                intentIntegrator.initiateScan();
            }
        });
        // ????????????????????????
        Seach_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Home_seach.class);
                intent.putExtra("Collection", mParam1);
                intent.putExtra("Exhibit", mParam2);
                startActivity(intent);
            }
        });

        /**
         * ????????????????????????
         */
        // ????????????
        CircleImageView1 = (CircleImageView) getActivity().findViewById(R.id.circleImageView11);
        CircleImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Home_quanjing.class);
                startActivity(intent);
            }
        });
        // ????????????
        CircleImageView2 = (CircleImageView) getActivity().findViewById(R.id.circleImageView12);
        CircleImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Home_canguanyuyue.class);
                startActivity(intent);
            }
        });
        // ????????????
        CircleImageView3 = (CircleImageView) getActivity().findViewById(R.id.circleImageView13);
        CircleImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_guide_map.class);
                intent.putExtra("bit", "map");
                startActivity(intent);
            }
        });
         //????????????
        CircleImageView4 = (CircleImageView) getActivity().findViewById(R.id.circleImageView3);
        CircleImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Home_Dangjiang.class);
                startActivity(intent);
            }
        });
        // ????????????
        CircleImageView5 = (CircleImageView) getActivity().findViewById(R.id.circleImageView9);
        CircleImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Home_inform.class);
                intent.putExtra("inform", Z_Subtitle);
                startActivity(intent);
            }
        });
        // ????????????
        CircleImageView6 = (CircleImageView) getActivity().findViewById(R.id.circleImageView4);
        CircleImageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Home_zhoubianwenchuang.class);
                startActivity(intent);
            }
        });
        // ????????????
        CircleImageView7 = (CircleImageView) getActivity().findViewById(R.id.circleImageView6);
        CircleImageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Home_bianmin.class);
                startActivity(intent);
            }
        });
        // ????????????
        CircleImageView8 = (CircleImageView) getActivity().findViewById(R.id.circleImageView7);
        CircleImageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Home_education.class);
                startActivity(intent);
            }
        });
        // ??????????????????
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.home_jiaoyu);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Home_education.class);
                startActivity(intent);
            }
        });
        // ????????????
        ImageView imageView2 = (ImageView) getActivity().findViewById(R.id.home_wenchuang);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Fragment_Home_zhoubianwenchuang.class);
                startActivity(intent);
            }
        });

    }

    // ??????????????????
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(scanResult!=null){
            String result = scanResult.getContents();
            if(result.equals("????????????????????????")){
                Intent intent = new Intent(getActivity(), Fragment_guide_webview.class);
                startActivity(intent);
            }else {
                System.out.println("666666666666666666666666");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private String inindate(){
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
        return re;
    }
    private String inindate2(){
        InitSDK.Initbmob();
        String re;
        String Title,Subtitle,Text,Pic1,Pic2,Pic3,Pic4;
        re = Bmob.findAll("Exhibit");
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
                Title = jsonFirst.getString("Title");
                if (Title != null) {
                    Z_Title.add(Title);
                }
                //????????????json?????????
                Subtitle = jsonFirst.getString("Subtitle");
                if (Subtitle != null) {
                    Z_Subtitle.add(Subtitle);
                }
                //????????????json?????????
                Text = jsonFirst.getString("Text");
                if (Text != null) {
                    Z_Text.add(Text);
                }
                //????????????json?????????
                Pic1 = jsonFirst.getString("Pic1");
                if (Pic1 != null) {
                    Z_Pic1.add(Pic1);
                }
                //????????????json?????????
                Pic2 = jsonFirst.getString("Pic2");
                if (Pic2 != null) {
                    Z_Pic2.add(Pic2);
                }
                //????????????json?????????
                Pic3 = jsonFirst.getString("Pic3");
                if (Pic3 != null) {
                    Z_Pic3.add(Pic3);
                }
                //????????????json?????????
                Pic4 = jsonFirst.getString("Pic4");
                if (Pic4 != null) {
                    Z_Pic4.add(Pic4);
                }
            }
        }
        return re;
    }
}