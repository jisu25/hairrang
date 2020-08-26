package hairrang.dao;

import java.util.List;

import hairrang.dto.Guest;



public interface GuestDao {

	int insertGuest(Guest guest);
	
	int updateGuest(Guest guest);
	
	int deleteGuest(Guest guest);

	int getGuestCurrVal();

	List<Guest> selectGuestByAll();
	
	Guest selectGuestByNo(Guest guest);

	List<Guest> searchGuestByName(Guest guest);

	List<Guest> selectGuestByName(Guest guest);


	//이름으로 검색
	
}
