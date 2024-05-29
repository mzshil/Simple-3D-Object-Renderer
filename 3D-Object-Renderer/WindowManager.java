import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.awt.Canvas;
import java.nio.*;
import java.util.concurrent.CountDownLatch;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class WindowManager extends Canvas {
    public static final float FOV = (float) Math.toRadians(60);
    public static final float Z_NEAR = 0.01f;
    public static final float Z_FAR = 1000f;

    private final String title;
    private int width, height;
    private long window;
    private boolean vSync;
    private String shapeToRender = "cube"; 
    private InputHandler inputHandler;
    private CountDownLatch latch = new CountDownLatch(1);

    public WindowManager(String title, int width, int height, boolean vsync) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vsync;

        new Thread(this::init).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        inputHandler = new InputHandler(window);

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
        }

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwSwapInterval(vSync ? 1 : 0);
        glfwShowWindow(window);
        latch.countDown(); 

        loop();
    }

    private void loop() {
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glLoadIdentity();
            glTranslatef(inputHandler.getTranslationX(), inputHandler.getTranslationY(), inputHandler.getTranslationZ());
            glRotatef(inputHandler.getRotationX(), 1.0f, 0.0f, 0.0f);
            glRotatef(inputHandler.getRotationY(), 0.0f, 1.0f, 0.0f);

            switch (shapeToRender) {
                case "cube":
                    Shapes.renderCube(0.0f, 0.0f, 0.0f, 0.5f, new float[]{1.0f, 0.0f, 0.0f});
                    break;
					case "sphere":
                    Shapes.renderSphere(0, 0, 0, 1, new float[]{0, 1, 0}, 30, 30);
                    break;
                case "pyramid":
                    Shapes.renderPyramid(0.0f, 0.0f, 0.0f, 0.5f, new float[]{0.0f, 0.0f, 1.0f});
                    break;
            }

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void setShapeToRender(String shapeToRender) {
        this.shapeToRender = shapeToRender;
    }
}
