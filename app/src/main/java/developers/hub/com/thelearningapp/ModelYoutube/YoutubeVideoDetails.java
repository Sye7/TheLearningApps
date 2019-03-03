package developers.hub.com.thelearningapp.ModelYoutube;

public class YoutubeVideoDetails {

    public  String videoId,videoDescription,Videotitle,videoUrl;

    public YoutubeVideoDetails(String videoId, String videoDescription, String videotitle, String videoUrl) {
        this.videoId = videoId;
        this.videoDescription = videoDescription;
        Videotitle = videotitle;
        this.videoUrl = videoUrl;
    }

    public YoutubeVideoDetails() {}

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getVideotitle() {
        return Videotitle;
    }

    public void setVideotitle(String videotitle) {
        Videotitle = videotitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
