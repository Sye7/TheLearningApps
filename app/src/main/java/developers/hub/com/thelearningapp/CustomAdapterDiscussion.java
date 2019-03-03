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

import developers.hub.com.thelearningapp.Helper.DiscussionDataModel;


public class CustomAdapterDiscussion extends RecyclerView.Adapter<CustomAdapterDiscussion.ContactViewHolder> {

    private List<DiscussionDataModel> discussionList;

    Activity myActivity;



    // constructor



    public CustomAdapterDiscussion(List<DiscussionDataModel> contactList, Activity activity) {

        this.discussionList = contactList;
        myActivity = activity;
    }

    @Override
    public int getItemCount() {
        return discussionList.size();
    }


    @Override
    public CustomAdapterDiscussion.ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.discussion_cardview, viewGroup, false);

        return new CustomAdapterDiscussion.ContactViewHolder(itemView);
    }


    @Override

    public void onBindViewHolder(@NonNull CustomAdapterDiscussion.ContactViewHolder contactViewHolder, int i) {

        DiscussionDataModel ci = discussionList.get(i);
        contactViewHolder.vName.setText(ci.getName());
        contactViewHolder.vContent.setText(ci.getContent());
        String dpUrl = ci.getDp();

        // setting dp on toppers leaderboard


        Glide.with(myActivity).load(dpUrl).into(contactViewHolder.vDp);


    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {


        public TextView vName;
        public TextView vContent;
        public ImageView vDp;

        public ContactViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.disName);
            vContent = (TextView) v.findViewById(R.id.disContent);
            vDp = (ImageView)v.findViewById(R.id.disDp);
        }
    }
}

