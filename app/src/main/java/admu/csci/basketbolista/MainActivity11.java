package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;

@EActivity(R.layout.activity_main11)
public class MainActivity11 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome4)
    ImageView logoToHome4;
    @ViewById(R.id.inputSearch)
    EditText inputSearch;
    @ViewById(R.id.buttonSearch)
    Button buttonSearch;
    // Realm
    Realm realm;

    @AfterViews
    public void init(){
        realm = Realm.getDefaultInstance();
    }

    @Click(R.id.buttonSearch)
    public void searchClick(View view){
        // + verify if searchable
        User result = realm.where(User.class).equalTo("username", inputSearch.getText().toString()).findFirst();
        if (result != null) {
            // search results were not null (may nahanap)
                 // get uuid of searched and init there
            // save UUID to shared prefs for welcome screen
            SharedPreferences prefsSearched = getSharedPreferences("myPrefsSearched", MODE_PRIVATE);
            SharedPreferences.Editor editorSearched = prefsSearched.edit();
            editorSearched.putString("uuid",result.getUuid());
            editorSearched.apply();
            // go to result
            MainActivity12_.intent(this).start();
        } else {
            // search results were null (walang nahanap)
            Toast.makeText(getApplicationContext(), "No such user exists", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.logoToHome4)
    public void toHome4Click(View view){
        MainActivity5_.intent(this).start();
    }
}