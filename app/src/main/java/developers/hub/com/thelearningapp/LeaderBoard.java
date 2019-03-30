package developers.hub.com.thelearningapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;

import developers.hub.com.thelearningapp.Helper.DataModel;
import developers.hub.com.thelearningapp.Helper.LeaderBoardDataModel;
import developers.hub.com.thelearningapp.Helper.ProfileInfo;

public class LeaderBoard extends AppCompatActivity {


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
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.

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




    private CustomAdapterLeaderBoard customAdapterLeaderBoard;
    public  static ArrayList<LeaderBoardDataModel> ourToppers;
    public RelativeLayout relativeLayout;
    public CardView cardView;

    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener childEventListener;

    public String name = "";
    public String dp = "";
    public int point = 0;
    public int rank = 1000;

    Activity myActivity = this;






    private  void detachDbReadListener() {

        if (childEventListener != null) {
            mMessageDatabaseReference.removeEventListener(childEventListener);
            childEventListener = null;
        }
    }



    private void attachDbReadListener() {

        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    ProfileInfo p = (ProfileInfo) dataSnapshot.getValue(ProfileInfo.class);
                   name = p.getName();
                   dp = p.getDp();
                   point = p.getPoint();

                   ourToppers.add(new LeaderBoardDataModel(name,point,dp));

                   customAdapterLeaderBoard.notifyDataSetChanged();


                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }

            };
            mMessageDatabaseReference.addChildEventListener(childEventListener);

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);


        mVisible = true;
        mMessageDatabaseReference = FirebaseDatabase.getInstance().getReference().child("profile");

        // Static toppers
        ourToppers = new ArrayList<>();

        initialiseCurrentAccount();


        attachDbReadListener();

        // RecycleView


        RecyclerView recList = (RecyclerView) findViewById(R.id.recycleLeader);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);



        recList.setItemAnimator(new DefaultItemAnimator());
        customAdapterLeaderBoard = new CustomAdapterLeaderBoard(ourToppers,this);
        recList.setAdapter(customAdapterLeaderBoard);


        //GridLayoutManager can also be used


        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        attachDbReadListener();


    }

    // Initialise dp and name of current user


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

                    Toast.makeText(myActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                System.out.println("yasir "+name + rank + point);
                point = profileInfo[0].getPoint();


                ImageView imageView = (ImageView) findViewById(R.id.currentDp);
                TextView pointTextView = (TextView) findViewById(R.id.currentPoint);
              //  TextView rankTextView = (TextView) findViewById(R.id.currentRank);

                Glide.with(myActivity).load(dp).into(imageView);
                pointTextView.setText(point+"");
               // rankTextView.setText(rank);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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



    // updateScore

    private void updateData() {


        mMessageDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dataSnapshot.getRef().child("point").setValue(100);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


}
