package com.base.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ResourceLoader {

    public static Texture loadTexture(String fileName) {
        String extension = getFileExtension(fileName);
        if (extension.equalsIgnoreCase(".png")) {
            Texture texture = new Texture(fileName);
            return  texture;
        }
        return null;
    }

    public static String loadShader(String fileName) {
        StringBuilder shaderSource = new StringBuilder();

        BufferedReader shaderReader;

        try {
            shaderReader = new BufferedReader(new FileReader("./resources/shaders/" + fileName));
            String line;
            while ((line = shaderReader.readLine()) != null) {
                shaderSource.append(line).append("\n");

            }

            shaderReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return shaderSource.toString();
    }


    public static Mesh loadMesh(String fileName) {

        String extension = getFileExtension(fileName);

        if (extension.equalsIgnoreCase(".obj")) {
            return loadOBJ(fileName);
        } else
            throw new RuntimeException("Error: File type not supported.");

    }

    public static Mesh loadOBJ(String fileName) {

        ArrayList<Vertex> vertices = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        BufferedReader meshReader;

        try {
            meshReader = new BufferedReader(new FileReader("./resources/models/" + fileName));
            String line;
            while ((line = meshReader.readLine()) != null) {

                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);

                if (tokens.length == 0 || tokens[0].equals("#")) {
                    continue;
                } else if (tokens[0].equals("v")) {
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    float z = Float.parseFloat(tokens[3]);
                    vertices.add(new Vertex(new Vector3f(x, y, z)));
                } else if (tokens[0].equals("f")) {
                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                }
            }

            Vertex[] loadedVertices = new Vertex[vertices.size()];
            vertices.toArray(loadedVertices);

            Integer[] loadedIndices = new Integer[indices.size()];
            indices.toArray(loadedIndices);

            return new Mesh(loadedVertices, Util.toIntArray(loadedIndices));

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    private static String getFileExtension(String fileName) {
        int periodIndex = fileName.lastIndexOf(".");
        if (periodIndex == -1) {
            throw new RuntimeException("Error: File type not supported.");
        }
        return fileName.substring(periodIndex);
    }

}
