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

@EActivity(R.layout.activity_main5)
public class MainActivity5 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.displayUsername)
    EditText displayUsername;
    @ViewById(R.id.buttonToProfile)
    Button buttonToProfile;
    @ViewById(R.id.buttonToSearch)
    Button buttonToSearch;
    @ViewById(R.id.buttonToCompare)
    Button buttonToCompare;

    @AfterViews
    public void init(){

    }

    @Click(R.id.buttonToProfile)
    public void toProfileClick(View view){
        // + sharedpref current user's UUID tas send sa mainactivity6 initialization
        MainActivity6_.intent(this).start();
    }

    @Click(R.id.buttonToSearch)
    public void toSearchClick(View view){
        MainActivity11_.intent(this).start();
    }

    @Click(R.id.buttonToCompare)
    public void toCompareClick(View view){
        MainActivity13_.intent(this).start();
    }
}