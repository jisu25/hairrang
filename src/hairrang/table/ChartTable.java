package hairrang.table;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import hairrang.dto.Sales;

public class ChartTable extends AbstractItemTable<Sales> {

	@Override
	Object[] getColName() {
		return new String[] {"영업번호", "영업일자", "고객명", "헤어명", "이벤트명", "금액"};
	}

	@Override
	Object[] toArray(Sales itemList) {
		return new Object[] {
			itemList.getSalesNo(),
			itemList.getSalesDay(),
			itemList.getGuestNo().getGuestName(),
			itemList.getHairNo().getHairName(),
			itemList.getEventNo().getEventName(),
			itemList.getTotalPrice()
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
		
		tableSetWidth(40, 120, 70, 100, 100, 80);
		
	}

}
