package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.realm.Realm;

@EActivity(R.layout.activity_main6)
public class MainActivity6 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome)
    ImageView logoToHome;
    @ViewById(R.id.profilePicture)
    ImageView profilePicture;
    @ViewById(R.id.profileName)
    TextView profileName;
    @ViewById(R.id.profileHometown)
    TextView profileHometown;
    @ViewById(R.id.profileTeam)
    TextView profileTeam;
    @ViewById(R.id.profileAge)
    TextView profileAge;
    @ViewById(R.id.profileHeight)
    TextView profileHeight;
    @ViewById(R.id.profileWeight)
    TextView profileWeight;
    @ViewById(R.id.buttonToStats)
    Button buttonToStats;
    @ViewById(R.id.buttonToEdit)
    Button buttonToEdit;
    // Realm
    Realm realm;

    @AfterViews
    public void init(){
        // Realm
        realm = Realm.getDefaultInstance();
        // SharedPrefs
        SharedPreferences prefsLogin = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
        String uuid = prefsLogin.getString("uuid",null);
        // initialize fields with PlayerInfo information
        PlayerInfo player = realm.where(PlayerInfo.class).equalTo("ownerid",uuid).findFirst();
        profileName.setText(player.getName());
        profileHometown.setText(player.getHometown());
        profileTeam.setText(player.getTeam());
        profileAge.setText(player.getAge());
        profileHeight.setText(player.getHeight());
        profileWeight.setText(player.getWeight());

        // LOAD IMAGE
        try{
            File savedImage = saveFile(player.getProfilepicture());
            refreshImageView(savedImage);
        }catch (Exception e){
            // WALANG IMAGE so default lang
        }
    }

    @Click(R.id.buttonToStats)
    public void toStatsClick(View view){
        finish();
        MainActivity7_.intent(this).start();
    }

    @Click(R.id.buttonToEdit)
    public void toEditClick(View view){
        finish();
        MainActivity9_.intent(this).start();
    }

    @Click(R.id.logoToHome)
    public void toHomeClick(View view){
        finish();
        MainActivity5_.intent(this).start();
    }


    /////////////////////////////////////////     IMAGES      //////////////////////////////////////
    private File saveFile(byte[] jpeg) throws IOException {
        // this is the root directory for the images
        File getImageDir = getExternalCacheDir();
        // just a sample, normally you have a diff image name each time
        File savedImage = new File(getImageDir, "savedImage.jpeg");
        FileOutputStream fos = new FileOutputStream(savedImage);
        fos.write(jpeg);
        fos.close();
        return savedImage;
    }

    private void refreshImageView(File savedImage) {
        // this will put the image saved to the file system to the imageview
        Picasso.get()
                .load(savedImage)           // where will the photo come from savedImage--from previous method
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(profilePicture);           // where will the photo be placed
    }
    /////////////////////////////////////////     IMAGES      //////////////////////////////////////

    @Override
    public void onBackPressed() {
        finish();
        MainActivity5_.intent(this).start();
    }
}