package developers.hub.com.thelearningapp;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class VideoDetailsU2 extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaybackEventListener, YouTubePlayer.PlayerStateChangeListener {


    YouTubePlayerView youTubePlayerView;
    int index = 0;
    String API_ID = "AIzaSyCPT-lN2xFNbnuXDv0UPreVsjpxJLkUuVE";
    String PLAY_LIST = "PLX9Zi6XTqOKQ7TdRz0QynGIKuMV9Q2H8E";
    ImageView seekButton;
    ImageView sendButton;
    EditText editText;
    static YouTubePlayer player;

    ListView listView;

   ArrayList<String> forumArrayList;
   ArrayAdapter forumArrayAdapter;

    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener childEventListener;



   public void seekingBack(View view){

       player.seekRelativeMillis(-10000);
   }

    public void seeking(View view){


    //    player.seekRelativeMillis(10000);

        player.seekToMillis(player.getCurrentTimeMillis() + 10000);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_details_u2);


        Intent intent = getIntent();
        PLAY_LIST = intent.getStringExtra("youtubePlaylist");
        index = intent.getIntExtra("index",1);

       seekButton = (ImageView)findViewById(R.id.seekButton);

       editText = (EditText)findViewById(R.id.forumEditText);
       sendButton = (ImageView) findViewById(R.id.sendBeginner);
       listView = (ListView)findViewById(R.id.ForumBeginner);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        forumArrayList = new ArrayList<>();
       forumArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_checked,forumArrayList);

       listView.setAdapter(forumArrayAdapter);



       mMessageDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Forum").child("Biginner"+index);

       attachDbReadListener();

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.playerViewYoutube);
        youTubePlayerView.initialize(API_ID,this);



    }


    // data Firebase



    private void attachDbReadListener() {

        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    forumArrayList.add(dataSnapshot.getValue().toString());
                    forumArrayAdapter.notifyDataSetChanged();

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


    public void sendingQuery (View view)
        {

            String message = editText.getText().toString();
            editText.setText("");
            mMessageDatabaseReference.push().setValue(message, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                }
            });


        }

        YouTubePlayer uPlayer;

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.setPlaybackEventListener(this);
        youTubePlayer.setPlayerStateChangeListener(this);
        player = youTubePlayer;
        uPlayer = youTubePlayer;


        if(!b){

            youTubePlayer.cuePlaylist(PLAY_LIST);





        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {




    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {


    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }

    public void rating(View view) {

        final RatingBar bar = (RatingBar)findViewById(R.id.ratingB);
        ImageView tick = (ImageView) findViewById(R.id.tickk);
        float f = bar.getRating();
        bar.setRating(f);
        bar.setVisibility(View.INVISIBLE);
        tick.setVisibility(View.INVISIBLE);

       Snackbar.make(view, "Thank You for your valuable feedback :)",Snackbar.LENGTH_SHORT).show();

    }
}
