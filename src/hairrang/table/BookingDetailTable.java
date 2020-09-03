package hairrang.table;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import hairrang.dto.Booking;

public class BookingDetailTable extends AbstractItemTable<Booking> {
	
	private int cnt = 1;
	
	public BookingDetailTable() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	Object[] getColName() {
		cnt = 1;
		return new String[] {"", "", "No", "예약시간", "고객명", "연락처", "헤어명", "비고"};
	}


	@Override
	Object[] toArray(Booking itemList) {
		return new Object[] {
			itemList.getBookNo(),
			itemList.getGuestNo().getGuestNo(),
			cnt++,
			itemList.getBookDayStr() + " " +itemList.getBookTimeStr(),
			itemList.getGuestNo().getGuestName(),
			itemList.getGuestNo().getPhone(),
			itemList.getHairNo().getHairName(),
			itemList.getBookNote()
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
		tcm.getColumn(5).setCellRenderer(dtcr);
		tcm.getColumn(6).setCellRenderer(dtcr);
		tcm.getColumn(7).setCellRenderer(dtcr);
		
		tableSetWidth(0, 0, 40, 180, 80, 120, 100, 180);
		
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
