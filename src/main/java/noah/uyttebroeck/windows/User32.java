package noah.uyttebroeck.windows;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.win32.W32APIOptions;

import java.util.Arrays;
import java.util.List;

public interface User32 extends com.sun.jna.platform.win32.User32 {
    /** The instance. */
    User32 INSTANCE = Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

    boolean SetWindowText(HWND handle, String text);


    class PAINTSTRUCT extends Structure {
        public HDC hdc;
        public boolean fErase;
        public RECT rcPaint;
        public boolean fRestore;
        public boolean fIncUpdate;
        public byte rgbReserved[] = new byte[32];

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("hdc", "fErase", "rcPaint", "fRestore", "fIncUpdate", "rgbReserved");
        }
    }

    HDC BeginPaint(HWND hWnd, PAINTSTRUCT lpPaint);
    boolean EndPaint(HWND hWnd, PAINTSTRUCT lpPaint);
    HCURSOR LoadCursor(HINSTANCE hInstance, int lpCursorName);
    int FillRect(HDC hdc, RECT rect, HBRUSH hbr);
    boolean SystemParametersInfo(int uiAction, int uiParam, Pointer pvParam, int fWinIni);

    boolean IsDialogMessageA(
        HWND  hDlg,
        MSG lpMsg
    );

    HANDLE SelectObject(
         HDC    hdc,
         HANDLE h
    );

    int FW_DONTCARE = 0;
    int OUT_OUTLINE_PRECIS = 8;
    int CLIP_DEFAULT_PRECIS = 0;
    int CLEARTYPE_QUALITY = 5;
    int VARIABLE_PITCH = 2;
    int DEFAULT_CHARSET = 1;
    int WM_LBUTTONDOWN = 0x0201;
    int WS_EX_LEFT = 0x00000000;
    int WS_EX_TOPMOST = 8;
    int WS_SYSMENU = 0x00080000;
    int CW_USEDEFAULT = 0x80000000;
    int WM_ACTIVATE = 0x0006;
    int WM_MOVE = 0x0003;
    int CS_VREDRAW = 0x0001;
    int CS_HREDRAW = 0x0002;
    int CS_OWNDC = 0x0020;
    int COLOR_WINDOW = 5;
    int WM_SETFONT = 0x0030;
    int WM_SETTEXT = 0x000C;
    int SPI_GETNONCLIENTMETRICS = 0x0029;
    int WM_COMMAND = 0x0111;
    int BN_CLICKED = 0;
    int BS_SPLITBUTTON = 0x0000000C;
    int WS_EX_APPWINDOW = 0x00040000;
    int WM_NOTIFY = 0x004E;

    int WM_PARENTNOTIFY = 0x0210;

    class LOGFONT extends Structure {
        public long lfHeight;
        public long lfWidth;
        public long lfEscapement;
        public long lfOrientation;
        public long lfWeight;
        public byte lfItalic;
        public byte lfUnderline;
        public byte lfStrikeOut;
        public byte lfCharSet;
        public byte lfOutPrecision;
        public byte lfClipPrecision;
        public byte lfQuality;
        public byte lfPitchAndFamily;
        public String lfFaceName;

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList(
                    "lfHeight",
                    "lfWidth",
                    "lfEscapement",
                    "lfOrientation",
                    "lfWeight",
                    "lfItalic",
                    "lfUnderline",
                    "lfStrikeOut",
                    "lfCharSet",
                    "lfOutPrecision",
                    "lfClipPrecision",
                    "lfQuality",
                    "lfPitchAndFamily",
                    "lfFaceName");
        }
    }

    class NONCLIENTMETRICS extends Structure {
        public int cbSize;
        public int iBorderWidth;
        public int iScrollWidth;
        public int iScrollHeight;
        public int iCaptionWidth;
        public int iCaptionHeight;
        public LOGFONT lfCaptionFont;
        public int iSmCaptionWidth;
        public int iSmCaptionHeight;
        public LOGFONT lfSmCaptionFont;
        public int iMenuWidth;
        public int iMenuHeight;
        public LOGFONT lfMenuFont;
        public LOGFONT lfStatusFont;
        public LOGFONT lfMessageFont;
        public int iPaddedBorderWidth;

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList(
                    "cbSize",
                    "iBorderWidth",
                    "iScrollWidth",
                    "iScrollHeight",
                    "iCaptionWidth",
                    "iCaptionHeight",
                    "lfCaptionFont",
                    "iSmCaptionWidth",
                    "iSmCaptionHeight",
                    "lfSmCaptionFont",
                    "iMenuWidth",
                    "iMenuHeight",
                    "lfMenuFont",
                    "lfStatusFont",
                    "lfMessageFont",
                    "iPaddedBorderWidth");
        }
    }
}