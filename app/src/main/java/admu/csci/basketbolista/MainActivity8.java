package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main8)
public class MainActivity8 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome2)
    ImageView logoToHome2;
    // points
    @ViewById(R.id.buttonPointsMinus)
    Button buttonPointsMinus;
    @ViewById(R.id.editPoints)
    EditText editPoints;
    @ViewById(R.id.buttonPointsAdd)
    Button buttonPointsAdd;
    // assists
    @ViewById(R.id.buttonAssistsMinus)
    Button buttonAssistsMinus;
    @ViewById(R.id.editAssists)
    EditText editAssists;
    @ViewById(R.id.buttonAssistsAdd)
    Button buttonAssistsAdd;
    // rebounds
    @ViewById(R.id.buttonReboundsMinus)
    Button buttonReboundsMinus;
    @ViewById(R.id.editRebounds)
    EditText editRebounds;
    @ViewById(R.id.buttonReboundsAdd)
    Button buttonReboundsAdd;
    // blocks
    @ViewById(R.id.buttonBlocksMinus)
    Button buttonBlocksMinus;
    @ViewById(R.id.editBlocks)
    EditText editBlocks;
    @ViewById(R.id.buttonBlocksAdd)
    Button buttonBlocksAdd;
    // steals
    @ViewById(R.id.buttonStealsMinus)
    Button buttonStealsMinus;
    @ViewById(R.id.editSteals)
    EditText editSteals;
    @ViewById(R.id.buttonStealsAdd)
    Button buttonStealsAdd;
    // wins
    @ViewById(R.id.buttonWinsMinus)
    Button buttonWinsMinus;
    @ViewById(R.id.editWins)
    EditText editWins;
    @ViewById(R.id.buttonWinsAdd)
    Button buttonWinsAdd;
    @ViewById(R.id.buttonSaveStats)
    Button buttonSaveStats;
    @ViewById(R.id.buttonToStats1)
    Button buttonToStats1;

    @AfterViews
    public void init(){

    }

    @Click(R.id.buttonSaveStats)
    public void saveStatsClick(View view){

    }

    @Click(R.id.buttonToStats1)
    public void toStats1Click(View view){

    }

    @Click(R.id.logoToHome2)
    public void toHome2Click(View view){

    }
}