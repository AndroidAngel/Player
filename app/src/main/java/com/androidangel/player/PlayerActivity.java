package com.androidangel.player;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

public class PlayerActivity extends AppCompatActivity {

    public static final String LOG_TAG = "PlayerActivity";
    public static final int MEDIA_RESOURCE_ID = R.raw.the_nights_avicii;

    private SeekBar mSeekBarAudio;
    private MusicAdapter mMusicAdapter;
    private boolean mUserIsSeeking = false;
    private ImageButton mPlayBtn;
    private ImageButton mPauseBtn;
    private ImageButton mResetBtn;
    private ImageView mPlayerImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initializeUI();
        initializeSeekBar();
        initializePlayback();
        Log.d(LOG_TAG, "onCreate: finished");
    }


    @Override
    public void onStart() {
        super.onStart();
        mMusicAdapter.loadMedia(MEDIA_RESOURCE_ID);
        Log.d(LOG_TAG, "ONSTART: CREATE MEDIAPLAYER");
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isChangingConfigurations() && mMusicAdapter.isPlaying()) {
            Log.d(LOG_TAG, "ONSTOP: MEDIAPLAYER STOP");
        } else {
            mMusicAdapter.release();
            Log.d(LOG_TAG, "ONSTOP: RELEASE MEDIAPLAYER");
        }
    }

    @SuppressLint("WrongViewCast")
    private void initializeUI() {

        mPlayBtn = findViewById(R.id.btn_play);
        mPauseBtn = findViewById(R.id.btn_pause);
        mResetBtn = findViewById(R.id.btn_reset);
        mSeekBarAudio = findViewById(R.id.seekbar_view);

        mPlayerImage = findViewById(R.id.imageView);

        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMusicAdapter.play();

            }
        });
        mPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMusicAdapter.pause();
            }
        });
        mResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMusicAdapter.reset();
            }
        });
    }

    private void initializePlayback() {
        MusicPlayerHolder mMusicPlayerHolder = new MusicPlayerHolder(this);
        Log.d(LOG_TAG, "Initializing Playback");
        mMusicPlayerHolder.setPlaybackInfoListener(new PlaybackListener());
        mMusicAdapter = mMusicPlayerHolder;
        Log.d(LOG_TAG, "initializePlayback: MediaPlayerHolder progress callback set");
    }

    private void initializeSeekBar() {
        mSeekBarAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int userPosition = 0;


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    userPosition = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mUserIsSeeking = true;

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mUserIsSeeking = false;
                mMusicAdapter.seekTo(userPosition);
            }
        });
    }


    public class PlaybackListener extends PlaybackInfoListener {
        @Override
        public void onDurationChanged(int duration) {
            mSeekBarAudio.setMax(duration);
            Log.d(LOG_TAG, String.format("SET PLAYBACK DURATION : setMax", duration));
        }


        @Override
        public void onPositionChanged(int position) {
            if (!mUserIsSeeking) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mSeekBarAudio.setProgress(position, true);
                }
                Log.d(LOG_TAG, String.format("SET PLAYBACK POSITION: setProgress", position));
            }
        }

        @Override
        public void onStateChanged(@State int state) {
            String stringState = PlaybackInfoListener.convertStateToString(state);
            onLogUpdated(String.format("ONSTATECHANGED", stringState));
        }

        @Override
        public void onPlaybackCompleted() {
        }
    }
}
