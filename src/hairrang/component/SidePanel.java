package hairrang.component;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hairrang.Configuration;
import hairrang.service.SalesService;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class SidePanel extends JPanel {
	private JPanel pNotice;
	private BookingPanel pBooking;
	private SalesService sService;
	private String todayCount;
	
	public SidePanel() {
		setBackground(new Color(255, 250, 205));
		
		sService = new SalesService();
		todayCount = String.valueOf(sService.getTodaySalesCount());
		
		initComponents();
		
	}
	
	private void initComponents() {
		
		setBounds(new Rectangle(0, 0, 258, 639));
		setLayout(null);
		
		JPanel pToday = new JPanel();
		pToday.setBounds(new Rectangle(20, 20, 218, 60));
		add(pToday);
		pToday.setLayout(new BoxLayout(pToday, BoxLayout.Y_AXIS));
		
		JLabel lblToday = new JLabel("방문 고객");
		lblToday.setFont(Configuration.GSANS_BOLD_20);
		pToday.add(lblToday);
		
		JLabel lblTodayCount = new JLabel();
		lblTodayCount.setFont(Configuration.GSANS_BOLD_28);
		lblTodayCount.setText(todayCount + "명");
		pToday.add(lblTodayCount);
		
		pNotice = new JPanel();
		pNotice.setBounds(20, 100, 218, 140);
		add(pNotice);
		
		pBooking = new BookingPanel();
		pBooking.setBounds(20, 260, 218, 360);
		add(pBooking);
	}
}
