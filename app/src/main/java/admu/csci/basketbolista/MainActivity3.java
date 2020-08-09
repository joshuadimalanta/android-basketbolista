package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main3)
public class MainActivity3 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.adminUsername)
    EditText adminUsername;
    @ViewById(R.id.adminPassword)
    EditText adminPassword;
    @ViewById(R.id.buttonAdminLogin)
    Button buttonAdminLogin;
    @ViewById(R.id.buttonCancel)
    Button buttonCancel;

    @AfterViews
    public void init(){

    }

    @Click(R.id.buttonAdminLogin)
    public void adminLoginClick(View view){
        // credentials check
        if(adminUsername.getText().toString().equals("admin") && adminPassword.getText().toString().equals("admin")){
            MainActivity4_.intent(this).start();
        }else{
            // username does not exist (results were null)
            Toast.makeText(getApplicationContext(), "Invalid admin credentials.", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.buttonCancel)
    public void cancelClick(View view){
        finish();
    }
}