package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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

    @AfterViews
    public void init(){
        // + clear login when logout is pressed
    }

    @Click(R.id.buttonLogin)
    public void loginClick(View view){
        // + credentials check
        MainActivity5_.intent(this).start();
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