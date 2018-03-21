package com.example.negm.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView textView;
    Button button;
    CountDownTimer countDownTimer;
    boolean c=false;
    public void resetTimer (){

            textView.setText("0:30");
            seekBar.setProgress(30);
            countDownTimer.cancel();
            button.setText("Go!");
            seekBar.setEnabled(true);
            c=false;

    }

    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft /60;
        int seconds =secondsLeft -(minutes*60);
        String secondsString=Integer.toString(seconds) ;
        if (secondsString=="0"){secondsString="00";}
        else if (seconds < 9) {
            secondsString ="0"+secondsString;

        }
        textView.setText( Integer.toString(minutes) +":" + secondsString );


    }
    public void controlTimer (View view){
        if (c==false){
            c=true;
            seekBar.setEnabled(false);
            button.setText("Stop!");


             countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 +100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l /1000);

                }

                @Override
                public void onFinish() {
                    textView.setText("0:00");
                    MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.eggsound);
                    mediaPlayer.start();
                    resetTimer();

                }
            }.start();
        }
        else {
            resetTimer();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=(SeekBar) findViewById(R.id.seekBar);
        textView =(TextView) findViewById(R.id.textView);
        button=(Button) findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
