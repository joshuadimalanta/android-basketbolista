package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

@EActivity(R.layout.activity_main16)
public class MainActivity16 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome10)
    ImageView logoToHome10;
    @ViewById(R.id.inputHighlight)
    EditText inputHighlight;
    @ViewById(R.id.buttonSearchHighlight)
    Button buttonSearchHighlight;
    // Realm
    Realm realm;

    @AfterViews
    public void init(){
        realm = Realm.getDefaultInstance();
    }

    @Click(R.id.buttonSearchHighlight)
    public void searchHighlightClick(View view){
        // + verify if searchable
        User result = realm.where(User.class).equalTo("username", inputHighlight.getText().toString()).findFirst();
        if (result != null) {
            // search results were not null (may nahanap)
            // get uuid of searched and init there
            // save UUID to shared prefs for highlight result screen
            SharedPreferences prefsSearchedHighlight = getSharedPreferences("myPrefsSearchedHighlight", MODE_PRIVATE);
            SharedPreferences.Editor editorSearchedHighlight = prefsSearchedHighlight.edit();
            editorSearchedHighlight.putString("uuid",result.getUuid());
            editorSearchedHighlight.apply();
            // go to result
            Intent intent = new Intent(this, HighlightsScreen_.class);
            startActivity(intent);
        } else {
            // search results were null (walang nahanap)
            Toast.makeText(getApplicationContext(), "No such user exists", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.logoToHome4)
    public void toHome4Click(View view){
        finish();
        MainActivity5_.intent(this).start();
    }

    @Override
    public void onBackPressed() {
        finish();
        MainActivity5_.intent(this).start();
    }

}