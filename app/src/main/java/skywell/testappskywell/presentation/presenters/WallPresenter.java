package skywell.testappskywell.presentation.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKScopes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rx.subscriptions.CompositeSubscription;
import skywell.testappskywell.data.network.PreferencesManager;
import skywell.testappskywell.data.response.UsersResponse;
import skywell.testappskywell.data.response.WallResponse;
import skywell.testappskywell.domain.repositories.VKRepository;
import skywell.testappskywell.presentation.views.ShowWallContract;

/**
 * Created by Den on 31.10.16.
 */

public class WallPresenter implements ShowWallContract.WallPresenter {

    private final ShowWallContract.WallView view;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    private String token;
    private String ownerId;
    private boolean b = false;

    private static final String COUNT_POSTS = "100";
    private static final String SIZE_PHOTO = "photo_100";
    private static final String EXTENDED = "1";

    public WallPresenter(ShowWallContract.WallView view) {
        this.view = view;
    }

    public void loginToVK() {
        VKSdk.login((Activity) view, VKScopes.STATS, VKScope.WALL);
    }

    @Override public void subscribe() {
        VKSdk.wakeUpSession((Context) view, new VKCallback<VKSdk.LoginState>() {
            @Override
            public void onResult(VKSdk.LoginState res) {
                switch (res) {
                    case LoggedOut:
                        loginToVK();
                        return;
                    case LoggedIn:
                        navigateToWallScreen();
                        return;
                    default:
                        break;
                }
            }

            @Override
            public void onError(VKError error) {
                view.showError(error.errorMessage);
            }
        });
    }

    private void navigateToWallScreen() {
        if (PreferencesManager.getInstance().getAccessToken() != null && PreferencesManager.getInstance().getUserId() != null) {
            getUser(PreferencesManager.getInstance().getUserId());
            getPosts(PreferencesManager.getInstance().getUserId());
        } else {
            loginToVK();
        }
    }

    @Override public boolean showContent(int requestCode, int resultCode, Intent data) {

        VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                PreferencesManager.getInstance().setAccessToken(res.accessToken);
                PreferencesManager.getInstance().setUserId(res.userId);

                token = res.accessToken;
                ownerId = res.userId;

                getPosts(ownerId);
                getUser(ownerId);

                b = true;
            }
            @Override
            public void onError(VKError error) {
                view.showError(error.errorMessage);
                b = false;
            }
        });
        return b;
    }

    private void getPosts(String id) {
        compositeSubscription.add(VKRepository.getInstance().getUserWall(id, COUNT_POSTS, EXTENDED).subscribe(this::onResponseSuccess, this::onResponseError));
    }

    private void getUser (String id) {
        compositeSubscription.add(VKRepository.getInstance().getUserInfo(id, SIZE_PHOTO).subscribe(this::onResponseSuccess, this::onResponseError));
    }

    private void onResponseSuccess(String response) {
        if(response != null) {
            String jsonString = response.replaceFirst(response.substring(response.indexOf("[") + 1, response.indexOf(",") + 1), "");

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            WallResponse wallResponse = gson.fromJson(jsonString, WallResponse.class);
            view.showPosts(wallResponse.getWall());
        }
    }

    private void onResponseSuccess(UsersResponse response) {
        if(response != null) {
            view.showUser(response.getUsers().get(0));
        }
    }

    private void onResponseError(Throwable throwable) {
        view.showError(throwable.getMessage());
    }

    @Override public void refreshMessages() {
        getPosts(PreferencesManager.getInstance().getUserId());
    }

    @Override public void unSubscribe() {
        compositeSubscription.unsubscribe();
    }

}
