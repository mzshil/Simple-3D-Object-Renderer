import org.lwjgl.glfw.*;


import static org.lwjgl.glfw.GLFW.*;
/**
 * InputHandler
 */
public class InputHandler {

    private float rotationX, rotationY;
    private float translationX, translationY, translationZ;

    public InputHandler(long window) {
        glfwSetKeyCallback(window, new KeyHandler());
        glfwSetMouseButtonCallback(window, new MouseHandler());
        glfwSetCursorPosCallback(window, new CursorPosHandler());
    }

    private class KeyHandler extends GLFWKeyCallback {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if (action == GLFW_PRESS || action == GLFW_REPEAT) {
                switch (key) {
                    case GLFW_KEY_W:
                        translationZ -= 0.1f;
                        break;
                    case GLFW_KEY_S:
                        translationZ += 0.1f;
                        break;
                    case GLFW_KEY_A:
                        translationX -= 0.1f;
                        break;
                    case GLFW_KEY_D:
                        translationX += 0.1f;
                        break;
                    case GLFW_KEY_UP:
                        rotationX -= 2.5f;
                        break;
                    case GLFW_KEY_DOWN:
                        rotationX += 2.5f;
                        break;
                    case GLFW_KEY_LEFT:
                        rotationY -= 2.5f;
                        System.out.println(getTranslationX());
                        break;
                    case GLFW_KEY_RIGHT:
                        rotationY += 2.5f;
                        break;
                }
            }
            else {
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            }
        }
    }

    private class MouseHandler extends GLFWMouseButtonCallback {
        @Override
        public void invoke(long window, int button, int action, int mods) {
            // Implement mouse handling logic if necessary
        }
    }

    private class CursorPosHandler extends GLFWCursorPosCallback {
        @Override
        public void invoke(long window, double xpos, double ypos) {
            // Implement cursor position handling logic if necessary
        }
    }

    public float getRotationX() {
        return rotationX;
    }

    public float getRotationY() {
        return rotationY;
    }

    public float getTranslationX() {
        return translationX;
    }

    public float getTranslationY() {
        return translationY;
    }

    public float getTranslationZ() {
        return translationZ;
    }
}