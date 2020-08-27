package hairrang.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import hairrang.dto.Sales;

@SuppressWarnings("serial")
public class GuestOrderInfoTable extends JTable {
	private List<Sales> salesList;
	private DefaultTableModel model;

	public GuestOrderInfoTable() {
		initComponents();
	}

	private void initComponents() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public void setItems(List<Sales> sales) {

		model = new DefaultTableModel(getRows(salesList), getColNames());
		setModel(model);

		//TableColumnModel tcm = getColumnModel();
		//DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		//dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		//tcm.getColumn(0).setCellRenderer(dtcr);
		//tcm.getColumn(1).setCellRenderer(dtcr);
		//tcm.getColumn(2).setCellRenderer(dtcr);
		//tcm.getColumn(3).setCellRenderer(dtcr);
		//tcm.getColumn(4).setCellRenderer(dtcr);
		//tcm.getColumn(5).setCellRenderer(dtcr);
		
		tableSetWidth(50,100,120,100,80,100);
	}
	
	private void tableSetWidth(int...width) {
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
		return new String[] { "고객번호","주문 일자", "주문명", "단가", "이벤트명", "금액" };
	}

	private Object[] toArray(Sales sales) {
		return new Object[] { 
				sales.getSalesNo(),
				sales.getSalesDay(),
				sales.getHairNo().getHairName(),
				sales.getHairNo().getPrice(),
				sales.getEventNo().getEventName(),
				sales.getHairNo().getPrice() * sales.getEventNo().getSale()
				
		};
	}

	

}