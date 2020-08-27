package hairrang.component;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SidePanel extends JPanel {

	public SidePanel() {
		CustomFonts font = new CustomFonts();
		Font nanumSqReg = font.getNanumSqReg16();
		
		setBounds(new Rectangle(0, 0, 258, 639));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(0, 0, 258, 100));
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("TODAY");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("20명");
		lblNewLabel_1.setFont(nanumSqReg);
		panel.add(lblNewLabel_1);

	}

}
