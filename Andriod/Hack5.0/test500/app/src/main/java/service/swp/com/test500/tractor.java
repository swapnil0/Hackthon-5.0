/*package service.swp.com.test500;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class tractor extends YouTubeBaseActivity implements
        OnInitializedListener, OnClickListener, OnCheckedChangeListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    private Button up;
    private Button down;
    private Button left1;
    private Button right1;
    private Button irrigationOn1;
    private Button irrigationOff1;
    private ToggleButton jcb1;


    // YouTube player view
    private YouTubePlayerView youTubeView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);

        up = (Button)findViewById(R.id.up);
        down = (Button)findViewById(R.id.down);
        left1 = (Button)findViewById(R.id.left);
        right1 = (Button)findViewById(R.id.right);
        irrigationOn1 = (Button)findViewById(R.id.irrigationOn1);
        irrigationOff1 = (Button)findViewById(R.id.irrigationOff1);
        jcb1 = (ToggleButton) findViewById(R.id.jcb1);

        // Initializing video player with developer key
        youTubeView.initialize(Config.DEVELOPER_KEY, this);

        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left1.setOnClickListener(this);
        right1.setOnClickListener(this);
        irrigationOn1.setOnClickListener(this);
        irrigationOff1.setOnClickListener(this);
        jcb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton jcb, boolean isChecked) {
                if (isChecked) {
                    //   Intent i = new Intent(this, tractor.class);
                    //  startActivity(i);
                } else {
                    //    Intent i = new Intent(this, MainActivity.class);
                }
            }
        });
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(Config.YOUTUBE_VIDEO_CODE);

            // Hiding player controls
            player.setPlayerStyle(PlayerStyle.CHROMELESS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    @Override
    public void onClick(View view) {
        if(view == up){
            myRef.setValue("10");
        }

        if(view == down){
            myRef.setValue("20");
        }

        if(view == left1){
            myRef.setValue("30");
        }

        if(view == right1){
            myRef.setValue("40");
        }
        if(view == irrigationOn1){
            myRef.setValue("90");
        }

        if(view == irrigationOff1){
            myRef.setValue("100");
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(isChecked){
    //        Intent i = new Intent(this, tractor.class);
     //         startActivity(i);
        }else{

        }
    }
}*/