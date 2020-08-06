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

@EActivity(R.layout.activity_main6)
public class MainActivity6 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome)
    ImageView logoToHome;
    @ViewById(R.id.profilePicture)
    ImageView profilePicture;
    @ViewById(R.id.profileName)
    EditText profileName;
    @ViewById(R.id.profileHometown)
    EditText profileHometown;
    @ViewById(R.id.profileTeam)
    EditText profileTeam;
    @ViewById(R.id.profileAge)
    EditText profileAge;
    @ViewById(R.id.profileHeight)
    EditText profileHeight;
    @ViewById(R.id.profileWeight)
    EditText profileWeight;
    @ViewById(R.id.buttonToStats)
    Button buttonToStats;
    @ViewById(R.id.buttonToEdit)
    Button buttonToEdit;

    @AfterViews
    public void init(){
        // + initialize fields
        // + make fields unclickable
    }

    @Click(R.id.buttonToStats)
    public void toStatsClick(View view){
        MainActivity7_.intent(this).start();
    }

    @Click(R.id.buttonToEdit)
    public void toEditClick(View view){
        MainActivity9_.intent(this).start();
    }

    @Click(R.id.logoToHome)
    public void toHomeClick(View view){

    }
}