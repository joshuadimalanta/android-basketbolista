package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

import io.realm.Realm;

@EActivity(R.layout.activity_main15)
public class MainActivity15 extends AppCompatActivity {

    String pathToFile;

    // VARIABLES
    @ViewById(R.id.logoToHome8)
    ImageView logoToHome8;
    @ViewById(R.id.profilePicture3)
    ImageView profilePicture3;
    @ViewById(R.id.registerName)
    EditText registerName;
    @ViewById(R.id.registerHometown)
    EditText registerHometown;
    @ViewById(R.id.registerTeam)
    EditText registerTeam;
    @ViewById(R.id.registerAge)
    EditText registerAge;
    @ViewById(R.id.registerHeight)
    EditText registerHeight;
    @ViewById(R.id.registerWeight)
    EditText registerWeight;
    @ViewById(R.id.buttonRegisterProfile)
    Button buttonRegisterProfile;
    @ViewById(R.id.buttonSelectPicture2)
    Button buttonSelectPicture2;
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

    public void init(){
        // Realm
        realm = Realm.getDefaultInstance();
        // initialize fields
        SharedPreferences prefsRegProfile = getSharedPreferences("myPrefsRegProfile", MODE_PRIVATE);
        String uuid = prefsRegProfile.getString("uuid",null);
        // initialize fields iwht PlayerInfo information
        PlayerInfo player = realm.where(PlayerInfo.class).equalTo("ownerid",uuid).findFirst();
        registerName.setText("");
        registerHometown.setText("");
        registerTeam.setText("");
        registerAge.setText("");
        registerHeight.setText("");
        registerWeight.setText("");

        // IMAGE INIT
        // check if savedImage.jpeg exists in path
        // load via picasso if exists
        File getImageDir = getExternalCacheDir();
        File savedImage = new File(getImageDir, "savedImage.jpeg");

//        if (savedImage.exists()) {
//            refreshImageView(savedImage);
//        }
    }

    @Click(R.id.buttonRegisterProfile)
    public void saveRegisterClick(View view){
        // save changes
        if (registerName.getText().toString().equals("") || registerHometown.getText().toString().equals("")
                || registerTeam.getText().toString().equals("") || registerAge.getText().toString().equals("")
                || registerHeight.getText().toString().equals("") || registerWeight.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Fields must not be left blank.", Toast.LENGTH_SHORT).show();
        }else {
            realm.beginTransaction();
            SharedPreferences prefsRegProfile = getSharedPreferences("myPrefsRegProfile", MODE_PRIVATE);
            String uuid = prefsRegProfile.getString("uuid",null);
            PlayerInfo toEdit = realm.where(PlayerInfo.class).equalTo("ownerid",uuid).findFirst();
            toEdit.setName(registerName.getText().toString());
            toEdit.setHometown(registerHometown.getText().toString());
            toEdit.setTeam(registerTeam.getText().toString());
            toEdit.setAge(registerAge.getText().toString());
            toEdit.setHeight(registerHeight.getText().toString());
            toEdit.setWeight(registerWeight.getText().toString());

            realm.commitTransaction();
            Toast.makeText(getApplicationContext(), "Profile created.", Toast.LENGTH_SHORT).show();
            //closing
            finish();
            MainActivity_.intent(this).start();
        }
    }

    @Click(R.id.logoToHome8)
    public void toHome3Click(View view){
        MainActivity5_.intent(this).start();
    }

    /////////////////////////////////////////     IMAGES      //////////////////////////////////////
    public static int REQUEST_CODE_IMAGE_SCREEN = 0;
    @Click(R.id.buttonSelectPicture2)
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
                SharedPreferences prefsRegProfile = getSharedPreferences("myPrefsRegProfile", MODE_PRIVATE);
                String uuid = prefsRegProfile.getString("uuid",null);
//                SharedPreferences prefsLogin = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
//                String uuid = prefsLogin.getString("uuid",null);
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
                .into(profilePicture3);           // where will the photo be placed
    }
    /////////////////////////////////////////     IMAGES      //////////////////////////////////////

}