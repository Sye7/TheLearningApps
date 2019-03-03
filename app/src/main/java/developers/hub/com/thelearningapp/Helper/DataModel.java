package developers.hub.com.thelearningapp.Helper;

public class DataModel {



    public int imageCardName;
    public String courseName;
    public String descriptionCourse;
    public String youtubePlaylist;



    public DataModel(){}

    public int getImageCardName() {
        return imageCardName;
    }

    public void setImageCardName(int imageCardName) {
        this.imageCardName = imageCardName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescriptionCourse() {
        return descriptionCourse;
    }

    public void setDescriptionCourse(String descriptionCourse) {
        this.descriptionCourse = descriptionCourse;
    }

    public String getYoutubePlaylist() {
        return youtubePlaylist;
    }

    public void setYoutubePlaylist(String youtubePlaylist) {
        this.youtubePlaylist = youtubePlaylist;
    }

    public DataModel(int imageCardName, String courseName, String descriptionCourse, String youtubePlaylist) {
        this.imageCardName = imageCardName;
        this.courseName = courseName;
        this.descriptionCourse = descriptionCourse;
        this.youtubePlaylist = youtubePlaylist;
    }
}
