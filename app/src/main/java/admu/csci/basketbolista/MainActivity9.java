package admu.csci.basketbolista;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

import static android.os.Environment.getExternalStoragePublicDirectory;

@EActivity(R.layout.activity_main9)
public class MainActivity9 extends AppCompatActivity {

    String pathToFile;

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
    @ViewById(R.id.editVideoID)
    EditText editVideoID;
    @ViewById(R.id.buttonSaveProfile)
    Button buttonSaveProfile;
    @ViewById(R.id.buttonToProfile2)
    Button buttonToProfile2;
    @ViewById(R.id.buttonSelectPicture)
    Button buttonSelectPicture;
    // Realm
    Realm realm;

    /////////////////////////////////////////PERMISSIONS START//////////////////////////////////////
    @AfterViews
    public void checkPermissions() {
        // REQUEST PERMISSIONS for Android 6+
        // THESE PERMISSIONS SHOULD MATCH THE ONES IN THE MANIFEST
        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                )
                .withListener(new BaseMultiplePermissionsListener() {
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            // all permissions accepted proceed
                            init();
                        }
                        else {
                            // notify about permissions
                            toastRequirePermissions();
                        }
                    }
                })
                .check();
    }
    /////////////////////////////////////////PERMISSIONS END////////////////////////////////////////


    public void toastRequirePermissions() {
        Toast.makeText(this, "You must provide permissions to edit profile", Toast.LENGTH_LONG).show();
        finish();
    }

//    @AfterViews
    public void init(){
        // Realm
        realm = Realm.getDefaultInstance();
        // initialize fields
        SharedPreferences prefsLogin = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
        String uuid = prefsLogin.getString("uuid",null);
        // initialize fields iwht PlayerInfo information
        PlayerInfo player = realm.where(PlayerInfo.class).equalTo("ownerid",uuid).findFirst();
        editName.setText(player.getName());
        editHometown.setText(player.getHometown());
        editTeam.setText(player.getTeam());
        editAge.setText(player.getAge());
        editHeight.setText(player.getHeight());
        editWeight.setText(player.getWeight());

        // IMAGE INIT
        // check if savedImage.jpeg exists in path
        // load via picasso if exists
        File getImageDir = getExternalCacheDir();
        File savedImage = new File(getImageDir, "savedImage.jpeg");

        if (savedImage.exists()) {
            refreshImageView(savedImage);
        }
    }

    @Click(R.id.buttonSaveProfile)
    public void saveProfileClick(View view){
        // save changes
        if (editName.getText().toString().equals("") || editHometown.getText().toString().equals("")
                || editTeam.getText().toString().equals("") || editAge.getText().toString().equals("")
                || editHeight.getText().toString().equals("") || editWeight.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Fields must not be left blank.", Toast.LENGTH_SHORT).show();
        }else {
            realm.beginTransaction();
            SharedPreferences prefsLogin = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
            String uuid = prefsLogin.getString("uuid",null);

            PlayerInfo toEdit = realm.where(PlayerInfo.class).equalTo("ownerid",uuid).findFirst();
            toEdit.setName(editName.getText().toString());
            toEdit.setHometown(editHometown.getText().toString());
            toEdit.setTeam(editTeam.getText().toString());
            toEdit.setAge(editAge.getText().toString());
            toEdit.setHeight(editHeight.getText().toString());
            toEdit.setWeight(editWeight.getText().toString());

            realm.commitTransaction();
            Toast.makeText(getApplicationContext(), "Profile updated", Toast.LENGTH_SHORT).show();

            // VIDEOID IS OPTIONAL
            if (editVideoID.getText().toString().equals("")) {
                realm.beginTransaction();
                SharedPreferences prefsLogin2 = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
                String uuid2 = prefsLogin2.getString("uuid",null);
                PlayerInfo toEdit2 = realm.where(PlayerInfo.class).equalTo("ownerid",uuid2).findFirst();
                toEdit2.setVideoid("Tn70NxIMk2Q"); //default video if left blank
                realm.commitTransaction();
                //closing
                finish();
                MainActivity6_.intent(this).start();
            }else{
                realm.beginTransaction();
                SharedPreferences prefsLogin2 = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
                String uuid2 = prefsLogin2.getString("uuid",null);
                PlayerInfo toEdit2 = realm.where(PlayerInfo.class).equalTo("ownerid",uuid2).findFirst();
                toEdit2.setVideoid(editVideoID.getText().toString()); //default video if left blank
                realm.commitTransaction();
                //closing
                finish();
                MainActivity6_.intent(this).start();
            }
        }
    }

    @Click(R.id.buttonToProfile2)
    public void toProfile2Click(View view){
        finish();
        MainActivity6_.intent(this).start();
    }

    @Click(R.id.logoToHome3)
    public void toHome3Click(View view){
        finish();
        MainActivity5_.intent(this).start();
    }

    /////////////////////////////////////////     IMAGES      //////////////////////////////////////
    public static int REQUEST_CODE_IMAGE_SCREEN = 0;
    @Click(R.id.buttonSelectPicture)
    public void selectPictureClick(View view){
        MainActivity10_.intent(this).startForResult(REQUEST_CODE_IMAGE_SCREEN);
    }

    // SINCE WE USE startForResult(), code will trigger this once the next screen calls finish()
    public void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        if (requestCode==REQUEST_CODE_IMAGE_SCREEN) {
            if (responseCode==MainActivity10.RESULT_CODE_IMAGE_TAKEN) {
                // receive the raw JPEG data from ImageActivity (MainActivity10)
                // this can be saved to a file or save elsewhere like Realm or online
                byte[] jpeg = data.getByteArrayExtra("rawJpeg");

                    // save byte jpeg data to realm
                realm.beginTransaction();
                SharedPreferences prefsLogin = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
                String uuid = prefsLogin.getString("uuid",null);
                PlayerInfo toEdit = realm.where(PlayerInfo.class).equalTo("ownerid",uuid).findFirst();
                toEdit.setProfilepicture(jpeg); // save byte[] to player's realm
                realm.commitTransaction();

                try {
                    // save rawImage to file
                    PlayerInfo player = realm.where(PlayerInfo.class).equalTo("ownerid",uuid).findFirst();
                    File savedImage = saveFile(player.getProfilepicture());
//                    File savedImage = saveFile(jpeg);

                    // load file to the image view via picasso
                    refreshImageView(savedImage);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


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
                .into(profilePicture2);           // where will the photo be placed
    }
    /////////////////////////////////////////     IMAGES      //////////////////////////////////////

    @Override
    public void onBackPressed() {
        finish();
        MainActivity6_.intent(this).start();
    }

}