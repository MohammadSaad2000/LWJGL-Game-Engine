package com.base.engine;

public class View {

    private Vector3f position;
    private Vector3f forward;
    private Vector3f up;

    public View(Vector3f position, Vector3f forward, Vector3f up) {
        this.position = position;
        this.forward = forward;
        this.up = up;
        this.forward.normalize();
        this.up.normalize();
    }

    public View() {
        this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getForward() {
        return forward;
    }

    public void setForward(Vector3f forward) {
        this.forward = forward.normalize();
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up = up.normalize();
    }

    public Vector3f getLeft() {
        Vector3f left = forward.cross(up);
        return left.normalize();
    }

    public Vector3f getRight() {
        Vector3f right = up.cross(forward);
        return right.normalize();
    }

    public Vector3f getDown() {
        return up.multiply(-1).normalize();
    }

    public Vector3f getBackward() {
        return forward.multiply(-1).normalize();
    }

    public void move(Vector3f direction, float amount) {
        position = position.add(direction.multiply(amount));
    }

    public void rotate(float angle, Vector3f axis) {

        Vector3f xAxis = Vector3f.UP.cross(forward);
        xAxis.normalize();

        forward.rotate(angle, axis);
        forward.normalize();

        up = forward.cross(xAxis);
        up.normalize();
    }



}
