package hairrang.component;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hairrang.Configuration;
import hairrang.dto.Booking;
import hairrang.service.BookingService;
import hairrang.table.BookingTable;

public class BookingPanel extends JPanel implements ActionListener {
	private JLabel lblBookingTitle;
	private JPanel panel;
	private JScrollPane scrollPane;
	private BookingTable table;
	private BookingAddDialog addDialog;

	private BookingService bService = new BookingService(); 
	private ArrayList<Booking> bookList;
	private JPanel pBtns;
	private JButton btnAdd;
	private JButton btnDel;
	
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
		
		btnAdd = new JButton("+");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnDel = new JButton("-");
		btnDel.addActionListener(this);
		pBtns.add(btnDel);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDel) {
			btnDelActionPerformed(e);
		}
		if (e.getSource() == btnAdd) {
			btnAddActionPerformed(e);
		}
	}
	
	// + 버튼을 눌렀을 때
	protected void btnAddActionPerformed(ActionEvent e) {
		addDialog = new BookingAddDialog();
		addDialog.setTitle("이용내역");
		addDialog.setVisible(true);
		addDialog.setParentPanel(this);
	}
	
	// - 버튼을 눌렀을 때
	protected void btnDelActionPerformed(ActionEvent e) {
		int selIdx = table.getSelectedRow();
		int bookNo = (int)table.getValueAt(selIdx, 0);
		
		Booking delBook = bookList.get(bookList.indexOf(new Booking(bookNo)));
		
		int res = JOptionPane.showConfirmDialog(null, String.format("%s(%s)님의 예약내역을 삭제하시겠습니까? \n(%s %s, %s)",
					delBook.getGuestNo().getGuestName(), delBook.getGuestNo().getPhone(),
					delBook.getBookDayStr(), delBook.getBookTimeStr(), delBook.getHairNo().getHairName()),
					"예약 내역 삭제 확인", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		if(res == JOptionPane.YES_OPTION) {
			table.removeRow(selIdx);
			bookList.remove(delBook);
//			bService.deleteBook(delBook);
			
			JOptionPane.showMessageDialog(null, String.format("%s(%s)님의 예약내역을 삭제했습니다.",
					delBook.getGuestNo().getGuestName(), delBook.getGuestNo().getPhone()),
					"예약 내역 삭제 완료",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void updateList() {
		bookList = (ArrayList<Booking>) bService.getBookList();
		table.setItems(bookList);
	}
}
