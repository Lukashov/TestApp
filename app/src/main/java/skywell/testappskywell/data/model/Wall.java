package skywell.testappskywell.data.model;

import java.util.List;

/**
 * Created by Den on 02.11.16.
 */

public class Wall {
    private List<Post> wall;

    private List<User> profiles;

    public List<Post> getPosts() {
        return wall;
    }

    public void setPosts(List<Post> response) {
        this.wall = response;
    }

    public List<User> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<User> profiles) {
        this.profiles = profiles;
    }
}
