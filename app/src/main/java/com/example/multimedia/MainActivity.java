package com.example.multimedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    MediaPlayer ring;
    VideoView videoview;
    boolean musicPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoview = (VideoView) findViewById(R.id.videoview);
        ring = MediaPlayer.create(MainActivity.this, R.raw.christmas);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.android);
        videoview.setVideoURI(uri);
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(videoview);
        videoview.setMediaController(controller);

        String frameVideo = "<html><body><iframe width=\"300\" height=\"200\" src=\"https://www.youtube.com/embed/6uUsfU6PGp0\" frameborder=\"0\" ></iframe></body></html>";
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //myWebView.loadData(frameVideo, "text/html", "utf-8");
        myWebView.loadUrl("https://www.youtube.com/embed/6uUsfU6PGp0");

        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });

    }

    public void musicPause(View v) {
        if(ring.isPlaying() || musicPaused) {
            if (!musicPaused) {
                ring.pause();
                musicPaused = true;
            } else{
                ring.start();
                musicPaused = false;
            }
        }
    }

    public void musicStopStart(View v) {
        try {
            if (ring.isPlaying() || musicPaused){
                ring.stop();
                ring.prepare();
                musicPaused = false;
            }
            else{
                ring.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


