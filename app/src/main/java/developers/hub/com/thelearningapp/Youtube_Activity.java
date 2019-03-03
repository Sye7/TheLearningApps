package developers.hub.com.thelearningapp;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import developers.hub.com.thelearningapp.ModelYoutube.CustomAdapterYoutube;
import developers.hub.com.thelearningapp.ModelYoutube.YoutubeVideoDetails;

public class Youtube_Activity extends AppCompatActivity {


    ListView youtubeListView;
    ArrayList<YoutubeVideoDetails> videoDetailsArrayList;
    CustomAdapterYoutube customAdapterYoutube;

    String API_ID = "AIzaSyCPT-lN2xFNbnuXDv0UPreVsjpxJLkUuVE";
  //  String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelid=UC5MtCj2LVwX30SEFdwXlww&maxResults=12&key=AIzaSyCPT-lN2xFNbnuXDv0UPreVsjpxJLkUuVE";


       String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCkGS_3D0HEzfflFnG0bD24A&maxResults=40&key=AIzaSyCPT-lN2xFNbnuXDv0UPreVsjpxJLkUuVE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_);

    //Init

        youtubeListView = (ListView) findViewById(R.id.youtubeListView);
        videoDetailsArrayList = new ArrayList<>();
        customAdapterYoutube = new CustomAdapterYoutube(this,videoDetailsArrayList);

        displayVideo();



    }

    private void displayVideo() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("items");

                    for(int i =0; i<jsonArray.length();i++){

                        JSONObject object = jsonArray.getJSONObject(i);
                        JSONObject jsonVideoId = object.getJSONObject("id");
                        JSONObject jsonSnippet = object.getJSONObject("snippet");
                        JSONObject jsonThumbnails = jsonSnippet.getJSONObject("thumbnails").getJSONObject("medium");

                        String video_Id = jsonVideoId.getString("videoId");

                            YoutubeVideoDetails videoDetails = new YoutubeVideoDetails();
                            videoDetails.setVideoId(video_Id);
                            videoDetails.setVideoDescription(jsonSnippet.getString("description"));
                            videoDetails.setVideotitle(jsonSnippet.getString("title"));
                            videoDetails.setVideoUrl(jsonThumbnails.getString("url"));

                            videoDetailsArrayList.add(videoDetails);
                    }

                    youtubeListView.setAdapter(customAdapterYoutube);
                    customAdapterYoutube.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Youtube_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }

        );

        requestQueue.add(stringRequest);

    }
}
