import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MouseSensor extends MouseAdapter {
	public void mouseClicked(MouseEvent click) {

		Component component = click.getComponent();
		while (!(component instanceof JFrame)) {
			component = component.getParent();
			if (component == null) {
				return;
			}
		}
			JFrame frame = (JFrame) component;
			MSPanel panelMS = (MSPanel) frame.getContentPane().getComponent(0);
			Insets inserted = frame.getInsets();
			int x1 = inserted.left;
			int y1 = inserted.top;
			click.translatePoint(-x1, -y1);
			int x = click.getX();
			int y = click.getY();
			panelMS.x = x;
			panelMS.y = y;
			panelMS.xGridMouseClick = panelMS.createXGrid(x, y);
			panelMS.yGridMouseClick = panelMS.createYGrid(x, y);
			panelMS.repaint();
		}
	}

