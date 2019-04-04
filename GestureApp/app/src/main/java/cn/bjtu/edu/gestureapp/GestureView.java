package cn.bjtu.edu.gestureapp;

import android.view.View;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

public class GestureView extends View {

    private final int stepForScroll = 10;
    private final int stepForFling = 500;

    private Bitmap girl;
    private int x;
    private int y;

    public GestureView(Context context) {
        super(context);

        setBackgroundColor(Color.WHITE);
        girl = BitmapFactory.decodeResource(getResources(), R.drawable.girl);

        x = 80;
        y = 80;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(girl, x, y, null);

        super.onDraw(canvas);
    }

    public void leftMoveS() {
        x -= stepForScroll;
        invalidate();
    }

    public void rightMoveS() {
        x += stepForScroll;
        invalidate();
    }

    public void upMoveS() {
        y -= stepForScroll;
        invalidate();
    }

    public void downMoveS() {
        y += stepForScroll;
        invalidate();
    }

//    public void leftMoveF() {
//        x -= stepForFling;
//        invalidate();
//    }
//
//    public void rightMoveF() {
//        x += stepForFling;
//        invalidate();
//    }
//
//    public void upMoveF() {
//        y -= stepForFling;
//        invalidate();
//    }
//
//    public void downMoveF() {
//        y += stepForFling;
//        invalidate();
//    }
}
