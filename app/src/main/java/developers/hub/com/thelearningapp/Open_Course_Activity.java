package developers.hub.com.thelearningapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import developers.hub.com.thelearningapp.Helper.DataModel;

public class Open_Course_Activity extends AppCompatActivity {



    private CustomeAdapter customeAdapter;
    public  static ArrayList<DataModel> ourSirList;
    public RelativeLayout relativeLayout;
    public CardView cardView;


    public  static ArrayList<DataModel> begComp;
    public  static ArrayList<DataModel> interComp;
    public  static ArrayList<DataModel> advComp;
    public  static ArrayList<DataModel> begAnd;
    public  static ArrayList<DataModel> intAnd;
    public  static ArrayList<DataModel> advAnd;
    public  static ArrayList<DataModel> begWeb;
    public  static ArrayList<DataModel> intWeb;
    public  static ArrayList<DataModel> advWeb;


    private LinearLayout youtubeToggle;





    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__course_);

        // Messy


        Intent intent = getIntent();
        index = intent.getIntExtra("list",-1);


        ourSirList = new ArrayList<>();


        begComp = new ArrayList<>();
        begAnd = new ArrayList<>();
        begWeb = new ArrayList<>();
        interComp = new ArrayList<>();
        intAnd = new ArrayList<>();
        intWeb = new ArrayList<>();
        advComp = new ArrayList<>();
        advAnd = new ArrayList<>();
        advWeb = new ArrayList<>();



        System.out.println("yasir"+ index);
        // Messy

        inintialisMessy();

        switch (index){

            case 1:
                ourSirList = begComp;
                break;

            case 2:
                ourSirList = interComp;
                break;

            case 3:
                ourSirList = advComp;
                break;

            case 4:
                ourSirList = begAnd;
                break;

            case 5:
                ourSirList = intAnd;
                break;

            case 6:
                ourSirList = advAnd;
                break;

            case 7:
                ourSirList = begWeb;
                break;

            case 8:
                ourSirList = intWeb;
                break;

            case 9:
                ourSirList = advWeb;
                break;


                default:
                    ourSirList = begComp;
                    break;

        }


        // Recycle view start

        relativeLayout = (RelativeLayout) findViewById(R.id.courseRel);
        cardView = (CardView) findViewById(R.id.card_viewYoutube);



        youtubeToggle = (LinearLayout) findViewById(R.id.youtubeToggle);





        // Recycler View

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);


        recList.setItemAnimator(new DefaultItemAnimator());
        customeAdapter = new CustomeAdapter(this, ourSirList);
        recList.setAdapter(customeAdapter);


        //GridLayoutManager

        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        // Recycleview end


    }



    // Not Usable dont mind

    private void inintialisMessy() {

        String des = "This course is basically for beginners , who are new in programming world, By this course you can start your journey towards a Better Professional programmer.";

        // Competetive

        begComp.add(  new DataModel(R.mipmap.c_beg,"C By Sourabh Shukla",des,"PLLioQ130_xjVgW5MVfLS1s7zfLFAbGIVs"));
        begComp.add(  new DataModel(R.mipmap.c_beg2,"C By Telusko",des,"PLsyeobzWxl7oBxHp43xQTFrw9f1CDPR6C"));
        begComp.add(new DataModel(R.mipmap.c_plus,"C++ By Sourabh Shukla",des,"PLLYz8uHU480j37APNXBdPz7YzAi4XlQUF"));
        begComp.add(new DataModel(R.mipmap.c_plus2,"C++ By LeaarningLad",des,"PLfVsf4Bjg79Cu5MYkyJ-u4SyQmMhFeC1C"));
        begComp.add(  new DataModel(R.mipmap.python_beg,"Python by The New Boston",des,"PLCg3kKKVIe7HbHliulxsDdJH2TePGDpnZ"));
        begComp.add(  new DataModel(R.mipmap.python2,"Python by Telusko Learning",des,"PLsyeobzWxl7poL9JTVyndKe62ieoN-MZ3"));



        interComp.add(  new DataModel(R.mipmap.java_beg,"JAVA By ProgrammingKnowledge",des,"PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g-Al"));
        interComp.add(  new DataModel(R.mipmap.java1,"JAVA By The New Boston",des,"PLFE2CE09D83EE3E28"));
        interComp.add(new DataModel(R.mipmap.python2,"Python By Edureka! ",des,"PL9ooVrP1hQOHY-BeYrKHDrHKphsJOyRyu"));
        interComp.add(new DataModel(R.mipmap.python3,"Python By Harshit Vashisth",des,"PLwgFb6VsUj_lQTpQKDtLXKXElQychT_2j"));


        advComp.add(  new DataModel(R.mipmap.java3,"JAVA By DurgaSoft",des,"PLd3UqWTnYXOmx_J1774ukG_rvrpyWczm0"));
        advComp.add(  new DataModel(R.mipmap.java2,"JAVA By StanfordUniversity",des,"PL03B458B57D1FB3A0"));
        advComp.add(new DataModel(R.mipmap.java1,"JAVA ADV by DurgaSoft",des,"PLd3UqWTnYXOniKfYRNY___weULTRd9Co0"));
        advComp.add(new DataModel(R.mipmap.python3,"Advance Python By DurgaSoft",des,"PLd3UqWTnYXOnkicyzePnIg8rc2qEXgjiF"));

        // Web

        begWeb.add(  new DataModel(R.mipmap.web_design,"HTML/CSS/JAVASCRIPT By LearnCode Academy",des,"PLoYCgNOIyGAB_8_iq1cL8MVeun7cB6eNc"));
        begWeb.add(  new DataModel(R.mipmap.web_design2,"Starter -> JQUERY By Udemy tech",des,"PL6cactdCCnTLkQah9GKzsJmiLbegy4dEk"));
        begWeb.add(new DataModel(R.mipmap.web_design3,"Starter JAVASCRIPT By Edukreka",des,"PL9ooVrP1hQOEloRCBI97ZXkWUg6MJn0Yf"));
        begWeb.add(new DataModel(R.mipmap.web_design4,"Beginners By Sourabh Shukla",des,"PL7ersPsTyYt01rXXWTejRx0F4WK4yddlT"));

        intWeb.add(  new DataModel(R.mipmap.web_design3,"Django By CodeQus",des,"PLknneukDQdN-0WDOpKN0ayOIC8qmrGSZw"));
        intWeb.add(  new DataModel(R.mipmap.web_design4,"XHTML/CSS By TNB",des,"PLC1322B5A0180C946"));
        intWeb.add(new DataModel(R.mipmap.web_design,"HTML5 By TNB",des,"PL081AC329706B2953"));
        intWeb.add(new DataModel(R.mipmap.web_design2,"Responsive Web Development By TNB",des,"PL6gx4Cwl9DGBaTsb1nse1UU48d_q7glGT"));
        intWeb.add(  new DataModel(R.mipmap.web_design4,"PHP By Bukky",des,"PL442FA2C127377F07"));


        advWeb.add(  new DataModel(R.mipmap.web_design3,"Full Stack By Richard Stibbard",des,"PLz_6dB4PItBFRZhRS9yvqa2N19zqpieuu"));
        advWeb.add(  new DataModel(R.mipmap.web_design,"Complete Web Development By Tom Tsiliopoulos",des,"PL5svY1bZDBZqQRphBtyUJ6x4pNNYUTw7H"));
        advWeb.add(new DataModel(R.mipmap.web_design2,"Full Resource By Logictuts",des,"PLwkOmtT9TMWSfbP18haewvJqAWo8M20Hj"));
        advWeb.add(new DataModel(R.mipmap.web_design2,"Complete Guidence By WB Web Development",des,"PLwoh6bBAszPrES-EOajos_E9gvRbL27wz"));


        // Android

        begAnd.add(  new DataModel(R.mipmap.android,"Starter By DevAndroid",des,"PLknSwrodgQ72X4sKpzf5vT8kY80HKcUSe"));
        begAnd.add(  new DataModel(R.mipmap.android2,"Begineers By TutorialsPoint",des,"PLWPirh4EWFpFO9DnEwsLF4hjPio7aD9Qc"));
        begAnd.add(new DataModel(R.mipmap.android3,"CheezyCode",des,"PLRKyZvuMYSIN9sVZTfDm4CTdTAzDQyLJU"));
        begAnd.add(new DataModel(R.mipmap.android4,"Beginners By Telusko Learning",des,"PLsyeobzWxl7p-lZvWabkVJdM_UVURhUh4"));

        intAnd.add(  new DataModel(R.mipmap.android4,"Google Dev India",des,"PLlyCyjh2pUe9wv-hU4my-Nen_SvXIzxGB"));
        intAnd.add(  new DataModel(R.mipmap.android3," The New Boston",des,"PL2F07DBCDCC01493A"));
        intAnd.add(  new DataModel(R.mipmap.android2,"Coding Cafe",des,"PLxefhmF0pcPlLVOWeYgwXDXpmns4UnyeR"));
        intAnd.add(new DataModel(R.mipmap.android,"Android Singh",des,"PLkLVBZqYV6k3oni6xFy59ld1UMey6MRZq"));


        advAnd.add(  new DataModel(R.mipmap.android3,"Geeks For Geeks",des,"PLqM7alHXFySF_Hb1GtyvCX44dBniJ5sNy"));
        advAnd.add(  new DataModel(R.mipmap.android,"EDMT Dev",des,"PLaoF-xhnnrRWHtmb8ZGmu8N4Wl2Zr26V7"));
        advAnd.add(new DataModel(R.mipmap.android2,"SmartHerd",des,"PLcuky0bWiZ8XBu9NJAnG5aKSvAxEKPsyQ"));
        advAnd.add(new DataModel(R.mipmap.android4,"Udacity SDK 28",des,"PLwDZ5yiByAzy0-8xgJnSfo5MV937SZ4X4"));
        advAnd.add(  new DataModel(R.mipmap.android3,"Complete Android By Udacity",des,"PL4dy5mzKT0VpsTWGojkXU_RdDHBTLOjLR"));




        // Messy



    }

}