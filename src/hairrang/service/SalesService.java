package hairrang.service;

import java.util.Date;
import java.util.List;

import hairrang.dao.SalesDao;
import hairrang.dao.impl.SalesDaoImpl;
import hairrang.dto.Sales;

public class SalesService {
	
	private SalesDao dao = SalesDaoImpl.getInstance();
	
	public List<Sales> selectSalesByAll(){
		return dao.selectSalesByAll();
	}
	
	public List<Sales> selectSalesByGuestNo(Sales sales){
		return dao.selectSalesByGuestNo(sales);
	}
	

	
	
	public List<Sales> selectSalesByDate(Date before, Date after){
		return dao.selectSalesByDate(before, after);	
	}
	
	
	
	public int insertSales(Sales sales) {
		return dao.insertSales(sales);
	}
	
	public int getTodaySalesCount() {
		return dao.getTodaySalesCount();
	}
	
	public int getSalesNO() {
		return dao.sequencesLastNumber();
	}
	
	
	/* 차트 패널 */
	
	// 테이블 연도별
	public List<Sales> getTableDataByYear(int startYear, int endYear) {
		return dao.selectSalesByDate(startYear, endYear);
	}
	
	// 테이블 월별

	
	// 차트 연도별
	public List<int[]> getChartDataByYear(int startYear, int endYear) {
		return dao.selectSalesByYearForChart(startYear, endYear);
	}
	
	// 차트 월별
}
