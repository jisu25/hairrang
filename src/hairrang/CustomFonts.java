package hairrang;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomFonts {
	
	private static final String FONT_PATH = System.getProperty("user.dir") + File.separator + "fonts" + File.separator;
	
	private static Font gSansBold;
	private static Font nanumSqReg;
	private static Font nanumSqBold;
	private static Font nanumSqEB;
	
	static {
//		InputStream is = HairshopManagementProgram.class.getResourceAsStream(FONT_PATH + "GmarketSansTTFBold.ttf");
		File gBoldFile = new File(FONT_PATH + "GmarketSansTTFBold.ttf");
		File nRegFile = new File(FONT_PATH + "NanumSquareR.ttf");
		File nBoldFile = new File(FONT_PATH + "NanumSquareB.ttf");
		File nEBFile = new File(FONT_PATH + "NanumSquareEB.ttf");
		
		try {
			gSansBold = Font.createFont(Font.TRUETYPE_FONT, gBoldFile);
			nanumSqReg = Font.createFont(Font.TRUETYPE_FONT, nRegFile);
			nanumSqBold = Font.createFont(Font.TRUETYPE_FONT, nBoldFile);
			nanumSqEB = Font.createFont(Font.TRUETYPE_FONT, nEBFile);
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
	
	public static Font getNanumSqEB(float size) {
		return nanumSqEB.deriveFont(size);
	}
	
}
