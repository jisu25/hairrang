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
	
	

}
