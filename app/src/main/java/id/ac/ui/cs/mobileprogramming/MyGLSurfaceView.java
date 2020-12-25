package id.ac.ui.cs.mobileprogramming;

import android.content.Context;
import android.graphics.Point;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.TextView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLSurfaceView extends GLSurfaceView {

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;

    TextView tv;
    AnimationActivity aaContext;

    MySimpleOpenGLES2DrawingClass mt;
    MyGLRenderer rend;

    public MyGLSurfaceView(Context context) {
        super(context);
        aaContext = (AnimationActivity) context;
        setEGLContextClientVersion(2);

        final float[] mMVPMatrix = new float[16];
        final float[] mProjectionMatrix = new float[16];
        final float[] mViewMatrix = new float[16];
        final float[] mRotationMatrix = new float[16];

        rend=new MyGLRenderer();
        rend.shouldDrawCube=true;
        setRenderer(rend);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        /*
        setRenderer(new Renderer() {

            public void onDrawFrame(GL10 unused) {
                // Redraw background color
                GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
            }

            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                GLES20.glClearColor(7.0f, 2.0f, 0.0f, 2.0f);
            }

            public void onSurfaceChanged(GL10 unused, int width, int height) {
                GLES20.glViewport(0, 0, width, height);
            }
    });

         */

    }

    public float[] SimpleTouch2GLCoord(Point touch) {
        Display display = aaContext.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        float screenW = size.x;
        float screenH = size.y;

        float normalizedX = 2f * touch.x/screenW - 1f;
        float normalizedY = 1f - 2f*touch.y/screenH;
        float normalizedZ = 0.0f;
        return new float[]{normalizedX,normalizedY,normalizedZ};

    }
    float mPreviousX = -1;
    float mPreviousY = -1;

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        final float x=e.getX();
        final float y=e.getY();

        final float[] normCoord=SimpleTouch2GLCoord(new Point((int)x,(int) y));
        final float []glCoord=rend.glCoordinate(normCoord[0], normCoord[1]);
        Log.i("GlX="+glCoord[0]+" glY="+glCoord[1] ,"X="+x+" Y="+y);
        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }

                rend.mover="NONE";
                if(rend.triangle.isTouched(glCoord))
                {
                    rend.mover="TRI";
                }
                if(rend.line.isTouched(glCoord))
                {
                    rend.mover="LINE";
                }
                if(rend.circle.isTouched(glCoord))
                {
                    rend.mover="CIR";
                }
                if(rend.rect.isTouched(glCoord))
                {
                    rend.mover="RECT";
                }

                rend.setAngle(rend.getAngle() +  ((dx + dy) * TOUCH_SCALE_FACTOR));  // = 180.0f / 320
                requestRender();
        }

        mPreviousX = x;
        mPreviousY = y;


        return true;

    }
}

// https://www.codeproject.com/Articles/822412/Article-Beginners-Guide-to-Android-Animation-Gr-2