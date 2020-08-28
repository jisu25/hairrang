package hairrang.component;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import hairrang.HairshopManagementProgram;

public class CustomFonts {
	
	private static final String FONT_PATH = System.getProperty("user.dir") + File.separator + "fonts" + File.separator;
	
	private static Font gSansBold;
	private static Font nanumSqReg;
	private static Font nanumSqBold;
	
	static {
//		InputStream is = HairshopManagementProgram.class.getResourceAsStream(FONT_PATH + "GmarketSansTTFBold.ttf");
		File gBoldFile = new File(FONT_PATH + "GmarketSansTTFBold.ttf");
		File nRegFile = new File(FONT_PATH + "NanumSquareR.ttf");
		File nBoldFile = new File(FONT_PATH + "NanumSquareB.ttf");
		
		try {
			gSansBold = Font.createFont(Font.TRUETYPE_FONT, gBoldFile);
			nanumSqReg = Font.createFont(Font.TRUETYPE_FONT, nRegFile);
			nanumSqBold = Font.createFont(Font.TRUETYPE_FONT, nBoldFile);
		} catch (FontFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Font getGSansBold(float size) {
		return gSansBold.deriveFont(size);
	}
	
	public static Font getNanumSqReg(float size) {
		return nanumSqReg.deriveFont(size);
	}
	
	public static Font getNanumSqBold(float size) {
		return nanumSqBold.deriveFont(size);
	}
	
}
