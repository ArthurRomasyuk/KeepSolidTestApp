package com.example.arutr.keepsolidapp.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arutr.keepsolidapp.R;
import com.example.arutr.keepsolidapp.fragment.UserFragment;
import com.example.arutr.keepsolidapp.models.User;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> users;
    private UserFragment.OnUserListFragmentInteractionListener listener;
    private Context context;

    public UserAdapter(List<User> users, UserFragment.OnUserListFragmentInteractionListener listener, Context context) {
        this.users = users;
        this.listener = listener;
        this.context =context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.user = users.get(position);
        holder.tvUsername.setText(users.get(position).getUsername());
        if (users.get(position).isOnline()) {
            holder.ivOnlineStatus.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.checkbox_blank_circle_green));
        } else {
            holder.ivOnlineStatus.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.checkbox_blank_circle_grey));
        }
        switch (users.get(position).getCategory()){
            case FRIENDS:
                holder.ivCategory.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.account_multiple));
                break;
            case FAMILY:
                holder.ivCategory.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.home_variant));
                break;
            case WORK:
                holder.ivCategory.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.desktop_classic));
                break;
            case OTHER:
                holder.ivCategory.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.help));
                break;
        }

        holder.view.setOnClickListener(new View.OnClickListener()

                                        {
                                            @Override
                                            public void onClick(View v) {
                                                if (null != listener) {
                                                    listener.onListFragmentInteraction(holder.user);
                                                }
                                            }
                                        }

        );
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View view;

        @BindView(R.id.username)
        TextView tvUsername;

        @BindView(R.id.onlineStatus)
        ImageView ivOnlineStatus;

        @BindView(R.id.category)
        ImageView ivCategory;

        User user;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
