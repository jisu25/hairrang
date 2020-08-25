package hairrang.table;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import hairrang.dto.Guest;
import hairrang.service.GuestService;

public class GuestSearchResultTable extends JTable{
	private GuestService gService;
	private ArrayList<Guest> guest;
	private CustomModel model;
	
	public GuestSearchResultTable() {

	}

	public GuestSearchResultTable(ArrayList<Guest> items) {
		setItems(items);
		initComponents();
	}

	public void setItems(ArrayList<Guest> guest) {
		this.guest = guest;
		loadData();

	}
	
	private void initComponents() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	
	private void loadData() {
		model = new CustomModel(getRows(), getColNames());
		setModel(model);

		tableSetWidth(150, 150, 100, 100, 100, 100, 100);

		tableCellAlign(SwingConstants.CENTER, 0, 1);
		tableCellAlign(SwingConstants.RIGHT, 2, 3, 4, 5, 6);

	}

	protected void tableSetWidth(int... width) {
		TableColumnModel cModel = getColumnModel();
		for (int i = 0; i < width.length; i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	protected void tableCellAlign(int align, int... idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);

		TableColumnModel cModel = getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			cModel.getColumn(idx[i]).setCellRenderer(dtcr);
		}

	}

	private Object[][] getRows() {
		Object[][] rows = new Object[guest.size()][];
		for (int i = 0; i < guest.size(); i++) {
			rows[i] = toArray(guest.get(i));
		}
		return rows;
	}

	private Object[] getColNames() {
		return new String[] {"고객번호","되나", "생년월일", "가입일자", "전화번호", "성별", "메모"};
	}

	private Object[] toArray(Guest itemList) {
		return new Object[] {
				itemList.getGuestNo(), 
				itemList.getGuestName(), 
				new SimpleDateFormat("yyyy-MM-dd").format(itemList.getBirthday()), 
				new SimpleDateFormat("yyyy-MM-dd").format(itemList.getJoinDay()), 
				itemList.getPhone(), 
				itemList.getGender()==1?"여":"남", 
				itemList.getGuestNote()
			
		};
	}
	
	private class CustomModel extends DefaultTableModel {

		public CustomModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

	}

}
