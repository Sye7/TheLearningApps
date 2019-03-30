package developers.hub.com.thelearningapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import developers.hub.com.thelearningapp.Helper.ForumDataModel;
import developers.hub.com.thelearningapp.Helper.LeaderBoardDataModel;
import developers.hub.com.thelearningapp.Helper.ProfileInfo;

import static developers.hub.com.thelearningapp.profile.downloadUri;

public class Forum extends AppCompatActivity {


    private CustomAdapterForum customAdapterForum;
    public static ArrayList<ForumDataModel> ourFourm;
    public RelativeLayout relativeLayout;
    public CardView cardView;
    RecyclerView recList;

    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener childEventListener;

    String name;
    String title;
    String post;
    String time;
    String like;
    String dp;

    EditText writePost;
    ImageButton send;
    ImageButton addPic;
    Uri selectedImageUri;

    int RC_PHOTO_PICKER = 1;

    private StorageReference mChatPhotoStorageRef;
    private FirebaseStorage mFirebaseStorage;

    @Override
    protected void onStop() {
        super.onStop();
        detachDbReadListener();
    }


    public void cang() {

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);

    }
    // bottom nav

    // Bottom Navigation

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_explore:
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_search:
                    cang();
                    return true;
                case R.id.navigation_bookmark:
                    return true;
                case R.id.navigation_profile:
                    Intent intent2 = new Intent(getApplicationContext(), ProfileViewNav.class);
                    startActivity(intent2);
                    return true;

            }
            return false;
        }
    };

    // dialog box

    public void editPost(final View view) {


        LayoutInflater factory = LayoutInflater.from(this);


        final View deleteDialogView = factory.inflate(R.layout.post_edit_dialog, null);

        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();

        deleteDialog.setView(deleteDialogView);

        deleteDialogView.findViewById(R.id.sendForum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your business logic

                final ProfileInfo[] profileInfo = new ProfileInfo[1];

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


                DatabaseReference temp = FirebaseDatabase.getInstance().getReference().child("profile/");


                temp.orderByChild("uid").equalTo(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                            profileInfo[0] = (ProfileInfo) childDataSnapshot.getValue(ProfileInfo.class);

                        }

                        String nameSend = "";
                        String dpSend = "";
                        try {

                            nameSend = profileInfo[0].getName();
                            dpSend = profileInfo[0].getDp();

                        } catch (Exception e) {

                          Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_SHORT).show();
                        }

                        //

                        writePost = deleteDialogView.findViewById(R.id.writeInForum);

                        post = writePost.getText().toString();

                        title = post.substring(0, post.length() / 3) + "....";


                        // upload


                        if (selectedImageUri != null) {

                            bang();
                            deleteDialog.dismiss();


                        } else {


                            if (nameSend.length() >= 3) {
                                java.util.Date date = new java.util.Date();
                                ForumDataModel model = new ForumDataModel(nameSend, title, post, date.toString(), 0 + "", dpSend);

                                mMessageDatabaseReference.push().setValue(model, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {


                                    }
                                });
                            }

                            deleteDialog.dismiss();

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
        deleteDialogView.findViewById(R.id.addImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // TODO: Fire an intent to show an image picker
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete Action Using"), RC_PHOTO_PICKER);


            }
        });

        deleteDialog.show();

    }

    public void bang() {


        //Get a reference to store file at image_photos/<FileName>
        final StorageReference photoRef = mChatPhotoStorageRef.child(selectedImageUri.getLastPathSegment());


        //Upload file to firebase storage
        photoRef.putFile(selectedImageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                photoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onSuccess(Uri uri) {

                        // got image url
                        downloadUri = uri;

                        title = downloadUri.toString();

                        java.util.Date date = new java.util.Date();
                        ForumDataModel model = new ForumDataModel(name, title, post, date.toString(), 0 + "", dp);
                        mMessageDatabaseReference.push().setValue(model);

                        Toast.makeText(Forum.this, "Executed", Toast.LENGTH_SHORT).show();


                    }


                });

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK) {

            selectedImageUri = data.getData();

        }

    }


    private void detachDbReadListener() {

        if (childEventListener != null) {
            mMessageDatabaseReference.removeEventListener(childEventListener);
            childEventListener = null;
        }
    }


    static int db = 0;

    private void attachDbReadListener() {

        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    ForumDataModel forumDataModel = (ForumDataModel) dataSnapshot.getValue(ForumDataModel.class);
                    name = forumDataModel.getName();
                    time = forumDataModel.getTime();
                    title = forumDataModel.getTitle();
                    post = forumDataModel.getPost();
                    like = forumDataModel.getLike();
                    dp = forumDataModel.getDp();

                    ourFourm.add(new ForumDataModel(name, title, post, time, like, dp, ++db));

                    customAdapterForum.notifyDataSetChanged();


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


    boolean backPressToExit = false;

    @Override
    public void onBackPressed() {


        if (backPressToExit) {

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


    public void likeForum(View view) {

        ImageView imageView = (ImageView) findViewById(R.id.forumLike);
        TextView tv = (TextView) findViewById(R.id.totalLike);
        int lik = Integer.parseInt(tv.getText().toString()) + 1;
        tv.setText(lik + " ");

        imageView.setImageResource(R.drawable.forum_liked);

    }


    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        // Bottom Navigation

        navigation = (BottomNavigationView) findViewById(R.id.bottomForum_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setSelectedItemId(R.id.navigation_bookmark);


        send = (ImageButton) findViewById(R.id.sendForum);
        writePost = (EditText) findViewById(R.id.writeInForum);
        addPic = (ImageButton) findViewById(R.id.addImage);


        mFirebaseStorage = FirebaseStorage.getInstance();
        mChatPhotoStorageRef = mFirebaseStorage.getReference().child("forum_image");


        mMessageDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Forum");

        // Static toppers
        ourFourm = new ArrayList<>();


        // RecycleView


        recList = (RecyclerView) findViewById(R.id.forumRecycleView);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);


        recList.setItemAnimator(new DefaultItemAnimator());
        customAdapterForum = new CustomAdapterForum(ourFourm, this);
        recList.setAdapter(customAdapterForum);


        //GridLayoutManager can also be used


        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        attachDbReadListener();


    }


}
