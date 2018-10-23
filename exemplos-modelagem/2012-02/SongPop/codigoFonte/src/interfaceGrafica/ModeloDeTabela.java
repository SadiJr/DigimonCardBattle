package interfaceGrafica;

import javax.swing.table.AbstractTableModel;

public class ModeloDeTabela extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	protected String[] cabecalho;
	protected String[][] dados;

	public ModeloDeTabela() 
	{
		this.cabecalho = new String[0];
		this.dados = new String[0][0];
	}

	public int getRowCount() 
	{
		return dados.length;
	}

	public int getColumnCount() 
	{
		return cabecalho.length;
	}

	public Object getValueAt(int row, int col) 
	{
		Object x = null;
		if (row<dados.length && col<cabecalho.length)
			x = dados[row][col];
		return x;
	}

	public String getColumnName(int col) 
	{
		String s = null;
		if (col<cabecalho.length)
			s = cabecalho[col];
		return s;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
		
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {	 		
		dados[rowIndex][columnIndex] = (String)aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public void setDados (String[][] dados)
	{
		this.dados = dados;
		fireTableDataChanged();
	}
	
	public void setCabecalho (String[] cabecalho)
	{
		this.cabecalho = cabecalho;
		fireTableStructureChanged();
	}
}