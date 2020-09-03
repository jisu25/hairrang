package hairrang.service;

import java.util.List;

import hairrang.dao.GuestDao;
import hairrang.dao.impl.GuestDaoImpl;
import hairrang.dto.Guest;

public class GuestService {
	
	private GuestDao dao = GuestDaoImpl.getInstance();
	
	public void addGuest(Guest guest) {
		dao.insertGuest(guest);
	}
	
	public void updateGuest(Guest guest) {
		dao.updateGuest(guest);
	}
	
	public void deleteGuest(Guest guest) {
		dao.deleteGuest(guest);
	}
	
	public List<Guest> getGuestList(){
		return dao.selectGuestByAll();
	}
	
	public List<Guest> selectGuestByName(Guest guest) {
		return dao.selectGuestByName(guest);
	}
	
	public List<Guest> searchGuestByName(Guest guest){
		return dao.searchGuestByName(guest);
	}
	
	public Guest selectGuestByNo(Guest guest) {
		return dao.selectGuestByNo(guest);
	}
	
	public int getGuestCurrVal() {
		return dao.getGuestCurrVal();
	}
	
	public List<Guest> searchGuestByBirthday(String test){
		return dao.searchGuestByBirthday(test);
	}
	
	public List<Guest> searchGuestByPhone(String guest){
		return dao.searchGuestByPhone(guest);
	}
}
