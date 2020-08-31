package hairrang.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
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
		pBtns.add(cancelBtn);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okBtn) {
			okBtnActionPerformed(e);
		}
	}
	
	
	// 등록 버튼을 눌렀을 때 패널에서 정보 가져오기
	protected void okBtnActionPerformed(ActionEvent e) {
		Guest newGuest = pBookAdd.getGuest();
		
		
		Booking newBook = new Booking();
//		bService.addBook();
		
	}
}
