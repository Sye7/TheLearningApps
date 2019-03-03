package developers.hub.com.thelearningapp.Helper;

public class ProfileInfo {

    String name;
    String contactInfo;
    String college;
    String year;
    String city;
    String dp;
    int point;
    String uid;
    int rank;

    public ProfileInfo() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public ProfileInfo(String name, String contactInfo, String college, String year, String city, String dp, int point, String uid, int rank) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.college = college;
        this.year = year;
        this.city = city;
        this.dp = dp;
        this.point = point;
        this.uid = uid;
        this.rank = rank;
    }
}

