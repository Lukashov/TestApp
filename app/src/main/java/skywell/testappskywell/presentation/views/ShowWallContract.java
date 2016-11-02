package skywell.testappskywell.presentation.views;

import android.content.Intent;

import java.util.List;

import skywell.testappskywell.data.model.Post;
import skywell.testappskywell.data.model.User;
import skywell.testappskywell.data.model.Wall;
import skywell.testappskywell.presentation.base.BasePresenter;
import skywell.testappskywell.presentation.base.BaseView;

/**
 * Created by Den on 31.10.16.
 */

public interface ShowWallContract {

    interface WallView extends BaseView {
        void showUser(User user);
        void showPosts(Wall wall);
    }

    interface WallPresenter extends BasePresenter{
        boolean showContent(int requestCode, int resultCode, Intent data);

        void refreshMessages();
    }
}
