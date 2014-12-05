
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi_sistemico_v1.utils;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import ppi_sistemico_v1.bean.Ficha;
import ppi_sistemico_v1.juego.Juego;
import ppi_sistemico_v1.juego.Lista;
import ppi_sistemico_v1.vistas.vista1JFrame;

/**
 *
 * @author SAMSUNG
 */
public class Archivo {

    Lista p;

    public boolean escribir_archivo(Juego juego, String url) {
        try {

            FileOutputStream archivo = new FileOutputStream(url);
            try (PrintStream abrirArhivo = new PrintStream(archivo)) {
                if (juego != null) {
                    String html = "<DOCTYPE html!>";
                    html += "<html>";
                    html += "<head>";
                    html += "   <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />";
                    html += "   <title>Domino</title>";
                    html += "</head>";
                    html += "</head>";
                    html += "<body>";
                    html += "    <div><h1>ListaSistemico</h1></div>";

                    Lista[] p = juego.getVec();
                    for (int i = 0; i < p.length; i++) {
                        Ficha ficha = p[i].getPunta();
                        html += " <table  border=\"2\"> <tr> <td> <div id='Vec" + i + "'><h1>|" + getNombre(i) + "|</h1></div></td>";
                        while (ficha != null) {
                            Ficha f = ficha;
                            html += "    <td><table border=\"2\"><tr><td>";
                            html += "    <span class=\"" + f.getNum1() + "\" >|" + f.getNum1() + "|</span></td>";
                            html += "    <td><span class=\"" + f.getNum2() + "\">|" + f.getNum2() + "|</span></td>";
                            html += "    <tr></table></td>";
                            ficha = ficha.getLiga();
                        }
                        html += "</tr></table><br/>";
                    }

                    html += "</body>";
                    html += "</html>";
                    abrirArhivo.print(html);
                } else {
                    return false;
                }
            }
            OpenWin.muestraURL(url, "mozilla");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getNombre(int opt) {
        String name = "";
        switch (opt) {
            case 0:
                name = "Todas las Fichas";
                break;
            case 1:
                name = "Restantes";
                break;
            case 2:
                name = "Computador";
                break;
            case 3:
                name = "Juegos";
                break;
            case 4:
                name = "Juego";
                break;
                

        }
        return name;
    }

    public String leerArchivo(vista1JFrame jframe) {
        String res = "";
        try {
            FileReader fr = new FileReader(OpenSelectFile(jframe));
            int valor = fr.read();

            while (valor != -1) {
                char c = (char) (valor);
                res += c + "";

            }
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public String obtenerPalabra(FileReader fr) {
        String res = "";
        try {
            int valor = fr.read();
            valor = fr.read();
            while ((valor != -1)) {
                res = (char) (valor) + "";
                if (("\'").equals((char) (valor) + "")) {
                    break;
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public String OpenSelectFile(vista1JFrame jframe) {
        String res = "";
        JFileChooser fc = new JFileChooser();
//        fc.setSelectedFile(jframe.get);
        int respuesta = fc.showOpenDialog(jframe);
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            File archivoElegido = fc.getSelectedFile();
            res = archivoElegido.getPath();
        }
        return res;
    }
}
