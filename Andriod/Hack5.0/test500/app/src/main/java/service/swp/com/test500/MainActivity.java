package service.swp.com.test500;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener, View.OnClickListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    DatabaseReference han = database.getReference("hand");
    DatabaseReference mov = database.getReference("movement");
    DatabaseReference dri = database.getReference("drive");

    private Button forward;
    private Button reverse;
    private Button left;
    private Button right;
    private Button forwardArm;
    private Button reverseArm;
    private Button circularLeft;
    private Button circularRight;
    private Button irrigationOn;
    private Button irrigationOff;
    private Button stop;
    private ToggleButton jcb;


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

        forward = (Button)findViewById(R.id.up);
        reverse = (Button)findViewById(R.id.reverse);
        left = (Button)findViewById(R.id.left);
        right = (Button)findViewById(R.id.right);
        forwardArm = (Button)findViewById(R.id.forwardArm);
        reverseArm = (Button)findViewById(R.id.reverseArm);
        circularLeft = (Button)findViewById(R.id.circularLeft);
        circularRight = (Button)findViewById(R.id.circularRight);
        irrigationOn = (Button)findViewById(R.id.irrigationOn);
        irrigationOff = (Button)findViewById(R.id.irrigationOff);
        stop = (Button)findViewById(R.id.stop);
     //   jcb = (ToggleButton) findViewById(R.id.jcb);

        // Initializing video player with developer key
        youTubeView.initialize(Config.DEVELOPER_KEY, this);

        forward.setOnClickListener(this);
        reverse.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        forwardArm.setOnClickListener(this);
        reverseArm.setOnClickListener(this);
        circularLeft.setOnClickListener(this);
        circularRight.setOnClickListener(this);
        irrigationOn.setOnClickListener(this);
        irrigationOff.setOnClickListener(this);
        stop.setOnClickListener(this);
    /*    jcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton jcb, boolean isChecked) {
                if (isChecked) {
                 //   Intent i = new Intent(this, tractor.class);
                  //  startActivity(i);
                } else {
                //    Intent i = new Intent(this, MainActivity.class);
                }
            }
        });*/
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
        if(view == forward){
            dri.setValue(2);
        }

        if(view == reverse){
            dri.setValue(0);
        }

        if(view == left){
            dri.setValue(3);
        }

        if(view == right){
            dri.setValue(4);
        }
        if(view == forwardArm){
            han.setValue(0);
        }
        if(view == reverseArm){
            han .setValue(2);
        }
        if(view == circularLeft){
            mov.setValue(0);
        }

        if(view == circularRight){
            mov.setValue(2);
        }

        if(view == irrigationOn){
            myRef.setValue(90);
        }


        if(view == irrigationOff){
            myRef.setValue(100);
        }

        if(view == stop){
           mov.setValue(1);
            dri.setValue(1);
            han.setValue(1);
        }
    }

 /*   @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(isChecked){
       //     Intent i = new Intent(this, tractor.class);
         //    startActivity(i);
        }else{

        }
    }*/
}