package skywell.testappskywell.data.services;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import skywell.testappskywell.data.response.UsersResponse;
import skywell.testappskywell.data.response.WallResponse;

/**
 * Created by Den on 31.10.16.
 */

public interface VKService {
    @GET("wall.get") Observable<String> getUserWall(@Query("owner_id") String ownerId,
                                                    @Query("count") String count,
                                                    @Query("extended") String extended);

    @GET("users.get") Observable<UsersResponse> getUsersInfo(@Query("user_ids") String ownerId,
                                                             @Query("fields") String photo);
}
