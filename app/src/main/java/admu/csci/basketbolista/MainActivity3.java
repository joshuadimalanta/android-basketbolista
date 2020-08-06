package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    }

    @Click(R.id.buttonCancel)
    public void cancelClick(View view){

    }
}