package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

@EActivity(R.layout.activity_main7)
public class MainActivity7 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome1)
    ImageView logoToHome1;
    @ViewById(R.id.profilePicture1)
    ImageView profilePicture1;
    @ViewById(R.id.profilePoints)
    TextView profilePoints;
    @ViewById(R.id.profileAssists)
    TextView profileAssists;
    @ViewById(R.id.profileRebounds)
    TextView profileRebounds;
    @ViewById(R.id.profileBlocks)
    TextView profileBlocks;
    @ViewById(R.id.profileSteals)
    TextView profileSteals;
    @ViewById(R.id.profileWins)
    TextView profileWins;
    @ViewById(R.id.buttonToAdjust)
    Button buttonToAdjust;
    @ViewById(R.id.buttonToProfile1)
    Button buttonToProfile1;
    // Realm
    Realm realm;

    @AfterViews
    public void init(){
        // Realm
        realm = Realm.getDefaultInstance();
        // SharedPrefs
        SharedPreferences prefsLogin = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
        String uuid = prefsLogin.getString("uuid",null);
        // initialize fields iwht PlayerInfo information
        PlayerInfo player = realm.where(PlayerInfo.class).equalTo("ownerid",uuid).findFirst();
        profilePoints.setText(player.getPoints());
        profileAssists.setText(player.getAssists());
        profileRebounds.setText(player.getRebounds());
        profileBlocks.setText(player.getBlocks());
        profileSteals.setText(player.getSteals());
        profileWins.setText(player.getWins());
        // LOAD IMAGE
        try{
            File savedImage = saveFile(player.getProfilepicture());
            refreshImageView(savedImage);
        }catch (Exception e){
            // WALANG IMAGE so default lang
        }
    }

    @Click(R.id.buttonToAdjust)
    public void toAdjustClick(View view){
        finish();
        MainActivity8_.intent(this).start();
    }

    @Click(R.id.buttonToProfile1)
    public void toProfile1Click(View view){
        finish();
        MainActivity6_.intent(this).start();
    }

    @Click(R.id.logoToHome1)
    public void toHome1Click(View view){
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
                .into(profilePicture1);           // where will the photo be placed
    }
    /////////////////////////////////////////     IMAGES      //////////////////////////////////////
}