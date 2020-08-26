package hairrang.dao;

import java.util.List;

import hairrang.dto.Event;

public interface EventDao {

		List<Event> selectEventByAll();
		
		List<String> selectEventNames();

		Event selectEventByEventNo(Event event);
		
		int insertEvent(Event event);

		int updateEvent(Event event);

		int deleteEvent(Event event);
	
}
