package com.Heba.alquran;

import androidx.appcompat.app.AppCompatActivity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.Heba.alquran.Modules.Ayahs;
import java.io.IOException;
import java.util.List;
import io.paperdb.Paper;

public class PlayAudio extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    TextView ViewAhyaText;
    ImageButton playAudioButton;
    int CountOfAyahRepetitions, CountOfFakraRepetitions, CountOfAlayat;
    List<Ayahs>ayahs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);
        ViewAhyaText =findViewById(R.id.ahya_text);
        ViewAhyaText.setMovementMethod(new ScrollingMovementMethod());
        playAudioButton =findViewById(R.id.playAudio_btn);
        mediaPlayer = new MediaPlayer();
        CountOfAyahRepetitions =0;
        CountOfAlayat =0;
        CountOfFakraRepetitions =0;
        ayahs = Paper.book().read("quran-ayahs");
        playAudio(Integer.parseInt(getIntent().getStringExtra("ayah_Repetition")),Integer.parseInt(getIntent().getStringExtra("fakra_Repetition")));

        playAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking the media player
                // if the audio is playing or not.
                if (mediaPlayer.isPlaying()) {
                      mediaPlayer.pause();
                      playAudioButton.setBackgroundResource(R.drawable.play);
                    // below line is to display a message
                    // when media player is paused.
                    Toast.makeText(PlayAudio.this, "Audio has been paused", Toast.LENGTH_SHORT).show();
                } else {
                    // this method is called when media
                    // player is not playing.
                    Toast.makeText(PlayAudio.this, "Audio has not played", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    public void playAudio( final int num1 ,final  int num2) {
        ViewAhyaText.setText(ayahs.get(CountOfAlayat).getText());
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(ayahs.get(CountOfAlayat).getAudio());
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {

//                    if(CountOfFakraRepetitions<num2) {
//                        if (CountOfAyahRepetitions < num1 && CountOfAlayat < ayahs.size() - 1) {
//                            if (CountOfAyahRepetitions < num1){
//                                ViewAhyaText.setText(ayahs.get(CountOfAlayat).getText());
//                                mediaPlayer.start();
//                                CountOfAyahRepetitions++;
//                                Log.e("result", CountOfAlayat +">>"+ CountOfAyahRepetitions + ">>"+ CountOfFakraRepetitions);
//                            }
//                            CountOfAlayat++;
//                            CountOfAyahRepetitions=0;
//                            mediaPlayer.reset();
//                            try {
//                                ViewAhyaText.setText(ayahs.get(CountOfAlayat).getText());
//                                mediaPlayer.setDataSource(ayahs.get(CountOfAlayat).getAudio());
//                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                                mediaPlayer.prepare();
//                                mediaPlayer.start();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                        CountOfAyahRepetitions=0;
//                        CountOfAlayat =0;
//                        CountOfFakraRepetitions++;
//                        mediaPlayer.reset();
//                        try {
//                            ViewAhyaText.setText(ayahs.get(CountOfAlayat).getText());
//                            mediaPlayer.setDataSource(ayahs.get(CountOfAlayat).getAudio());
//                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                            mediaPlayer.prepare();
//                            mediaPlayer.start();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Log.e("result3", CountOfAlayat +">>"+ CountOfAyahRepetitions + ">>"+ CountOfFakraRepetitions +">>"+num1+">>"+num2);
//
//                    }
                     if (CountOfAyahRepetitions < num1)
                     {
                         mediaPlayer.start();
                         Log.e("result", CountOfAlayat +">>"+ CountOfAyahRepetitions + ">>"+ CountOfFakraRepetitions);
                         CountOfAyahRepetitions++;
                         if(CountOfAyahRepetitions==num1&& CountOfAlayat <ayahs.size()-1){
                             CountOfAyahRepetitions =0;
                             CountOfAlayat++;
                             mediaPlayer.reset();
                             try {
                                 ViewAhyaText.setText(ayahs.get(CountOfAlayat).getText());
                                 mediaPlayer.setDataSource(ayahs.get(CountOfAlayat).getAudio());
                                 mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                 mediaPlayer.prepare();
                                 mediaPlayer.start();
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
                             Log.e("result2", CountOfAlayat +">>"+ CountOfAyahRepetitions + ">>"+ CountOfFakraRepetitions);
                         }
                         if(CountOfAyahRepetitions==num1&& CountOfAlayat==ayahs.size()-1&& CountOfFakraRepetitions<num2-1){
                             CountOfAyahRepetitions=0;
                             CountOfAlayat =0;
                             CountOfFakraRepetitions++;
                             mediaPlayer.reset();
                             try {
                                 ViewAhyaText.setText(ayahs.get(CountOfAlayat).getText());
                                 mediaPlayer.setDataSource(ayahs.get(CountOfAlayat).getAudio());
                                 mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                 mediaPlayer.prepare();
                                 mediaPlayer.start();
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
                             Log.e("result3", CountOfAlayat +">>"+ CountOfAyahRepetitions + ">>"+ CountOfFakraRepetitions +">>"+num1+">>"+num2);
                         }
                         if(CountOfAyahRepetitions==num1&& CountOfAlayat==ayahs.size()-1&& CountOfFakraRepetitions==num2-1){
                             mediaPlayer.stop();
                         }

                     }
                }
            });

    }



}
