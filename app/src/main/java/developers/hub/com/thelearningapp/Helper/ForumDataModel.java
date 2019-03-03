package developers.hub.com.thelearningapp.Helper;

public class ForumDataModel {

    String name;
    String title;
    String post;
    String time;
    String like;
    String dp;
    int db;

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public ForumDataModel(String name, String title, String post, String time, String like, String dp, int db) {
        this.name = name;
        this.title = title;
        this.post = post;
        this.time = time;
        this.like = like;
        this.dp = dp;
        this.db = db;
    }

    public ForumDataModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public ForumDataModel(String name, String title, String post, String time, String like, String dp) {
        this.name = name;
        this.title = title;
        this.post = post;
        this.time = time;
        this.like = like;
        this.dp = dp;
    }
}
