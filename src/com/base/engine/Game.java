package com.base.engine;


import static org.lwjgl.glfw.GLFW.*;

public class Game {

    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    private View view;

    float temp;

    public Game() {
        mesh = new Mesh();
        shader = new Shader();
        transform = new Transform();
        view = new View();
        Transform.setProjection(MainComponent.WIDTH, MainComponent.HEIGHT, 70f,0.1f, 1000f);
        Transform.setView(view);

        mesh = ResourceLoader.loadMesh("cube.obj");

        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vert"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.frag"));
        shader.compileShader();

        shader.addUniform("transform");

    }

    public void input() {


    }

    public void update() {
        temp += Time.getDelta();

        transform.setTranslation(0, 0, 5);
        transform.setRotation(0, (float) Math.sin(temp) * 180,0);
        //transform.setScale(0.5f, 0.5f, 0.5f);

        float moveAmount = 10.0f;
        float rotateAmount = 100.0f;
        if (Input.getKey(GLFW_KEY_W)) {
            view.move(view.getForward(), (float) (moveAmount * Time.getDelta()));
        } else if (Input.getKey(GLFW_KEY_A)) {
            view.move(view.getLeft(), (float) (moveAmount * Time.getDelta()));
        } else if (Input.getKey(GLFW_KEY_S)) {
            view.move(view.getBackward(), (float) (moveAmount * Time.getDelta()));
        } else if (Input.getKey(GLFW_KEY_D)) {
            view.move(view.getRight(), (float) (moveAmount * Time.getDelta()));
        }

        if (Input.getKey(GLFW_KEY_UP)) {
            view.rotate((float) (-rotateAmount * Time.getDelta()), Vector3f.RIGHT);
        } else if (Input.getKey(GLFW_KEY_LEFT)) {
            view.rotate((float) (-rotateAmount * Time.getDelta()), Vector3f.UP);
        } else if (Input.getKey(GLFW_KEY_DOWN)) {
            view.rotate((float) (rotateAmount * Time.getDelta()), Vector3f.RIGHT);
        } else if (Input.getKey(GLFW_KEY_RIGHT)) {
            view.rotate((float) (rotateAmount * Time.getDelta()), Vector3f.UP);
        }


    }

    public void render() {
        shader.bind();
        shader.setUniform("transform", transform.getProjectedTransformation());
        mesh.draw();
    }

}
