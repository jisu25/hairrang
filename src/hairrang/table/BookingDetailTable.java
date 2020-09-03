package hairrang.table;

import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import hairrang.dto.Booking;

public class BookingDetailTable extends AbstractItemTable<Booking> {

	private int no = 1;
	
	@Override
	public void setItems(ArrayList<Booking> itemList) {
		super.setItems(itemList);
		no = 1;
	}
	
	@Override
	Object[] getColName() {
		return new String[] {"", "", "No", "예약일자", "고객명", "연락처", "헤어명", "비고"};
	}

	@Override
	Object[] toArray(Booking itemList) {
		return new Object[] {
				itemList.getBookNo(),
				itemList.getGuestNo().getGuestNo(),
				no++,
				itemList.getBookDayStr() + " " + itemList.getBookTimeStr(),
				itemList.getGuestNo().getGuestName(),
				itemList.getGuestNo().getPhone(),
				itemList.getHairNo().getHairName(),
				itemList.getBookNote()
			};
	}

	@Override
	void setWidthAndAlign() {
		TableColumnModel tcm = getColumnModel();
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
		tcm.getColumn(5).setCellRenderer(dtcr);
		tcm.getColumn(6).setCellRenderer(dtcr);
		
		tableSetWidth(0, 0, 50, 180, 80, 120, 90, 180);
		
		tcm.getColumn(0).setMinWidth(0);
		tcm.getColumn(0).setMaxWidth(0);
		tcm.getColumn(1).setMinWidth(0);
		tcm.getColumn(1).setMaxWidth(0);
	}

}
