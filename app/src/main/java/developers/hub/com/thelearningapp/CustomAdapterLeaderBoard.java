package developers.hub.com.thelearningapp;


import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import developers.hub.com.thelearningapp.Helper.LeaderBoardDataModel;

public class CustomAdapterLeaderBoard extends RecyclerView.Adapter<CustomAdapterLeaderBoard.ContactViewHolder> {

    private List<LeaderBoardDataModel> topperList;

    Activity myActivity;



    // constructor

    public CustomAdapterLeaderBoard(List<LeaderBoardDataModel> contactList, Activity activity) {

        this.topperList = contactList;
        myActivity = activity;
    }

    @Override
    public int getItemCount() {
        return topperList.size();
    }


    @Override
    public CustomAdapterLeaderBoard.ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_view_leader_board, viewGroup, false);

        return new CustomAdapterLeaderBoard.ContactViewHolder(itemView);
    }


    @Override

    public void onBindViewHolder(@NonNull CustomAdapterLeaderBoard.ContactViewHolder contactViewHolder, int i) {

        LeaderBoardDataModel ci = topperList.get(i);
        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.vPoint.setText(ci.points +"");
        String dpUrl = ci.dp;

        // setting dp on toppers leaderboard


        Glide.with(myActivity).load(dpUrl).into(contactViewHolder.vDp);





    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {


        public TextView vName;
        public TextView vPoint;
        public ImageView vDp;

        public ContactViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.leaderName);
            vPoint = (TextView) v.findViewById(R.id.leaderPoints);
            vDp = (ImageView)v.findViewById(R.id.dpToppers);
        }
    }
}

