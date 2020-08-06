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

@EActivity(R.layout.activity_main11)
public class MainActivity11 extends AppCompatActivity {

    // VARIABLES
    @ViewById(R.id.logoToHome4)
    ImageView logoToHome4;
    @ViewById(R.id.inputSearch)
    EditText inputSearch;
    @ViewById(R.id.buttonSearch)
    Button buttonSearch;

    @AfterViews
    public void init(){

    }

    @Click(R.id.buttonSearch)
    public void searchClick(View view){
        // + verify if searchable
        // + get uuid of searched and init there
        MainActivity12_.intent(this).start();
    }

    @Click(R.id.logoToHome4)
    public void toHome4Click(View view){
        MainActivity5_.intent(this).start();
    }
}