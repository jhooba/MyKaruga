package engine;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

/**
 * Copied from http://fabiensanglard.net/neverGiveUp/index.php
 * Created by jhooba on 2016-06-19.
 */
public class Engine {
  private static final int SCREEN_WIDTH = 1024;
  private static final int SCREEN_HEIGHT = 768;
  private static final boolean FULL_SCREEN = false;

  public static void main(String[] args) {
    createWindow(SCREEN_WIDTH, SCREEN_HEIGHT, FULL_SCREEN);
    initGL();


  }

  private static void initGL() {
    GL11.glEnable(GL11.GL_LIGHTING);
    GL11.glEnable(GL11.GL_LIGHT1);
    GL11.glEnable(GL11.GL_LIGHT2);
    GL11.glEnable(GL11.GL_COLOR_MATERIAL);

    FloatBuffer lightAmbient =
        (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] {0f, 0f, 0f, 1f}).rewind();
    GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, lightAmbient);

    GL11.glColorMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE);
    FloatBuffer lightDiffuse =
        (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] {0.7f, 0.7f, 0.7f, 1f}).rewind();
    GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, lightDiffuse);

    FloatBuffer lightSpec =
        (FloatBuffer)BufferUtils.createFloatBuffer(4).put(new float[] {0.8f, 0.8f, 0.8f, 1f}).rewind();
    GL11.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, lightSpec);


  }

  private static void createWindow(int screenWidth, int screenHeight, boolean fullScreen) {
    try {
      if (!fullScreen) {  // create windowed mode
        Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
      } else {
        Display.setFullscreen(true);
        try {
          DisplayMode dm[] = org.lwjgl.util.Display.getAvailableDisplayModes(320, 240, -1, -1, -1, -1, 60, 85);
          org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
              "width=" + screenWidth,
              "height=" + screenHeight,
              "freq=85",
              "bpp=" + Display.getDisplayMode().getBitsPerPixel()
          });
        } catch (Exception e) {
          Sys.alert("Error", "Could not start full screen, switching to windowed mode");
          Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
        }
      }
      Display.create();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
