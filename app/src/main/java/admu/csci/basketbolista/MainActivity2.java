package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.UUID;

import io.realm.Realm;

@EActivity(R.layout.activity_main2)
public class MainActivity2 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.registerUsername)
    EditText registerUsername;
    @ViewById(R.id.registerPassword)
    EditText registerPassword;
    @ViewById(R.id.confirmPassword)
    EditText confirmPassword;
    @ViewById(R.id.buttonRegister)
    Button buttonRegister;
    @ViewById(R.id.buttonCancel)
    Button buttonCancel;
    // Realm
    Realm realm;

    @AfterViews
    public void init(){
        realm = Realm.getDefaultInstance();
    }

    @Click(R.id.buttonRegister)
    public void registerClick(View view){
        if (registerUsername.getText().toString().equals("")) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Name must not be blank", Toast.LENGTH_SHORT);
            toast1.show();
        } else {
            Log debuggg;
            // check if username has already been taken
            User result = realm.where(User.class).equalTo("username", registerUsername.getText().toString()).findFirst();
            if (result != null) {
                // username is taken (results were not null)
                Toast toast9 = Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT);
                toast9.show();
            } else {
                // username is available (results were null)
                if (registerPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                    // right combination
                    //saving
                    User newUser = new User();
                    newUser.setUuid(UUID.randomUUID().toString());
                    newUser.setUsername(registerUsername.getText().toString());
                    newUser.setPassword(registerPassword.getText().toString());
                    long count = 0;
                    try {
                        // good code
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(newUser); //save
                        count = realm.where(User.class).count(); //count how many saved
                        realm.commitTransaction();
                        Toast toast7 = Toast.makeText(getApplicationContext(), "New user saved.  Total: " + count, Toast.LENGTH_SHORT);
                        toast7.show();
                    } catch (Exception e) {
                        // erroneous code
                        Toast toast8 = Toast.makeText(getApplicationContext(), "Error saving", Toast.LENGTH_SHORT);
                        toast8.show();
                    }
                    //closing
                    finish();
                } else {
                    // wrong
                    Toast toast3 = Toast.makeText(getApplicationContext(), "Confirm password does not match", Toast.LENGTH_SHORT);
                    toast3.show();
                }
            }
        }
    }

    @Click(R.id.buttonCancel)
    public void cancelClick(View view){
        finish();
    }
}