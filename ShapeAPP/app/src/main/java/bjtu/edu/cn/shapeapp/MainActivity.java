package bjtu.edu.cn.shapeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ShapeView(this));
    }

    //import android.view.Menu;
    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu) {
    //            Inflate the menu; this adds items to the action bar if it is present.
    //	    getMenuInflater().inflate(R.menu.main, menu);
    //	    return true;
    //}
}