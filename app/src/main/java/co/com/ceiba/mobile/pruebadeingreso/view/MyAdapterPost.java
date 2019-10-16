package co.com.ceiba.mobile.pruebadeingreso.view;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.view.Model.Posts;
import co.com.ceiba.mobile.pruebadeingreso.view.Model.Users;

public class MyAdapterPost  extends RecyclerView.Adapter<MyAdapterPost.ViewHolderDatos> {
    private List<Posts> postsList;

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView title;
        TextView body;
        public ViewHolderDatos(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titlePosts);
            body = itemView.findViewById(R.id.bodyPosts);
        }
        public void asignarDatos(Posts post) {
            title.setText(post.getTitle());
            body.setText(post.getBody());

        }
    }

    MyAdapterPost(List<Posts> postsList) {
        this.postsList = postsList;
    }


    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_item,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(postsList.get(position));
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }





    }

