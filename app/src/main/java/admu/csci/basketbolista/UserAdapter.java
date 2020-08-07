package admu.csci.basketbolista;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class UserAdapter extends RealmRecyclerViewAdapter<User, UserAdapter.ViewHolder> {

    // IMPORTANT
    // THE ACTIVITY NEEDS TO BE PASSED SO YOU CAN GET THE LayoutInflator(see below)
    MainActivity4 activity;

    public UserAdapter(MainActivity4 activity, @Nullable OrderedRealmCollection<User> data, boolean autoUpdate){
        super(data, autoUpdate);
        // THIS IS TYPICALLY THE ACTIVITY YOUR RECYCLERVIEW IS IN
        this.activity = activity;
    }

    // THIS DEFINES WHAT VIEWS YOU ARE FILLING IN
    public class ViewHolder extends RecyclerView.ViewHolder {

        // declare variables
        TextView username;
        TextView password;
        ImageButton delete;
//        ImageButton edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initialize and point variables
            username = itemView.findViewById(R.id.username);
            password = itemView.findViewById(R.id.password);
            delete = itemView.findViewById(R.id.deleteButton);
//            edit = itemView.findViewById(R.id.editButton);
        }
    }

    // Create a view that looks like this particular layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        // create the raw view for this ViewHolder
        View v = activity.getLayoutInflater().inflate(R.layout.row_layout, parent, false);  // VERY IMPORTANT TO USE THIS STYLE
        // assign view to the viewholder
        return new ViewHolder(v);
    }

    // Given an empty viewholder, what goes inside it?
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        // gives you the data object at the given position
        User u = getItem(position);
        // copy all the values needed to the appropriate views
        holder.username.setText(u.getUsername());
        holder.password.setText(String.valueOf(u.getPassword()));
        // delete this particular realm data (passed to method in mainactivity4)
        holder.delete.setTag(u);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ///////////////////////CONFIRMATION OF DELETE///////////////////////////////////////
                AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                        .setMessage("Confirm delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // YES code
                                try{
                                    activity.delete(view);
                                    Toast.makeText(activity.getApplicationContext(), "User Deleted", Toast.LENGTH_SHORT).show();
                                }catch(Exception e){
                                    Toast.makeText(activity.getApplicationContext(), "Error Deleting", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // NO code
                                Toast.makeText(activity.getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.show();
            }
            //////////////////////////////////////////////////////////////
        });

        // FOR EDIT IF NEEDED
//        // edit this particular realm data (passed to method in mainactivity4)
//        holder.edit.setTag(u);
//        holder.edit.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                activity.edit(view);
//            }
//        });

    }
}
