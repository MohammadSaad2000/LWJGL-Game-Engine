package com.base.engine;

import static org.lwjgl.glfw.GLFW.*;


public class MainComponent {


    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    public static final String TITLE = "3D Game Engine";
    public static final double FRAME_CAP = 5000.0;

    private boolean isRunning;
    private Game game;

    public MainComponent() {
        System.out.println(RenderUtil.getOpenGLVersion());
        RenderUtil.initGraphics();
        Input.initialize();
        isRunning = false;
        game = new Game();
    }

    public void start() {

        if(isRunning)
            return;

        run();
    }

    public void stop() {

        if(!isRunning)
            return;

        isRunning = false;

    }

    private void run() {

        int frames = 0;
        long frameCounter = 0;

        boolean render = false;

        isRunning = true;

        final double frameTime = 1.0 / FRAME_CAP;

        long lastTime = Time.getTime();
        double unprocessedTime = 0;

        while (isRunning) {

            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += (passedTime / (double) Time.SECOND);
            frameCounter += passedTime;

            while (unprocessedTime > frameTime) {
                render = true;
                unprocessedTime -= frameTime;

                if(glfwWindowShouldClose(Window.getWindow()))
                    stop();

                Time.setDelta(frameTime);
                Input.update();

                game.input();
                game.update();

                if (frameCounter >= Time.SECOND) {
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if (render) {
                render();
                frames++;
            } else {
                try { Thread.sleep(1);}
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
        cleanUp();
    }

    private void render() {
        RenderUtil.clearScreen();
        game.render();
        Window.render();
    }

    private void cleanUp() {
        Window.cleanUp();
    }

    public static void main(String[] args) {
        Window.createWindow(WIDTH, HEIGHT, TITLE);
        MainComponent game = new MainComponent();
        game.start();
    }

}
