package hairrang.service;

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
	
	
	

}
