package com.base.engine;

public class Transform {

    private static View view;

    private static float width;
    private static float height;
    private static float FOV;
    private static float zNearClip;
    private static float zFarClip;

    private Vector3f translation;
    private Vector3f rotation;
    private Vector3f scale;

    public Transform() {
        translation = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0,0);
        scale = new Vector3f(1,1, 1);
    }

    public Matrix4f getProjectedTransformation() {
        Matrix4f transformationMatrix = getTransformationMatrix();
        Matrix4f projectionMatrix = Matrix4f.getProjectionMatrix(width, height, FOV, zNearClip, zFarClip);
        Matrix4f viewMatrix = getViewMatrix();

        return projectionMatrix.multiply(viewMatrix.multiply(transformationMatrix));
    }

    public Matrix4f getOrthographicTransformation() {
        Matrix4f transformationMatrix = getTransformationMatrix();
        Matrix4f viewMatrix = getViewMatrix();

        return viewMatrix.multiply(transformationMatrix);
    }

    public Matrix4f getTransformationMatrix() {
        Matrix4f translationMatrix = Matrix4f.getTranslationMatrix(translation.getX(), translation.getY(), translation.getZ());
        Matrix4f rotationMatrix = Matrix4f.getRotationMatrix(rotation.getX(), rotation.getY(), rotation.getZ());
        Matrix4f scaleMatrix = Matrix4f.getScaleMatrix(scale.getX(), scale.getY(), scale.getZ());

        return translationMatrix.multiply(rotationMatrix.multiply(scaleMatrix));
    }

    public static Matrix4f getViewMatrix() {
        Matrix4f viewRotationMatrix = Matrix4f.getViewMatrix(view.getForward(), view.getUp());
        Matrix4f viewTranslationMatrix =
                Matrix4f.getTranslationMatrix(-view.getPosition().getX(), -view.getPosition().getY(), -view.getPosition().getZ());
        return viewRotationMatrix.multiply(viewTranslationMatrix);
    }

    public static void setProjection(float width, float height, float FOV, float zNearClip, float zFarClip) {
        Transform.width = width;
        Transform.height = height;
        Transform.FOV = FOV;
        Transform.zNearClip = zNearClip;
        Transform.zFarClip = zFarClip;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public void setTranslation(float x, float y, float z) {
        this.translation = new Vector3f(x, y, z);
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation = new Vector3f(x, y, z);
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public void setScale(float x, float y, float z) {
        this.scale = new Vector3f(x, y, z);
    }

    public static View getView() {
        return view;
    }

    public static void setView(View view) {
        Transform.view = view;
    }
}
