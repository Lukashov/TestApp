package skywell.testappskywell.domain.repositories;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import skywell.testappskywell.data.response.UsersResponse;
import skywell.testappskywell.data.network.RestApiClient;
import skywell.testappskywell.data.services.VKService;

/**
 * Created by Den on 31.10.16.
 */

public class VKRepository {

    private static VKRepository INSTANCE;

    private VKService vkServiceGson;
    private VKService vkServiceScalar;

    public VKRepository() {
        vkServiceGson = RestApiClient.getInstance().getVKServiceGson();
        vkServiceScalar = RestApiClient.getInstance().getVKServiceScalar();
    }

    public static synchronized VKRepository getInstance() {
        return INSTANCE == null ? INSTANCE = new VKRepository() : INSTANCE;
    }

    private <T> Observable<T> getObservable(Observable<T> observable) {
        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<String> getUserWall(String id, String count, String extended) {
        return getObservable(vkServiceScalar.getUserWall(id, count, extended));
    }

    public Observable<UsersResponse> getUserInfo(String id, String photo) {
        return getObservable(vkServiceGson.getUsersInfo(id, photo));
    }
}
