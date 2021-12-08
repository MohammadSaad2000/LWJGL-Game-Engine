package com.base.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryStack;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class Input {


    public static final int KEY_SPACE                                           = 0;
    public static final int KEY_APOSTROPHE                                      = 1;
    public static final int KEY_COMMA                                           = 2;
    public static final int KEY_MINUS                                           = 3;
    public static final int KEY_PERIOD                                          = 4;
    public static final int KEY_SLASH                                           = 5;
    public static final int KEY_0                                               = 6;
    public static final int KEY_1                                               = 7;
    public static final int KEY_2                                               = 8;
    public static final int KEY_3                                               = 9;
    public static final int KEY_4                                               = 10;
    public static final int KEY_5                                               = 11;
    public static final int KEY_6                                               = 12;
    public static final int KEY_7                                               = 13;
    public static final int KEY_8                                               = 14;
    public static final int KEY_9                                               = 15;
    public static final int KEY_SEMICOLON                                       = 16;
    public static final int KEY_EQUAL                                           = 17;
    public static final int KEY_A                                               = 18;
    public static final int KEY_B                                               = 19;
    public static final int KEY_C                                               = 20;
    public static final int KEY_D                                               = 21;
    public static final int KEY_E                                               = 22;
    public static final int KEY_F                                               = 23;
    public static final int KEY_G                                               = 24;
    public static final int KEY_H                                               = 25;
    public static final int KEY_I                                               = 26;
    public static final int KEY_J                                               = 27;
    public static final int KEY_K                                               = 28;
    public static final int KEY_L                                               = 29;
    public static final int KEY_M                                               = 30;
    public static final int KEY_N                                               = 31;
    public static final int KEY_O                                               = 32;
    public static final int KEY_P                                               = 33;
    public static final int KEY_Q                                               = 34;
    public static final int KEY_R                                               = 35;
    public static final int KEY_S                                               = 36;
    public static final int KEY_T                                               = 37;
    public static final int KEY_U                                               = 38;
    public static final int KEY_V                                               = 39;
    public static final int KEY_W                                               = 40;
    public static final int KEY_X                                               = 41;
    public static final int KEY_Y                                               = 42;
    public static final int KEY_Z                                               = 43;
    public static final int KEY_LEFT_BRACKET                                    = 44;
    public static final int KEY_BACKSLASH                                       = 45;
    public static final int KEY_RIGHT_BRACKET                                   = 46;
    public static final int KEY_GRAVE_ACCENT                                    = 47;
    public static final int KEY_WORLD_1                                         = 48;
    public static final int KEY_WORLD_2                                         = 49;
    public static final int KEY_ESCAPE                                          = 50;
    public static final int KEY_ENTER                                           = 51;
    public static final int KEY_TAB                                             = 52;
    public static final int KEY_BACKSPACE                                       = 53;
    public static final int KEY_INSERT                                          = 54;
    public static final int KEY_DELETE                                          = 55;
    public static final int KEY_RIGHT                                           = 56;
    public static final int KEY_LEFT                                            = 57;
    public static final int KEY_DOWN                                            = 58;
    public static final int KEY_UP                                              = 59;
    public static final int KEY_PAGE_UP                                         = 60;
    public static final int KEY_PAGE_DOWN                                       = 61;
    public static final int KEY_HOME                                            = 62;
    public static final int KEY_END                                             = 63;
    public static final int KEY_CAPS_LOCK                                       = 64;
    public static final int KEY_SCROLL_LOCK                                     = 65;
    public static final int KEY_NUM_LOCK                                        = 66;
    public static final int KEY_PRINT_SCREEN                                    = 67;
    public static final int KEY_PAUSE                                           = 68;
    public static final int KEY_F1                                              = 69;
    public static final int KEY_F2                                              = 70;
    public static final int KEY_F3                                              = 71;
    public static final int KEY_F4                                              = 72;
    public static final int KEY_F5                                              = 73;
    public static final int KEY_F6                                              = 74;
    public static final int KEY_F7                                              = 75;
    public static final int KEY_F8                                              = 76;
    public static final int KEY_F9                                              = 77;
    public static final int KEY_F10                                             = 78;
    public static final int KEY_F11                                             = 79;
    public static final int KEY_F12                                             = 80;
    public static final int KEY_F13                                             = 81;
    public static final int KEY_F14                                             = 82;
    public static final int KEY_F15                                             = 83;
    public static final int KEY_F16                                             = 84;
    public static final int KEY_F17                                             = 85;
    public static final int KEY_F18                                             = 86;
    public static final int KEY_F19                                             = 87;
    public static final int KEY_F20                                             = 88;
    public static final int KEY_F21                                             = 89;
    public static final int KEY_F22                                             = 90;
    public static final int KEY_F23                                             = 91;
    public static final int KEY_F24                                             = 92;
    public static final int KEY_F25                                             = 93;
    public static final int KEY_KP_0                                            = 94;
    public static final int KEY_KP_1                                            = 95;
    public static final int KEY_KP_2                                            = 96;
    public static final int KEY_KP_3                                            = 97;
    public static final int KEY_KP_4                                            = 98;
    public static final int KEY_KP_5                                            = 99;
    public static final int KEY_KP_6                                            = 100;
    public static final int KEY_KP_7                                            = 101;
    public static final int KEY_KP_8                                            = 102;
    public static final int KEY_KP_9                                            = 103;
    public static final int KEY_KP_DECIMAL                                      = 104;
    public static final int KEY_KP_DIVIDE                                       = 105;
    public static final int KEY_KP_MULTIPLY                                     = 106;
    public static final int KEY_KP_SUBTRACT                                     = 107;
    public static final int KEY_KP_ADD                                          = 108;
    public static final int KEY_KP_ENTER                                        = 109;
    public static final int KEY_KP_EQUAL                                        = 110;
    public static final int KEY_LEFT_SHIFT                                      = 111;
    public static final int KEY_LEFT_CONTROL                                    = 112;
    public static final int KEY_LEFT_ALT                                        = 113;
    public static final int KEY_LEFT_SUPER                                      = 114;
    public static final int KEY_RIGHT_SHIFT                                     = 115;
    public static final int KEY_RIGHT_CONTROL                                   = 116;
    public static final int KEY_RIGHT_ALT                                       = 117;
    public static final int KEY_RIGHT_SUPER                                     = 118;
    public static final int KEY_MENU                                            = 119;

    public static final int MOUSE_BUTTON_PRIMARY                                = 0;
    public static final int MOUSE_BUTTON_SECONDARY                              = 1;
    public static final int MOUSE_BUTTON_3                                      = 2;
    public static final int MOUSE_BUTTON_4                                      = 3;
    public static final int MOUSE_BUTTON_5                                      = 4;
    public static final int MOUSE_BUTTON_6                                      = 5;
    public static final int MOUSE_BUTTON_7                                      = 6;
    public static final int MOUSE_BUTTON_8                                      = 7;


    private static boolean initialized = false;

    private static boolean[] lastKeys;
    private static boolean[] lastMouseButtons;
    private static final ArrayList<Integer> allKeys = new ArrayList<>();
    private static final ArrayList<Integer> allMouseButtons = new ArrayList<>();


    private static Vector2f previousPos, currentPos;
    private static Vector2f mouseDelta = new Vector2f(0, 0);

    private static boolean cursorInWindow = false;



    public static void initialize() {

        /* Add all GLFW key values: https://www.glfw.org/docs/3.3/group__keys.html*/
        allKeys.add(32);
        allKeys.add(39);
        for (int i = 44; i < 58; i++) {
            allKeys.add(i);
        }
        allKeys.add(59);
        allKeys.add(61);
        for (int i = 65; i < 94; i++) {
            allKeys.add(i);
        }
        allKeys.add(96);
        allKeys.add(161);
        allKeys.add(162);
        for (int i = 256; i < 270; i++) {
            allKeys.add(i);
        }
        for (int i = 280; i < 285; i++) {
            allKeys.add(i);
        }
        for (int i = 290; i < 315; i++) {
            allKeys.add(i);
        }
        for (int i = 320; i < 337; i++) {
            allKeys.add(i);
        }
        for (int i = 340; i < 349; i++) {
            allKeys.add(i);
        }

        /* Add all GLFW mouse button values: https://www.glfw.org/docs/3.3/group__buttons.html*/
        for (int i = 0; i < 8; i++) {
            allMouseButtons.add(i);
        }

        lastKeys = new boolean[allKeys.size()];
        lastMouseButtons = new boolean[allMouseButtons.size()];


        previousPos = new Vector2f(-1, -1);
        currentPos = new Vector2f(0, 0);
        GLFW.glfwSetCursorPosCallback(Window.getWindow(), (window, xPos, yPos) -> {
            currentPos.setX((float) xPos);
            currentPos.setY((float) yPos);

        });
        GLFW.glfwSetCursorEnterCallback(Window.getWindow(), (window, entered) -> {
            cursorInWindow = entered;
        });

        initialized = true;
    }

    public static void update() {

        if (!initialized)
            throw new RuntimeException("Did Not Initialize Input");

        for (int i = 0; i < lastKeys.length; i++)
            lastKeys[i] = getKey(i);

        for (int i = 0; i < lastMouseButtons.length; i++)
            lastMouseButtons[i] = getMouseButton(i);


        mouseDelta.setX(0);
        mouseDelta.setY(0);
        if (previousPos.getX() > 0 && previousPos.getY() > 0 && cursorInWindow) {
            double x = currentPos.getX() - previousPos.getX();
            double y = currentPos.getY() - previousPos.getY();
            if (x != 0)
                mouseDelta.setX((float) x);
            if (y != 0)
                mouseDelta.setY((float) y);

        }
        previousPos.setX(currentPos.getX());
        previousPos.setY(currentPos.getY());

        glfwPollEvents();
    }

    public static boolean getKey(int keyCode) {
        return glfwGetKey(Window.getWindow(), allKeys.get(keyCode)) == GLFW_PRESS;
    }

    public static boolean getKeyDown(int keyCode) {
        return getKey(keyCode) && !lastKeys[keyCode];
    }

    public static boolean getKeyUp(int keyCode) {
        return !getKey(keyCode) && lastKeys[keyCode];
    }

    public static boolean getMouseButton(int mouseButton) {
        return glfwGetMouseButton(Window.getWindow(), allMouseButtons.get(mouseButton)) == GLFW_PRESS;
    }

    public static boolean getMouseDown(int mouseButton) {
        return getMouseButton(mouseButton) && !lastMouseButtons[mouseButton];
    }

    public static boolean getMouseUp(int mouseButton) {
        return !getMouseButton(mouseButton) && lastMouseButtons[mouseButton];
    }

    public static Vector2f getMousePosition() {
        return currentPos;
    }

    public static Vector2f getMouseDelta() {
        return mouseDelta;
    }

}
