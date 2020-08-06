package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main12)
public class MainActivity12 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome5)
    ImageView logoToHome5;
    @ViewById(R.id.searchedProfilePicture)
    ImageView searchedProfilePicture;
    @ViewById(R.id.searchedName)
    EditText searchedName;
    @ViewById(R.id.searchedHometown)
    EditText searchedHometown;
    @ViewById(R.id.searchedTeam)
    EditText searchedTeam;
    @ViewById(R.id.searchedAge)
    EditText searchedAge;
    @ViewById(R.id.searchedHeight)
    EditText searchedHeight;
    @ViewById(R.id.searchedWeight)
    EditText searchedWeight;

    @AfterViews
    public void init(){

    }

}