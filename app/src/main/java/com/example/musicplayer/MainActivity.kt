package com.example.musicplayer

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer : MediaPlayer
    var totalTime:Int=0
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val btnplay=findViewById<ImageView>(R.id.img_play)
        val btnpause=findViewById<ImageView>(R.id.img_pause)
        val btnstop=findViewById<ImageView>(R.id.img_stop)
        val seekBarMusic=findViewById<SeekBar>(R.id.seekBar)

        mediaPlayer=MediaPlayer.create(this,R.raw.music)
        mediaPlayer.setVolume(1f,1f)
        totalTime=mediaPlayer.duration

        btnplay.setOnClickListener{
            Toast.makeText(this,"Music Started",Toast.LENGTH_SHORT).show()
            mediaPlayer?.start()
        }
        btnpause.setOnClickListener{
            Toast.makeText(this,"Music Paused",Toast.LENGTH_SHORT).show()
            mediaPlayer?.pause()
        }
        btnstop.setOnClickListener{
            Toast.makeText(this,"Music Stopped",Toast.LENGTH_SHORT).show()
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
        }

        seekBarMusic.max=totalTime
        seekBarMusic.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        val handler= Handler()
        handler.postDelayed(object :Runnable{
            override fun run(){
                try {
                    seekBarMusic.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                }catch(exception:java.lang.Exception){
                    seekBarMusic.progress=0
                }
                }
        },0)
    }
}