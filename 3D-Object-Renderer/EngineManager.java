// import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
// import org.lwjgl.glfw.GLFWErrorCallback;


// public class EngineManager {
//     public static final long NANOSECOND = 1000000000L;
//     public static final float FRAMERATE = 1000;

//     private static int fps;
//     private static float frametime = 1.0f / FRAMERATE;

//     private boolean isRunning;

//     private WindowManager window;
//     private GLFWErrorCallback errorCallback;

//     private void init() throws Exception {
//         glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
//         window = Launcher.GetWindow();
//         //window.init();
//     }

//     public void start() throws Exception {
//         init();
//         if(isRunning) {
//             return;
            
//         }
//         else {
//             Run();
//         }
//     }

//     public void Run() {
//         this.isRunning = true;
//         int frames = 0;
//         long frameCounter = 0;
//         long lastTime = System.nanoTime();
//         double unprocessedTime = 0;

//         while (isRunning) {
//             boolean render = true;
//             long startTime = System.nanoTime();
//             long passedTime = startTime - lastTime;
//             lastTime = startTime;

//             unprocessedTime += passedTime / (double) NANOSECOND;
//             frameCounter += passedTime;
//         }
//     }

//     private void Stop() {

//     }

//     private void Input() {

//     }

//     private void Update() {

//     }

//     private void CleanUp() {

//     }

//     public static int getFps() {
//         return fps;
//     }

//     public static void setFps() {
//         EngineManager.fps = fps;
//     }
// }
