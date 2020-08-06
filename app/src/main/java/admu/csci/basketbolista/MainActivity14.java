package admu.csci.basketbolista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

    @AfterViews
    public void init(){
        // get playerinfo of user and opponent
    }

    @Click(R.id.logoToHome7)
    public void toHome7Click(View view){
        MainActivity5_.intent(this).start();
    }
}