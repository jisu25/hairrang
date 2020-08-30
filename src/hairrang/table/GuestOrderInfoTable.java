package hairrang.table;

import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.hamcrest.core.SubstringMatcher;

import hairrang.dto.Guest;
import hairrang.dto.Sales;
import hairrang.service.GuestService;
import hairrang.service.SalesService;

@SuppressWarnings("serial")
public class GuestOrderInfoTable extends JTable {
	private DefaultTableModel model;
	private GuestService gService = new GuestService();
	private List<Guest> guestList = gService.getGuestList();
	private SalesService sService = new SalesService();
	private List<Sales> salesList = sService.selectSalesByAll();

	public GuestOrderInfoTable() {
		initComponents();
	}

	private void initComponents() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public void setItems(List<Sales> sales) {

		model = new DefaultTableModel(getRows(sales), getColNames());
		setModel(model);

		TableColumnModel tcm = getColumnModel();
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
		tcm.getColumn(5).setCellRenderer(dtcr);

		tableSetWidth(60, 100, 120, 100, 80, 100);
	}

	private void tableSetWidth(int... width) {
		TableColumnModel cModel = getColumnModel();
		for (int i = 0; i < width.length; i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}

	}

	private Object[][] getRows(List<Sales> sales) {

		Object[][] rows = new Object[sales.size()][];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = toArray(sales.get(i));
		}

		return rows;
	}

	private Object[] getColNames() {
		return new String[] { "영업번호", "주문 일자", "주문명", "단가", "이벤트명", "금액" };
	}

	private Object[] toArray(Sales sales) {
		// double sum = sales.getHairNo().getPrice() * (1 -
		// sales.getEventNo().getSale());
		return new Object[] {

				sales.getSalesNo(), sales.getSalesDay(), sales.getHairNo().getHairName(), sales.getHairNo().getPrice(),
				sales.getEventNo().getEventName(),
				// 할인율적용된 단가 셋하기
				// Math.round(sum)
				sales.getTotalPrice()

		};
	}

}
