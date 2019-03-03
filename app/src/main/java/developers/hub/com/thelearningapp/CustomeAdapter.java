package developers.hub.com.thelearningapp;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import developers.hub.com.thelearningapp.Helper.DataModel;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.ContactViewHolder> {

        private List<DataModel> contactList;
        private Context myActivity;
        int index = 0;

        public CustomeAdapter(Context context, List<DataModel> contactList) {
            this.contactList = contactList;
            myActivity = context;
        }

        @Override
        public int getItemCount() {
            return contactList.size();
        }

        @Override
        public void onBindViewHolder(ContactViewHolder contactViewHolder,  int i) {
            final DataModel ci = contactList.get(i);
            index = i;
            contactViewHolder.vNameImage.setImageResource(ci.getImageCardName());
            contactViewHolder.description.setText(ci.getDescriptionCourse());
            contactViewHolder.vTitle.setText(ci.getCourseName());
            contactViewHolder.vPlayVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(myActivity,VideoDetailsU2.class);
                    intent.putExtra("youtubePlaylist",ci.getYoutubePlaylist());
                    intent.putExtra("index",index+ci.imageCardName);
                    myActivity.startActivity(intent);
                }
            });



        }

        @Override
        public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.card_view_open_course_activity, viewGroup, false);

            return new ContactViewHolder(itemView);
        }

        public static class ContactViewHolder extends RecyclerView.ViewHolder {


            public ImageView vNameImage;
            public TextView description;
            public TextView vTitle;
            public LinearLayout vPlayVideo;


            public ContactViewHolder(View v) {
                super(v);
                vNameImage = (ImageView) v.findViewById(R.id.image_card);
                description = (TextView) v.findViewById(R.id.description);
                vTitle = (TextView) v.findViewById(R.id.title_course);
                vPlayVideo = (LinearLayout)v.findViewById(R.id.youtubeToggle);

            }
        }
    }

