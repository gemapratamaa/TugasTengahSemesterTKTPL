package id.ac.ui.cs.mobileprogramming;

import java.util.Arrays;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private static final String TAG = "MyGLRenderer";

    //private Square   mSquare;

    // mMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];
    public boolean shouldDrawCube=false;
    public MySimpleOpenGLES2DrawingClass line, rect, triangle, circle;
    /////////////////////////// For Cube//////////////////////////////
    private float verticesCube[] = {
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f,  1.0f, -1.0f,
            -1.0f, 1.0f, -1.0f,
            -1.0f, -1.0f,  1.0f,
            1.0f, -1.0f,  1.0f,
            1.0f,  1.0f,  1.0f,
            -1.0f,  1.0f,  1.0f
    };
    /*
private float colorsCube[] = {
           0.3f,  0.2f,  1.0f,  1.0f,
           0.0f,  1.0f,  0.0f,  1.0f,
           1.0f,  0.5f,  0.0f,  1.0f,
           1.0f,  0.5f,  0.0f,  1.0f,
           1.0f,  0.0f,  0.0f,  1.0f,
           1.0f,  0.0f,  0.0f,  1.0f,
           0.0f,  0.0f,  1.0f,  1.0f,
           1.0f,  0.0f,  1.0f,  1.0f
        };
        */
    private float colorsCube[] = {
            0.3f,  0.2f,  1.0f,  1.0f,
    };


    private short indicesCube[] = {
            0, 4, 5, 0, 5, 1,
            1, 5, 6, 1, 6, 2,
            2, 6, 7, 2, 7, 3,
            3, 7, 4, 3, 4, 0,
            4, 7, 6, 4, 6, 5,
            3, 0, 1, 3, 1, 2
    };
    public MySimpleOpenGLES2DrawingClass cube;
    ///////////////////////////////////////////////////////////////////////
    private float mAngle;

    public String mover="NONE";
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {

        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        //Line Demo..............................
        line=new MySimpleOpenGLES2DrawingClass(3, new float[]{ -0.5f,  0.5f, 0.0f,     -0.5f, -0.5f, .0f,   -0.45f, -0.5f, 0.0f,       -0.45f,  0.5f, 0.0f }, new float[]{1.0f,0.0f,0.0f,1.0f},new short[]{0,1,2,0,2,3});
        rect=new MySimpleOpenGLES2DrawingClass(3, new float[]{ -1.0f,  0.5f, 0.0f,     -1.0f, 1.0f, 0.0f,   0, 1.0f, 0.0f,       0,  .5f, 0.3f }, new float[]{0.5f,1.5f,1.0f,1.0f},new short[]{0,1,2,0,2,3});
        triangle=new MySimpleOpenGLES2DrawingClass(3, new float[]{ 0.9f,  0.7f, 0.0f,     .9f,.2f, 0.0f,   .4f, .2f, 0.0f,  }, new float[]{0.0f,0.0f,1.0f,1.0f},new short[]{0,1,2});
        for (int i = 0; i < verticesCube.length; i++) {
            verticesCube[i]=verticesCube[i]/3;
        }
        cube = new MySimpleOpenGLES2DrawingClass(3, verticesCube,colorsCube,indicesCube);
        //      mSquare   = new Square();
        //////////////////////// Circle/////////////////////////////////////
        float vertices[] = new float[364 * 3];
        vertices[0] = 0;
        vertices[1] = 0;
        vertices[2] = 0;

        for (int i =1; i < 364; i++){
            vertices[(i * 3)+ 0] = (float) (0.5 * Math.cos((3.14/180) * (float)i ) + vertices[0]);
            vertices[(i * 3)+ 1] = (float) (0.5 * Math.sin((3.14/180) * (float)i ) + vertices[1]);
            vertices[(i * 3)+ 2] = 0;
        }
        circle= new MySimpleOpenGLES2DrawingClass(3, vertices, new float[]{0.0f,1.0f,1.0f,1.0f},new short[]{0,1,2});

    }

    public float[]glCoordinate(float normalizedX,float normalizedY) {
        float[] invertedMatrix, transformMatrix, normalizedInPoint, outPoint;
        invertedMatrix = new float[16];
        transformMatrix = new float[16];
        normalizedInPoint = new float[4];
        normalizedInPoint[0] = normalizedX;
        normalizedInPoint[1] = normalizedY;
        normalizedInPoint[2] = -1.0f;
        normalizedInPoint[3] = 1.0f;

        outPoint = new float[4];
        Matrix.multiplyMM(
                transformMatrix, 0,
                mProjectionMatrix, 0,
                mMVPMatrix, 0);
        Matrix.invertM(invertedMatrix, 0,
                transformMatrix, 0);
        Matrix.multiplyMV(
                outPoint, 0,
                invertedMatrix, 0,
                normalizedInPoint, 0);

        if (outPoint[3] == 0.0) {
            // Avoid /0 error.
            Log.e("World coords", "ERROR!");
            return new float[]{9999,9999,9999};
        }
        float[] c = new float[]{ outPoint[0] / outPoint[3],outPoint[1] / outPoint[3]};
        return c;

    }
    float mCubeRotation = 0;
    float[] mRotateCubeM = new float[16];

    @Override
    public void onDrawFrame(GL10 unused) {
        float[] scratch = new float[16];

        // Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);


        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        //////////////////////// Applying Rotation/////////////////////////////////


        // Create a rotation for the triangle
        // long time = SystemClock.uptimeMillis() % 4000L;
        // float angle = 0.090f * ((int) time);
        Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0, 0, -1.0f);

        //Matrix.scaleM(mRotationMatrix, 0, 1.50f, 1.5f, -1.0f);

        // Combine the rotation matrix with the projection and camera view
        // Note that the mMVPMatrix factor *must be first* in order
        // for the matrix multiplication product to be correct.
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);

        ////////////////////////////////////////////////////////////////////////
        // Draw triangle
        //
        line.draw(mMVPMatrix);

        if (!mover.equals("RECT")) {
            rect.draw(mMVPMatrix);
        } else {
            rect.draw(scratch);// With rotation
        }
        // Rectangle Demo..............................

        //Triangle..............................

        if(!mover.equals("TRI")) {
            triangle.draw(mMVPMatrix);
        } else {
            triangle.draw(scratch);
        }

        if(!mover.equals("CIR")) {
            circle.draw(mMVPMatrix);
        } else {
            circle.draw(scratch);
        }
        ///////////////////////// Cube Code/////////////////
        //Creating rotation matrix
        if (shouldDrawCube) {
            Matrix.setRotateM(mRotateCubeM, 0, mCubeRotation, 0f, 0f, -1f);

            //rotation x camera = modelView
            float[] duplicateMatrix = Arrays.copyOf(mMVPMatrix, 16);

            Matrix.multiplyMM(mMVPMatrix, 0, duplicateMatrix, 0, mRotateCubeM, 0);

            Matrix.setRotateM(mRotateCubeM, 0, mCubeRotation, 0f, -1f, 0f);
            duplicateMatrix = Arrays.copyOf(mMVPMatrix, 16);
            Matrix.multiplyMM(mMVPMatrix, 0, duplicateMatrix, 0, mRotateCubeM, 0);

            Matrix.setRotateM(mRotateCubeM, 0, mCubeRotation, -1f, 0f, 0f);
            duplicateMatrix = Arrays.copyOf(mMVPMatrix, 16);
            Matrix.multiplyMM(mMVPMatrix, 0, duplicateMatrix, 0, mRotateCubeM, 0);

            cube.draw(mMVPMatrix);
            ///////////////////////////////////////////////////////
            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotateCubeM, 0);

            mCubeRotation += 0.15f;
        }
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }


    public float getAngle() {
        return mAngle;
    }


    public void setAngle(float angle) {
        mAngle = angle;
    }

}