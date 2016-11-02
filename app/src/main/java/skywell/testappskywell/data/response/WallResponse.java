package skywell.testappskywell.data.response;

import android.util.Log;

import org.json.JSONObject;

import java.util.List;

import skywell.testappskywell.data.model.Post;
import skywell.testappskywell.data.model.Wall;

/**
 * Created by Den on 31.10.16.
 */

public class WallResponse {

    private Wall response;

    public Wall getWall() {
        return response;
    }

    public void setWall(Wall response) {
        this.response = response;
    }
}
