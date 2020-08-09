package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

@EActivity(R.layout.activity_main13)
public class MainActivity13 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome6)
    ImageView logoToHome6;
    @ViewById(R.id.inputCompare)
    EditText inputCompare;
    @ViewById(R.id.buttonCompare)
    Button buttonCompare;
    // Realm
    Realm realm;

    @AfterViews
    public void init(){
        realm = Realm.getDefaultInstance();
    }

    @Click(R.id.buttonCompare)
    public void compareClick(View view){
        // + verify if searchable
        User result = realm.where(User.class).equalTo("username", inputCompare.getText().toString()).findFirst();
        if (result != null) {
            // search results were not null (may nahanap)
            // check if the one being searched is the same UUID as the one searching, DONT ALLOW
            SharedPreferences prefsLogin = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
            String uuid = prefsLogin.getString("uuid",null);
            User comparer = realm.where(User.class).equalTo("uuid",uuid).findFirst();

            User compared = realm.where(User.class).equalTo("username",inputCompare.getText().toString()).findFirst();
            // find the uuid of the one being edited (editor.getUuid), then compare it with the uuid of the duplicate name (owner.getUuid)
            if((comparer.getUuid()).equals(compared.getUuid())){
                // DONT ALLOW COMPARISON (same lang yung tao)
                Toast.makeText(getApplicationContext(), "Can't compare with yourself.", Toast.LENGTH_SHORT).show();
            }else{
                // ALLOW COMPARISON
                // get uuid of searched and init there
                // save UUID to shared prefs for welcome screen
                SharedPreferences prefsSearched = getSharedPreferences("myPrefsSearched", MODE_PRIVATE);
                SharedPreferences.Editor editorSearched = prefsSearched.edit();
                editorSearched.putString("uuid",compared.getUuid());
                editorSearched.apply();
                // go to result
                finish();
                MainActivity14_.intent(this).start();
            }
        } else {
            // search results were null (walang nahanap)
            Toast.makeText(getApplicationContext(), "No such user exists", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.logoToHome6)
    public void toHome6Click(View view){
        MainActivity5_.intent(this).start();
    }
}