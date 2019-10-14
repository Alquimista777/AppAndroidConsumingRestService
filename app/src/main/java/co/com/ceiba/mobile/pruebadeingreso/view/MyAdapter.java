package co.com.ceiba.mobile.pruebadeingreso.view;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.view.Model.Posts;
import co.com.ceiba.mobile.pruebadeingreso.view.Model.Users;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolderDatos> {

    List<Users> usersList;

    public MyAdapter(List<Users> usersList) {
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(usersList.get(position));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView name;
        TextView phone;
        TextView email;

        public ViewHolderDatos(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);


        }


        public void asignarDatos(Users users) {
            name.setText(users.getName());
            phone.setText(users.getPhone());
            email.setText(users.getEmail());
        }
    }
}

