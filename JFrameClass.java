import javax.swing.JFrame;

public class JFrameClass {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Minesweeper");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(500, 250);
		frame.setSize(550, 550);

		MouseSensor mouseClick = new MouseSensor();
		
		MSPanel panelMS = new MSPanel();
	}
}

