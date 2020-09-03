package hairrang;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import hairrang.chart.HairrangChart;
import hairrang.component.BookingDetail;
import hairrang.component.CustomFonts;
import hairrang.component.GuestManagement;
import hairrang.component.GuestSearch;
import hairrang.component.OrderDetail;
import hairrang.component.SalesTest;
import hairrang.component.SidePanel;

public class HairshopManagementProgram extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel layeredPane;

	private GuestManagement p1;
	private GuestSearch p2;
	private SalesTest p3;
	private OrderDetail p4;
	private HairrangChart p5;
	private BookingDetail p6;
	private SidePanel sidePanel;
	private JPanel emptyPane;

	private JPanel menuPanel;

	private Dimension btnMenuDim = new Dimension(100, 60);
	private Dimension btnHomeDim = new Dimension(230, 90);

	private JPanel menuBtnsPanel;
	private JButton btnMenu1;
	private JButton btnMenu2;
	private JButton btnMenu3;
	private JButton btnMenu4;
	private JButton btnMenu5;
	private JButton btnHome;
	private JButton[] btnsMenu;

	private JPanel[] pArr;
	private JLabel lblMenuName;

	public HairshopManagementProgram() throws FontFormatException, IOException {

		/* Font 설정 */
		setUIFont(CustomFonts.getNanumSqReg(14));

		UIManager.put("Panel.background", Color.WHITE);
		UIManager.put("TextField.border", BorderFactory.createMatteBorder(0, 0, 1, 0, Configuration.COLOR_GRAY_LINE));
		UIManager.put("TextField.inactiveBackground", Color.WHITE);
		
		UIManager.put("Button.font", CustomFonts.getNanumSqBold(14));
		UIManager.put("Button.foreground", Configuration.COLOR_MAIN);
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("Button.border", BorderFactory.createLineBorder(Configuration.COLOR_MAIN, 1));
		
		UIManager.put("CheckBox.background", Color.WHITE);
		UIManager.put("RadioButton.background", Color.WHITE);
		UIManager.put("RadioButton.font", CustomFonts.getNanumSqBold(14));
		
		UIManager.put("Label.font", CustomFonts.getNanumSqBold(14));
		
		UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder());
		UIManager.put("Viewport.background", Color.WHITE);
		UIManager.put("Table.gridColor", Configuration.COLOR_GRAY_LINE);
		
		UIManager.put("TableHeader.font", CustomFonts.getNanumSqBold(14));
		UIManager.put("TableHeader.gridColor", Configuration.COLOR_GRAY_LINE);
		UIManager.put("TableHeader.cellBorder", BorderFactory.createMatteBorder(0, 1, 0, 1, Color.WHITE));
		UIManager.put("TableHeader.background", Configuration.COLOR_MAIN);
		UIManager.put("TableHeader.foreground", Color.white);
		
		
		
		/* Frame 생성*/
		initComponents();
		
	}

	private void initComponents() throws FontFormatException, IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//		setTitle

		/* panel 정의 */

		layeredPane = new JPanel();
		layeredPane.setBounds(30, 160, 700, 540);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		// dto 수정하면서 각 패널들 고장나서 기본 JPanel로 바꿔 넣음
		// dto에 맞게 구현하고 나면 morph 하세요.

		p1 = new GuestManagement();
		layeredPane.add(p1, "GuestManagement");
		p1.setLayout(null);

		p2 = new GuestSearch();
		layeredPane.add(p2, "GuestSearch");
		p2.setLayout(null);
		p2.setProgram(this);

		p3 = new SalesTest();
		layeredPane.add(p3, "SalesTest");
		p3.setLayout(null);
		
		p4 = new OrderDetail();
		layeredPane.add(p4, "OrderDetail");
		p4.setLayout(null);
		
		p5 = new HairrangChart();
		layeredPane.add(p5, "Chart");
		
		p6 = new BookingDetail();
		layeredPane.add(p6, "BookingDetail");
		
		pArr = new JPanel[] { p1, p2, p3, p4, p5, p6};
		
		
		/* 메뉴부 */

		menuPanel = new JPanel();
		menuPanel.setBackground(Configuration.COLOR_MAIN);
		menuPanel.setBounds(0, 0, 1008, 90);
		contentPane.add(menuPanel);
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		btnHome = new JButton("HAIRRANG");
		btnHome.setFont(Configuration.GSANS_BOLD_28);
		btnHome.setPreferredSize(new Dimension(230, 90));
		btnHome.addActionListener(this);
		btnHome.setOpaque(false);
		btnHome.setContentAreaFilled(false);
		btnHome.setBorderPainted(false);
		btnHome.setForeground(Color.white);
		menuPanel.add(btnHome);

