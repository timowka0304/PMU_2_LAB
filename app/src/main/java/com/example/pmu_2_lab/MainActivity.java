package com.example.pmu_2_lab;

import androidx.appcompat.app.AppCompatActivity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView gLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gLView = new GLSurfaceView(this);
        gLView.setRenderer(new MyGLRenderer(this));
        setContentView(gLView);
    }
    @Override
    protected void onPause() {
        super.onPause();
        gLView.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        gLView.onResume();
    }
}
