package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main13)
public class MainActivity13 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome6)
    ImageView logoToHome6;
    @ViewById(R.id.inputCompare)
    EditText inputCompare;
    @ViewById(R.id.buttonCompare)
    Button buttonCompare;

    @AfterViews
    public void init(){

    }

    @Click(R.id.buttonCompare)
    public void compareClick(View view){
        // + verify if comparable
        // + sharedpref to compare, init there
        MainActivity5_.intent(this).start();
    }

    @Click(R.id.logoToHome6)
    public void toHome6Click(View view){
        MainActivity5_.intent(this).start();
    }
}