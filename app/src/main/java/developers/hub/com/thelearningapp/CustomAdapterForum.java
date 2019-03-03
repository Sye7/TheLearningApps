package developers.hub.com.thelearningapp;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import developers.hub.com.thelearningapp.Helper.ForumDataModel;
import developers.hub.com.thelearningapp.Helper.LeaderBoardDataModel;

public class CustomAdapterForum extends RecyclerView.Adapter<CustomAdapterForum.ContactViewHolder> {

    private List<ForumDataModel> forumPostList;

    Activity myActivity;
    int db;


    // constructor

    public CustomAdapterForum(List<ForumDataModel> contactList, Activity activity) {

        this.forumPostList = contactList;
        myActivity = activity;
    }

    @Override
    public int getItemCount() {
        return forumPostList.size();
    }


    @Override
    public CustomAdapterForum.ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.forum_cardview, viewGroup, false);

        return new CustomAdapterForum.ContactViewHolder(itemView);
    }


    @Override

    public void onBindViewHolder(@NonNull final CustomAdapterForum.ContactViewHolder contactViewHolder,  int i) {

        final ForumDataModel ci = forumPostList.get(i);
         db =i;

        contactViewHolder.vName.setText(ci.getName());
        contactViewHolder.vTitle.setText(ci.getTitle());
        contactViewHolder.vPost.setText(ci.getPost());
        contactViewHolder.vTime.setText(ci.getTime());
        contactViewHolder.vLike.setText(ci.getLike());

        String picUrlTitle = ci.getTitle();




        final String dpUrl = ci.getDp();

        // setting dp on toppers leaderboard


        if(picUrlTitle.contains("https://firebasestorage"))
            contactViewHolder.vTitle.setText("");

        Glide.with(myActivity).load(dpUrl).into(contactViewHolder.vDp);
        Glide.with(myActivity).load(picUrlTitle).into(contactViewHolder.vPostImage);



        contactViewHolder.vReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ImageView rep =  (ImageView)   v.findViewById(R.id.forumReply);
                Intent intent = new Intent(myActivity, Discussion.class);
                intent.putExtra("Database", ci.getDb());
                myActivity.startActivity(intent);



            }
        });

        contactViewHolder.vLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Forum");

                databaseReference.orderByChild("title").equalTo(contactViewHolder.vTitle.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                            String s = childDataSnapshot.getKey();

                            Map<String,Object> taskMap = new HashMap<String,Object>();
                            taskMap.put("like", Integer.parseInt(ci.getLike())+1+"");

                               databaseReference.child(s).updateChildren(taskMap);


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                TextView textView = contactViewHolder.vLike;
                textView.setText(Integer.parseInt(textView.getText().toString())+1 + "");

                ImageView imageView = contactViewHolder.vLikeButton;
                imageView.setImageResource(R.drawable.forum_liked);






                Toast.makeText(myActivity, "Done"+textView.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }



    public static class ContactViewHolder extends RecyclerView.ViewHolder {


        public TextView vName;
        public TextView vTitle;
        public TextView vPost;
        public TextView vTime;
        public ImageView vDp;
        public TextView vLike;
        public ImageView vReply;
        public ImageView vLikeButton;
        public ImageView vPostImage;



        public ContactViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.nameForum);
            vTitle = (TextView) v.findViewById(R.id.forumHead);
            vPost = (TextView) v.findViewById(R.id.forumText);

            vTime = (TextView) v.findViewById(R.id.timeForum);
            vDp = (ImageView)  v.findViewById(R.id.dpForum);
            vLike = (TextView)   v.findViewById(R.id.totalLike);
            vReply = (ImageView)   v.findViewById(R.id.forumReply);
            vLikeButton = (ImageView)   v.findViewById(R.id.forumLike);
            vPostImage = (ImageView)  v.findViewById(R.id.titleAlterForum);


        }
    }
}

