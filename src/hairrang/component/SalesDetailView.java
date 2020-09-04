package hairrang.component;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import hairrang.dto.Sales;
import hairrang.service.SalesService;
import hairrang.table.GuestOrderInfoTable;


@SuppressWarnings("serial")
public class SalesDetailView extends JPanel implements ActionListener {

	private GuestOrderInfoTable table;
	private SalesService salesService = new SalesService();

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radioWeek;
	private JRadioButton radioMonth;
	private JRadioButton radioDay;
	private JButton btnSreach;

	private JDateChooser afterDate;
	private JDateChooser beforeDate;
	private Calendar beforecal;
	private Calendar aftercal;

	public SalesDetailView() {
		initComponents();

		listUpdate();
	}

	private void initComponents() {
		setLayout(null);

		JPanel DetailTablePanel = new JPanel();
		DetailTablePanel.setBounds(0, 157, 697, 315);
		add(DetailTablePanel);
		DetailTablePanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		DetailTablePanel.add(scrollPane, BorderLayout.CENTER);

		table = new GuestOrderInfoTable();
		scrollPane.setViewportView(table);

		JLabel lblDate = new JLabel("날  짜 :");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(120, 30, 62, 21);
		add(lblDate);

		beforeDate = new JDateChooser();
		beforeDate.setBounds(194, 30, 125, 21);
		add(beforeDate);
		beforeDate.setDateFormatString("yyyy-MM-dd");

		JLabel lblDate2 = new JLabel("~");
		lblDate2.setFont(new Font("굴림", Font.BOLD, 12));
		lblDate2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate2.setBounds(331, 33, 29, 15);
		add(lblDate2);

		afterDate = new JDateChooser();
		afterDate.setBounds(372, 30, 125, 21);
		afterDate.setDateFormatString("yyyy-MM-dd");
		add(afterDate);

		radioMonth = new JRadioButton("한달");
		buttonGroup.add(radioMonth);
		radioMonth.setHorizontalAlignment(SwingConstants.CENTER);
		radioMonth.setBounds(416, 76, 70, 23);
		radioMonth.addActionListener(this);
		add(radioMonth);

		radioWeek = new JRadioButton("일주일");
		radioWeek.setSelected(true);
		buttonGroup.add(radioWeek);
		radioWeek.setHorizontalAlignment(SwingConstants.CENTER);
		radioWeek.setBounds(342, 76, 70, 23);
		radioWeek.addActionListener(this);
		add(radioWeek);

		radioDay = new JRadioButton("하루");
		buttonGroup.add(radioDay);
		radioDay.setHorizontalAlignment(SwingConstants.CENTER);
		radioDay.setBounds(268, 76, 70, 23);
		radioDay.addActionListener(this);
		add(radioDay);

		btnSreach = new JButton("검색");
		btnSreach.addActionListener(this);
		btnSreach.setBounds(505, 76, 57, 23);
		add(btnSreach);

		radioTotal = new JRadioButton("전체");
		buttonGroup.add(radioTotal);
		radioTotal.setHorizontalAlignment(SwingConstants.CENTER);
		radioTotal.setBounds(194, 76, 70, 23);
		radioTotal.addActionListener(this);
		add(radioTotal);
	}

	/*
		ActionListener acitonLIstener = new ActionListener() {
			Calendar beforecal = Calendar.getInstance();
			Calendar aftercal = Calendar.getInstance();
	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == radioMonth) {
					setPeriod(Calendar.MONTH, -1);
					return;
				}
				if (e.getSource() == radioWeek) {
					setPeriod(Calendar.DATE, -7);
					return;
				}
				if (e.getSource() == radioDay) {
					setPeriod(Calendar.DATE, -1);
					return;
				}
				if (e.getSource() == btnSreach) {
					changeTable();
				}
				if (e.getSource() == radioTotal) {
					setPeriod(0, 0);
				}
			}
	
			private void setPeriod(int field, int amount) {
				beforecal.add(Calendar.MONTH, -1);
	
				beforeDate.setDate(beforecal.getTime());
				afterDate.setDate(aftercal.getTime());
	
				beforecal = Calendar.getInstance();
				aftercal = Calendar.getInstance();
			}
		};
		*/
	
	// 대훈 미안합니다!!!
	
	private JRadioButton radioTotal;

	private void changeTable() {
		try {
			ArrayList<Sales> list = new ArrayList<Sales>();
			list = (ArrayList<Sales>) salesService.selectSalesByDate(beforeDate.getDate(), afterDate.getDate());
			table.setItems(list);
		} catch (NullPointerException e) {
			table.setItems((ArrayList<Sales>)salesService.selectSalesByAll());
		}
	}

	public void listUpdate() {
		radioWeek.setSelected(true);
		setPeriod(Calendar.DATE, -7);
		changeTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Date oldDay = salesService.oldSalesDay();
		if (e.getSource() == radioTotal) {
			
			afterDate.setDate(null);
			beforeDate.setDate(oldDay);
			return;
		}
		if (e.getSource() == radioMonth) {
			setPeriod(Calendar.MONTH, -1);
			return;
		}
		if (e.getSource() == radioWeek) {
			setPeriod(Calendar.DATE, -7);
			return;
		}
		if (e.getSource() == radioDay) {
			setPeriod(Calendar.DATE, -1);
			return;
		}
		if (e.getSource() == btnSreach) {
			changeTable();
		}
	}

	private void setPeriod(int field, int amount) {
		beforecal = Calendar.getInstance();
		aftercal = Calendar.getInstance();

		beforecal.add(field, amount);

		beforeDate.setDate(beforecal.getTime());
		afterDate.setDate(aftercal.getTime());

	}

}