package hairrang.chart;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public interface ChartDaoo {

	Vector<HairrangDto> selectHairrangDtoByAll();

	Vector<HairrangDto> selectHairrangDtoByYear(int startYear, int endYear);

	Vector<HairrangDto> selectHairrangDtoByMonth(int year);

	List<Integer> totalPrice(int startYear, int endYear);

	////////////////////////////////////// [static 메소드]
	////////////////////////////////////// /////////////////////////////////////////////

	// 테이블 행 모두 지우기 (화면단에서만)
	public static void clearRows(int rowSize, DefaultTableModel dtm) {
		if (rowSize > 0) {
			for (int i = rowSize - 1; i >= 0; i--) {
				dtm.removeRow(i);
			}
		}
	}

}
