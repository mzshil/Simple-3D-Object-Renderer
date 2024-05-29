import javax.swing.*;
import java.awt.*;


public class Launcher extends JFrame {
    private static WindowManager windowManager;
	

	public Launcher() {
        setTitle("LWJGL with JAWT");
        setSize(500, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        windowManager = new WindowManager("3D Object Renderer", 800, 600, false);
        add(windowManager, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton renderCubeButton = new JButton("Render Cube");
        JButton renderSphereButton = new JButton("Render Sphere");
        JButton renderPyramidButton = new JButton("Render Pyramid");

         renderCubeButton.addActionListener(e -> windowManager.setShapeToRender("cube"));
         renderSphereButton.addActionListener(e -> windowManager.setShapeToRender("sphere"));
         renderPyramidButton.addActionListener(e -> windowManager.setShapeToRender("pyramid"));

        controlPanel.add(renderCubeButton);
        controlPanel.add(renderSphereButton);
        controlPanel.add(renderPyramidButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

	

	public static void main(String[] args) {
		//windowManager = new WindowManager("Simple 3D Object Renderer", 1600, 900, false);
		

		SwingUtilities.invokeLater(() -> {
            Launcher frame = new Launcher();
            frame.setVisible(true);
        });
	}

	public static WindowManager GetWindow() {
		return windowManager;
	}
}