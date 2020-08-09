package admu.csci.basketbolista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;

@EActivity(R.layout.activity_highlights_screen)
public class HighlightsScreen extends AppCompatActivity {

    // Realm
    Realm realm;

    @AfterViews
    public void init(){
        // Realm
        realm = Realm.getDefaultInstance();
        // SharedPrefs
        SharedPreferences prefsSearchedHighlight = getSharedPreferences("myPrefsSearchedHighlight", MODE_PRIVATE);
        String uuid = prefsSearchedHighlight.getString("uuid",null);
        final PlayerInfo resultSearchedHighlight = realm.where(PlayerInfo.class).equalTo("ownerid",uuid).findFirst();

        // INIT CORRECT VIDEO OF SEARCHED USER HIGHLIGHT
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = resultSearchedHighlight.getVideoid(); //VIDEO THAT WOULD BE SHOWN default = Tn70NxIMk2Q
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }

    @Click(R.id.logoToHome9)
    public void toHomeClick(View view){
        finish();
        MainActivity5_.intent(this).start();
    }

    @Override
    public void onBackPressed() {
        finish();
        MainActivity5_.intent(this).start();
    }

}