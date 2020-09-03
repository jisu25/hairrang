package hairrang.component.booking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hairrang.dto.Booking;
import hairrang.dto.Guest;
import hairrang.service.BookingService;

public class BookingAddDialog extends JDialog implements ActionListener {

	private BookingAddInputPanel pBookAdd = new BookingAddInputPanel();
	private JPanel pBtns;
	private JButton okBtn;
	private JButton cancelBtn;
	
	private BookingService bService;
	private BookingPanel parentPanel;
	
	public BookingAddDialog() {
		bService = new BookingService();
		
		initComponets();
	}

	private void initComponets() {
		setBounds(100, 100, 380, 360);
		
		getContentPane().setLayout(new BorderLayout());
		
		pBookAdd.setBorder(new EmptyBorder(20, 20, 20, 20));
		pBookAdd.setLayout(null);
		getContentPane().add(pBookAdd, BorderLayout.CENTER);
		
		pBtns = new JPanel();
		pBtns.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(pBtns, BorderLayout.SOUTH);
		
		okBtn = new JButton("등록");
		okBtn.addActionListener(this);
		pBtns.add(okBtn);
		
		cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(this);
		pBtns.add(cancelBtn);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelBtn) {
			cancelBtnActionPerformed(e);
		}
		if (e.getSource() == okBtn) {
			okBtnActionPerformed(e);
		}
	}
	
	
	// 등록 버튼을 눌렀을 때 패널에서 정보 가져오기
	protected void okBtnActionPerformed(ActionEvent e) {
		Booking newBook = pBookAdd.getBook();
		int res = JOptionPane.showConfirmDialog(null, String.format("다음 예약 내역을 추가하시겠습니까?\n\n %s", newBook.getBookInfo()), "예약확인", JOptionPane.YES_NO_OPTION);
		System.out.println(newBook);
		// Yes: 0, No: 1
		if (res == 0) {
			bService.addBook(newBook);
			parentPanel.updateList();
			JOptionPane.showMessageDialog(null, "예약이 등록되었습니다!");
			dispose();
		}
	}
	
	
	/* 내 부모 패널 BookingPanel getter & setter*/
	
	public BookingPanel getParentPanel() {
		return parentPanel;
	}

	public void setParentPanel(BookingPanel parentPanel) {
		this.parentPanel = parentPanel;
	}

	protected void cancelBtnActionPerformed(ActionEvent e) {
		dispose();
	}
}
