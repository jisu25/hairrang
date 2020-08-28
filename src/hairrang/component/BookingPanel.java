package hairrang.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hairrang.Configuration;
import hairrang.dto.Booking;
import hairrang.service.BookingService;
import hairrang.table.BookingTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class BookingPanel extends JPanel {
	private JLabel lblBookingTitle;
	private JPanel panel;
	private JScrollPane scrollPane;
	private BookingTable table;

	private BookingService bService = new BookingService(); 
	private ArrayList<Booking> bookList;
	private JPanel pBtns;
	private JLabel lblAdd;
	private JLabel lblDel;
	
	public BookingPanel() {
		
		bookList = (ArrayList<Booking>) bService.getBookList();
		initComponents();
	}
	
	private void initComponents() {
		setBounds(new Rectangle(0, 0, 218, 300));
		setLayout(null);
		
		lblBookingTitle = new JLabel("예약 상황");
		lblBookingTitle.setBounds(0, 0, 140, 36);
		lblBookingTitle.setFont(Configuration.GSANS_BOLD_20);
		add(lblBookingTitle);
		
		panel = new JPanel();
		panel.setBounds(0, 39, 218, 261);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new BookingTable();
		table.setItems(bookList);
		System.out.println("BooingPanel : " + bookList);
		scrollPane.setViewportView(table);
		
		pBtns = new JPanel();
		pBtns.setBounds(168, 0, 50, 30);
		add(pBtns);
		pBtns.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblAdd = new JLabel("+");
		lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdd.setFont(Configuration.NANUMSQ_EB_20);
		lblAdd.setForeground(new Color(120, 255, 0));
		pBtns.add(lblAdd);
		
		lblDel = new JLabel("-");
		lblDel.setHorizontalAlignment(SwingConstants.CENTER);
		lblDel.setFont(Configuration.NANUMSQ_EB_20);
		lblDel.setForeground(Color.RED);
		pBtns.add(lblDel);
	}
}
