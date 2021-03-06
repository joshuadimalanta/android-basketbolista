package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

@EActivity(R.layout.activity_main14)
public class MainActivity14 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome7)
    ImageView logoToHome7;
    @ViewById(R.id.textSummary)
    TextView textSummary;
    // user
    @ViewById(R.id.myProfilePicture)
    ImageView myProfilePicture;
    @ViewById(R.id.myName)
    TextView myName;
    @ViewById(R.id.myPoints)
    TextView myPoints;
    @ViewById(R.id.myAssists)
    TextView myAssists;
    @ViewById(R.id.myRebounds)
    TextView myRebounds;
    @ViewById(R.id.myBlocks)
    TextView myBlocks;
    @ViewById(R.id.mySteals)
    TextView mySteals;
    @ViewById(R.id.myWins)
    TextView myWins;
    // compared to
    @ViewById(R.id.comparedProfilePicture)
    ImageView comparedProfilePicture;
    @ViewById(R.id.comparedName)
    TextView comparedName;
    @ViewById(R.id.comparedPoints)
    TextView comparedPoints;
    @ViewById(R.id.comparedAssists)
    TextView comparedAssists;
    @ViewById(R.id.comparedRebounds)
    TextView comparedRebounds;
    @ViewById(R.id.comparedBlocks)
    TextView comparedBlocks;
    @ViewById(R.id.comparedSteals)
    TextView comparedSteals;
    @ViewById(R.id.comparedWins)
    TextView comparedWins;
    // indicators
    @ViewById(R.id.indicatorPoints)
    ImageView indicatorPoints;
    @ViewById(R.id.indicatorAssists)
    ImageView indicatorAssists;
    @ViewById(R.id.indicatorRebounds)
    ImageView indicatorRebounds;
    @ViewById(R.id.indicatorBlocks)
    ImageView indicatorBlocks;
    @ViewById(R.id.indicatorSteals)
    ImageView indicatorSteals;
    @ViewById(R.id.indicatorWins)
    ImageView indicatorWins;
    // Realm
    Realm realm;

    @AfterViews
    public void init(){
        // Realm
        realm = Realm.getDefaultInstance();
        // User's SharedPrefs
        SharedPreferences prefsLogin = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
        String uuidplayer1 = prefsLogin.getString("uuid",null);
        // Compared's SharedPrefs
        SharedPreferences prefsSearched = getSharedPreferences("myPrefsSearched", MODE_PRIVATE);
        String uuidplayer2 = prefsSearched.getString("uuid",null);
        // User's PlayerInfo
        PlayerInfo player1 = realm.where(PlayerInfo.class).equalTo("ownerid",uuidplayer1).findFirst();
        myName.setText(player1.getName());
        myPoints.setText(player1.getPoints());
        myAssists.setText(player1.getAssists());
        myRebounds.setText(player1.getRebounds());
        myBlocks.setText(player1.getBlocks());
        mySteals.setText(player1.getSteals());
        myWins.setText(player1.getWins());
        // Compared's PlayerInfo
        PlayerInfo player2 = realm.where(PlayerInfo.class).equalTo("ownerid",uuidplayer2).findFirst();
        comparedName.setText(player2.getName());
        comparedPoints.setText(player2.getPoints());
        comparedAssists.setText(player2.getAssists());
        comparedRebounds.setText(player2.getRebounds());
        comparedBlocks.setText(player2.getBlocks());
        comparedSteals.setText(player2.getSteals());
        comparedWins.setText(player2.getWins());

        // Win Indicators
        long wincount = 0;
        if(Integer.parseInt(myPoints.getText().toString()) <= Integer.parseInt(comparedPoints.getText().toString())){
            // lose
            indicatorPoints.setVisibility(View.INVISIBLE);
        }else{
            // win
            wincount = wincount + 1;
            indicatorPoints.setVisibility(View.VISIBLE);
        }
        if(Integer.parseInt(myAssists.getText().toString()) <= Integer.parseInt(comparedAssists.getText().toString())){
            // lose
            indicatorAssists.setVisibility(View.INVISIBLE);
        }else{
            // win
            wincount = wincount + 1;
            indicatorAssists.setVisibility(View.VISIBLE);
        }
        if(Integer.parseInt(myRebounds.getText().toString()) <= Integer.parseInt(comparedRebounds.getText().toString())){
            // lose
            indicatorRebounds.setVisibility(View.INVISIBLE);
        }else{
            // win
            wincount = wincount + 1;
            indicatorRebounds.setVisibility(View.VISIBLE);
        }
        if(Integer.parseInt(myBlocks.getText().toString()) <= Integer.parseInt(comparedBlocks.getText().toString())){
            // lose
            indicatorBlocks.setVisibility(View.INVISIBLE);
        }else{
            // win
            wincount = wincount + 1;
            indicatorBlocks.setVisibility(View.VISIBLE);
        }
        if(Integer.parseInt(mySteals.getText().toString()) <= Integer.parseInt(comparedSteals.getText().toString())){
            // lose
            indicatorSteals.setVisibility(View.INVISIBLE);
        }else{
            // win
            wincount = wincount + 1;
            indicatorSteals.setVisibility(View.VISIBLE);
        }
        if(Integer.parseInt(myWins.getText().toString()) <= Integer.parseInt(comparedWins.getText().toString())){
            // lose
            indicatorWins.setVisibility(View.INVISIBLE);
        }else{
            // win
            wincount = wincount + 1;
            indicatorWins.setVisibility(View.VISIBLE);
        }

        // set summary text
        textSummary.setText("You win in a total of "+wincount+" stat categories.");

        // LOAD IMAGE player 1
        try{
            File savedImage = saveFile(player1.getProfilepicture());
            refreshImageView(savedImage);
        }catch (Exception e){
            // WALANG IMAGE so default lang
        }

        // LOAD IMAGE player 2
        try{
            File savedImage2 = saveFile2(player2.getProfilepicture());
            refreshImageView2(savedImage2);
        }catch (Exception e){
            // WALANG IMAGE so default lang
        }
    }

    /////////////////////////////////////////     IMAGES player1      //////////////////////////////////////
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
                .into(myProfilePicture);           // where will the photo be placed
    }
    /////////////////////////////////////////     IMAGES player1      //////////////////////////////////////
    /////////////////////////////////////////     IMAGES player2      //////////////////////////////////////
    private File saveFile2(byte[] jpeg) throws IOException {
        // this is the root directory for the images
        File getImageDir = getExternalCacheDir();
        // just a sample, normally you have a diff image name each time
        File savedImage2 = new File(getImageDir, "savedImage2.jpeg");
        FileOutputStream fos = new FileOutputStream(savedImage2);
        fos.write(jpeg);
        fos.close();
        return savedImage2;
    }

    private void refreshImageView2(File savedImage) {
        // this will put the image saved to the file system to the imageview
        Picasso.get()
                .load(savedImage)           // where will the photo come from savedImage--from previous method
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(comparedProfilePicture);           // where will the photo be placed
    }
    /////////////////////////////////////////     IMAGES player2      //////////////////////////////////////

    @Click(R.id.logoToHome7)
    public void toHome7Click(View view){
        finish();
        MainActivity5_.intent(this).start();
    }
}