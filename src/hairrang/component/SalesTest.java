package hairrang.component;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hairrang.dto.Hair;
import hairrang.table.HairItemTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class SalesTest extends JPanel {
	private ArrayList<Hair> list = new ArrayList<Hair>();
	private HairItemTable table;
	private JTextField tfSumPrice;
	private JTextField tfTotalPrice;
	private JButton btnOrder;
	private JButton btnGuestSreach;
	private JButton btnCancel;
	private SalesOrderPanel salesPanel;
	


	/**
	 * Create the panel.
	 */
	public SalesTest() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 629, 219);
		add(panel);
		panel.setLayout(null);
		
		salesPanel = new SalesOrderPanel();
		salesPanel.setBounds(0, 0, 437, 219);
		panel.add(salesPanel);
		
		JPanel hairTable = new JPanel();
		hairTable.setBounds(438, 0, 191, 219);
		panel.add(hairTable);
		hairTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		hairTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new HairItemTable();
		table.setItems(list);
		scrollPane.setViewportView(table);
		
		salesPanel.setHtable(table);
		
		JLabel lblSumPrice = new JLabel("합계 :");
		lblSumPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblSumPrice.setBounds(444, 229, 57, 21);
		add(lblSumPrice);
		
		tfSumPrice = new JTextField();
		tfSumPrice.setBounds(501, 229, 116, 21);
		salesPanel.setTfSumPrice(tfSumPrice);
		add(tfSumPrice);
		tfSumPrice.setColumns(10);
		
		JLabel lblTotalPrice = new JLabel("총계 :");
		lblTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPrice.setBounds(444, 260, 57, 21);
		add(lblTotalPrice);
		
		tfTotalPrice = new JTextField();
		tfTotalPrice.setColumns(10);
		tfTotalPrice.setBounds(501, 260, 116, 21);
		salesPanel.setTfTotalPrice(tfTotalPrice);
		add(tfTotalPrice);
		
		btnOrder = new JButton("주문");
		btnOrder.addActionListener(actionlistener);
		btnOrder.setBounds(523, 291, 94, 23);
		add(btnOrder);
		
		btnGuestSreach = new JButton("고객검색");
		btnGuestSreach.setBounds(417, 291, 94, 23);
		add(btnGuestSreach);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(actionlistener);
		btnCancel.setBounds(311, 291, 94, 23);
		add(btnCancel);

	}
	


	ActionListener actionlistener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancel) {
				btnCancelAction();
			}
			
		}

		private void btnCancelAction() {
		System.out.println("액션시작");
		salesPanel.clearTf();
			
		}
	};
	
}
