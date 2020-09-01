package hairrang.component;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import hairrang.dto.Guest;
import hairrang.dto.Sales;
import hairrang.service.GuestService;
import hairrang.service.SalesService;
import hairrang.table.GuestSearchTable;

public class SearchFrame extends JFrame {

	private JPanel contentPane;
	private GuestSearchTable table;
	private GuestService gService;
	private ArrayList<Guest> guestList;
	private ArrayList<Sales> salesList;
	private TestSearchPanel pSearch;
	private JPanel pTable;
	private SalesService sService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFrame frame = new SearchFrame();
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
	public SearchFrame() {
		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();

		sService = new SalesService();
		salesList = (ArrayList<Sales>) sService.selectSalesByAll();

		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pSearch = new TestSearchPanel();
		pSearch.setBounds(5, 0, 674, 117);
		contentPane.add(pSearch);
		pSearch.setMainFrame(this);
		
		pTable = new JPanel();
		pTable.setBounds(5, 117, 674, 377);
		contentPane.add(pTable);
		pTable.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pTable.add(scrollPane);
		
		table = new GuestSearchTable();
		scrollPane.setViewportView(table);
		table.setItems(guestList);
	}
	
	public void searchResult(String search) {
		ArrayList<Guest> result = (ArrayList<Guest>) gService.searchGuestByName(new Guest(search));
		// result.stream().forEach(System.out::println);
		table.setItems(result);

	}
	
	public void searchBirthday(String search) {
		ArrayList<Guest> result = (ArrayList<Guest>) gService.searchGuestByBirthday(search);
		result.stream().forEach(System.out::println);
		table.setItems(result);
	}
	
	public void searchPhone(String search) {
		ArrayList<Guest> result = (ArrayList<Guest>) gService.searchGuestByPhone(search);
		// result.stream().forEach(System.out::println);
		table.setItems(result);

	}
}
