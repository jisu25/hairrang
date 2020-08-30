package hairrang.component;

import javax.swing.JPanel;

public class OrderDetail extends JPanel {

	/**
	 * Create the panel.
	 */
	public OrderDetail() {
		setLayout(null);
		
		OrderDetailPanel panel = new OrderDetailPanel();
		panel.setBounds(0, 0, 719, 471);
		add(panel);

	}

}