//		emptyPane = new JPanel();
//		emptyPane.setPreferredSize(new Dimension(20, 90));
//		emptyPane.setOpaque(false);
//		menuPanel.add(emptyPane);

		menuBtnsPanel = new JPanel();
		menuBtnsPanel.setOpaque(false);
		menuPanel.add(menuBtnsPanel);
		menuBtnsPanel.setLayout(new GridLayout(1, 6, 0, 0));
		menuBtnsPanel.setPreferredSize(new Dimension(700, 90));

		btnMenu1 = new JButton("고객 관리");
		btnMenu2 = new JButton("고객 검색");
		btnMenu3 = new JButton("주문");
		btnMenu4 = new JButton("주문 내역");
		btnMenu5 = new JButton("통계");

		btnsMenu = new JButton[] { btnMenu1, btnMenu2, btnMenu3, btnMenu4, btnMenu5 };

		for (int i = 0; i < btnsMenu.length; i++) {
			btnsMenu[i].addActionListener(this);
			btnsMenu[i].setPreferredSize(btnMenuDim);
			btnsMenu[i].setOpaque(false);
			btnsMenu[i].setContentAreaFilled(false);
			btnsMenu[i].setBorderPainted(false);
			btnsMenu[i].setFont(Configuration.GSANS_BOLD_16);
			btnsMenu[i].setForeground(Color.white);
		}

		menuBtnsPanel.add(btnMenu1);
		menuBtnsPanel.add(btnMenu2);
		menuBtnsPanel.add(btnMenu3);
		menuBtnsPanel.add(btnMenu4);
		menuBtnsPanel.add(btnMenu5);

		lblMenuName = new JLabel(Configuration.MENU_NAMES[0]);
		lblMenuName.setBounds(30, 120, 140, 32);
		lblMenuName.setFont(Configuration.GSANS_BOLD_20);
		contentPane.add(lblMenuName);

		sidePanel = new SidePanel();
		sidePanel.setMainProgram(this);
		sidePanel.setBounds(750, 90, 258, 639);
		contentPane.add(sidePanel);
	}

	public SalesTest getP3() {
		return p3;
	}

	public void actionPerformed(ActionEvent e) {
		/*
		if (e.getSource() == btnMenu6) {
			JFrame temp = new JFrame();.
			temp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			temp.setBounds(100, 100, 940, 690);
			temp.getContentPane().add(new chartPanel());
			temp.setVisible(true);
			return;
		}
		*/
		
//		if (e.getSource() == btnMenu6 ) {
//			p6.add(new chartPanel());
//			p6.setVisible(true);
//			return;
//		}
		 
		if (e.getSource() == btnMenu1 || e.getSource() == btnMenu2 || e.getSource() == btnMenu3 || e.getSource() == btnMenu4 || e.getSource() == btnMenu5 ) {
			btnsMenuActionPerformed(e);
		}
	}

	protected void btnsMenuActionPerformed(ActionEvent e) {
		for (int i = 0; i < btnsMenu.length; i++) {
			if (e.getSource() == btnsMenu[i]) {
				switchPanel(i);
			}
		}
	}

	public void switchPanel(int i) {
		lblMenuName.setText(Configuration.MENU_NAMES[i]);
		pArr[i].revalidate();
		layeredPane.removeAll();
		layeredPane.add(pArr[i]);
		
		//layeredPane.getComponent(0).getComponentAt(500, 20).setVisible(false);
		repaint();
		revalidate();
		
		//관리-검색 이동할때 테이블 리스트 재셋팅
		p2.listUpdate(); //야매메소드,,
		
		//검색에서 주문 누르면 해당 고객 정보 가지고 주문창으로 이동
		
	}


	public static void setUIFont(Font font) {
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			
			String keyStr = String.valueOf(key);
			int dotIdx = keyStr.lastIndexOf(".");
			
			if (dotIdx != -1) {
				if (keyStr.substring(dotIdx + 1).equals("foreground")) {
					//System.out.println(keyStr);
					UIManager.put(key, Configuration.COLOR_DEEPGRAY_TEXT);
				}
			}
				
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
	}
}