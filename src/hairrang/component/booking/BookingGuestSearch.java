package hairrang.component.booking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private ArrayList<Guest> list;
	private JButton cancelButton;
	private JButton okButton;
	
	public BookingGuestSearch() {
		
		gService = new GuestService();
		list = (ArrayList<Guest>)gService.getGuestList();
		
//		UIManager.put("Button.font", CustomFonts.getNanumSqBold(14));
//		UIManager.put("Label.font", CustomFonts.getNanumSqBold(14));
		
		initComponents();
	}


	private void initComponents() {
		setBounds(100, 100, 760, 480);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel pSearchInput = new JPanel();
		pSearchInput.setLayout(null);
		pSearchInput.setBounds(190, 0, 280, 50);
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
		pTable.setBounds(0, 50, 730, 340);
		contentPanel.add(pTable);
		pTable.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 0, 734, 358);
		pTable.add(scrollPane);
		
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

			@Override
			public void mouseReleased(MouseEvent e) {
				tfName.setText((String) table.getValueAt(table.getSelectedRow(), 1));
			}
			
		});
		scrollPane.setViewportView(table);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		
		getRootPane().setDefaultButton(okButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
	
	
	/* ActionPerformed */
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			okButtonActionPerformed(e);
		}
		if (e.getSource() == cancelButton) {
			cancelButtonActionPerformed(e);
		}
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
		list = (ArrayList<Guest>) gService.searchGuestByName(guest);
		table.setItems(list);
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

	protected void cancelButtonActionPerformed(ActionEvent e) {
		dispose();
	}
	
	protected void okButtonActionPerformed(ActionEvent e) {
		try {
			getSelectedGuest();
		} catch (ArrayIndexOutOfBoundsException ex) {
			JOptionPane.showMessageDialog(null, "고객을 선택해주세요.");
		}
		
	}
}
