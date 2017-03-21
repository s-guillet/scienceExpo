package com.scienceaction.expo_propulsion.pages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.scienceaction.expo_propulsion.R;
import com.scienceaction.expo_propulsion.helper.PleinEcran;
import com.scienceaction.expo_propulsion.helper.ViewpagerActivity;

import java.util.Timer;
import java.util.TimerTask;

public class PageVideo extends Activity {

    Button btnRetourMenu;
    Button btnLecture;
    Button btnAugmenteVolume;
    Button btnVolDiminue;
    TextView textVolSonor;
    VideoView video = null;
    String videoALire;
    String itemRetour;
    String pathPrefix = ("mnt/external_sd/video/");  // adresse des videos sur carte SD
    RelativeLayout layoutVideoAction;
    AudioManager audioManager;  //pour gerer volume sonor
    private int currentVolume;  //volume sonor actuel
    String strCurrentVolume;
    private int tempsPause = 1000;  //pause pendant transition


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_page_video);

        //plein ecran
        PleinEcran.pleinEcran(this);

        //recupere nom video a lire depuis le menu
        videoALire = getIntent().getExtras().getString("videoALire");

        //recupere fragment origine pour retour
        itemRetour = getIntent().getExtras().getString("itemRetour");

        //layout action
        layoutVideoAction = (RelativeLayout) findViewById(R.id.layout_video_action);
        layoutVideoAction.setVisibility(View.INVISIBLE);

        //bouton retour menu
        btnRetourMenu = (Button) findViewById(R.id.btnRetourMenu);
        btnRetourMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.i("pagevideo","click retour");
                Intent pageVideo = new Intent(PageVideo.this, ViewpagerActivity.class);
                pageVideo.putExtra("itemRetour",itemRetour);
                startActivity(pageVideo);
                overridePendingTransition(R.transition.transition_vers_menu_out,R.transition.transition_vers_menu_in);
            }
        });

        //video
        video = (VideoView) findViewById(R.id.videoView);
        video.setVideoPath(pathPrefix + videoALire +".mp4");

        video.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener(){
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent second = new Intent(PageVideo.this, ViewpagerActivity.class);
                        second.putExtra("itemRetour",itemRetour);
                        startActivity(second);
                    }
                }
        );

        video.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("vidoe","click");
                video.pause();
                layoutVideoAction.setVisibility(View.VISIBLE);
                return false;
            }
        });
        video.canPause();
        video.seekTo(1);

        //pause video pendant transition
        Handler handler = new Handler();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                video.start();
            }
        }, tempsPause);

        //bouton reprise de la lecture
        btnLecture = (Button) findViewById(R.id.btnLecture);
        btnLecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutVideoAction.setVisibility(View.INVISIBLE);
                video.start();
            }
        });

        //gestion du volume sonor
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //texte vol sonor
        textVolSonor = (TextView) findViewById(R.id.textVolSonor);
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        strCurrentVolume = String.valueOf(currentVolume) ;
        textVolSonor.setText(strCurrentVolume);

        //bouton augmente volume
        btnAugmenteVolume = (Button) findViewById(R.id.btnVolAugmente);
        btnAugmenteVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,currentVolume + 1,0);
                currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                strCurrentVolume = String.valueOf(currentVolume) ;
                textVolSonor.setText(strCurrentVolume);
            }
        });

        btnVolDiminue = (Button) findViewById(R.id.btnVolDiminue);
        btnVolDiminue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                if (currentVolume > 3 ) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume - 1, 0);
                    currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                }else{
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 3, 0);
                }
                strCurrentVolume = String.valueOf(currentVolume) ;
                textVolSonor.setText(strCurrentVolume);
            }
    });


    }

}
