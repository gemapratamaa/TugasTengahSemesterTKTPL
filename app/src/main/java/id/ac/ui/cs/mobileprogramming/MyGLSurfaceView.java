package id.ac.ui.cs.mobileprogramming;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLSurfaceView extends GLSurfaceView {

    //MyRenderer mr;
    TextView tv;
    AnimationActivity aaContext;
    //MainActivity maContext;
    public MyGLSurfaceView(Context context) {
        super(context);
        aaContext = (AnimationActivity) context;
        setEGLContextClientVersion(2);

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
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        final float x=e.getX();
        final float y=e.getY();
        Log.i("GL Surface View","X="+x+" Y="+y);
        aaContext.runOnUiThread(() -> aaContext.setTitle("X="+x+" Y="+y));
        return false;

    }
}

// https://www.codeproject.com/Articles/822412/Article-Beginners-Guide-to-Android-Animation-Gr-2