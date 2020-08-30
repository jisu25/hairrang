package hairrang.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import hairrang.dto.Hair;
import hairrang.service.HairService;

@SuppressWarnings("serial")
public class HairItemTable extends AbstractItemTable<Hair> {
	private HairService hService = new HairService();
	private List<Hair> hairList = hService.getHairList();
	private int count = 0;
	
	public void setCount(int count) {
		this.count = count;
	}

	public HairItemTable() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	public void setItems(ArrayList<Hair> list) {
		super.setItems(list);
		hairList = list;
		
	}

	@Override
	Object[] toArray(Hair itemList) {
		System.out.println("toArray 시작" +"+"+itemList);
		return new Object[] { ++count, itemList.getHairName(), itemList.getPrice() };
	}
	@Override
	Object[] getColName() {
		// TODO Auto-generated method stub
		return new String[] {"번호","상품명","가격"};
	}

	

	@Override
	void setWidthAndAlign() {
		TableColumnModel tcm = getColumnModel();
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
	}
	@Override
	public void addRow(Hair item) {
		super.addRow(item);
		hairList.add(item);
	} 
	@Override
	public void removeRow(int idx) {
		super.removeRow(idx);
		hairList.remove(idx);
	}
//	public void removeAll() {
//		for(int i=0; i<hairList.size(); i++ ) {
//			
//		}
//	}
	@Override
	public void updateRow(int idx, Hair updateItem) {
		super.updateRow(idx, updateItem);
		hairList.remove(idx);
		hairList.add(idx, updateItem);
	}

	public List<Hair> getHairList() {
		return hairList;
	}

	public Hair getSelectedRow(int selectIndex) {
		return hairList.get(selectIndex);
		
		
	}
	
	
	

}
