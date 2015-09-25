package hupp_technologies.androidradiodemo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinkedList<String> urls;
    private Button btnPlay;
    private MediaPlayer mediaPlayer;
    String URL = "http://revolutionradio.ru/live.ogg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.btn_play);
        btnPlay.setOnClickListener(this);

        mediaPlayer = new MediaPlayer();

        initializeMediaPlayer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:

                //To Start the music
                if(btnPlay.getText().equals("Play")){
                    btnPlay.setText("Stop");
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                }
               //To Stop the music
                else {
                    btnPlay.setText("Play");
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        initializeMediaPlayer();
                    }
                }
                break;
        }
    }
    //To Prepare or initialize and Music player
    private void initializeMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(URL);
            mediaPlayer.prepareAsync();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (SecurityException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        Log.d("tag", "on Destroy");
    }
}
