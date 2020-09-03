package hairrang.component.booking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import hairrang.CustomFonts;
import hairrang.dto.Guest;
import hairrang.service.GuestService;
import hairrang.table.GuestSearchTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class BookingGuestSearch extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private GuestSearchTable table;
	private JLabel lblName;
	private JTextField tfName;
	private JButton btnSearch;
	
	private GuestService gService;
	private BookingAddInputPanel pBookingAddInput;

	public BookingGuestSearch() {
		
		gService = new GuestService();
		ArrayList<Guest> list = (ArrayList<Guest>)gService.getGuestList();
		
		UIManager.put("Button.font", CustomFonts.getNanumSqBold(14));
		UIManager.put("Label.font", CustomFonts.getNanumSqBold(14));
		
		setBounds(100, 100, 620, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel pSearchInput = new JPanel();
		pSearchInput.setLayout(null);
		pSearchInput.setBounds(160, 0, 280, 50);
		contentPanel.add(pSearchInput);
		
		lblName = new JLabel("고객명 :");
		lblName.setBounds(12, 20, 57, 15);
		pSearchInput.add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(70, 14, 116, 26);
		pSearchInput.add(tfName);
		tfName.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(198, 14, 64, 26);
		pSearchInput.add(btnSearch);
		
		JPanel pTable = new JPanel();
		pTable.setBounds(0, 50, 590, 340);
		contentPanel.add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new GuestSearchTable();
		table.setItems(list);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					getSelectedGuest();
					dispose();
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
	
	
	/* ActionPerformed */
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			btnSearchActionPerformed(e);
		}
	}
	
	// 1. search 버튼 클릭하면 검색하도록 이름을 넘겨줌
	protected void btnSearchActionPerformed(ActionEvent e) {
		String name = tfName.getText().trim();
		searchGuest(new Guest(name));
	}
	
	// 2. 넘겨받은 이름으로 회원 검색
	public void searchGuest(Guest guest) {
		ArrayList<Guest> result = (ArrayList<Guest>) gService.searchGuestByName(guest);
		table.setItems(result);
	}
	
	// 테이블에서 선택된 고객 정보를 얻어온 후, 이전의 입력 패널에 set 해줌 
	private void getSelectedGuest() {
		int selectedRow = table.getSelectedRow();
		int no = (int) table.getValueAt(selectedRow, 0);
		
		Guest guest = gService.selectGuestByNo(new Guest(no));
		pBookingAddInput.setSelectedGuestToPanel(guest);
		
	}
	
	
	/* getter & setter */
	
	public BookingAddInputPanel getpBookingAddInput() {
		return pBookingAddInput;
	}

	public void setpBookingAddInput(BookingAddInputPanel pBookingAddInput) {
		this.pBookingAddInput = pBookingAddInput;
	}

}
