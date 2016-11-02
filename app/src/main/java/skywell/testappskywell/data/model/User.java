package skywell.testappskywell.data.model;

/**
 * Created by Den on 01.11.16.
 */

public class User {
    private String uid;
    private String first_name;
    private String last_name;
    private String photo_100;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhoto() {
        return photo_100;
    }

    public void setPhoto(String photo_100) {
        this.photo_100 = photo_100;
    }


//  Only fo profiles field
    private String photo;
    private String sex;
    private String online;

    public String getPhotoProfile() {
        return photo;
    }

    public void setPhotoProfile(String photo) {
        this.photo = photo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
}
