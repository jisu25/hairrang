package hairrang.chart;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import hairrang.TestMain;

public class HairrangChart extends JPanel {
	JComboBox comboStartYear;
	JComboBox comboEndYear;
	JButton btnSearch;

	JScrollPane spTable;
	JTable tableResult;
	DefaultTableModel tmodel;

	JRadioButton rdbtnYearRadioButton;
	JRadioButton rdbtnMonthRadioButton;

	JPanel pGraph;
	CardLayout graphCard = new CardLayout();

	final ButtonGroup buttonGroup = new ButtonGroup();

	public HairrangChart() {
		setLayout(null);
		setSize(new Dimension(1144, 623));

		// #검색조건 패널#
		JPanel pSetSearch = new JPanel();
		pSetSearch.setLayout(null);
		pSetSearch.setBounds(12, 27, 495, 37);
		add(pSetSearch);
 
		JLabel lbShowDate = new JLabel("조회년도 :");
		lbShowDate.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lbShowDate.setBounds(12, 0, 101, 37);
		pSetSearch.add(lbShowDate);

		btnSearch = new JButton("조회");
		btnSearch.setBorder(UIManager.getBorder("Button.border"));
		btnSearch.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnSearch.setBounds(379, 0, 101, 37);
		pSetSearch.add(btnSearch);
		
		
		// [콤보박스]
		// 콤보박스 안의 값 설정
		Vector<Integer> YearValues = new Vector<Integer>(); // 년도 저장할 벡터
		//sYearValues에 현재 년도 - 2015년까지 넣기
		Calendar oCalendar = Calendar.getInstance(); // 현재 날짜/시간 등의 각종 정보 얻기
		// 현재 날짜

		int toyear = oCalendar.get(Calendar.YEAR);
		 for(int i = toyear; i>= 1995; i--){
			  YearValues.add(i);
		 }  
		
		// 콤보박스 세팅
		comboStartYear = new JComboBox<Integer>(YearValues); //정수값만 넣는 콤보박스
		comboStartYear.setBounds(113, 0, 114, 37);
		pSetSearch.add(comboStartYear);
		 
		JLabel label = new JLabel("-");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		label.setBounds(236, 2, 15, 32);
		pSetSearch.add(label);
		
		// 끝년도 콤보박스
		comboEndYear = new JComboBox<Integer>(YearValues); //정수값만 넣는 콤보박스
		comboEndYear.setBounds(251, 0, 114, 37);
		pSetSearch.add(comboEndYear);

		// #테이블 스크롤 패널#
		spTable = new JScrollPane();
		spTable.setBounds(12, 74, 682, 174);
		add(spTable);
		
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
		tableResult.setRowHeight(26);
		// 테이블 값 가운데 정렬
//		tableCellCenter(tableResult);

		spTable.setViewportView(tableResult);
		
		// #그래프 패널#
		pGraph = new JPanel();
		pGraph.setVisible(false);
		pGraph.setLayout(graphCard);
		
		pGraph.setBounds(12, 258, 682, 291);
		add(pGraph);
		

		
		// #이벤트 등록#
		HairrangChartService hcs = new HairrangChartService(this);
		JRadioButton rdbtnYearRadioButton = new JRadioButton("연도별");
		buttonGroup.add(rdbtnYearRadioButton);
		rdbtnYearRadioButton.setBounds(511, 31, 82, 33);
		add(rdbtnYearRadioButton);
		
		JRadioButton rdbtnMonthRadioButton = new JRadioButton("월별");
		buttonGroup.add(rdbtnMonthRadioButton);
		rdbtnMonthRadioButton.setBounds(598, 27, 82, 37);
		add(rdbtnMonthRadioButton);
		
		btnSearch.addActionListener(hcs);
		rdbtnYearRadioButton.addItemListener(hcs);
		rdbtnMonthRadioButton.addItemListener(hcs);
				
	}

	public JComboBox getComboStartYear() {
		return comboStartYear;
	}

	public JComboBox getComboEndYear() {
		return comboEndYear;
	}
	
	
}
