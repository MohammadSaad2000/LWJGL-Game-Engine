package com.base.engine;

import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL21.glUniformMatrix4x3fv;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

public class Shader {

    private int programPointer;
    private HashMap<String, Integer> uniforms;

    public Shader() {
        programPointer = glCreateProgram();
        uniforms = new HashMap<>();

        if (programPointer == 0) {
            throw new RuntimeException("Shader creation failed: Could not find valid memory location when creating shader.");
        }

    }

    public void bind() {
        glUseProgram(programPointer);
    }

    public void addUniform(String uniform) {
        int uniformLocation = glGetUniformLocation(programPointer, uniform);

        if (uniformLocation == -1) {
            throw new RuntimeException("Uniform Error: Could not find uniform " + uniform + ".");
        }

        uniforms.put(uniform, uniformLocation);

    }

    public void addVertexShader(String text) {
        addProgram(text, GL_VERTEX_SHADER);
    }

    public void addFragmentShader(String text) {
        addProgram(text, GL_FRAGMENT_SHADER);
    }

    public void addGeometryShader(String text) {
        addProgram(text, GL_GEOMETRY_SHADER);
    }

    public void compileShader() {
        glLinkProgram(programPointer);
        if(glGetProgrami(programPointer, GL_LINK_STATUS) == 0) {
            throw new RuntimeException(glGetShaderInfoLog(programPointer, 1024));
        }

        glValidateProgram(programPointer);
        if(glGetProgrami(programPointer, GL_VALIDATE_STATUS) == 0) {
            throw new RuntimeException(glGetShaderInfoLog(programPointer, 1024));
        }
    }

    private void addProgram(String text, int type) {
        int shader = glCreateShader(type);

        if (shader == 0) {
            throw new RuntimeException("Shader creation failed: Could not find valid memory location when adding shader.");
        }

        glShaderSource(shader, text);
        glCompileShader(shader);

        if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
            throw new RuntimeException(glGetShaderInfoLog(shader));
        }

        glAttachShader(programPointer, shader);
    }

    public void setUniform(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, float value) {
        glUniform1f(uniforms.get(uniformName), value);
    }

    public void setUniform(String uniformName, Vector3f value) {
        glUniform3f(uniforms.get(uniformName), value.getX(), value.getY(), value.getZ());
    }

    public void setUniform(String uniformName, Matrix4f value) {
        glUniformMatrix4fv(uniforms.get(uniformName), true, Util.createFlippedBuffer(value));
    }

}
