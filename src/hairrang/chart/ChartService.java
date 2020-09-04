package hairrang.chart;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import hairrang.service.SalesService;

public class ChartService {

	private SalesChart salesChart;
	private SalesService sService = new SalesService();

	private String statType = "연도별"; // 라디오버튼 값 (디폴트는 "연도별")
	
	// 부모에서 리스트 넘겨 받아서 setChart() 해줬었음. 생성시 초기화할 필요가 없어 보임.
	// private List<int[]> results = sService.getChartDataByYear(2020, 2020); 

	
	// 차트 서비스를 호출하는 부모랑 연결. SalesChart에서 new ChartService(this) 해줌
	public ChartService(SalesChart hc) {
		this.salesChart = hc;
//		setChart(statType, results); // 연결하고 바로 생성. 초기화 불필요해서 주석처리
	}

	// Table의 setItems 같은 거
	// <라디오버튼 값에 따른 막대 그래프 세팅> 이벤트
	// option : 1 -연도별 2-월별
	public void setChart(String type, List<int[]> results) {

		// #차트 생성#
		// [데이터 생성]
		DefaultCategoryDataset dataset;

		// [데이터 세팅]
		// type(통계 분류)에 따라 다르게 세팅됨
		dataset = getGraphDataset(type, results);

		// [렌더링]
		// 렌더링 생성
		final BarRenderer renderer = new BarRenderer();
		

		// 공통 옵션 정의
		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
		final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
		final ItemLabelPosition p_below = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
		Font f = new Font("맑은고딕", Font.BOLD, 16);
		Font axisF = new Font("맑은고딕", Font.BOLD, 16);

		// 렌더링 세팅
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBasePositiveItemLabelPosition(p_center);
		renderer.setBaseItemLabelFont(f);
		renderer.setBaseItemLabelPaint(Color.white); //막대 글씨 색
		renderer.setSeriesPaint(0, new Color(35,43,153)); //막대 색

		// [plot]
		// plot 생성
		final CategoryPlot plot = new CategoryPlot();
		

		// plot 에 데이터 적재
		plot.setDataset(dataset);
		plot.setRenderer(renderer);

		// plot 기본 설정
		plot.setOrientation(PlotOrientation.VERTICAL); // 그래프 표시 방향
		plot.setRangeGridlinesVisible(true); // X축 가이드 라인 표시여부
		plot.setDomainGridlinesVisible(true); // Y축 가이드 라인 표시여부

		// X축 세팅
		plot.setDomainAxis(new CategoryAxis()); // X축 종류 설정
		plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
		plot.getDomainAxis().setTickLabelPaint(Color.black); // X축 눈금라벨 폰트 색상 조정
		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 카테고리 라벨 위치 조정

		// Y축 세팅
		plot.setRangeAxis(new NumberAxis()); // Y축 종류 설정
		plot.getRangeAxis().setTickLabelFont(axisF); // Y축 눈금라벨 폰트 조정
		plot.getRangeAxis().setTickLabelPaint(Color.black); // X축 눈금라벨 폰트 색상 조정

		// 세팅된 plot을 바탕으로 chart 생성
		JFreeChart chart = new JFreeChart(plot);
		chart.setBackgroundPaint(Color.WHITE);
		chart.getPlot().setBackgroundPaint(Color.white);

		// #생성된 차트로 차트 패널 생성#
		ChartPanel chartp = new ChartPanel(chart);
		chartp.setSize(554, 451); // 사이즈 지정 필수!

		switch (type) {
		case "월별":
			salesChart.pGraph.add("graphMonth", chartp);
			salesChart.graphCard.show(salesChart.pGraph, "graphMonth");
			salesChart.comboEndYear.setVisible(false);
			salesChart.label.setVisible(false);
			break;

		case "연도별":
			renderer.setSeriesPaint(0, new Color(153, 102, 255));
			salesChart.pGraph.add("graphYear", chartp);
			salesChart.graphCard.show(salesChart.pGraph, "graphYear");
			salesChart.comboEndYear.setVisible(true);
			salesChart.label.setVisible(true);
			
			break;

		default:
			break;
		}

		salesChart.pGraph.setVisible(true);

	}

	// <타입(월별,연도별)에 따른 그래프 데이터셋 리턴> 메소드
	public DefaultCategoryDataset getGraphDataset(String type, List<int[]> results) {

		DefaultCategoryDataset dataset = null;

		if (results != null) { // 조회 결과 있을 때만 그래프 값 설정
			System.out.println("그래프 값 설정!!!!!");
			Vector<String> date = new Vector<String>(); // 날짜 (한 행)
			Vector<Integer> values = new Vector<Integer>(); // 분류별 값 (한 행)

			dataset = new DefaultCategoryDataset();

			int size = results.size();

			switch (type) {
			case "월별":
				for (int i = 0; i < results.size(); i++) {
					dataset.addValue((Integer) results.get(i)[1], type, (Integer) results.get(i)[0]);
				}
				break;
			/*case "월별":
				//rows.addElement((toString().valueOf(results.get(i).getHairname().getHairName())));
				System.out.println("");
				for (int i = 0; i < size; i++) {
			 		
					date.addElement(String.valueOf((results.get(i).getSales().getSalesDay()))); 
					values.addElement((results.get(i).getSales().getTotalPrice()));
			
					// 값, 범례, 카테고리 지정
					dataset.addValue(values.get(i), type, date.get(i));
				}
				break;
			*/
			case "연도별":
				for (int i = 0; i < results.size(); i++) {
					dataset.addValue((Integer) results.get(i)[1], type, (Integer) results.get(i)[0]);
				}
				break;

			default:
				break;
			}

		}

		return dataset;
	}

}