package com.base.engine;

public class Matrix4f {

    private float[][] matrix;

    public Matrix4f() {
        matrix = new float[4][4];
    }

    public Matrix4f initIdentity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j)
                    matrix[i][j] = 1.0f;
                else
                    matrix[i][j] = 0.0f;
            }
        }
        return this;
    }



    public Matrix4f multiply(Matrix4f otherMatrix) {
        Matrix4f result = new Matrix4f();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                result.matrix[i][j] =
                        matrix[i][0] * otherMatrix.matrix[0][j] +
                        matrix[i][1] * otherMatrix.matrix[1][j] +
                        matrix[i][2] * otherMatrix.matrix[2][j] +
                        matrix[i][3] * otherMatrix.matrix[3][j];

            }
        }
        return result;
    }

    public float get(int x, int y) {
        return matrix[x][y];
    }

    public void set(int x, int y, float value) {
        matrix[x][y] = value;
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(float[][] matrix) {
        this.matrix = matrix;
    }

    public static Matrix4f getTranslationMatrix(float x, float y, float z) {
        Matrix4f m = Matrix4f.getIdentityMatrix();

        m.matrix[0][3] = x;
        m.matrix[1][3] = y;
        m.matrix[2][3] = z;
        m.matrix[3][3] = 1;

        return m;
    }

    public static Matrix4f getRotationMatrix(float x, float y, float z) {
        Matrix4f rx = Matrix4f.getIdentityMatrix();
        Matrix4f ry = Matrix4f.getIdentityMatrix();
        Matrix4f rz = Matrix4f.getIdentityMatrix();

        x = (float) Math.toRadians(x);
        y = (float) Math.toRadians(y);
        z = (float) Math.toRadians(z);


        rx.matrix[1][1] = (float) Math.cos(x); rx.matrix[1][2] = (float) -Math.sin(x);
        rx.matrix[2][1] = (float) Math.sin(x); rx.matrix[2][2] = (float) Math.cos(x);

        ry.matrix[0][0] = (float) Math.cos(y); ry.matrix[0][2] = (float) Math.sin(y);
        ry.matrix[2][0] = (float) -Math.sin(y); ry.matrix[2][2] = (float) Math.cos(y);

        rz.matrix[0][0] = (float) Math.cos(z); rz.matrix[0][1] = (float) -Math.sin(z);
        rz.matrix[1][0] = (float) Math.sin(z); rz.matrix[1][1] = (float) Math.cos(z);

        return rx.multiply(ry).multiply(rz);
    }

    public static Matrix4f getScaleMatrix(float x, float y, float z) {
        Matrix4f m = new Matrix4f();
        m.matrix[0][0] = x;
        m.matrix[1][1] = y;
        m.matrix[2][2] = z;
        m.matrix[3][3] = 1.0f;
        return m;
    }

    public static Matrix4f getIdentityMatrix() {
        Matrix4f m = new Matrix4f();
        m.matrix[0][0] = 1.0f;
        m.matrix[1][1] = 1.0f;
        m.matrix[2][2] = 1.0f;
        m.matrix[3][3] = 1.0f;
        return m;
    }

    public static Matrix4f getProjectionMatrix(float width, float height, float FOV, float zNearClip, float zFarClip) {
        Matrix4f m = new Matrix4f();
        float tanHalfFOV = (float) (Math.tan(Math.toRadians(FOV / 2)));
        float aspectRatio = width / height;
        m.matrix[0][0] = 1.0f / (tanHalfFOV * aspectRatio);
        m.matrix[1][1] = 1.0f / tanHalfFOV;
        m.matrix[2][2] = (-zNearClip - zFarClip) / (zNearClip - zFarClip);
        m.matrix[2][3] = (2 * zFarClip * zNearClip) / (zNearClip - zFarClip);
        m.matrix[3][2] = 1;

        return m;
    }

    public static Matrix4f getViewMatrix(Vector3f forward, Vector3f up) {

        Matrix4f m = new Matrix4f();
        Vector3f forwardCopy = forward.clone().normalize();
        Vector3f upCopy = up.clone().normalize();

        Vector3f right = upCopy.cross(forwardCopy);

        m.matrix[0][0] = right.getX();          m.matrix[0][1] = right.getY();          m.matrix[0][2] = right.getZ();
        m.matrix[1][0] = upCopy.getX();         m.matrix[1][1] = upCopy.getY();         m.matrix[1][2] = upCopy.getZ();
        m.matrix[2][0] = forwardCopy.getX();    m.matrix[2][1] = forwardCopy.getY();    m.matrix[2][2] = forwardCopy.getZ();
        m.matrix[3][3] = 1;

        return m;
    }


}
