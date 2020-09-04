package hairrang.table;

import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import hairrang.dto.Guest;
import hairrang.dto.Sales;
import hairrang.service.GuestService;
import hairrang.service.SalesService;

@SuppressWarnings("serial")
public class GuestOrderInfoTable extends AbstractItemTable<Sales> {
	private DefaultTableModel model;
	private GuestService gService = new GuestService();
	private SalesService sService = new SalesService();


	@Override
	Object[] getColName() {
		return new String[] { "영업번호", "주문 일자", "고객명", "주문명", "단가", "이벤트명", "금액" };
	}

	@Override
	Object[] toArray(Sales itemList) {
		return new Object[] {
			itemList.getSalesNo(), itemList.getSalesDay(), itemList.getGuestNo().getGuestName(), itemList.getHairNo().getHairName(), itemList.getHairNo().getPrice(),
			itemList.getEventNo().getEventName(),
			// 할인율적용된 단가 셋하기
			// Math.round(sum)
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
		tcm.getColumn(6).setCellRenderer(dtcr);

		tableSetWidth(70, 100, 70, 100, 100, 80, 100);		
	}


}
