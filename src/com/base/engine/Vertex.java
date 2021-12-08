package com.base.engine;

public class Vertex {

    public static final int SIZE = 5;

    private Vector3f position;
    private Vector2f textureUV;


    public Vertex(Vector3f position) {
        this(position, new Vector2f(0, 0));
    }

    public Vertex(Vector3f position, Vector2f textureUV) {
        this.position = position;
        this.textureUV = textureUV;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector2f getTextureUV() {
        return textureUV;
    }

    public void setTextureUV(Vector2f textureUV) {
        this.textureUV = textureUV;
    }
}
