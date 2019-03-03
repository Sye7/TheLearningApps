package developers.hub.com.thelearningapp.Helper;

public class LeaderBoardDataModel {

    public String name;
    public int points;
    public String dp;


    public LeaderBoardDataModel(String name, int points, String year) {
        this.name = name;
        this.points = points;
        this.dp = year;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getYear() {
        return dp;
    }

    public void setYear(String year) {
        this.dp = year;
    }
}
