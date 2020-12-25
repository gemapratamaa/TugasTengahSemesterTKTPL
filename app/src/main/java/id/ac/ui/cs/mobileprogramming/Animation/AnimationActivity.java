package id.ac.ui.cs.mobileprogramming;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

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
        setContentView(new MyGLSurfaceView(this));
    }

    @Override
    protected void onPause() {
        super.onPause();

        //   mgsv.onPause();// Pauses rendering

    }
    @Override
    protected void onResume() {
        super.onResume();

        //    mgsv.onResume();
    }
    int menuNo=1;
    AnimationDrawable puppet;

    void PerformDrawableAnimation()
    {
        iv.setImageBitmap(null);
        // iv.setBackgroundResource(R.drawable.fox_puppet);
        //	 puppet = (AnimationDrawable) iv.getBackground();
        //////////// My Method/////////////////////
        puppet=new AnimationDrawable();
        InputStream is = null;
        for(int i=1;i<=31;i++) {
            try {
                is = this.getResources().getAssets().open("fox/photo"+i+".png");
                Bitmap b = BitmapFactory.decodeStream(is);
                Drawable d=new BitmapDrawable(b);
                puppet.addFrame(d, 200);
            } catch (Exception e) {
                ;
            }
        }
        iv.setBackgroundDrawable(puppet);
        puppet.setOneShot(true);

        //////////////////////////////////
        puppet.start();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id)
        {
            ///////////// Main Demo Mode Selection/////////////////////////////
            case R.id.menuDrawableAnimation1:
                setContentView(R.layout.activity_main);

                iv=(ImageView)findViewById(R.id.ivAnimation);
                tv=(TextView)findViewById(R.id.tvTop);
                drawView = (DrawingView)findViewById(R.id.dvMain);
                btnColor=(Button)findViewById(R.id.btnColor);
                btnColor.setOnClickListener(this);
                this.setTitle("Drawable Anim");
                PerformDrawableAnimation();

                break;
            case R.id.menuDrawableAnimation2:
                setContentView(R.layout.activity_main);
                drawView = (DrawingView)findViewById(R.id.dvMain);
                iv=(ImageView)findViewById(R.id.ivAnimation);
                tv=(TextView)findViewById(R.id.tvTop);
                btnColor=(Button)findViewById(R.id.btnColor);
                btnColor.setOnClickListener(this);
                this.setTitle("Drawable Anim");
                PerformDrawableAnimation();

                break;
            case R.id.menuDrawableAnimation3:
                setContentView(R.layout.activity_main);
                drawView = (DrawingView)findViewById(R.id.dvMain);
                iv=(ImageView)findViewById(R.id.ivAnimation);
                tv=(TextView)findViewById(R.id.tvTop);
                btnColor=(Button)findViewById(R.id.btnColor);
                btnColor.setOnClickListener(this);
                this.setTitle("Drawable Anim");
                PerformDrawableAnimation();
                break;
            case R.id.menuViewAnimation:
                setContentView(R.layout.activity_main);

                iv=(ImageView)findViewById(R.id.imgMain);
                tv=(TextView)findViewById(R.id.tvTop);
                drawView = (DrawingView)findViewById(R.id.dvMain);
                btnColor=(Button)findViewById(R.id.btnColor);
                btnColor.setOnClickListener(this);
                menuNo=2;
                this.setTitle("View Anim");
                invalidateOptionsMenu();// to call back oncreate menu again
                break;
            case R.id.menuViewAnimation1:
                setContentView(R.layout.activity_main);
                drawView = (DrawingView)findViewById(R.id.dvMain);
                iv=(ImageView)findViewById(R.id.imgMain);
                tv=(TextView)findViewById(R.id.tvTop);
                menuNo=2;
                this.setTitle("View Anim");
                btnColor=(Button)findViewById(R.id.btnColor);
                btnColor.setOnClickListener(this);
                invalidateOptionsMenu();// to call back oncreate menu again
                break;
            case R.id.menuOpenGL:
                menuNo=1;
                setContentView(new MyGlSurfaceView(this));
                this.setTitle("OpenGL");
                invalidateOptionsMenu();
                break;
            case R.id.menuOpenGl1:
                menuNo=1;
                setContentView(new MyGlSurfaceView(this));
                this.setTitle("OpenGL");
                invalidateOptionsMenu();
                break;
            case R.id.menuPropertyAnimation1:
                menuNo=3;
                setContentView(R.layout.activity_main);
                iv=(ImageView)findViewById(R.id.imgMain);
                tv=(TextView)findViewById(R.id.tvTop);
                drawView = (DrawingView)findViewById(R.id.dvMain);
                btnColor=(Button)findViewById(R.id.btnColor);
                btnColor.setOnClickListener(this);
                this.setTitle("Property Anim");
                invalidateOptionsMenu();
                break;
            case R.id.menuPropertyAnimation2:
                menuNo=3;
                setContentView(R.layout.activity_main);
                iv=(ImageView)findViewById(R.id.imgMain);
                tv=(TextView)findViewById(R.id.tvTop);
                drawView = (DrawingView)findViewById(R.id.dvMain);
                btnColor=(Button)findViewById(R.id.btnColor);
                btnColor.setOnClickListener(this);
                this.setTitle("Property Anim");
                invalidateOptionsMenu();
                break;
            /////////////////////// View Animations////////////////////////
            case R.id.menuFadeIn:
                anim = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.fade_in);
                anim.setAnimationListener(this);
                iv.startAnimation(anim);
                break;
            case R.id.menuFadeOut:
                anim = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.fade_out);
                anim.setAnimationListener(this);
                iv.startAnimation(anim);
                break;
            case R.id.menuSequential:
                anim = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.sequential);
                anim.setAnimationListener(this);
                iv.startAnimation(anim);
                break;
            case R.id.menuZoomIn:
                anim = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.zoom_in);
                anim.setAnimationListener(this);
                iv.startAnimation(anim);
                break;

            case R.id.menuZoomOut:
                anim = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.zoom_out);
                anim.setAnimationListener(this);
                iv.startAnimation(anim);
                break;
            case R.id.menuFlip:
                anim = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.flip);
                anim.setAnimationListener(this);
                iv.startAnimation(anim);
                break;
            case R.id.menuRotate:
                anim = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.rotate);
                anim.setAnimationListener(this);
                iv.startAnimation(anim);
                break;
            case R.id.menuSlideUp:
                anim = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.slide_up);
                anim.setAnimationListener(this);
                iv.startAnimation(anim);
                break;
            case R.id.menuSlideDown:
                anim = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.slide_down);
                anim.setAnimationListener(this);
                iv.startAnimation(anim);
                break;

            case R.id.menuTogatherRotateScale:
                anim = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.together);
                anim.setAnimationListener(this);
                iv.startAnimation(anim);
                break;
            case R.id.menuBlink:
                anim = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.blink);
                anim.setAnimationListener(this);
                iv.startAnimation(anim);
                break;
            case R.id.menuBounce:
                anim = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.bounce);
                anim.setAnimationListener(this);
                iv.startAnimation(anim);
                break;
            //////////////////////////////// Property Animation/////////
            case R.id.menuValueAnimation:

                ValueAnimator animation = ValueAnimator.ofFloat(0f, 1f);
                animation.setDuration(8000);
                animation.addUpdateListener(new AnimatorUpdateListener()
                {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        float val=Float.parseFloat(animation.getAnimatedValue().toString());
                        iv.setAlpha(val);
                        iv.setScaleX(val);
                        iv.setScaleY(val);
                        // TODO Auto-generated method stub
                        tv.setText(animation.getAnimatedValue().toString());

                    }
                });

                animation.start();
                break;
            case R.id.menuObjectAnimation:
                ObjectAnimator oa=ObjectAnimator.ofFloat(iv, "rotation", 0, 45);
                //ObjectAnimator oa=ObjectAnimator.ofFloat(iv, "scaleX", 0, 4);
                //ObjectAnimator oa=ObjectAnimator.ofFloat(iv, "translationX", 0, 400);
                oa.setDuration(6000);


                oa.setRepeatMode(ValueAnimator.REVERSE);
                oa.setRepeatCount(ValueAnimator.INFINITE);
                oa.start();
                break;
            case R.id.menuAnimatorSet:
                ObjectAnimator oaRotation=ObjectAnimator.ofFloat(iv, "rotation", 0, 45);
                oaRotation.setDuration(5000);
                oaRotation.setRepeatCount(ValueAnimator.INFINITE);
                oaRotation.setRepeatMode(ValueAnimator.REVERSE);
                ObjectAnimator oaScaleX=ObjectAnimator.ofFloat(iv, "scaleX", 0, 4);
                oaScaleX.setDuration(5000);
                ObjectAnimator oaScaleY=ObjectAnimator.ofFloat(iv, "scaleY", 0, 4);
                oaScaleY.setDuration(5000);
                ObjectAnimator oaAlpha=ObjectAnimator.ofFloat(iv, "alpha", 0, 1);
                oaAlpha.setDuration(5000);

                AnimatorSet combine = new AnimatorSet();

                combine.playTogether(oaScaleX,oaScaleY);
                combine.play(oaAlpha).before(oaRotation);
                combine.start();
                break;
            ///////////////////////////////////////////////////////////////





        }
        return super.onOptionsItemSelected(item);
    }


}
