package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;

@EActivity(R.layout.activity_main5)
public class MainActivity5 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.displayUsername)
    TextView displayUsername;
    @ViewById(R.id.buttonToProfile)
    Button buttonToProfile;
    @ViewById(R.id.buttonToSearch)
    Button buttonToSearch;
    @ViewById(R.id.buttonToCompare)
    Button buttonToCompare;
    @ViewById(R.id.buttonLogout)
    Button buttonLogout;
    // Realm
    Realm realm;

    @AfterViews
    public void init(){
        // Realm
        realm = Realm.getDefaultInstance();
        // SharedPrefs
        SharedPreferences prefsLogin = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
        String uuid = prefsLogin.getString("uuid",null);
        User result = realm.where(User.class).equalTo("uuid",uuid).findFirst();
        // display name
        displayUsername.setText(result.getUsername());
    }

    @Click(R.id.buttonToProfile)
    public void toProfileClick(View view){
        // + sharedpref current user's UUID tas send sa mainactivity6 initialization
        MainActivity6_.intent(this).start();
    }

    @Click(R.id.buttonToSearch)
    public void toSearchClick(View view){
        MainActivity11_.intent(this).start();
    }

    @Click(R.id.buttonToCompare)
    public void toCompareClick(View view){
        MainActivity13_.intent(this).start();
    }

    @Click(R.id.buttonLogout)
    public void logoutClick(View view){
        MainActivity_.intent(this).start();
    }
}