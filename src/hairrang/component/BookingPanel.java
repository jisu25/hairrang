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
import java.awt.FlowLayout;
import java.awt.Dimension;

public class BookingPanel extends JPanel implements ActionListener {

	private JPanel pBtns;
	private JButton btnShowAll;
	private JButton btnAdd;
	private JButton btnDel;
	
	private JPanel panel;
	private JScrollPane scrollPane;
	private BookingTable table;
	
	private BookingAddDialog addDialog;
	
	public SidePanel getSidePanel() {
		return sidePanel;
	}

	public void setSidePanel(SidePanel sidePanel) {
		this.sidePanel = sidePanel;
	}

	private SidePanel sidePanel;
	
	private BookingService bService = new BookingService(); 
	private ArrayList<Booking> bookList;
	
	
	
	public BookingPanel() {
		bookList = (ArrayList<Booking>) bService.getTodayBookList();
		initComponents();
	}
	
	private void initComponents() {
		setBounds(new Rectangle(0, 0, 218, 300));
		setLayout(null);
		
		JLabel lblBookingTitle = new JLabel("예약 상황");
		lblBookingTitle.setBounds(0, 0, 100, 36);
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
		pBtns.setBounds(114, 5, 110, 30);
		add(pBtns);
		pBtns.setLayout(null);
		
		btnShowAll = new JButton("전체");
		btnShowAll.addActionListener(this);
		btnShowAll.setBounds(5, 0, 40, 24);
		pBtns.add(btnShowAll);
		
		btnAdd = new JButton("+");
		btnAdd.setLocation(50, 0);
		btnAdd.setSize(Configuration.DIM_BTNMINI);
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnDel = new JButton("-");
		btnDel.setLocation(80, 0);
		btnDel.setSize(Configuration.DIM_BTNMINI);
		btnDel.addActionListener(this);
		pBtns.add(btnDel);
		
	}
	
	
	// ActionPerformed
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnShowAll) {
			btnShowAllActionPerformed(e);
		}
		if (e.getSource() == btnDel) {
			btnDelActionPerformed(e);
		}
		if (e.getSource() == btnAdd) {
			btnAddActionPerformed(e);
		}
	}
	

	// 데이터 삽입하고 삭제할 때 씀. 화면 패널 전환시에도 쓸 예정.
	public void updateList() {
		bookList = (ArrayList<Booking>) bService.getTodayBookList();
		table.setItems(bookList);
	}
	
	
	// + 버튼을 눌렀을 때 : 예약 등록하는 대화상자 띄우기
	protected void btnAddActionPerformed(ActionEvent e) {
		addDialog = new BookingAddDialog();
		addDialog.setTitle("이용내역");
		addDialog.setVisible(true);
		addDialog.setParentPanel(this);
	}
	
	
	/** TODO 예약내역 삭제 (- 버튼을 눌렀을 때)
	 *	
	 * 	1. 테이블의 선택된 항목 얻어와서
	 * 	2. 사용자에게 삭제 여부 묻기
	 * 	3. DB에서 삭제하고 DB로부터 list를 업데이트 받는다.
	 */
	protected void btnDelActionPerformed(ActionEvent e) {
		int selIdx = table.getSelectedRow();
		int bookNo = (int)table.getValueAt(selIdx, 0);
		
		Booking delBook = bookList.get(bookList.indexOf(new Booking(bookNo)));
		
		int res = JOptionPane.showConfirmDialog(null, String.format("%s(%s)님의 예약내역을 삭제하시겠습니까? \n(%s %s, %s)",
					delBook.getGuestNo().getGuestName(), delBook.getGuestNo().getPhone(),
					delBook.getBookDayStr(), delBook.getBookTimeStr(), delBook.getHairNo().getHairName()),
					"예약 내역 삭제 확인", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		if(res == JOptionPane.YES_OPTION) {
			bService.deleteBook(delBook);
			updateList();
			JOptionPane.showMessageDialog(null, String.format("%s(%s)님의 예약내역을 삭제했습니다.",
					delBook.getGuestNo().getGuestName(), delBook.getGuestNo().getPhone()),
					"예약 내역 삭제 완료",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	protected void btnShowAllActionPerformed(ActionEvent e) {
		sidePanel.getMainProgram().switchPanel(5);
	}
	
}
