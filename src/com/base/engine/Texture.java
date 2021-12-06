package com.base.engine;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengles.GLES20.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Texture {

    private int id;
    private String filePath;

    public Texture(String filePath) {
        loadTexture(filePath);
    }

    public void loadTexture(String filePath) {
        this.filePath = filePath;

        this.id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        IntBuffer width = Util.createIntBuffer(1);
        IntBuffer height = Util.createIntBuffer(1);
        IntBuffer channels = Util.createIntBuffer(1);

        ByteBuffer image = stbi_load(filePath, width, height, channels, 0);

        if (image != null) {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
        } else {
            throw new RuntimeException("Texture Load Error: Could not load image '" + filePath + "'");
        }

        stbi_image_free(image);

    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }

}
