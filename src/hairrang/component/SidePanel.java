package hairrang.component;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SidePanel extends JPanel {
	private JPanel pNotice;
	private JPanel pBooking;

	public SidePanel() {

		initComponents();
	}
	private void initComponents() {
		CustomFonts font = new CustomFonts();
		Font nanumSqReg = font.getNanumSqReg16();
		
		setBounds(new Rectangle(0, 0, 258, 639));
		setLayout(null);
		
		JPanel pToday = new JPanel();
		pToday.setBounds(new Rectangle(0, 0, 258, 100));
		add(pToday);
		pToday.setLayout(new BoxLayout(pToday, BoxLayout.Y_AXIS));
		
		JLabel lblToday = new JLabel("TODAY");
		pToday.add(lblToday);
		
		JLabel lblTodayCount = new JLabel("20명");
		lblTodayCount.setFont(nanumSqReg);
		pToday.add(lblTodayCount);
		
		pNotice = new JPanel();
		pNotice.setBounds(0, 100, 258, 200);
		add(pNotice);
		
		pBooking = new JPanel();
		pBooking.setBounds(0, 300, 258, 339);
		add(pBooking);
	}
}
