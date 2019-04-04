package cn.bjtu.edu.vibrator;

import android.os.Bundle;
import android.app.Activity;
//import android.view.Menu;  //注释掉
import android.os.Vibrator;  //6-10新增
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;

public class VibratorActivity extends Activity {
    /** Called when the activity is first created. */
    EditText duration;   //新增
    Vibrator mVibrator;  //新增

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        duration = (EditText)findViewById(R.id.EdtDuration);  //21-31新增
        mVibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        final Button ButVib = (Button)findViewById(R.id.ButVib);
        final Button ButVibStop = (Button)findViewById(R.id.ButVibStop);

        ButVib.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                int value = Integer.parseInt(duration.getText().toString());
                mVibrator.vibrate(value);
            }
        });

        ButVibStop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
//                int value = Integer.parseInt(duration.getText().toString());
                mVibrator.cancel();
            }
        });
    }
}
