package id.ac.ui.cs.mobileprogramming;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnimationActivity extends AppCompatActivity implements Animation.AnimationListener, View.OnClickListener {

    MyGLSurfaceView mgsv;
    TextView tv;
    ImageView iv;
    Animation anim;
    Button btnColor;

    private DrawingView drawView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_animation);
        mgsv = new MyGLSurfaceView(this);
        setContentView(mgsv);
    }
}
