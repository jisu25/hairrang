package hairrang.component;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import hairrang.HairshopManagementProgram;

public class CustomFonts {
	
	private static final String FONT_PATH = System.getProperty("user.dir") + File.separator + "fonts" + File.separator;
	
	private Font gSansBold;
	private Font nanumSqReg;
	
	public CustomFonts() {
//		InputStream is = HairshopManagementProgram.class.getResourceAsStream(FONT_PATH + "GmarketSansTTFBold.ttf");
		File gBoldFile = new File(FONT_PATH + "GmarketSansTTFBold.ttf");
		File nRegFile = new File(FONT_PATH + "NanumSquareR.ttf");
		
		try {
			gSansBold = Font.createFont(Font.TRUETYPE_FONT, gBoldFile);
			nanumSqReg = Font.createFont(Font.TRUETYPE_FONT, nRegFile);
		} catch (FontFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Font getGSansBold16() {
		return gSansBold.deriveFont(16f);
	}
	
	public Font getGSansBold20() {
		return gSansBold.deriveFont(20f);
	}
	
	public Font getGSansBold28() {
		return gSansBold.deriveFont(28f);
	}
	
	public Font getNanumSqReg16() {
		return nanumSqReg.deriveFont(16f);
	}
	
}
