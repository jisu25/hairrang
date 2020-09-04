package hairrang;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Configuration {

	public static Font GSANS_BOLD_16 = CustomFonts.getGSansBold(16);
	public static Font GSANS_BOLD_20 = CustomFonts.getGSansBold(20);
	public static Font GSANS_BOLD_28 = CustomFonts.getGSansBold(28);
	
	public static Font NANUMSQ_REG_12 = CustomFonts.getNanumSqReg(12);
	public static Font NANUMSQ_REG_14 = CustomFonts.getNanumSqReg(14);
	public static Font NANUMSQ_REG_16 = CustomFonts.getNanumSqReg(16);
	public static Font NANUMSQ_REG_20 = CustomFonts.getNanumSqReg(20);
	public static Font NANUMSQ_REG_28 = CustomFonts.getNanumSqReg(28);
	
	public static Font NANUMSQ_BOLD_16 = CustomFonts.getNanumSqBold(16);
	public static Font NANUMSQ_BOLD_20 = CustomFonts.getNanumSqBold(20);
	public static Font NANUMSQ_BOLD_28 = CustomFonts.getNanumSqBold(28);
	
	public static Font NANUMSQ_EB_14 = CustomFonts.getNanumSqEB(14);
	public static Font NANUMSQ_EB_16 = CustomFonts.getNanumSqEB(16);
	public static Font NANUMSQ_EB_20 = CustomFonts.getNanumSqEB(20);
	public static Font NANUMSQ_EB_28 = CustomFonts.getNanumSqEB(28);
	
	
	/* Color */
	public static Color COLOR_MAIN = new Color(153, 102, 255);
	public static Color COLOR_TRANSPARENT = new Color(0, 0, 0, 0);
	
	public static Color COLOR_LIGHTGRAY = new Color(245, 245, 245);
	public static Color COLOR_GRAY_LINE = new Color(225, 225, 225);
	public static Color COLOR_DEEPGRAY_TEXT = new Color(60, 60, 60);
	public static Color COLOR_BTN = new Color(182, 146, 254);
	
	
	
	// 텍스트 사이즈...
	public static Dimension DIM_TF = new Dimension(120, 28); 
	
	// 버튼 사이즈
	public static Dimension DIM_BTN = new Dimension(100, 32); 
	public static Dimension DIM_BTNMINI = new Dimension(24, 24);
	
	public static String[] MENU_NAMES = new String[] {"▶ 고객 관리", "▶ 주문", "▶ 주문 내역", "▶ 통계", "▶ 예약 내역"};
}
