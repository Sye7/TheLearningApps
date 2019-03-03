package developers.hub.com.thelearningapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import developers.hub.com.thelearningapp.Helper.LeaderBoardDataModel;
import developers.hub.com.thelearningapp.Helper.ProfileInfo;

import static developers.hub.com.thelearningapp.LeaderBoard.ourToppers;

public class ProfileViewNav extends AppCompatActivity {





    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };







        String name;
    String contactInfo;
    String college;
    String year;
    String city;
    String dp;
    int point;
    String uid;
    int rank;

    public TextView tName;
    public TextView tCollege;
    public TextView tYear;
    public TextView tCity;
    public TextView tPoint;
    public ImageView tDp;
    public TextView tContactInfo;


    Activity myActivity = this;







    private void initialiseCurrentAccount() {

        final ProfileInfo[] profileInfo = new ProfileInfo[1];

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


        DatabaseReference temp = FirebaseDatabase.getInstance().getReference().child("profile/");


        temp.orderByChild("uid").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    profileInfo[0] = (ProfileInfo) childDataSnapshot.getValue(ProfileInfo.class);

                }


                try {

                    name = profileInfo[0].getName();
                    dp = profileInfo[0].getDp();

                }catch (Exception e){

                    Intent intent = new Intent(getApplicationContext(),profile.class);
                    intent.putExtra("message","yes");
                    startActivity(intent);
                }

                contactInfo =   profileInfo[0].getContactInfo();
                college =   profileInfo[0].getCollege();
                year =   profileInfo[0].getYear();
                city =   profileInfo[0].getCity();
                point =   profileInfo[0].getPoint();

                tName.setText(name);
                tCity.setText(city);
                tCollege.setText(college);
                tContactInfo.setText(contactInfo);
                tPoint.setText(point+"");
                tYear.setText(year);

                Glide.with(myActivity).load(dp).into(tDp);
                tPoint.setText(point+"");
                // rankTextView.setText(rank);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void leader(View view){

        Intent intent = new Intent(getApplicationContext(),LeaderBoard.class);
        startActivity(intent);
    }



    public void bang(){

        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);

    }
    // Bottom Navigation

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_explore:
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_search:
                    bang();
                    return true;
                case R.id.navigation_bookmark:
                    Intent intent1 = new Intent(getApplicationContext(),Forum.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_profile:

                    return true;

            }
            return false;
        }
    };


    boolean  backPressToExit = false;

    @Override
    public void onBackPressed() {


        if(backPressToExit)
        {

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

        Toast.makeText(this, "Back Again to Exit", Toast.LENGTH_SHORT).show();

        this.backPressToExit = true;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                backPressToExit = false;
            }
        }, 2000);



    }




    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile_view_nav);




        mVisible = true;
        mContentView = findViewById(R.id.headerProfile);



        navigation = (BottomNavigationView) findViewById(R.id.bot_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setSelectedItemId(R.id.navigation_profile);

        tName = (TextView)findViewById(R.id.nameNavigation);
        tCity = (TextView)findViewById(R.id.cityNavigation);
        tCollege = (TextView)findViewById(R.id.college_nameNavigation);
        tYear = (TextView)findViewById(R.id.yearNavigation);
        tPoint = (TextView)findViewById(R.id.scoresNavigation);
        tDp = (ImageView) findViewById(R.id.profileNavigation);
        tContactInfo = (TextView) findViewById(R.id.contactNavigation);


        initialiseCurrentAccount();


        // Set up the user interaction to manually show or hide the system UI.



        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });


    }

    public void editProfile(View view ){

        Intent intent = new Intent(getApplicationContext(),profile.class);
        startActivity(intent);
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }



}
