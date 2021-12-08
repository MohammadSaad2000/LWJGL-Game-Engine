package com.base.engine;

public class Vector3f {

    public static final Vector3f UP = new Vector3f(0, 1, 0);
    public static final Vector3f FORWARD = new Vector3f(0, 0, 1);
    public static final Vector3f RIGHT = new Vector3f(1, 0, 0);
    public static final Vector3f DOWN = new Vector3f(0, -1, 0);
    public static final Vector3f BACKWARD = new Vector3f(0, 0, -1);
    public static final Vector3f LEFT = new Vector3f(-1, 0, 0);

    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float magnitude() {
        return (float) Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public float sqrMagnitude() {
        return ((x * x) + (y * y) + (z * z));
    }

    public float dot(Vector3f otherVector) {
        return (x * otherVector.x) + (y + otherVector.y) + (z + otherVector.z);
    }

    public Vector3f cross(Vector3f otherVector) {
        float newX = y * otherVector.z - z * otherVector.y;
        float newY = z * otherVector.x - x * otherVector.z;
        float newZ = x * otherVector.y - y * otherVector.x;

        return new Vector3f(newX, newY, newZ);
    }

    public Vector3f normalized() {
        float magnitude = this.magnitude();
        return new Vector3f(x / magnitude, y / magnitude, z / magnitude);
    }

    Vector3f rotate(float angle, Vector3f axis) {
        float sinHalfAngle = (float) (Math.sin(Math.toRadians(angle / 2)));
        float cosHalfAngle = (float) (Math.cos(Math.toRadians(angle / 2)));

        axis = axis.normalized();
        float rx = axis.x * sinHalfAngle;
        float ry = axis.y * sinHalfAngle;
        float rz = axis.z * sinHalfAngle;
        float rw = cosHalfAngle;

        Quaternion rotation = new Quaternion(rx, ry, rz, rw);
        Quaternion conjugate = rotation.conjugate();

        Quaternion result = rotation.multiply(this).multiply(conjugate);

        x = result.getX();
        y = result.getY();
        z = result.getZ();

        return this;
    }

    public Vector3f add(Vector3f otherVector) {
        return new Vector3f(x + otherVector.x, y + otherVector.y, z + otherVector.z);
    }

    public Vector3f subtract(Vector3f otherVector) {
        return new Vector3f(x - otherVector.x, y - otherVector.y, z - otherVector.z);
    }

    public Vector3f multiply(float number) {
        return new Vector3f(x * number, y * number, z * number);
    }

    public Vector3f divide(float number) {
        return new Vector3f(x / number, y / number, z / number);
    }

    public Vector3f abs(Vector3f vector) {
        return new Vector3f(Math.abs(vector.x), Math.abs(vector.y), Math.abs(vector.z));
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

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public Vector3f clone() {
        return new Vector3f(x, y, z);
    }

}
