package ELLE_GUI;

public class TradesView {

	private static String columnNames;

	public TradesView() {

	}

	public static String setDefaultView() {
		columnNames = "defaultView_trades";
		return columnNames;
	}

	public static String viewAll() {
		columnNames = "*";
		return columnNames;
	}

	public static String setDefViewOfPositions() {
		String str;
		str = "defaultView_positions";
		return str;
        }
        
        public static String setDefViewOfIB_8949() {
		String str;
		str = "defaultView_IB_8949";
		return str;    
        }
        
        public static String setDefViewOfTL_8949() {
		String str;
		str = "defaultView_TL_8949";
		return str;
	}

}
