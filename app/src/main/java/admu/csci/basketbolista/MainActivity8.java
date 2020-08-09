package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;

@EActivity(R.layout.activity_main8)
public class MainActivity8 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome2)
    ImageView logoToHome2;
    // points
    @ViewById(R.id.buttonPointsMinus)
    Button buttonPointsMinus;
    @ViewById(R.id.editPoints)
    TextView editPoints;
    @ViewById(R.id.buttonPointsAdd)
    Button buttonPointsAdd;
    // assists
    @ViewById(R.id.buttonAssistsMinus)
    Button buttonAssistsMinus;
    @ViewById(R.id.editAssists)
    TextView editAssists;
    @ViewById(R.id.buttonAssistsAdd)
    Button buttonAssistsAdd;
    // rebounds
    @ViewById(R.id.buttonReboundsMinus)
    Button buttonReboundsMinus;
    @ViewById(R.id.editRebounds)
    TextView editRebounds;
    @ViewById(R.id.buttonReboundsAdd)
    Button buttonReboundsAdd;
    // blocks
    @ViewById(R.id.buttonBlocksMinus)
    Button buttonBlocksMinus;
    @ViewById(R.id.editBlocks)
    TextView editBlocks;
    @ViewById(R.id.buttonBlocksAdd)
    Button buttonBlocksAdd;
    // steals
    @ViewById(R.id.buttonStealsMinus)
    Button buttonStealsMinus;
    @ViewById(R.id.editSteals)
    TextView editSteals;
    @ViewById(R.id.buttonStealsAdd)
    Button buttonStealsAdd;
    // wins
    @ViewById(R.id.buttonWinsMinus)
    Button buttonWinsMinus;
    @ViewById(R.id.editWins)
    TextView editWins;
    @ViewById(R.id.buttonWinsAdd)
    Button buttonWinsAdd;
    // others
    @ViewById(R.id.buttonSaveStats)
    Button buttonSaveStats;
    @ViewById(R.id.buttonToStats1)
    Button buttonToStats1;
    // Realm
    Realm realm;

    @AfterViews
    public void init(){
        // Realm
        realm = Realm.getDefaultInstance();
        // initialize fields
        SharedPreferences prefsLogin = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
        String uuid = prefsLogin.getString("uuid",null);
        // initialize fields iwht PlayerInfo information
        PlayerInfo player = realm.where(PlayerInfo.class).equalTo("ownerid",uuid).findFirst();
        editPoints.setText(player.getPoints());
        editAssists.setText(player.getAssists());
        editRebounds.setText(player.getRebounds());
        editBlocks.setText(player.getBlocks());
        editSteals.setText(player.getSteals());
        editWins.setText(player.getWins());
    }

    @Click(R.id.buttonPointsMinus)
    public void pointsMinusClick(View view){
        editPoints.setText(String.valueOf(Integer.parseInt(editPoints.getText().toString())-1));
    }
    @Click(R.id.buttonPointsAdd)
    public void pointsAddClick(View view){
        editPoints.setText(String.valueOf(Integer.parseInt(editPoints.getText().toString())+1));
    }

    @Click(R.id.buttonAssistsMinus)
    public void assistsMinusClick(View view){
        editAssists.setText(String.valueOf(Integer.parseInt(editAssists.getText().toString())-1));
    }
    @Click(R.id.buttonAssistsAdd)
    public void assistsAddClick(View view){
        editAssists.setText(String.valueOf(Integer.parseInt(editAssists.getText().toString())+1));
    }

    @Click(R.id.buttonReboundsMinus)
    public void reboundsMinusClick(View view){
        editRebounds.setText(String.valueOf(Integer.parseInt(editRebounds.getText().toString())-1));
    }
    @Click(R.id.buttonReboundsAdd)
    public void reboundsAddClick(View view){
        editRebounds.setText(String.valueOf(Integer.parseInt(editRebounds.getText().toString())+1));
    }

    @Click(R.id.buttonBlocksMinus)
    public void blocksMinusClick(View view){
        editBlocks.setText(String.valueOf(Integer.parseInt(editBlocks.getText().toString())-1));
    }
    @Click(R.id.buttonBlocksAdd)
    public void blocksAddClick(View view){
        editBlocks.setText(String.valueOf(Integer.parseInt(editBlocks.getText().toString())+1));
    }

    @Click(R.id.buttonStealsMinus)
    public void stealsMinusClick(View view){
        editSteals.setText(String.valueOf(Integer.parseInt(editSteals.getText().toString())-1));
    }
    @Click(R.id.buttonStealsAdd)
    public void stealsAddClick(View view){
        editSteals.setText(String.valueOf(Integer.parseInt(editSteals.getText().toString())+1));
    }

    @Click(R.id.buttonWinsMinus)
    public void winsMinusClick(View view){
        editWins.setText(String.valueOf(Integer.parseInt(editWins.getText().toString())-1));
    }
    @Click(R.id.buttonWinsAdd)
    public void winsAddClick(View view){
        editWins.setText(String.valueOf(Integer.parseInt(editWins.getText().toString())+1));
    }


    @Click(R.id.buttonSaveStats)
    public void saveStatsClick(View view){
        // save changes
        if(Integer.parseInt(editPoints.getText().toString()) >= 0 && Integer.parseInt(editAssists.getText().toString()) >= 0
                && Integer.parseInt(editRebounds.getText().toString()) >= 0 && Integer.parseInt(editBlocks.getText().toString()) >= 0
                && Integer.parseInt(editSteals.getText().toString()) >= 0 && Integer.parseInt(editWins.getText().toString()) >= 0){
            realm.beginTransaction();
            SharedPreferences prefsLogin = getSharedPreferences("myPrefsLogin", MODE_PRIVATE);
            String uuid = prefsLogin.getString("uuid",null);

            PlayerInfo toEdit = realm.where(PlayerInfo.class).equalTo("ownerid",uuid).findFirst();
            toEdit.setPoints(editPoints.getText().toString());
            toEdit.setAssists(editAssists.getText().toString());
            toEdit.setRebounds(editRebounds.getText().toString());
            toEdit.setBlocks(editBlocks.getText().toString());
            toEdit.setSteals(editSteals.getText().toString());
            toEdit.setWins(editWins.getText().toString());
            realm.commitTransaction();
            Toast.makeText(getApplicationContext(), "Stats adjusted.", Toast.LENGTH_SHORT).show();
            //closing
            finish();
            MainActivity7_.intent(this).start();
        }else {
            // may negative value
            Toast.makeText(getApplicationContext(), "Cannot save negative values.", Toast.LENGTH_SHORT).show();
        }

    }

    @Click(R.id.buttonToStats1)
    public void toStats1Click(View view){
        MainActivity7_.intent(this).start();
    }

    @Click(R.id.logoToHome2)
    public void toHome2Click(View view){
        MainActivity5_.intent(this).start();
    }

    @Override
    public void onBackPressed() {
        finish();
        MainActivity7_.intent(this).start();
    }
}