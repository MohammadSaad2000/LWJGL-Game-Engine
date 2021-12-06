package com.base.engine;

public class Quaternion {

    private float x;
    private float y;
    private float z;
    private float w;

    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float magnitude() {
        return (float)(Math.sqrt((x * x) + (y * y) + (z * z) + (w * w)));
    }

    public float sqrMagnitude() {
        return (x * x) + (y * y) + (z * z) + (w * w);
    }

    public Quaternion normalize() {
        float magnitude = this.magnitude();
        x /= magnitude;
        y /= magnitude;
        z /= magnitude;
        return this;
    }

    public Quaternion conjugate() {
        return new Quaternion(-x, -y, -z, w);
    }

    public Quaternion multiply(Quaternion quaternion) {
        float newW = w * quaternion.getW() - x * quaternion.getX() - y * quaternion.getY() - z * quaternion.getZ();
        float newX = x * quaternion.getW() + w * quaternion.getX() + y * quaternion.getZ() - z * quaternion.getY();
        float newY = y * quaternion.getW() + w * quaternion.getY() + z * quaternion.getX() - x * quaternion.getZ();
        float newZ = z * quaternion.getW() + w * quaternion.getZ() + x * quaternion.getY() - y * quaternion.getX();

        return new Quaternion(newX, newY, newZ, newW);
    }

    public Quaternion multiply(Vector3f vector) {
        float newW = -x * vector.getX() - y * vector.getY() - z * vector.getZ();
        float newX =  w * vector.getX() + y * vector.getZ() - z * vector.getY();
        float newY =  w * vector.getY() + z * vector.getX() - x * vector.getZ();
        float newZ =  w * vector.getZ() + x * vector.getY() - y * vector.getX();

        return new Quaternion(newX, newY, newZ, newW);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }
}
