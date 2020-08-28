package hairrang.component;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hairrang.Configuration;
import hairrang.service.SalesService;

public class SidePanel extends JPanel {
	private JPanel pNotice;
	private BookingPanel pBooking;
	private SalesService sService;
	private String todayCount;
	
	public SidePanel() {
		
		sService = new SalesService();
		todayCount = String.valueOf(sService.getTodaySalesCount());
		
		initComponents();
		
	}
	
	private void initComponents() {
		CustomFonts font = new CustomFonts();
		
		setBounds(new Rectangle(0, 0, 258, 639));
		setLayout(null);
		
		JPanel pToday = new JPanel();
		pToday.setBounds(new Rectangle(0, 0, 258, 100));
		add(pToday);
		pToday.setLayout(new BoxLayout(pToday, BoxLayout.Y_AXIS));
		
		JLabel lblToday = new JLabel("TODAY");
		lblToday.setFont(Configuration.GSANS_BOLD_20);
		pToday.add(lblToday);
		
		JLabel lblTodayCount = new JLabel();
		lblTodayCount.setFont(Configuration.GSANS_BOLD_28);
		lblTodayCount.setText(todayCount + "명");
		pToday.add(lblTodayCount);
		
		pNotice = new JPanel();
		pNotice.setBounds(0, 100, 258, 200);
		add(pNotice);
		
		pBooking = new BookingPanel();
		pBooking.setBounds(0, 300, 258, 339);
		add(pBooking);
	}
}
