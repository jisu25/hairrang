package hairrang.table;

import java.util.Date;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import hairrang.dto.Booking;

public class BookingTable extends AbstractItemTable<Booking> {
	
	public BookingTable() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	Object[] getColName() {
		return new String[] {"", "", "예약시간", "고객명", "헤어명"};
	}


	@Override
	Object[] toArray(Booking itemList) {
		return new Object[] {
			itemList.getBookNo(),
			itemList.getBookDay(),
			itemList.getBookTimeStr(),
			itemList.getGuestNo().getGuestName(),
			itemList.getHairNo().getHairName(),
//			itemList.getBookNote()
		};
	}
	
	// 총 너비 218px
	
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
		
		tableSetWidth(0, 0, 90, 60, 78);
		
		tcm.getColumn(0).setMinWidth(0);
		tcm.getColumn(0).setMaxWidth(0);
		tcm.getColumn(1).setMinWidth(0);
		tcm.getColumn(1).setMaxWidth(0);
		
	}
//
//	public int getSelBookNo() {
//		int selIdx = getSelectedRow();
//		int bookNo = (int)getValueAt(selIdx, 0);
//		
//		return bookNo;
//	}

}
