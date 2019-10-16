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
import co.com.ceiba.mobile.pruebadeingreso.view.Interface.JsonPlaceHolderApi;
import co.com.ceiba.mobile.pruebadeingreso.view.Model.Users;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolderDatos> {
    private List<Users> usersList;
    private List<Users> usersListFull;
    private Button button;


    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView name;
        TextView phone;
        TextView email;
        Button button;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            button = itemView.findViewById(R.id.btn_view_post);
        }

        public void asignarDatos(Users users) {
            name.setText(users.getName());
            phone.setText(users.getPhone());
            email.setText(users.getEmail());
        }
    }

        MyAdapter(List<Users> usersList) {
        this.usersList = usersList;
        usersListFull = new ArrayList<>(usersList);
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
        final Users users = usersList.get(position);
        holder.asignarDatos(usersList.get(position));
        holder.button.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PostActivity.class);
                intent.putExtra("name", users.getName() );
                intent.putExtra("email", users.getEmail());
                intent.putExtra("phone", users.getPhone());
                intent.putExtra("userId", users.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
    public Filter getFilter() {
        return usersFilter;
    }

    private Filter usersFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Users> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(usersListFull);
            } else {
                String filerPattern = constraint.toString().toLowerCase().trim();
                for (Users user: usersListFull){
                    if(user.getName().toLowerCase().contains(filerPattern)){
                        filteredList.add(user);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

     @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        usersList.clear();
        usersList.addAll((List) results.values);

        notifyDataSetChanged();
    }
};
}

