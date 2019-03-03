package developers.hub.com.thelearningapp.Helper;

public class DiscussionDataModel {

    String name;
    String content;
    String dp;


    public  DiscussionDataModel() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public DiscussionDataModel(String name, String content, String dp) {
        this.name = name;
        this.content = content;
        this.dp = dp;
    }
}
