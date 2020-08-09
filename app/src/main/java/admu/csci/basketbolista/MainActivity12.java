package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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
    // Realm
    Realm realm;

    @AfterViews
    public void init(){
        // Realm
        realm = Realm.getDefaultInstance();
        // SharedPrefs
        SharedPreferences prefsSearched = getSharedPreferences("myPrefsSearched", MODE_PRIVATE);
        String uuidSearched = prefsSearched.getString("uuid", null);
        PlayerInfo player = realm.where(PlayerInfo.class).equalTo("ownerid",uuidSearched).findFirst();
        // initialize fields with PlayerInfo information
        searchedName.setText(player.getName());
        searchedHometown.setText(player.getHometown());
        searchedTeam.setText(player.getTeam());
        searchedAge.setText(player.getAge());
        searchedHeight.setText(player.getHeight());
        searchedWeight.setText(player.getWeight());
        // LOAD IMAGE
        try{
            File savedImage = saveFile(player.getProfilepicture());
            refreshImageView(savedImage);
        }catch (Exception e){
            // WALANG IMAGE so default lang
        }
    }

    @Click(R.id.logoToHome5)
    public void toHome5Click(View view){
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
                .into(searchedProfilePicture);           // where will the photo be placed
    }
    /////////////////////////////////////////     IMAGES      //////////////////////////////////////
}