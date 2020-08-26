package hairrang.chart;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import hairrang.TestMain;

import java.awt.CardLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class HairrangChart extends JPanel {
	JComboBox comboStartYear;
	JComboBox comboEndYear;
	JButton btnSearch;

	JScrollPane spTable;
	JTable tableResult;
	DefaultTableModel tmodel;
	
	JRadioButton rdBtnTotalPrice;
	JRadioButton rdBtnNetIncome;
	JRadioButton rdBtnCash;
	JRadioButton rdBtnCard;

	JPanel pGraph;
	CardLayout graphCard = new CardLayout();
	
	 final ButtonGroup buttonGroup = new ButtonGroup();
	
	public HairrangChart() {
		setLayout(null);
		
		JPanel pSearch = new JPanel();
		pSearch.setBounds(12, 0, 477, 56);
		add(pSearch);
		pSearch.setLayout(null);
		
		JLabel lblShowYear = new JLabel("조회연도 :");
		lblShowYear.setFont(new Font("굴림", Font.BOLD, 20));
		lblShowYear.setBounds(0, 10, 111, 39);
		pSearch.add(lblShowYear);
		
		
		
		// [콤보박스]
		// 콤보박스 안의 값 설정
		Vector<Integer> YearValues = new Vector<Integer>(); // 년도 저장할 벡터
		//sYearValues에 현재 년도 - 2015년까지 넣기
		Calendar oCalendar = Calendar.getInstance( );  		// 현재 날짜/시간 등의 각종 정보 얻기
		// 현재 날짜
		 int toyear = oCalendar.get(Calendar.YEAR);
		 for(int i = toyear; i>= 2015; i--){
			  YearValues.add(i);
		 }  
		
		// 콤보박스 세팅
		 JComboBox comboStartYear = new JComboBox<Integer>(YearValues);
			comboStartYear.setBounds(108, 12, 89, 39);
			pSearch.add(comboStartYear);
		
		JLabel lblNewLabel_1 = new JLabel("-");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setBounds(209, 10, 20, 39);
		pSearch.add(lblNewLabel_1);
		
		JComboBox comboEndYear = new JComboBox<Integer>(YearValues);
		comboEndYear.setBounds(229, 12, 89, 39);
		pSearch.add(comboEndYear);
		
		JButton btnSearch = new JButton("조회");
		btnSearch.setFont(new Font("굴림", Font.BOLD, 16));
		btnSearch.setBounds(350, 10, 83, 40);
		pSearch.add(btnSearch);
		
		JScrollPane spTable = new JScrollPane();
		spTable.setBounds(12, 59, 682, 241);
		add(spTable);
		
		JPanel pGraph = new JPanel();
		pGraph.setBounds(12, 310, 682, 250);
		add(pGraph);
		pGraph.setLayout(new CardLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("\uADF8\uB798\uD504");
		pGraph.add(lblNewLabel, "name_724415271712000");
		
		// [테이블]
				// 테이블 열 세팅
				Vector<String> col = new Vector<String>(); // 열
				col.add("영업번호");
				col.add("영업일자");
				col.add("고객명");
				col.add("헤어명");
				col.add("이벤트명");
				col.add("단가");
				tmodel = new DefaultTableModel(col, 0);

				// 테이블 보이기
				tableResult = new JTable(tmodel);
				tableResult.setRowMargin(10);
				tableResult.setRowHeight(30);
				// 테이블 값 가운데 정렬
				TestMain.tableCellCenter(tableResult);

				spTable.setViewportView(tableResult);

				
				// #이벤트 등록#
				HairrangChartService hcs = new  HairrangChartService(this);
				
				JRadioButton rdbtnYearRadioButton = new JRadioButton("\uC5F0\uB3C4\uBCC4");
				buttonGroup.add(rdbtnYearRadioButton);
				rdbtnYearRadioButton.setBounds(492, 23, 82, 33);
				add(rdbtnYearRadioButton);
				
				JRadioButton rdbtnMonthRadioButton = new JRadioButton("\uC6D4\uBCC4");
				buttonGroup.add(rdbtnMonthRadioButton);
				rdbtnMonthRadioButton.setBounds(573, 28, 82, 23);
				add(rdbtnMonthRadioButton);
				
				btnSearch.addActionListener(hcs);
				rdbtnYearRadioButton.addItemListener(hcs);
				rdbtnMonthRadioButton.addItemListener(hcs);
				
	}
}
