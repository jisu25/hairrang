package hairrang.table;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import hairrang.Configuration;
import hairrang.dto.Sales;


@SuppressWarnings({ "serial", "hiding" })
public abstract class AbstractItemTable<T> extends JTable {
    private CustomModel model;

	public AbstractItemTable() {
    	initComponents();
    }
    
    protected void initComponents() {
    	getTableHeader().setPreferredSize(new Dimension(700, 30));
    	setRowHeight(26);
    	getTableHeader().setReorderingAllowed(false);
    	getTableHeader().setResizingAllowed(false);
    	
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }


    void loadData(ArrayList<T> itemList) {
    	//System.out.println("loadData : " + itemList);
        model = new CustomModel(getRows(itemList), getColName());
        setModel(model);
        model.fireTableDataChanged();
    }

    abstract Object[] getColName();

    Object[][] getRows(ArrayList<T> list) {
    	try {
	    	if (list == null) {
				return new Object[1][];
			}
    	}
    	catch (NullPointerException e) {
    		System.out.println("list Null이어서 빈칸 메우기");
    	}
	    	
        Object[][] rows = new Object[list.size()][];
        for(int i=0; i<rows.length; i++) {
            rows[i] = toArray(list.get(i));
        }
        return rows;
    }

    abstract Object[] toArray(T itemList);

    
    public void setItems(ArrayList<T> list) {
        loadData(list);
        setWidthAndAlign();
    } 

    /**
     * //column width<br>
        tableSetWidth(150, 200, 100, 100, 100, 100, 100); <br>
        //column alignment<br>
        tableCellAign(SwingConstants.CENTER, 0, 1);<br>
        tableCellAign(SwingConstants.RIGHT, 2, 3, 4, 5, 6);<br>
     */
    abstract void setWidthAndAlign();
    
    void tableCellAlign(int align, int...idx) {
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(align);
        
        TableColumnModel tcm = getColumnModel();
        for(int i=0; i<idx.length; i++) {
            tcm.getColumn(idx[i]).setCellRenderer(dtcr);
        }
    }
    
    void tableSetWidth(int...width) {
        TableColumnModel tcm = getColumnModel();
        for(int i=0; i<width.length; i++) {
            tcm.getColumn(i).setPreferredWidth(width[i]);
        }
    }
    
    private class CustomModel extends DefaultTableModel{

        public CustomModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;//각 셀 수정 불가능하게
        }
    }

    public void addRow(T item) {
        model.addRow(toArray(item));
    }

    public void removeRow(int idx) {
        model.removeRow(idx);
    }

    public void updateRow(int idx, T updateItem) {
        model.removeRow(idx);
        model.insertRow(idx, toArray(updateItem));
    }


}




