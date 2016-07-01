<<<<<<< HEAD
package root;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class TableWithRowHeader  extends JTable{
	  
	private static final long serialVersionUID = 1L;
	
	protected JTable mainTable;

    public TableWithRowHeader(JTable table){
        super();
        mainTable = table;
        setModel(new RowNumberTableModel());
        setPreferredScrollableViewportSize(getPreferredSize());
        getColumnModel().getColumn(0).setPreferredWidth(50);

        getColumnModel().getColumn(0).setCellRenderer( mainTable.getTableHeader().getDefaultRenderer() );
    }

    public int getRowHeight(int row){
        return mainTable.getRowHeight();
    }

    class RowNumberTableModel extends AbstractTableModel
    {
        public int getRowCount(){
             return mainTable.getModel().getRowCount();
        }

        public int getColumnCount(){       
        	return 1;
        }

        public Object getValueAt(int row, int column){
            return new Integer(row + 1);
        }
    }
=======
package root;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class TableWithRowHeader  extends JTable{
	  
    protected JTable mainTable;

    public TableWithRowHeader(JTable table){
        super();
        mainTable = table;
        setModel(new RowNumberTableModel());
        setPreferredScrollableViewportSize(getPreferredSize());
        getColumnModel().getColumn(0).setPreferredWidth(50);

        getColumnModel().getColumn(0).setCellRenderer( mainTable.getTableHeader().getDefaultRenderer() );
    }

    public int getRowHeight(int row){
        return mainTable.getRowHeight();
    }

    class RowNumberTableModel extends AbstractTableModel
    {
        public int getRowCount(){
             return mainTable.getModel().getRowCount();
        }

        public int getColumnCount(){       
        	return 1;
        }

        public Object getValueAt(int row, int column){
            return new Integer(row + 1);
        }
    }
>>>>>>> 98345c69f42d188badeefb5425e1a25dff76cf4c
}