<<<<<<< HEAD
package root;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

class RowHeaderRenderer extends JLabel implements ListCellRenderer {

	private static final long serialVersionUID = 1L;

	RowHeaderRenderer(JTable table) {
	    JTableHeader header = table.getTableHeader();
	    setOpaque(true);
	    setBorder(UIManager.getBorder("TableHeader.cellBorder"));
	    setHorizontalAlignment(CENTER);
	    setForeground(header.getForeground());
	    setBackground(header.getBackground());
	    setFont(header.getFont());
	  }
	 
	  public Component getListCellRendererComponent( JList list,
	         Object value, int index, boolean isSelected, boolean cellHasFocus) {
	    setText((value == null) ? "" : value.toString());
	    return this;
	  }
	}
=======
package root;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

class RowHeaderRenderer extends JLabel implements ListCellRenderer {
	 
	  RowHeaderRenderer(JTable table) {
	    JTableHeader header = table.getTableHeader();
	    setOpaque(true);
	    setBorder(UIManager.getBorder("TableHeader.cellBorder"));
	    setHorizontalAlignment(CENTER);
	    setForeground(header.getForeground());
	    setBackground(header.getBackground());
	    setFont(header.getFont());
	  }
	 
	  public Component getListCellRendererComponent( JList list,
	         Object value, int index, boolean isSelected, boolean cellHasFocus) {
	    setText((value == null) ? "" : value.toString());
	    return this;
	  }
	}
>>>>>>> 98345c69f42d188badeefb5425e1a25dff76cf4c
