package hairrang.table;

import java.util.ArrayList;
import java.util.List;

import hairrang.dto.Booking;
import hairrang.dto.Sales;

public abstract class AbstractDetailTable<T> extends AbstractItemTable<T> {

	@Override
	Object[] getColName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Object[] toArray(T itemList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	void setWidthAndAlign() {
		// TODO Auto-generated method stub
		
	}

	private void changeTable(ArrayList<T> list) {
		try {
			setItems(list);
		} catch (NullPointerException e) {
			throw new RuntimeException(e);
		}
	}

}
