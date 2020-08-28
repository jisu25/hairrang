package hairrang.component;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hairrang.Configuration;
import hairrang.dto.Booking;
import hairrang.service.BookingService;
import hairrang.table.BookingTable;
import java.awt.GridLayout;

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
		setBounds(new Rectangle(0, 0, 258, 339));
		setLayout(null);
		
		lblBookingTitle = new JLabel("예약 상황");
		lblBookingTitle.setBounds(0, 0, 80, 24);
		lblBookingTitle.setFont(Configuration.GSANS_BOLD_20);
		add(lblBookingTitle);
		
		panel = new JPanel();
		panel.setBounds(0, 39, 258, 300);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new BookingTable();
		table.setItems(bookList);
		scrollPane.setViewportView(table);
		
		pBtns = new JPanel();
		pBtns.setBounds(178, 0, 80, 30);
		add(pBtns);
		pBtns.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblAdd = new JLabel("+");
		pBtns.add(lblAdd);
		
		lblDel = new JLabel("-");
		pBtns.add(lblDel);
	}
}
