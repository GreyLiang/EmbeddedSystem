package cn.bjtu.edu.soundplayapp;

import android.os.Bundle;
import java.util.ArrayList;
import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;


public class SoundPlayAppActivity extends Activity implements OnGestureListener {
    private static final int FILING_MIN_DISTANCE = 500;
    private static final int FILING_LIMIT_VELOCITY = 200;

    private GestureDetector gesture;
    private MediaPlayer media;
    private LinearLayout linear;
    private TextView text;

    private ArrayList<Integer> data;
    private int cur_pos;

    private boolean pause;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitUI();

        for(int i = 0; i != 3; ++i) {
            data.add(R.raw.test1 + i);
        }

        media = new MediaPlayer();

        cur_pos = -1;
        pause = false;
    }

    @Override
    protected void onPause() {
        Stop();

        super.onPause();
    }

    private void InitUI() {
        gesture = new GestureDetector(this);
        linear = new LinearLayout(this);
        text = new TextView(this);
        data = new ArrayList<Integer>();

        linear.setGravity(Gravity.CENTER_VERTICAL);
        linear.setBackgroundColor(Color.MAGENTA);

        text.setTextSize(50);
        text.setTextColor(Color.WHITE);
        text.setGravity(Gravity.CENTER);
        text.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        text.setText("Ready");

        linear.addView(text);
        setContentView(linear);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gesture.onTouchEvent(event);

        if(event.getAction() == MotionEvent.ACTION_UP) {
            linear.setBackgroundColor(Color.MAGENTA);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        linear.setBackgroundColor(Color.YELLOW);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        //do not
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        //do not
        return false;
    }

    /*
    使用onScroll函数实现控制乐曲
    实现思路：
    修改onScroll部分代码，识别到在屏幕上向左或向右的滑动事件时对乐曲进行相应的操作
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        //识别到向左滑动时正向顺序播放
        if (e1.getX() - e2.getX() > FILING_MIN_DISTANCE && Math.abs(distanceX) > FILING_LIMIT_VELOCITY) {
            PrevPlay();
        }

        //识别到向右滑动时播放上一首
        if (e2.getX() - e1.getX() > FILING_MIN_DISTANCE  && Math.abs(distanceX) > FILING_LIMIT_VELOCITY) {
            NextResumePlay();
        }

        //识别到向上滑动时暂停
        if (e1.getY() - e2.getY() > FILING_MIN_DISTANCE && Math.abs(distanceY) > FILING_LIMIT_VELOCITY) {
            Pause();
        }

        //识别到向下滑动时停止播放
        if (e2.getY() - e1.getY() > FILING_MIN_DISTANCE && Math.abs(distanceY) > FILING_LIMIT_VELOCITY) {
            Stop();
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        //do not
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

//        if (e1.getX() - e2.getX() > FILING_MIN_DISTANCE && Math.abs(velocityX) > FILING_LIMIT_VELOCITY) {
//            PrevPlay();
//        }
//        else if (e2.getX() - e1.getX() > FILING_MIN_DISTANCE  && Math.abs(velocityX) > FILING_LIMIT_VELOCITY) {
//            NextResumePlay();
//        }
//        else if (e1.getY() - e2.getY() > FILING_MIN_DISTANCE && Math.abs(velocityY) > FILING_LIMIT_VELOCITY) {
//            Pause();
//        }
//        else if (e2.getY() - e1.getY() > FILING_MIN_DISTANCE && Math.abs(velocityY) > FILING_LIMIT_VELOCITY) {
//            Stop();
//        }

        return false;
    }

    private void StartPlay() {
        media.setOnCompletionListener(completionEvent);
        media.start();
    }

    private void PrevPlay() {
        pause = false;
        media.stop();

        if(--cur_pos < 0) {
            cur_pos = (data.size() - 1);
        }

        text.setText(String.format("Play: %d", cur_pos));

        media = MediaPlayer.create(this, data.get(cur_pos));
        StartPlay();
    }

    private void NextResumePlay() {
        if(pause){
            pause = false;
            text.setText(String.format("Resume Play: %d", cur_pos));
        }
        else {
            if(media.isPlaying()) {
                media.stop();
            }

            cur_pos = (++cur_pos) % 3;
            media = MediaPlayer.create(this, data.get(cur_pos));

            text.setText(String.format("Play: %d", cur_pos));
        }

        StartPlay();
    }

    private void Pause() {
        if(media.isPlaying()) {
            text.setText("Pause");

            pause = true;
            media.pause();
        }
    }

    private void Stop() {
        text.setText("Stop");

        pause = false;
        media.stop();
    }

    private OnCompletionListener completionEvent = new OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {
            NextResumePlay();
        }
    };
}

