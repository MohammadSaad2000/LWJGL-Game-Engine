package com.base.engine;


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glColor3ub;
import static org.lwjgl.opengl.GL11.glColorMaterial;
import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13C.glActiveTexture;

public class Game {

    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    private Texture texture;
    private View view;

    float temp;

    public Game() {
        mesh = new Mesh();
        shader = new Shader();

        texture = ResourceLoader.loadTexture("test.png");
        view = new View();

        //mesh = ResourceLoader.loadMesh("cube.obj");
        Vertex[] vertices = new Vertex[]
                {new Vertex(new Vector3f(1, 0, 0), new Vector2f(1, 0)),
                 new Vertex(new Vector3f(0, 1, 0), new Vector2f(0, 1)),
                 new Vertex(new Vector3f(1, 1, 0), new Vector2f(1, 1)),
                 new Vertex(new Vector3f(0, 0, 0), new Vector2f(0, 0))};

        int[] indices = new int[]
                {1, 2, 0,
                 0, 3, 1};

        mesh.setVertices(vertices, indices);

        Transform.setProjection(MainComponent.WIDTH, MainComponent.HEIGHT, 70f,0.1f, 1000f);
        Transform.setView(view);
        transform = new Transform();

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
        //transform.setRotation(0, 180,0);
        //transform.setScale(0.5f, 0.5f, 0.5f);

        float moveAmount = 10.0f;

        float mouseSens = 300.0f;

        if (Input.getMouseButton(Input.MOUSE_BUTTON_3)
            && Input.getMouseDelta().getX() != 0) {
            view.rotate((float)(Input.getMouseDelta().getX() * Time.getDelta() * mouseSens), Vector3f.UP);
        }
        if (Input.getMouseButton(Input.MOUSE_BUTTON_3)
                && Input.getMouseDelta().getY() != 0) {
            view.rotate((float)(Input.getMouseDelta().getY() * Time.getDelta() * mouseSens), Vector3f.RIGHT);
        }
        if (Input.getKey(Input.KEY_W)) {
            view.move(view.getForward(), (float) (moveAmount * Time.getDelta()));
        } else if (Input.getKey(Input.KEY_A)) {
            view.move(view.getLeft(), (float) (moveAmount * Time.getDelta()));
        } else if (Input.getKey(Input.KEY_S)) {
            view.move(view.getBackward(), (float) (moveAmount * Time.getDelta()));
        } else if (Input.getKey(Input.KEY_D)) {
            view.move(view.getRight(), (float) (moveAmount * Time.getDelta()));
        }



    }

    public void render() {

        shader.bind();
        shader.setUniform("transform", transform.getProjectedTransformation());
        texture.bind();
        mesh.draw();
    }

}
