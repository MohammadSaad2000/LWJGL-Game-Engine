package com.base.engine;


public class Vector2f {

    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float magnitude() {
        return (float) Math.sqrt((x * x) + (y * y));
    }

    public float sqrMagnitude() {
        return ((x * x) + (y * y));
    }

    public float dot(Vector2f otherVector) {
        return (x * otherVector.x) + (y + otherVector.y);
    }

    public Vector2f normalized() {
        float magnitude = this.magnitude();
        return new Vector2f(x / magnitude, y / magnitude);
    }

    public Vector2f rotate(float angle) {

        double radians = Math.toRadians(angle);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);


        return new Vector2f((float) (x * cos - y * sin), (float) (x * sin + y * cos));
    }

    public Vector2f add(Vector2f otherVector) {
        return new Vector2f(x + otherVector.x, y + otherVector.y);
    }

    public Vector2f subtract(Vector2f otherVector) {
        return new Vector2f(x - otherVector.x, y - otherVector.y);
    }

    public Vector2f multiply(float number) {
        return new Vector2f(x * number, y * number);
    }

    public Vector2f divide(float number) {
        return new Vector2f(x / number, y / number);
    }

    public Vector2f abs(Vector2f vector) {
        return new Vector2f(Math.abs(vector.x), Math.abs(vector.y));
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

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public Vector2f clone() {
        return new Vector2f(this.x, this.y);
    }
}
