import javax.swing.JFrame;

public class JFrameClass {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Minesweeper");
		frame.setLocation(600, 300);
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		MouseSensor mouseClick = new MouseSensor();
		frame.addMouseListener(mouseClick);

		
		MSPanel panelMS = new MSPanel();
		frame.add(panelMS);
		

		frame.setVisible(true);
	}
}

