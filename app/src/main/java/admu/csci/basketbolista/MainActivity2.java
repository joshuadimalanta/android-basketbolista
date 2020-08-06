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

@EActivity(R.layout.activity_main2)
public class MainActivity2 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.registerUsername)
    EditText registerUsername;
    @ViewById(R.id.registerPassword)
    EditText registerPassword;
    @ViewById(R.id.buttonRegister)
    Button buttonRegister;
    @ViewById(R.id.buttonCancel)
    Button buttonCancel;

    @AfterViews
    public void init(){

    }

    @Click(R.id.buttonRegister)
    public void registerClick(View view){

    }

    @Click(R.id.buttonCancel)
    public void cancelClick(View view){

    }
}