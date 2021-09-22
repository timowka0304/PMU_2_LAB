package com.example.pmu_2_lab;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {
    Context context;
    private Square mSquare;
    private Cube mCube;
    private Sphere mSphere;
    private static float angleCube = 0;
    private static float angleSphere = 0;
    public MyGLRenderer(Context context) {
        this.context = context;
        mSquare = new Square();
        mCube = new Cube();
        mSphere = new Sphere(3);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glDisable(GL10.GL_DITHER);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) height = 1;
        float aspect = (float)width / height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor (1.0f, 1.0f, 1.0f, 0.0f);

        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 3.0f, -11.0f);
        mSquare.draw(gl);

        gl.glLoadIdentity();
        gl.glTranslatef(0f, 0.0f, -11.0f);
        gl.glScalef(0.75f, 0.75f, 0.75f);
        gl.glRotatef(angleCube, 1.0f, 0.5f, 0.2f);
        mCube.draw(gl);
        angleCube += 1.0f;

        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, -3.0f, -11.0f);
        gl.glScalef(0.35f, 0.35f, 0.35f);
        gl.glRotatef(angleSphere, 0.0f, 1.0f, 0.0f);
        mSphere.draw(gl);
        angleSphere += 1.0f;
    }
}

