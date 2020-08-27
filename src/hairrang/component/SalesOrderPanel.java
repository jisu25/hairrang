package hairrang.component;

import javax.swing.JPanel;
import javax.rmi.CORBA.UtilDelegate;
import javax.swing.JLabel;
import java.awt.Color;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import hairrang.dto.Event;
import hairrang.dto.Guest;
import hairrang.dto.Hair;
import hairrang.dto.Sales;
import hairrang.service.HairService;

import java.awt.Checkbox;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

public class SalesOrderPanel extends JPanel {
	private JTextField tfSalesNo;
	private JTextField tfSalesDay;
	private JTextField tfHairPrice;
	private JTextField tfGuestName;
	private JTextField tfGuestNo;
	private JTextField tfSale;
	private Checkbox checkMember;
	SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
	HairService hairService = new HairService();

	/**
	 * Create the panel.
	 */
	public SalesOrderPanel() {
		setLayout(null);
		
		JLabel lblSalesNo = new JLabel("영업번호 :");
		lblSalesNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesNo.setBounds(26, 47, 62, 25);
		add(lblSalesNo);
		
		tfSalesNo = new JTextField();
		tfSalesNo.setHorizontalAlignment(SwingConstants.CENTER);
		tfSalesNo.setBounds(100, 49, 101, 21);
		add(tfSalesNo);
		tfSalesNo.setColumns(10);
		
		JLabel lblHairName = new JLabel("헤어명 :");
		lblHairName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHairName.setBounds(213, 47, 62, 25);
		add(lblHairName);
		
		JLabel lblSalesDay = new JLabel("영업일자 :");
		lblSalesDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesDay.setBounds(26, 84, 62, 25);
		add(lblSalesDay);
		
		
		java.util.Date today = new java.util.Date();
		tfSalesDay = new JTextField();
		tfSalesDay.setHorizontalAlignment(SwingConstants.CENTER);
		tfSalesDay.setColumns(10);
		tfSalesDay.setBounds(100, 84, 101, 21);
		tfSalesDay.setText(format.format(today));
		add(tfSalesDay);
		
		JLabel lblHairPrice = new JLabel("단가 :");
		lblHairPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblHairPrice.setBounds(213, 82, 62, 25);
		add(lblHairPrice);
		
		tfHairPrice = new JTextField();
		tfHairPrice.setHorizontalAlignment(SwingConstants.CENTER);
		tfHairPrice.setColumns(10);
		tfHairPrice.setBounds(287, 84, 101, 21);
		add(tfHairPrice);
		
		JLabel lblMemberCheck = new JLabel("회원체크 :");
		lblMemberCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemberCheck.setBounds(26, 119, 62, 25);
		add(lblMemberCheck);
		
		JLabel lblGuestName = new JLabel("고객명 :");
		lblGuestName.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuestName.setBounds(26, 154, 62, 25);
		add(lblGuestName);
		
		tfGuestName = new JTextField();
		tfGuestName.setHorizontalAlignment(SwingConstants.CENTER);
		tfGuestName.setColumns(10);
		tfGuestName.setBounds(100, 156, 101, 21);
		add(tfGuestName);
		
		JLabel lblEventName = new JLabel("이벤트명 :");
		lblEventName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEventName.setBounds(213, 154, 62, 25);
		add(lblEventName);
		
		JLabel lblGuestNo = new JLabel("고객번호 :");
		lblGuestNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuestNo.setBounds(26, 189, 62, 25);
		add(lblGuestNo);
		
		tfGuestNo = new JTextField();
		tfGuestNo.setHorizontalAlignment(SwingConstants.CENTER);
		tfGuestNo.setColumns(10);
		tfGuestNo.setBounds(100, 191, 101, 21);
		add(tfGuestNo);
		
		JLabel lblSale = new JLabel("할인율 :");
		lblSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblSale.setBounds(213, 189, 62, 25);
		add(lblSale);
		
		tfSale = new JTextField();
		tfSale.setHorizontalAlignment(SwingConstants.CENTER);
		tfSale.setColumns(10);
		tfSale.setBounds(287, 191, 101, 21);
		add(tfSale);
		
		checkMember = new Checkbox("비회원");
		checkMember.setBounds(110, 119, 81, 23);
		add(checkMember);
		
		JComboBox comboHair = new JComboBox();
		comboHair.setBounds(287, 49, 101, 21);
		
		add(comboHair);
		
		JComboBox comboEvent = new JComboBox();
		comboEvent.setBounds(287, 156, 101, 21);
		add(comboEvent);

	}
	
	//수정해야함!!!!!!!!!!!!!!!!!!!!!!!!!
	public Sales getSales() {
		int no = Integer.parseInt(tfSalesNo.getText().trim());
		Date day = new Date(System.currentTimeMillis());
		Guest guest = new Guest();
		guest.setGuestName(tfGuestName.getText().trim());
		guest.setGuestNo(Integer.parseInt(tfGuestNo.getText().trim()));
		Event event = new Event();
		
		//event.setEventName();
		
		event.setSale(Double.parseDouble(tfSale.getText().trim()));
		Hair hair = new Hair();
		
		//hair.setHairName(tfHairName.getText().trim());
		
		hair.setPrice(Integer.parseInt(tfHairPrice.getText().trim()));
		return new Sales(no, day, guest, event, hair);
	}
	
	//수정해야함!!!!!!!!!!!!!!!!!!!!!!!!!
	public void setSales(Sales sales) {
		//영업번호는 지금 dbscript가 이상해서 회의해야함 그리고 impl에서 영업번호의 시퀀스 마지막을 가져와 +1을 시켜서 세팅
		tfSalesDay.setText(format.format(sales.getSalesDay()));
		
		//tfHairName.setText(sales.getHairNo().getHairName());
		
		tfHairPrice.setText(String.valueOf(sales.getHairNo().getPrice()));
		tfGuestName.setText(sales.getGuestNo().getGuestName());
		tfGuestNo.setText(String.valueOf(sales.getGuestNo().getGuestNo()));
		
		//tfEventName.setText(sales.getEventNo().getEventName());
		
		tfSale.setText(String.valueOf(sales.getEventNo().getSale()));
			
		
	}
	
	public void clearSalesTf() {
		
	}
}
