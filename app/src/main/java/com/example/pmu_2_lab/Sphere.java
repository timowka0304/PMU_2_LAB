package com.example.pmu_2_lab;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Sphere {
    public FloatBuffer mVertexBuffer;
    public int n = 0, sz = 0;
    private float[][] colors = {
            {0.0f, 0.0f, 0.0f, 1.0f},
            {0.8f, 1.0f, 0.8f, 1.0f},
            {0.5f, 1.0f, 0.5f, 1.0f},
    };
    public Sphere(float R) {
        int dtheta = 15, dphi = 15;
        float DTOR = (float) (Math.PI / 180.0f);
        ByteBuffer byteBuf = ByteBuffer.allocateDirect(8000 * 3 * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        mVertexBuffer = byteBuf.asFloatBuffer();
        byteBuf = ByteBuffer.allocateDirect(8000 * 2 * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        for (int theta = -90; theta <= 90 - dtheta; theta += dtheta) {
            for (int phi = 0; phi <= 360 - dphi; phi += dphi) {
                sz++;
                mVertexBuffer.put((float) (Math.cos(theta * DTOR) * Math.cos(phi *
                        DTOR)) * R);
                mVertexBuffer.put((float) (Math.cos(theta * DTOR) * Math.sin(phi *
                        DTOR)) * R);
                mVertexBuffer.put((float) (Math.sin(theta * DTOR)) * R);
                mVertexBuffer.put((float) (Math.cos((theta + dtheta) * DTOR) *
                        Math.cos(phi * DTOR)) * R);
                mVertexBuffer.put((float) (Math.cos((theta + dtheta) * DTOR) *
                        Math.sin(phi * DTOR)) * R);
                mVertexBuffer.put((float) (Math.sin((theta + dtheta) * DTOR)) * R);
                mVertexBuffer.put((float) (Math.cos((theta + dtheta) * DTOR) *
                        Math.cos((phi + dphi) * DTOR)) * R);
                mVertexBuffer.put((float) (Math.cos((theta + dtheta) * DTOR) *
                        Math.sin((phi + dphi) * DTOR)) * R);
                mVertexBuffer.put((float) (Math.sin((theta + dtheta) * DTOR)) * R);
                n += 3;
                mVertexBuffer.put((float) (Math.cos(theta * DTOR) * Math.cos((phi +
                        dphi) * DTOR)) * R);
                mVertexBuffer.put((float) (Math.cos(theta * DTOR) * Math.sin((phi +
                        dphi) * DTOR)) * R);
                mVertexBuffer.put((float) (Math.sin(theta * DTOR)) * R);
                n++;
            }
        }
        mVertexBuffer.position(0);
    }
    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
        for (int face = 0; face < n; face += 4) {
            gl.glColor4f(colors[face % 3][0], colors[face % 3][1], colors[face % 3][2],
                    colors[face % 3][3]);
            gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, face, 4);
        }
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
