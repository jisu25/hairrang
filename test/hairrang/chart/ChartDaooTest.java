package hairrang.chart;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChartDaooTest {
	private ChartDaoo dao;
	@Before
	public void setUp() throws Exception {
		dao = ChartDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void testTotalPrice() {
		System.out.println("testTotalPrice()");
		List<Integer> list = dao.totalPrice(1995, 2020);
		Assert.assertNotNull(list);
		System.out.println(list);
	}

}
