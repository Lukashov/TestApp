package skywell.testappskywell.presentation.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skywell.testappskywell.R;
import skywell.testappskywell.data.model.Post;
import skywell.testappskywell.data.model.User;
import skywell.testappskywell.data.model.Wall;
import skywell.testappskywell.presentation.presenters.WallPresenter;
import skywell.testappskywell.presentation.utils.CircleTransform;
import skywell.testappskywell.presentation.utils.Utils;

/**
 * Created by Den on 01.11.16.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private Wall wall;
    private List<Post> list;
    private Context context;
    private WallPresenter presenter;

    public PostsAdapter(Wall wall, Context context, WallPresenter presenter) {
        this.wall = wall;
        this.list = wall.getPosts();
        this.context = context;
        this.presenter = presenter;
    }

    @Override public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post_wall, parent, false);
        return new ViewHolder(itemView);
    }

    @Override public void onBindViewHolder(PostsAdapter.ViewHolder holder, int position) {
        if (list.get(position).getAttachment() != null && list.get(position).getAttachment().getPhoto() != null) {
            Picasso.with(context).load(list.get(position)
                    .getAttachment()
                    .getPhoto()
                    .getSrc_big())
                    .into(holder.imageView);
        }

        for (User user: wall.getProfiles()) {
            if (user.getUid().equals(list.get(position).getFrom_id())) {
                Picasso.with(context).load(user.getPhotoProfile())
                        .transform(new CircleTransform())
                        .into(holder.ivAvatar);

                holder.tvName.setText(user.getFirst_name() + " " + user.getLast_name());
            }
        }

        holder.tvText.setText(list.get(position).getText());
    }

    @Override public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_headline_LIPW)
        ImageView imageView;
//        @BindView(R.id.tv_last_time_response_LIPW)
//        TextView tvTime;
        @BindView(R.id.tv_body_LIRM)
        TextView tvText;
        @BindView(R.id.ivAvatar_LIPW)
        ImageView ivAvatar;
        @BindView(R.id.tv_recent_name_LIPW)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
