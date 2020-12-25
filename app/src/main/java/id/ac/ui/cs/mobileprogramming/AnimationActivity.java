package id.ac.ui.cs.mobileprogramming;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AnimationActivity extends AppCompatActivity {

    MyGLSurfaceView mgsv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_animation);
        mgsv = new MyGLSurfaceView(this);
        setContentView(mgsv);
    }
}
