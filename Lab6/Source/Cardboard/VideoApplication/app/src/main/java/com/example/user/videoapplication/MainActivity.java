package com.example.user.videoapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private VrVideoView mVrVideoView;
    private SeekBar mSeekBar;
    private Button mVolumeButton;
    private static final String STATE_PROGRESS = "state_progress";
    private static final String STATE_DURATION = "state_duration";
    private boolean mIsPaused;
    private boolean mIsMuted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        VideoLoaderTask mBackgroundVideoLoaderTask = new VideoLoaderTask();
        mBackgroundVideoLoaderTask.execute();

    }

    private void initViews() {
        mVrVideoView = (VrVideoView) findViewById(R.id.video_view);
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mVolumeButton = (Button) findViewById(R.id.btn_volume);

        mVolumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onVolumeToggleClicked();
            }
        });
        mVrVideoView.setEventListener(new ActivityEventListener());
        mSeekBar.setOnSeekBarChangeListener(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        mVrVideoView.pauseRendering();
        mIsPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVrVideoView.resumeRendering();
        mIsPaused = false;
    }

    @Override
    protected void onDestroy() {
        mVrVideoView.shutdown();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(STATE_PROGRESS, mVrVideoView.getCurrentPosition());
        outState.putLong(STATE_DURATION, mVrVideoView.getDuration());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        long progress = savedInstanceState.getLong(STATE_PROGRESS);

        mVrVideoView.seekTo(progress);
        mSeekBar.setMax((int) savedInstanceState.getLong(STATE_DURATION));
        mSeekBar.setProgress((int) progress);
    }
    public void playPause() {
        if( mIsPaused ) {
            mVrVideoView.playVideo();
        } else {
            mVrVideoView.pauseVideo();
        }

        mIsPaused = !mIsPaused;
    }

    public void onVolumeToggleClicked() {
        mIsMuted = !mIsMuted;
        mVrVideoView.setVolume(mIsMuted ? 0.0f : 1.0f);

    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if( fromUser ) {
            mVrVideoView.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private class ActivityEventListener extends VrVideoEventListener {
        @Override
        public void onLoadSuccess() {
            super.onLoadSuccess();
            mSeekBar.setMax((int) mVrVideoView.getDuration());
            mIsPaused = false;
        }

        @Override
        public void onLoadError(String errorMessage) {
            super.onLoadError(errorMessage);
        }

        @Override
        public void onClick() {
            playPause();
        }

        @Override
        public void onNewFrame() {
            super.onNewFrame();
            mSeekBar.setProgress((int) mVrVideoView.getCurrentPosition());
        }

        @Override
        public void onCompletion() {
            super.onCompletion();
            mVrVideoView.seekTo(0);
        }
    }
    class VideoLoaderTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                VrVideoView.Options options = new VrVideoView.Options();
                options.inputType = VrVideoView.Options.TYPE_MONO;
                mVrVideoView.loadVideoFromAsset("space.mp4", options);
            } catch( IOException e ) {
                //Handle exception
            }

            return true;
        }
    }
}