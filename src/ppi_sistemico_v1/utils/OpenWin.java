/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi_sistemico_v1.utils;

/**
 *
 * @author SAMSUNG
 */
public class OpenWin {

    public static void muestraURL(String url, String nombreNavegador) {
        final String WIN_ID = "Windows";
        // The default system browser under windows.
        final String WIN_PATH = "rundll32";
        // The flag to display a url.
        final String WIN_FLAG = "url.dll,FileProtocolHandler";
        // The default browser under unix.
        final String UNIX_PATH_DEFECTO = "mozilla";
        // The flag to display a url
        final String UNIX_FLAG = "-remote openURL";

        boolean windows = isWindowsPlatform();
        String cmd = null;
        try {
            if (windows) {
                // cmd = 'rundll32 url.dll,FileProtocolHandler http://...'
                cmd = WIN_PATH + " " + WIN_FLAG + " " + url;
                Process p = Runtime.getRuntime().exec(cmd);
            } else {
                if (nombreNavegador != null && nombreNavegador.length() > 0) {
                    cmd = nombreNavegador + " " + UNIX_FLAG + "(" + url + ")";
                } else {
                    cmd = UNIX_PATH_DEFECTO + " " + UNIX_FLAG + "(" + url + ")";
                }
                Process p = Runtime.getRuntime().exec(cmd);
                try {
                    int exitCode = p.waitFor();
                    if (exitCode != 0) {
                        if (nombreNavegador != null
                                && nombreNavegador.length() > 0) {
                            cmd = nombreNavegador + " " + url;
                        } else {
                            cmd = UNIX_PATH_DEFECTO + " " + url;
                        }
                        p = Runtime.getRuntime().exec(cmd);
                    }
                } catch (InterruptedException x) {
                    System.err.println("Error abriendo pagina, cmd='"
                            + cmd + "'");
                    System.err.println("ERROR: " + x);
                }
            }
        } catch (java.io.IOException x) {
            // couldn't exec browser
            System.err.println("No puedo invocar al navegador, command=" + cmd);
            System.err.println("ERROR:: " + x);
        }
    }

    private static boolean isWindowsPlatform() {
        final String WIN_ID = "Windows";
        String os = System.getProperty("os.name");
        if (os != null && os.startsWith(WIN_ID)) {
            return true;
        } else {
            return false;
        }
    }
}
