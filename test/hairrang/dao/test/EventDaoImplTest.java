package hairrang.dao.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hairrang.dao.EventDao;
import hairrang.dao.impl.EventDaoImpl;
import hairrang.dto.Booking;
import hairrang.dto.Event;
import hairrang.dto.Guest;

public class EventDaoImplTest {

	private EventDao dao;
	
	@Before
	public void setUp() {
		dao = EventDaoImpl.getInstance();
	}

	@After
	public void tearDown() {
		dao = null;
	}
	
	@Test
	public void test04SelectEventByAll() {
		System.out.println("testSelectEventByAll()");
		List<Event> list = dao.selectEventByAll();
		Assert.assertNotNull(list);
		System.out.println("> 전체 이벤트 조회");
		list.stream().forEach(System.out::println);
		System.out.println();
	}

	@Test
	public void test05SelectEventNames() {
		System.out.println("testSelectEventNames()");
		List<String> list = dao.selectEventNames();
		Assert.assertNotNull(list);
		System.out.println("> 전체 이벤트 이름 조회");
		list.stream().forEach(System.out::println);
		System.out.println();
	}

	@Test
	public void test06SelectEventByEventNo() {
		System.out.println("testSelectEventByAll()");
		Event event = dao.selectEventByEventNo(new Event(1));
		Assert.assertNotNull(event);
		System.out.printf("> %d번 이벤트 내용 조회\n", event.getEventNo());
		System.out.println(event + "\n");
	}

	@Test
	public void test01InsertEvent() {
		System.out.println("test01InsertEvent()");
		Event event = new Event(10, "1주년", 0.1);
		int res = dao.insertEvent(event);
		Assert.assertEquals(1, res);
		System.out.println("추가된 이벤트 : " + event + "\n");
	}

	@Test
	public void test02UpdateEvent() {
		System.out.println("test02UpdateEvent()");
		Event event = new Event(10, "1분기 행사", 0.15);
		int res = dao.updateEvent(event);
		Assert.assertEquals(1, res);
		System.out.println("수정 후 이벤트 : " + event + "\n");
	}

	@Test
	public void test03DeleteEvent() {
		System.out.println("test03DeleteEvent()");
		Event event = new Event(10);
		int res = dao.deleteEvent(event);
		Assert.assertEquals(1, res);
		System.out.println("삭제 완료");
	}

}
