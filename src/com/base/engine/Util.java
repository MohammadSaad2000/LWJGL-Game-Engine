package com.base.engine;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class Util {

    public static FloatBuffer createFloatBuffer(int size) {
        return BufferUtils.createFloatBuffer(size);
    }

    public static IntBuffer createIntBuffer(int size) {
        return BufferUtils.createIntBuffer(size);
    }

    public static FloatBuffer createFlippedBuffer(Vertex[] vertices) {
        FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);


        for (int i = 0; i < vertices.length; i++) {
            buffer.put(vertices[i].getPosition().getX());
            buffer.put(vertices[i].getPosition().getY());
            buffer.put(vertices[i].getPosition().getZ());
            buffer.put(vertices[i].getTextureUV().getX());
            buffer.put(vertices[i].getTextureUV().getY());
        }

        buffer.flip();
        return buffer;
    }

    public static FloatBuffer createFlippedBuffer(Matrix4f matrix) {
        FloatBuffer buffer = createFloatBuffer(matrix.getMatrix().length * matrix.getMatrix().length);


        for (int i = 0; i < matrix.getMatrix().length; i++) {
            for (int j = 0; j < matrix.getMatrix().length; j++) {
                buffer.put(matrix.get(i, j));
            }
        }

        buffer.flip();
        return buffer;
    }

    public static IntBuffer createFlippedBuffer(int... values) {
        IntBuffer buffer = createIntBuffer(values.length);

        buffer.put(values);
        buffer.flip();

        return buffer;
    }

    public static String[] removeEmptyStrings(String[] array) {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if (!array[i].isBlank())
                result.add(array[i]);
        }

        String[] returnResult = new String[result.size()];
        result.toArray(returnResult);
        return result.toArray(returnResult);
    }

    public static int[] toIntArray(Integer[] array){
        int[] result = new int[array.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = array[i];
        }
        return result;
    }


}
