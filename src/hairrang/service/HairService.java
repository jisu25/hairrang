package hairrang.service;

import java.util.List;

import hairrang.dao.HairDao;
import hairrang.dao.impl.HairDaoImpl;
import hairrang.dto.Hair;



public class HairService {

	private HairDao dao = HairDaoImpl.getInstance();
	
	public void addHair(Hair hair) {
		dao.insertHair(hair);
	}
	
	public void updateHair(Hair event) {
		dao.updateHair(event);
	}
	
	public void deleteHair(Hair event) {
		dao.deleteHair(event);
	}
	
	public List<Hair> getHairList(){
		return dao.selectHairByAll();
	}
	
	public List<String> getHairNames(){
		return dao.selectHairByName();
	}
	
	public Hair getHairListByHairNo(Hair event) {
		return dao.selectHairByNo(event);
	}
}
