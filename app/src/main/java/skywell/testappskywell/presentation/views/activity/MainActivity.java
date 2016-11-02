package skywell.testappskywell.presentation.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import skywell.testappskywell.R;
import skywell.testappskywell.data.model.User;
import skywell.testappskywell.data.model.Wall;
import skywell.testappskywell.presentation.base.BaseActivity;
import skywell.testappskywell.presentation.presenters.WallPresenter;
import skywell.testappskywell.presentation.utils.CircleTransform;
import skywell.testappskywell.presentation.views.ShowWallContract;
import skywell.testappskywell.presentation.views.adapters.PostsAdapter;

public class MainActivity extends BaseActivity implements ShowWallContract.WallView,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.root_AM)
    CoordinatorLayout root;

    @BindView(R.id.ivAvatar_AM)
    ImageView avatar;

    @BindView(R.id.tvName_AM)
    TextView name;

    @BindView(R.id.rvWall_AM)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_container_AM)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.progress_bar_layout)
    RelativeLayout loadMoreProgressView;

    private PostsAdapter adapter;

    private WallPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        root.setVisibility(View.INVISIBLE);
        presenter = new WallPresenter(this);
        presenter.subscribe();

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override public int getContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!presenter.showContent(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override public void showUser(User user) {
        root.setVisibility(View.VISIBLE);
        Picasso.with(this).load(user.getPhoto()).transform(new CircleTransform()).into(avatar);
        name.setText(user.getFirst_name() + " " + user.getLast_name());
    }

    @Override public void showPosts(Wall wall) {
        swipeRefreshLayout.setRefreshing(false);
        adapter = new PostsAdapter(wall, this, presenter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override public void showError(String error) {
        Snackbar.make(root, error, Snackbar.LENGTH_LONG).show();
    }

    @Override public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.refreshMessages();
    }

    @Override protected void onStop() {
        presenter.unSubscribe();
        super.onStop();
    }
}
