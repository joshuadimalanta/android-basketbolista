package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@EActivity(R.layout.activity_main4)
public class MainActivity4 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.recyclerView)
    RecyclerView recyclerView;
    @ViewById(R.id.buttonClearRealm)
    Button buttonClearRealm;
    // RealmObject Adapter
    UserAdapter adapter;
    // Realm
    Realm realm;

    @AfterViews
    public void init() {
        // initialize the RecyclerView row_layout
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        // get realm data
        realm = Realm.getDefaultInstance();
        // load up the live results for this query
        RealmResults<User> list = realm.where(User.class).findAll().sort("username", Sort.ASCENDING);
        // attach adapter to the RecyclerView
        adapter = new UserAdapter(this, list, true);
        recyclerView.setAdapter(adapter);
    }

    // delete called by UserAdapter
    public void delete(View view){
        User user = (User) view.getTag();
        // double-delete safeguard
        if (user.isValid()){
            realm.beginTransaction();
            user.deleteFromRealm();
            realm.commitTransaction();
        }
    }

    @Click(R.id.buttonClearRealm)
    public void clear2Click(View view){
        // find the entries you wish to delete
        final RealmResults<User> list = realm.where(User.class).findAll();
        ///////////////////////CONFIRMATION OF DELETE///////////////////////////////////////
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("Confirm clear all users?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // YES code
                        try{
                            realm.beginTransaction();
                            list.deleteAllFromRealm();  // delete the list
                            realm.commitTransaction();
                            Toast.makeText(getApplicationContext(), "All Users Cleared", Toast.LENGTH_SHORT).show();
                        }catch(Exception e){
                            Toast.makeText(getApplicationContext(), "Error Clearing", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // NO code
                        Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
        //////////////////////////////////////////////////////////////
    }

    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}