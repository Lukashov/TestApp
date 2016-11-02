package skywell.testappskywell.data.response;

import java.util.List;

import skywell.testappskywell.data.model.User;

/**
 * Created by Den on 31.10.16.
 */

public class UsersResponse {

    private List<User> response;

    public List<User> getUsers() {
        return response;
    }

    public void setUsers(List<User> response) {
        this.response = response;
    }

}
