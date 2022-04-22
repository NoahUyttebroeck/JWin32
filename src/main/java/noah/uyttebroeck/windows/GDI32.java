package noah.uyttebroeck.windows;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;

public interface GDI32 extends com.sun.jna.platform.win32.GDI32 {

    GDI32 INSTANCE = Native.load("gdi32", GDI32.class, W32APIOptions.DEFAULT_OPTIONS);

    WinDef.HFONT CreateFontA(
            int    cHeight,
            int    cWidth,
            int    cEscapement,
            int    cOrientation,
            int    cWeight,
            boolean  bItalic,
            boolean  bUnderline,
            boolean  bStrikeOut,
            int  iCharSet,
            int  iOutPrecision,
            int  iClipPrecision,
            int  iQuality,
            int  iPitchAndFamily,
            String pszFaceName
    );
}
