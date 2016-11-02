package skywell.testappskywell.data.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import skywell.testappskywell.data.services.VKService;

/**
 * Created by Den on 30.10.16.
 */

public class RestApiClient {

    private static RestApiClient INSTANCE;
    private Retrofit retrofitGson;
    private Retrofit retrofitScalar;

    private RestApiClient() {}

    public static synchronized RestApiClient getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RestApiClient();
            INSTANCE.getClient();
        }
        return INSTANCE;
    }

    private void getClient() {
        final OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        retrofitGson = new Retrofit.Builder()
                .baseUrl(Config.getBaseUrl())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        retrofitScalar = new Retrofit.Builder()
                .baseUrl(Config.getBaseUrl())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();
    }

    public VKService getVKServiceGson() {
        return retrofitGson.create(VKService.class);
    }

    public VKService getVKServiceScalar() {
        return retrofitScalar.create(VKService.class);
    }
}
