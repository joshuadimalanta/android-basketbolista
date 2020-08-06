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

@EActivity(R.layout.activity_main7)
public class MainActivity7 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome1)
    ImageView logoToHome1;
    @ViewById(R.id.profilePicture1)
    ImageView profilePicture1;
    @ViewById(R.id.profilePoints)
    EditText profilePoints;
    @ViewById(R.id.profileAssists)
    EditText profileAssists;
    @ViewById(R.id.profileRebounds)
    EditText profileRebounds;
    @ViewById(R.id.profileBlocks)
    EditText profileBlocks;
    @ViewById(R.id.profileSteals)
    EditText profileSteals;
    @ViewById(R.id.profileWins)
    EditText profileWins;
    @ViewById(R.id.buttonToAdjust)
    Button buttonToAdjust;
    @ViewById(R.id.buttonToProfile1)
    Button buttonToProfile1;

    @AfterViews
    public void init(){

    }

    @Click(R.id.buttonToAdjust)
    public void toAdjustClick(View view){

    }

    @Click(R.id.buttonToProfile1)
    public void toProfile1Click(View view){

    }

    @Click(R.id.logoToHome1)
    public void toHome1Click(View view){

    }
}