package hairrang.service;

import java.util.List;

import hairrang.dao.EventDao;
import hairrang.dao.impl.EventDaoImpl;
import hairrang.dto.Event;

public class EventService {
	
		private EventDao dao = EventDaoImpl.getInstance();
		
		public void addEvent(Event event) {
			dao.insertEvent(event);
		}
		
		public void updateEvent(Event event) {
			dao.updateEvent(event);
		}
		
		public void deleteEvent(Event event) {
			dao.deleteEvent(event);
		}
		
		public List<Event> getEventList(){
			return dao.selectEventByAll();
		}
		
		public List<String> getEventNames(){
			return dao.selectEventNames();
		}
		
		public Event getEventListByEventNo(Event event) {
			return dao.selectEventByEventNo(event);
		}
		
		
		/*
		public List<Event> selectEventByName(Event event) {
			return dao.selectEventByName(event);
		}
		
		public List<Event> searchEventByName(Event event){
			return dao.searchEventByName(event);
		}
		
		public int getEventCurrVal() {
			return dao.getEventCurrVal();
		}
		*/
}
