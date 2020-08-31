package hairrang.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

public class BookingAddDialog extends JDialog {

	private BookingAddInputPanel pBookAdd = new BookingAddInputPanel();
	private JPanel pBtns;
	private JButton okBtn;
	private JButton cancelBtn;

	public BookingAddDialog() {
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
		pBtns.add(okBtn);
		
		cancelBtn = new JButton("취소");
		pBtns.add(cancelBtn);
	}

}
