package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.loginUsername)
    EditText loginUsername;
    @ViewById(R.id.loginPassword)
    EditText loginPassword;
    @ViewById(R.id.buttonLogin)
    Button buttonLogin;
    @ViewById(R.id.buttonToRegister)
    Button buttonToRegister;
    @ViewById(R.id.buttonToAdmin)
    Button buttonToAdmin;
    // Realm
    Realm realm;

    @AfterViews
    public void init(){
        // Realm
        realm = Realm.getDefaultInstance();
        // + clear login when logout is pressed

    }

    @Click(R.id.buttonLogin)
    public void loginClick(View view){
        // credentials check
        // search for the User with the corresponding name
        User result = realm.where(User.class).equalTo("username",loginUsername.getText().toString()).findFirst();
        if(result!=null){
            // username exists (results were not null)
            // check credentials
            if(loginPassword.getText().toString().equals(result.getPassword())){
                // right credentials
                // save UUID to shared prefs for welcome screen
                SharedPreferences prefsLogin = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
                SharedPreferences.Editor editorLogin = prefsLogin.edit();
                editorLogin.putString("uuid",result.getUuid());
                editorLogin.apply();
                // start
                MainActivity5_.intent(this).start();
            }else{
                // wrong credentials
                Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        }else{
            // username does not exist (results were null)
            Toast.makeText(getApplicationContext(), "No user found", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.buttonToRegister)
    public void toRegisterClick(View view){
        MainActivity2_.intent(this).start();
    }

    @Click(R.id.buttonToAdmin)
    public void toAdminClick(View view){
        MainActivity3_.intent(this).start();
    }


}