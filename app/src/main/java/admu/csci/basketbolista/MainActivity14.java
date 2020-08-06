package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main14)
public class MainActivity14 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome7)
    ImageView logoToHome7;
    // user
    @ViewById(R.id.myName)
    EditText myName;
    @ViewById(R.id.myPoints)
    EditText myPoints;
    @ViewById(R.id.myAssists)
    EditText myAssists;
    @ViewById(R.id.myRebounds)
    EditText myRebounds;
    @ViewById(R.id.myBlocks)
    EditText myBlocks;
    @ViewById(R.id.mySteals)
    EditText mySteals;
    @ViewById(R.id.myWins)
    EditText myWins;
    // compared to
    @ViewById(R.id.comparedName)
    EditText comparedName;
    @ViewById(R.id.comparedPoints)
    EditText comparedPoints;
    @ViewById(R.id.comparedAssists)
    EditText comparedAssists;
    @ViewById(R.id.comparedRebounds)
    EditText comparedRebounds;
    @ViewById(R.id.comparedBlocks)
    EditText comparedBlocks;
    @ViewById(R.id.comparedSteals)
    EditText comparedSteals;
    @ViewById(R.id.comparedWins)
    EditText comparedWins;
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

    @AfterViews
    public void init(){
        // get playerinfo of user and opponent
    }

    @Click(R.id.logoToHome7)
    public void toHome7Click(View view){
        MainActivity5_.intent(this).start();
    }
}