package com.example.studentslist.realm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentslist.R;
import com.example.studentslist.realm.data.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users;
    private LayoutInflater inflater;

    public UserAdapter(List<User> users) {
        this.users = users;
        setHasStableIds(true);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return UserViewHolder.create(inflater, parent);
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getId();
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatar;
        private TextView userName;
        private TextView linkGoogle;
        private TextView linkGit;

        private UserViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.realmAvatar);
            userName = (TextView) itemView.findViewById(R.id.realmTextUserName);
            linkGoogle = (TextView) itemView.findViewById(R.id.textLinkGitHub);
            linkGit = (TextView) itemView.findViewById(R.id.textLinkGoogle);
        }


        public static UserViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            return new UserViewHolder(inflater.inflate(R.layout.list_item_realm, parent, false));
        }

        public void bind(User user) {
            avatar.setImageResource(user.getPhoto());
            userName.setText(user.getName());
            linkGoogle.setText(user.getLinkGoogle());
            linkGit.setText(user.getLinkGit());
        }
    }
}
