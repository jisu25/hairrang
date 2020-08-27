package hairrang.dao.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hairrang.dao.SalesDao;
import hairrang.dao.impl.SalesDaoImpl;
import hairrang.dto.Sales;


public class SalesDaoTest {
	private SalesDao dao;	
	
	@Before
	public void setUp() throws Exception {
		 dao=SalesDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		dao=null;
		
	}

	//@Test
	public void testSelectSalesByAll() {
		System.out.printf("%s()%n","testSelectSalesByAll()");
		List<Sales> list = dao.selectSalesByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		System.out.println("야광돼지번쩍번쩍");
		
	}

	
	//@Test
	public void selectSalesByGuestNo() {
		System.out.printf("%s()%n","selectSalesByGuestNo()");
		List<Sales> list = dao.selectSalesByGuestNo(new Sales(2));
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
		
	}
	
	/*
		@Test
		public void testInsertSales() {
			System.out.printf("%s()%n","testInsertSales()");
			java.sql.Date d=  java.sql.Date .valueOf("2004-06-22");
			
			java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			Sales newSales = new Sales(5, d,  new Guest(2), new Event(1),new Hair(1));
			int res = dao.insertSales(newSales);
			Assert.assertEquals(1, res);
		}
	
		//@Test
		public void testUpdateSales() {
			System.out.printf("%s()%n","testUpdateSales()");
			Sales updqteSales = dao.selectSalesByNo(new Sales(3));
			
			updqteSales.setGuestNo(new Guest(2));
			java.sql.Date d=  java.sql.Date .valueOf("2004-06-22");
			updqteSales.setSalesDay(d);
			java.util.Date utilDate = new java.util.Date();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    updqteSales.setSalesTime(sqlDate);
		    dao.updateSales(updqteSales);
		    Assert.assertNotNull(updqteSales);
		    System.out.println(updqteSales);
			
		}
	
		//@Test
		public void testDeleteSales() {
			System.out.printf("%s()%n","testDeleteSales()");
			Sales deleteSales = dao.selectSalesByNo(new Sales(4));
			dao.deleteSales(deleteSales);
			Assert.assertNotNull(deleteSales);
			System.out.println(deleteSales);
		}
	*/
	@Test
	public void testselectSalesByDate() throws ParseException {
		System.out.printf("%s()%n","testselectSalesByDate()");
		SimpleDateFormat Format = new SimpleDateFormat("yyyy-MM-dd");
	
		Date befroe = Format.parse("2020-08-24");
		Date after = Format.parse("2020-08-26");
		
		List<Sales> daySales = dao.selectSalesByDate(befroe, after);
		Assert.assertNotNull(daySales);
		daySales.stream().forEach(System.out::println);
		
	}
}
