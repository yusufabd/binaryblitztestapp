package com.yusufabd.binaryblitztestapp.screens.list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yusufabd.binaryblitztestapp.R;
import com.yusufabd.binaryblitztestapp.RecyclerClickCallback;
import com.yusufabd.domain.User;

import java.util.List;

/**
 * Created by yusufabd on 9/8/2017.
 */

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    private List<User> userList;
    private RecyclerClickCallback clickCallback;

    public UsersListAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User item = userList.get(holder.getAdapterPosition());
        holder.fullName.setText(item.getFirstName() + " " + item.getLastName());
        holder.email.setText(item.getEmail());

        String avatarUrl = item.getAvatarUrl();
        Log.d("LogTag", item.getId() + (avatarUrl != null ? avatarUrl : "null"));
        if (avatarUrl != null && !avatarUrl.isEmpty()){
            Picasso.with(holder.itemView.getContext()).load(avatarUrl).into(holder.avatar);
        }else {
            holder.avatar.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView fullName, email;

        ViewHolder(final View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.user_avatar);
            fullName = itemView.findViewById(R.id.user_full_name);
            email = itemView.findViewById(R.id.user_email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickCallback != null){
                        clickCallback.onItemClick(userList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public void setClickCallback(RecyclerClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }

}
