package bjtu.edu.cn.shapeapp;

import android.view.View;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;

public class ShapeView extends View {
    private final float COMM_WIDTH = 250;
    private final float LEG_WIDTH = 100;
    private final float HEAD_HEIGHT = COMM_WIDTH - (COMM_WIDTH / 4);
    private final float EYE_RADIUS = HEAD_HEIGHT  / 12;
    private final float MARGINE = EYE_RADIUS / 2;
    private final float BODY_HEIGHT = HEAD_HEIGHT - COMM_WIDTH / 10;
    private final float ARM_WIDTH = EYE_RADIUS * 5;

    private Paint paint;
    private Point center;

    private RectF head_rect;
    private RectF body_rect;
    private RectF arm_rect;
    private RectF heart_rectL;
    private RectF heart_rectR;

    private float x1, y1, x2, y2;

    public ShapeView(Context context) {
        super(context);

        center = new Point();
        paint = new Paint();

        paint.setAntiAlias(true);

        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        center.x = getWidth() / 2;

        Configuration config = getResources().getConfiguration();
        if(config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            center.y = getHeight() / 2;
        }
        else {
            center.y = getHeight() / 3;
        }

        headDraw(canvas, paint);
        bodyDraw(canvas, paint);

        super.onDraw(canvas);
    }

    private void headDraw(Canvas canvas, Paint paint) {                                             //头
        x1 = center.x - COMM_WIDTH / 2;
        y1 = center.y - HEAD_HEIGHT;
        x2 = center.x + COMM_WIDTH / 2;
        y2 = center.y;
        head_rect = new RectF(x1, y1, x2, y2);

        x1 = head_rect.centerX();
        y1 = head_rect.centerY();
        RadialGradient grad = new RadialGradient(x1, y1, head_rect.width(), Color.GREEN, Color.BLACK, Shader.TileMode.CLAMP);

        paint.setAlpha(255);
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(grad);

        canvas.drawArc(head_rect, 0, -180, false, paint);
        paint.setShader(null);
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x1 - EYE_RADIUS * 3, y1 - EYE_RADIUS * 3, EYE_RADIUS, paint);
        canvas.drawCircle(x1 + EYE_RADIUS * 3, y1 - EYE_RADIUS * 3, EYE_RADIUS, paint);

        antennaDraw(canvas, paint);
    }

    private void antennaDraw(Canvas canvas, Paint paint) {                                          //天线
        x1 = head_rect.centerX();
        y1 = head_rect.top;

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.rgb(0, 80, 0));

        canvas.drawLine(x1 - 60, y1 - 20, x1 - 40, y1+ 7, paint);
        canvas.drawLine(x1 + 60, y1 - 20, x1 + 40, y1 + 7, paint);
    }

    private void bodyDraw(Canvas canvas, Paint paint) {                                             //身体
        x1 = head_rect.left;
        y1 = head_rect.bottom - HEAD_HEIGHT / 2 + MARGINE;
        x2 = head_rect.right;
        y2 = head_rect.bottom + BODY_HEIGHT;
        body_rect = new RectF(x1, y1, x2, y2);

        x1 = body_rect.centerX();
        y1 = body_rect.top;
        x2 = body_rect.centerX();
        y2 = body_rect.bottom;
        LinearGradient grad = new LinearGradient(x1, y1, x2, y2, Color.RED, Color.BLACK, Shader.TileMode.CLAMP);

        legDraw(canvas,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(grad);
        canvas.drawRoundRect(body_rect, 10, 10, paint);

        armDraw(canvas, paint);
        heartDraw(canvas, paint);
        markDraw(canvas, paint);

    }

    private void armDraw(Canvas canvas, Paint paint) {                                              //胳膊
        arm_rect = new RectF(0, body_rect.top, 0, body_rect.bottom - MARGINE * 2);

        x1 = body_rect.left - ARM_WIDTH;
        y1 = arm_rect.top;
        x2 = body_rect.left - MARGINE;
        y2 = arm_rect.bottom;
        RectF rect_left = new RectF(x1, y1, x2, y2);

        x1 = body_rect.right + MARGINE;
        y1 = arm_rect.top;
        x2 = body_rect.right + ARM_WIDTH;
        y2 = arm_rect.bottom;
        RectF rect_right = new RectF(x1, y1 - LEG_WIDTH, x2, y2 - LEG_WIDTH);

        paint.setStrokeWidth(20);
        canvas.drawRoundRect(rect_left, 30, 30, paint);
        canvas.drawRoundRect(rect_right, 30, 30, paint);
        paint.setShader(null);
    }

    private void legDraw(Canvas canvas, Paint paint) {                                              //腿
        arm_rect = new RectF(0, body_rect.top, 0, body_rect.bottom - MARGINE * 2);

        x1 = body_rect.left - ARM_WIDTH + LEG_WIDTH;
        y1 = arm_rect.top + COMM_WIDTH - 2 * EYE_RADIUS;
        x2 = body_rect.left - MARGINE + LEG_WIDTH - EYE_RADIUS;
        y2 = arm_rect.bottom + COMM_WIDTH -  2 * EYE_RADIUS;
        RectF rect_left = new RectF(x1, y1, x2, y2);

        x1 = body_rect.right + MARGINE - LEG_WIDTH + EYE_RADIUS;
        y1 = arm_rect.top + COMM_WIDTH - 2 * EYE_RADIUS;
        x2 = body_rect.right + ARM_WIDTH - LEG_WIDTH;
        y2 = arm_rect.bottom + COMM_WIDTH - 2 * EYE_RADIUS;
        RectF rect_right = new RectF(x1, y1, x2, y2);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(0, 200, 0));

        paint.setStrokeWidth(20);
        canvas.drawRoundRect(rect_left, 30, 30, paint);
        canvas.drawRoundRect(rect_right, 30, 30, paint);
        paint.setShader(null);
    }

    private void markDraw(Canvas canvas, Paint paint) {
        Path path = new Path();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(EYE_RADIUS * 2);
        paint.setColor(Color.YELLOW);
        paint.setAlpha(50);

        path.moveTo(body_rect.left + 40, body_rect.top + 40);
        path.lineTo(center.x, body_rect.bottom - 70);
        path.lineTo(body_rect.right - 40, body_rect.top + 40);

        canvas.drawPath(path, paint);
    }

    private void heartDraw(Canvas canvas, Paint paint) {                                            //心形
        Path path = new Path();

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(EYE_RADIUS / 2);
        paint.setColor(Color.rgb(241, 158, 194));
        paint.setAlpha(255);

        x1 = center.x;
        y1 = center.y - HEAD_HEIGHT / 2 + 13;
        x2 = center.x + COMM_WIDTH / 6;
        y2 = center.y;
        heart_rectL = new RectF(x1, y1, x2, y2);

        x1 = center.x- COMM_WIDTH / 6;
        y1 = center.y - HEAD_HEIGHT / 2 + 13;
        x2 = center.x ;
        y2 = center.y;
        heart_rectR = new RectF(x1, y1, x2, y2);

        path.addArc(heart_rectL, - 180, 180);
        path.addArc(heart_rectR, - 180, 180);
        path.moveTo(body_rect.left + 81, body_rect.top + 40);
        path.lineTo(center.x, body_rect.bottom - 150);
        path.lineTo(body_rect.right - 81, body_rect.top + 40);

        canvas.drawPath(path, paint);
    }
}