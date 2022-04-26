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

    //TODO place these constants in own class or enum
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
    int SS_CENTER = 0x00000001;

    int TA_RIGHT = 2;
    int TA_CENTER = 6;
    int TA_BOTTOM = 8;
    int TA_BASELINE = 24;

    int DT_TOP                      = 0x00000000;
    int DT_LEFT                     = 0x00000000;
    int DT_CENTER                   = 0x00000001;
    int DT_RIGHT                    = 0x00000002;
    int DT_VCENTER                  = 0x00000004;
    int DT_BOTTOM                   = 0x00000008;
    int DT_WORDBREAK                = 0x00000010;
    int DT_SINGLELINE               = 0x00000020;
    int DT_EXPANDTABS               = 0x00000040;
    int DT_TABSTOP                  = 0x00000080;
    int DT_NOCLIP                   = 0x00000100;
    int DT_EXTERNALLEADING          = 0x00000200;
    int DT_CALCRECT                 = 0x00000400;
    int DT_NOPREFIX                 = 0x00000800;
    int DT_INTERNAL                 = 0x00001000;
    int DT_EDITCONTROL              = 0x00002000;
    int DT_PATH_ELLIPSIS            = 0x00004000;
    int DT_END_ELLIPSIS             = 0x00008000;
    int DT_MODIFYSTRING             = 0x00010000;
    int DT_RTLREADING               = 0x00020000;
    int DT_WORD_ELLIPSIS            = 0x00040000;
    int DT_NOFULLWIDTHCHARBREAK     = 0x00080000;
    int DT_HIDEPREFIX               = 0x00100000;
    int DT_PREFIXONLY               = 0x00200000;
    int SS_CENTERIMAGE = 0x00000200;
    String WC_LISTVIEW = "SysListView32";
    int LVS_REPORT = 0x0001;
    int LVS_LIST = 0x0003;
    int LVS_TYPEMASK = 0x0003;
    int LVS_SINGLESEL = 0x0004;
    int LVS_AUTOARRANGE = 0x0100;
    int LVS_EDITLABELS = 0x0200;
    int LVS_NOSCROLL = 0x2000;
    int LVS_NOCOLUMNHEADER = 0x4000;

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

    HDC GetWindowDC(
        HWND hWnd
    );

    int DrawTextExA(
            HDC hdc,
            String lpchText,
            int cchText,
            RECT lprc,
            int format,
            Object  lpdtp
    );

    default int DrawText(HDC hdc, String lpchText, RECT lprc, int format) {
        return DrawTextExA(hdc, lpchText, lpchText.length(), lprc, format, null);
    }
}