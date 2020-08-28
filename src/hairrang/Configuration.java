package hairrang;

import java.awt.Dimension;
import java.awt.Font;

import hairrang.component.CustomFonts;

public class Configuration {

	public static Font GSANS_BOLD_16 = CustomFonts.getGSansBold(16);
	public static Font GSANS_BOLD_20 = CustomFonts.getGSansBold(20);
	public static Font GSANS_BOLD_28 = CustomFonts.getGSansBold(28);
	
	public static Font NANUMSQ_REG_12 = CustomFonts.getNanumSqReg(12);
	public static Font NANUMSQ_REG_14 = CustomFonts.getNanumSqReg(14);
	public static Font NANUMSQ_REG_16 = CustomFonts.getNanumSqReg(16);
	public static Font NANUMSQ_REG_20 = CustomFonts.getNanumSqReg(20);
	public static Font NANUMSQ_REG_28 = CustomFonts.getNanumSqReg(28);
	
	// tfDim : 텍스트필드 사이즈...
	public static Dimension tfDim = new Dimension(140, 28); 
	
	public static String[] menuNames = new String[] {"▶ 고객 등록", "▶ 고객 관리", "▶ 주문", "▶ 주문 내역", "▶ 통계"};
}
