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
            Toast.makeText(getApplicationContext(), "Name must not be blank", Toast.LENGTH_SHORT).show();
        } else {
            // check if username has already been taken
            User result = realm.where(User.class).equalTo("username", registerUsername.getText().toString()).findFirst();
            if (result != null) {
                // username is taken (results were not null)
                Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
            } else {
                // username is available (results were null)
                if (registerPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                    // right combination
                    // saving User RealmObject
                    User newUser = new User();
                    newUser.setUuid(UUID.randomUUID().toString());
                    newUser.setUsername(registerUsername.getText().toString());
                    newUser.setPassword(registerPassword.getText().toString());
                    long count = 0;
                    try {
                        // good code
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(newUser);
                        realm.commitTransaction();
                        Toast.makeText(getApplicationContext(), "New user saved.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        // erroneous code
                        Toast.makeText(getApplicationContext(), "Error saving", Toast.LENGTH_SHORT).show();
                    }


                    // saving User PlayerInfo
                    User uuidToOwnerID = realm.where(User.class).equalTo("username",registerUsername.getText().toString()).findFirst();
                    PlayerInfo newPlayerInfo = new PlayerInfo();
                    newPlayerInfo.setOwnerid(uuidToOwnerID.getUuid()); // COPYING THE UUID TO OWNERID
//                  newPlayerInfo.setProfilepicture(PROFILE PICTURE PATH WILL GO HERE);
                    newPlayerInfo.setName(" :) ");
                    newPlayerInfo.setHometown(" :) ");
                    newPlayerInfo.setTeam(" :) ");
                    newPlayerInfo.setAge(" :) ");
                    newPlayerInfo.setHeight("0");
                    newPlayerInfo.setWeight("1");
                    newPlayerInfo.setPoints("2");
                    newPlayerInfo.setAssists("3");
                    newPlayerInfo.setRebounds("4");
                    newPlayerInfo.setBlocks("5");
                    newPlayerInfo.setSteals("6");
                    newPlayerInfo.setWins("7");
                    try {
                        // good code
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(newPlayerInfo);
                        realm.commitTransaction();
                    } catch (Exception e) {
                        // erroneous code
                        Toast.makeText(getApplicationContext(), "Error saving", Toast.LENGTH_SHORT).show();
                    }
                    //closing
                    finish();

                } else {
                    // wrong
                    Toast.makeText(getApplicationContext(), "Confirm password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Click(R.id.buttonCancel)
    public void cancelClick(View view){
        finish();
    }
}