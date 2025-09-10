package com.javaredo.util;

import java.io.InputStream;

import javafx.scene.text.Font;

public class FontLoader {
     public static Font load(String resourcePath, double size) {
        try (InputStream fontStream = FontLoader.class.getResourceAsStream(resourcePath)) {
            if (fontStream == null) {
                System.err.println("Font file not found: " + resourcePath);
                return null;
            }
            Font font = Font.loadFont(fontStream, size);
            if (font != null) {
                System.out.println("Loaded font: " + font.getName() +
                                   " | Family: " + font.getFamily());
            } else {
                System.err.println("Failed to load font: " + resourcePath);
            }
            return font;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
