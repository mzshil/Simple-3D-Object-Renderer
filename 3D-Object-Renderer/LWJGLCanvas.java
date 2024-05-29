import java.awt.Canvas;
import java.util.concurrent.CountDownLatch;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class LWJGLCanvas extends Canvas{
    private long window;
    private int width;
    private int height;
    private String shapeToRender = "cube";

    public LWJGLCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        initialize();
    }

    private void initialize() {
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            try {
                if (!GLFW.glfwInit()) {
                    throw new IllegalStateException("Unable to initialize GLFW");
                }

                GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
                window = GLFW.glfwCreateWindow(width, height, "LWJGL Canvas", 0, 0);
                if (window == 0) {
                    throw new RuntimeException("Failed to create the GLFW window");
                }

                GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
                GLFW.glfwSetWindowPos(window, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);

                GLFW.glfwMakeContextCurrent(window);
                GL.createCapabilities();

                GLFW.glfwShowWindow(window);
                latch.countDown();

                while (!GLFW.glfwWindowShouldClose(window)) {
                    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
                    render();
                    GLFW.glfwSwapBuffers(window);
                    GLFW.glfwPollEvents();
                }

                GLFW.glfwDestroyWindow(window);
                GLFW.glfwTerminate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setShapeToRender(String shapeToRender) {
        this.shapeToRender = shapeToRender;
    }

    private void render() {
        switch (shapeToRender) {
            case "cube":
                renderCube();
                break;
            case "sphere":
                renderSphere();
                break;
            case "pyramid":
                renderPyramid();
                break;
        }
    }

    private void renderCube() {
        // Your code to render a cube
        GL11.glBegin(GL11.GL_QUADS);
        // Define vertices for the cube...
        GL11.glEnd();
    }

    private void renderSphere() {
        // Your code to render a sphere
    }

    private void renderPyramid() {
        // Your code to render a pyramid
    }

    // @Override
    // public void addNotify() {
    //     super.addNotify();
    //     GLFW.glfwMakeContextCurrent(window);
        
    // }

    // @Override
    // public void removeNotify() {
    //     GLFW.glfwMakeContextCurrent(0);
    //     super.removeNotify();
    // }
}
