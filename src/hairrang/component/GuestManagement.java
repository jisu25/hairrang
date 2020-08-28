package hairrang.component;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout.Constraints;

import hairrang.Configuration;
import hairrang.dto.Guest;
import hairrang.service.GuestService;
import hairrang.table.GuestManagementTable;

public class GuestManagement extends JPanel implements ActionListener {

	private JPanel pBtn;
	private JPanel pTable;
	private JButton btnCancel;
	private JButton btnAdd;
	private JScrollPane scrollPane;
	private ArrayList<Guest> guestList;
	private GuestService gService;
	private GuestManagementTable table;
	private GuestManagementPanel pGuest;
	private int curr;

	public GuestManagement() {

		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();
		curr = gService.getGuestCurrVal();

		initComponents();

	}

	private void initComponents() {
		setLayout(null);

		pGuest = new GuestManagementPanel();
		pGuest.setBounds(0, 0, 700, 190);
		add(pGuest);
		// 가입날짜, 고객번호 자동입력////////////////////////////////////////////////
		pGuest.setTfJoinDay();
		pGuest.setTfNo(curr);
		//////////////////////////////////////////////////

		pBtn = new JPanel();
		pBtn.setBounds(0, 190, 700, 40);
		add(pBtn);
		pBtn.setLayout(null);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(345, 5, Configuration.btnDim.width, Configuration.btnDim.height);
		pBtn.add(btnCancel);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(460, 5, Configuration.btnDim.width, Configuration.btnDim.height);
		pBtn.add(btnAdd);

		pTable = new JPanel();
		pTable.setBounds(0, 230, 700, 310);
		add(pTable);
		pTable.setLayout(new GridLayout(1, 0, 0, 0));

		scrollPane = new JScrollPane();
		pTable.add(scrollPane);
		
		table = new GuestManagementTable();
		scrollPane.setViewportView(table);
		
		guestList = (ArrayList<Guest>) gService.getGuestList();
		table.setItems(guestList);
		table.setComponentPopupMenu(createPopMenu());
		

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}

		if (e.getSource() == btnAdd) {
			if (e.getActionCommand().contentEquals("추가")) {
				try {
					btnAddActionPerformed(e);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			if (e.getActionCommand().equals("수정"))
				try {
					btnUpdateActionPerformed();
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
		}
	}

	// 고객리턴
	private Guest getSelectedGuest() {
		int selectedRow = table.getSelectedRow();
		guestList = (ArrayList<Guest>) gService.getGuestList();
		
		return guestList.get(selectedRow);
	}

	// 추가
	private void btnAddActionPerformed(ActionEvent e) throws ParseException {
		Guest addGuest;
		try {
			addGuest = pGuest.getGuest();
			// System.out.println(addGuest);
			
			gService.addGuest(addGuest);
			table.addRow(addGuest);
			
			guestList = (ArrayList<Guest>) gService.getGuestList();
			table.setItems(guestList);
			
			curr++;

			JOptionPane.showMessageDialog(null, String.format("%s님이 추가되었습니다.", addGuest.getGuestName()));
			
			
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		pGuest.clearTf();
		pGuest.setTfNo(curr);
		
		

	}

	// 수정
	private void btnUpdateActionPerformed() throws ParseException {
		
		Guest updateGuest = getSelectedGuest();
		Guest updateInfo = pGuest.getGuest();

		int idx = guestList.indexOf(updateGuest);
		table.updateRow(idx, updateInfo);
		gService.updateGuest(updateInfo);
		JOptionPane.showMessageDialog(null, String.format("%s님의 정보가 수정되었습니다.", updateGuest.getGuestName()));
		pGuest.clearTf();
		btnAdd.setText("추가");

//			System.out.println(updateGuest);
//			System.out.println(updateInfo);

	}

	// 제거
	private void btnCancelActionPerformed(ActionEvent e) {
		pGuest.clearTf();
		pGuest.setTfNo(curr);
		table.clearSelection();
		btnAdd.setText("추가");
	}

	// 팝메뉴//////////////////////////////////////////////////////////////////////////////////////////////////////////

	public JPopupMenu createPopMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateMenu = new JMenuItem("고객정보수정");
		JMenuItem deleteMenu = new JMenuItem("고객정보삭제");

		popMenu.add(updateMenu);
		popMenu.add(deleteMenu);

		updateMenu.addActionListener(addActionlistener);
		deleteMenu.addActionListener(addActionlistener);

		return popMenu;
	}

	
	ActionListener addActionlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("고객정보수정")) {
				try {
					actionUpdate();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getActionCommand().equals("고객정보삭제")) {
				actionDelete();
			}

		}
	};

	// 팝업메뉴를 눌렀을때
	protected void actionUpdate() throws ParseException {
		int index = table.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(null, "고객을 선택하세요");
			return;
		}
		
		JOptionPane.showMessageDialog(null, "정보 수정 후 수정버튼을 눌러주세요.");
		Guest update = getSelectedGuest();
		pGuest.setGuest(update);
		btnAdd.setText("수정");
		

	}

	protected void actionDelete() {
		int index = table.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(null, "고객을 선택하세요");
			return;
		}
		
		int idx = table.getSelectedRow();
		Guest delete = getSelectedGuest();

		int result = JOptionPane.showConfirmDialog(null, String.format("%s님을 삭제하시겠습니까?", delete.getGuestName()), "삭제",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			gService.deleteGuest(delete);
			table.removeRow(idx);
			pGuest.clearTf();
		} else {
			return;
		}

	}
	
	

}