package hairrang.chart;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import hairrang.dto.Sales;
import hairrang.service.SalesService;
import hairrang.table.ChartTable;

public class SalesChart extends JPanel implements ActionListener, ItemListener {
	
	JComboBox<Integer> comboStartYear;
	JComboBox<Integer> comboEndYear;
	JButton btnSearch;

	JScrollPane spTable;
	ChartTable tableResult;
	DefaultTableModel tmodel;
	JLabel label;
	SalesService sService = new SalesService();
	
	ArrayList<int[]> chartList;
	
	JPanel pGraph;
	CardLayout graphCard = new CardLayout();
	
	final ButtonGroup buttonGroup = new ButtonGroup();
	
	// 이게 차트네
	private ChartService hcs;
	
	private String type = "연도별";

	public SalesChart() {
		
		setLayout(null);
		setSize(new Dimension(1144, 623));

		
		// #검색조건 패널#
		
		JPanel pSetSearch = new JPanel();
		pSetSearch.setLayout(null);
		pSetSearch.setBounds(12, 27, 495, 37);
		add(pSetSearch);
 
		JLabel lbShowDate = new JLabel("조회년도 :");
		lbShowDate.setBounds(12, 0, 101, 37);
		pSetSearch.add(lbShowDate);

		btnSearch = new JButton("조회");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(379, 0, 101, 37);
		pSetSearch.add(btnSearch);
		
		
		// [콤보박스]
		// 콤보박스 안의 값 설정
		Vector<Integer> YearValues = new Vector<Integer>(); // 년도 저장할 벡터
		//sYearValues에 현재 년도 - 2015년까지 넣기
		Calendar oCalendar = Calendar.getInstance(); // 현재 날짜/시간 등의 각종 정보 얻기
		// 현재 날짜

		int toyear = oCalendar.get(Calendar.YEAR);
		 for(int i = toyear; i>= 2015; i--){
			  YearValues.add(i);
		 }  
		
		// 콤보박스 세팅
		comboStartYear = new JComboBox<Integer>(YearValues); //정수값만 넣는 콤보박스
		comboStartYear.setBounds(113, 0, 114, 37);
		pSetSearch.add(comboStartYear);
		 
		label = new JLabel("-");
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
		tableResult = new ChartTable();
		spTable.setViewportView(tableResult);
		
		
		// #그래프 패널#
		pGraph = new JPanel();
		pGraph.setVisible(false);
		pGraph.setLayout(graphCard);
		
		pGraph.setBounds(12, 258, 682, 291);
		add(pGraph);
		

		
		// #이벤트 등록 (라디오버튼)#
		hcs = new ChartService(this);
		JRadioButton rdbtnYear = new JRadioButton("연도별");
		rdbtnYear.setSelected(true);
		buttonGroup.add(rdbtnYear);
		rdbtnYear.setBounds(511, 31, 82, 33);
		add(rdbtnYear);
		
		JRadioButton rdbtnMonth = new JRadioButton("월별");
		buttonGroup.add(rdbtnMonth);
		rdbtnMonth.setBounds(598, 27, 82, 37);
		add(rdbtnMonth);
		
		rdbtnYear.addItemListener(this);
		rdbtnMonth.addItemListener(this);
		
		// 테이블 setItems, 차트 setChart
		searchTableChart();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			// '검색' 버튼을 눌렀을 때
			searchTableChart();
		}
		
	}

	// 콤보박스의 값을 받아서 serach(start, end)에 넘겨준다
	private void searchTableChart() {
		int startYear = (int) comboStartYear.getItemAt(comboStartYear.getSelectedIndex());
		int endYear = (int) comboEndYear.getItemAt(comboEndYear.getSelectedIndex());
		
		ArrayList<Sales> list = (ArrayList<Sales>) sService.getTableDataByYear(startYear, endYear);
		tableResult.setItems(list);
		
		chartList = (ArrayList<int[]>) sService.getChartDataByYear(startYear, endYear);
		hcs.setChart(type, chartList);
	}

	// 라디오 버튼의 글자를 읽는 itemListener
	@Override
	public void itemStateChanged(ItemEvent e) {
		type = ((JRadioButton) e.getSource()).getText();
		hcs.setChart(type, chartList); // 차트 생성 & pGraph패널의 카드 레이아웃으로 show()
	}
	
	
}
