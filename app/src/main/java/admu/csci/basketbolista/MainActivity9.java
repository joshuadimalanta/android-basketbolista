package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main9)
public class MainActivity9 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome3)
    ImageView logoToHome3;
    @ViewById(R.id.profilePicture2)
    ImageView profilePicture2;
    @ViewById(R.id.editName)
    EditText editName;
    @ViewById(R.id.editHometown)
    EditText editHometown;
    @ViewById(R.id.editTeam)
    EditText editTeam;
    @ViewById(R.id.editAge)
    EditText editAge;
    @ViewById(R.id.editHeight)
    EditText editHeight;
    @ViewById(R.id.editWeight)
    EditText editWeight;
    @ViewById(R.id.buttonSaveProfile)
    Button buttonSaveProfile;
    @ViewById(R.id.buttonToProfile2)
    Button buttonToProfile2;
    @ViewById(R.id.buttonSelectPicture)
    Button buttonSelectPicture;

    @AfterViews
    public void init(){
        // + initialize fields
        // + make fields unclickable
    }

    @Click(R.id.buttonSaveProfile)
    public void saveProfileClick(View view){
        // + save changes
        MainActivity6_.intent(this).start();
    }

    @Click(R.id.buttonToProfile2)
    public void toProfile2Click(View view){
        //  MainActivity6_.intent(this).start();
        finish();
    }

    @Click(R.id.buttonSelectPicture)
    public void selectPictureClick(View view){
        // code for camera
        // fix camera
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }

    @Click(R.id.logoToHome3)
    public void toHome3Click(View view){
        MainActivity5_.intent(this).start();
    }
}