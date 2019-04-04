package cn.bjtu.edu.gestureapp;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


public class GestureAppActivity extends Activity implements OnGestureListener {

//    private static final int FILING_MIN_DISTANCE = 100;
//    private static final int FILING_LIMIT_VELOCITY = 200;

    //修改可识别的最小移动距离
    private static final int SCROLLING_MIN_DISTANCE = 10;
    //修改每次移动触发所需要的最小移动距离
    private static final int SCROLLING_LIMIT_VELOCITY = 15;
    private int mMotionX = 0;
    private int mMotionY = 0;

    private GestureDetector gesture;
    private GestureView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gesture = new GestureDetector(this);
        view = new GestureView(this);

        setContentView(view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {                                               //对触屏事件进行反馈返回事件
        gesture.onTouchEvent(event);

        return super.onTouchEvent(event);

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {                                                        //点击事件
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {                                                   //点击事件结束
        return false;
    }


    /*
    修改部分代码
    实现思路：
    使用onScroll函数，传入两个事件（即摁住并在屏幕上移动的事件）并建立监听，对于不同动作进行相应处理；
    其中图片的移动距离和实际在屏幕上的滑动距离有关；
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        //若传入事件为向左滑动，则将图片相应向左进行移动；
        if (e1.getX() - e2.getX() > SCROLLING_MIN_DISTANCE  && Math.abs(distanceX) > SCROLLING_LIMIT_VELOCITY) {
            view.leftMoveS();
            if(e2.getX() - e1.getX() > SCROLLING_MIN_DISTANCE && Math.abs(distanceX) > SCROLLING_LIMIT_VELOCITY){
                view.rightMoveS();
            }
        }

        //若传入事件为向右滑动，则将图片相应向右进行移动；
        if (e2.getX() - e1.getX() > SCROLLING_MIN_DISTANCE && Math.abs(distanceX) > SCROLLING_LIMIT_VELOCITY) {
            view.rightMoveS();
            if(e1.getX() - e2.getX() > SCROLLING_MIN_DISTANCE && Math.abs(distanceX) > SCROLLING_LIMIT_VELOCITY){
                view.leftMoveS();
            }
        }

        //若传入事件为向上滑动，则将图片相应向上进行移动；
        if (e1.getY() - e2.getY() > SCROLLING_MIN_DISTANCE  && Math.abs(distanceY) > SCROLLING_LIMIT_VELOCITY) {
            view.upMoveS();
            if (e2.getY() - e1.getY() > SCROLLING_MIN_DISTANCE  && Math.abs(distanceY) > SCROLLING_LIMIT_VELOCITY) {
                view.downMoveS();
            }
        }

        //若传入事件为向下滑动，则将图片相应向下进行移动；
        if (e2.getY() - e1.getY() > SCROLLING_MIN_DISTANCE  && Math.abs(distanceY) > SCROLLING_LIMIT_VELOCITY) {
            view.downMoveS();
            if (e1.getY() - e2.getY() > SCROLLING_MIN_DISTANCE  && Math.abs(distanceY) > SCROLLING_LIMIT_VELOCITY) {
                view.upMoveS();
            }
        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {                                                        //长点击事件
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

//        if (e1.getX() - e2.getX() > FILING_MIN_DISTANCE  && Math.abs(velocityX) > FILING_LIMIT_VELOCITY) {
//            view.leftMoveF();
//        }
//
//        else if (e2.getX() - e1.getX() > FILING_MIN_DISTANCE && Math.abs(velocityX) > FILING_LIMIT_VELOCITY) {
//            view.rightMoveF();
//        }
//
//        else if (e1.getY() - e2.getY() > FILING_MIN_DISTANCE  && Math.abs(velocityY) > FILING_LIMIT_VELOCITY) {
//            view.upMoveF();
//        }
//
//        else if (e2.getY() - e1.getY() > FILING_MIN_DISTANCE  && Math.abs(velocityY) > FILING_LIMIT_VELOCITY) {
//            view.downMoveF();
//        }

        return false;
    }
}
