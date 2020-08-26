package hairrang.component;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import hairrang.dto.Guest;
import hairrang.service.GuestService;
import hairrang.table.GuestSearchResultTable;
import hairrang.table.GuestSearchTable;

public class FrameGuestSearch extends JFrame {

	private JPanel contentPane;
	private GuestSearchTable table;
	private GuestService gService;
	private ArrayList<Guest> guestList;
	private GuestSearchPanel pGuest;
	private JPanel pBtn;
	private JButton btnCancel;
	private JButton btnInfo;
	private JButton btnOrder;
	private JPanel pTable;
	private JScrollPane scrollPane;
	private ArrayList<Guest> searchGuestByName;
	private GuestSearchResultTable rTable;
	private Guest guest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameGuestSearch frame = new FrameGuestSearch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameGuestSearch() {
		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();
		//searchGuestByName = (ArrayList<Guest>) gService.searchGuestByName(guest);
		
		
		initComponents();
		
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pGuest = new GuestSearchPanel();
		pGuest.setBounds(5, 5, 714, 186);
		contentPane.add(pGuest);
		
		pBtn = new JPanel();
		pBtn.setBounds(5, 190, 714, 38);
		contentPane.add(pBtn);
		pBtn.setLayout(null);
		
		btnCancel = new JButton("취소");
		btnCancel.setBounds(327, 5, 85, 23);
		pBtn.add(btnCancel);
		
		btnInfo = new JButton("이용 내역");
		btnInfo.setBounds(426, 5, 90, 23);
		pBtn.add(btnInfo);
		
		btnOrder = new JButton("주문");
		btnOrder.setBounds(530, 5, 85, 23);
		pBtn.add(btnOrder);
		
		pTable = new JPanel();
		pTable.setBounds(5, 234, 714, 260);
		contentPane.add(pTable);
		pTable.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		pTable.add(scrollPane);
		
		table = new GuestSearchTable();
		scrollPane.setViewportView(table);
		table.setItems(guestList);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Guest selectedGuest = getSelectedGuest();
					pGuest.setGuest(selectedGuest);

				}
			}
		});	
	}
	
	private Guest getSelectedGuest() {
		int selectedRow = table.getSelectedRow();
		return guestList.get(selectedRow);
	}
	
	public void searchGuestByName(){
		System.out.println("소환");
		
		
		
	}
	
}
