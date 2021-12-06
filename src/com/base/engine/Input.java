package com.base.engine;

import org.lwjgl.system.MemoryStack;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class Input {

    private static boolean initialized = false;
    private static ArrayList<Integer> allKeys = new ArrayList<>();
    private static ArrayList<Integer> currentKeys = new ArrayList<>();
    private static ArrayList<Integer> downKeys = new ArrayList<>();
    private static ArrayList<Integer> upKeys = new ArrayList<>();

    private static ArrayList<Integer> allMouse = new ArrayList<>();
    private static ArrayList<Integer> currentMouse = new ArrayList<>();
    private static ArrayList<Integer> downMouse = new ArrayList<>();
    private static ArrayList<Integer> upMouse = new ArrayList<>();



    public static void initialize() {

        allKeys.add(32);
        allKeys.add(39);
        for (int i = 44; i < 58; i++) {
            allKeys.add(i);
        }
        allKeys.add(59);
        allKeys.add(61);
        for (int i = 65; i < 93; i++) {
            allKeys.add(i);
        }
        allKeys.add(96);
        allKeys.add(161);
        allKeys.add(162);
        for (int i = 256; i < 285; i++) {
            allKeys.add(i);
        }
        for (int i = 290; i < 349; i++) {
            allKeys.add(i);
        }

        for (int i = 0; i < 8; i++) {
            allMouse.add(i);
        }

        initialized = true;
    }

    public static void update() {

        if (!initialized)
            throw new RuntimeException("Did Not Initialize Input");

        glfwPollEvents();

        upKeys.clear();
        for (Integer key : allKeys)
            if (!getKey(key) && currentKeys.contains(key))
                upKeys.add(key);

        downKeys.clear();
        for (Integer key : allKeys)
            if (getKey(key) && !currentKeys.contains(key))
                downKeys.add(key);

        currentKeys.clear();
        for (Integer key : allKeys)
            if (getKey(key))
                currentKeys.add(key);


        upMouse.clear();
        for (Integer key : allMouse)
            if (!getMouseButton(key) && currentMouse.contains(key))
                upMouse.add(key);

        downMouse.clear();
        for (Integer key : allMouse)
            if (getMouseButton(key) && !currentMouse.contains(key))
                downMouse.add(key);

        currentMouse.clear();
        for (Integer key : allMouse)
            if (getMouseButton(key))
                currentMouse.add(key);

    }

    public static boolean getKey(int keyCode) {
        return glfwGetKey(Window.getWindow(), keyCode) == GLFW_PRESS;
    }

    public static boolean getKeyDown(int keyCode) {
        return downKeys.contains(keyCode);
    }

    public static boolean getKeyUp(int keyCode) {
        return upKeys.contains(keyCode);
    }

    public static boolean getMouseButton(int mouseButton) {
        return glfwGetMouseButton(Window.getWindow(), mouseButton) == GLFW_PRESS;
    }

    public static boolean getMouseDown(int mouseButton) {
        return downMouse.contains(mouseButton);
    }

    public static boolean getMouseUp(int mouseButton) {
        return upMouse.contains(mouseButton);
    }

    public static Vector2f getMousePosition() {
        try (MemoryStack stack = stackPush()) {
            DoubleBuffer xPos = stack.mallocDouble(1);
            DoubleBuffer yPos = stack.mallocDouble(1);
            glfwGetCursorPos(Window.getWindow(), xPos, yPos);
            return new Vector2f((float) xPos.get(), (float) yPos.get());
        }
    }

}
