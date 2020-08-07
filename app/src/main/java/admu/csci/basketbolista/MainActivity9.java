package admu.csci.basketbolista;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
//                save file path to realm db here TODO(1)
            }
        }
    }

    @Click(R.id.buttonSelectPicture)
    public void selectPictureClick(View view){
        // code for camera
        // fix camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            photoFile = createPhotoFile();

            if (photoFile != null) {
                pathToFile = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(MainActivity9.this, "fssdfs", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, 1);
            }

        }
        startActivity(intent);
    }

    private File createPhotoFile() {
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(name, ".jpg", storageDir);
        } catch (IOException e) {
            Log.d("mylog", "Excep : " + e.toString());
        }
        return image;
    }

    @Click(R.id.logoToHome3)
    public void toHome3Click(View view){
        MainActivity5_.intent(this).start();
    }
}