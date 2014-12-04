
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
                    html += "   <title>Domino</title>";
                    html += "</head>";
                    html += "</head>";
                    html += "<body>";
                    html += "    <div><h1>ListaSistemico</h1></div>";

                    Lista[] p = juego.getVec();
                    for (int i = 0; i < p.length; i++) {
                        Ficha ficha = p[i].getPunta();
                        html += "    <div id='Vec[" + i + "]'><h1>VEC[" + i + "]</h1></div>";
                        while (ficha != null) {
                            Ficha f = ficha;
                            html += "    <h2 id='" + f.getNum1() + "'>Numero <h1>1</h1><span>" + f.getNum1() + "</span></h2>";
                            html += "    <h2 id='" + f.getNum2() + "'>Numero <h1>2</h1><span>" + f.getNum2() + "</span></h2>";
                            ficha = ficha.getLiga();
                        }
                    }

                    html += "</body>";
                    html += "</html>";
                    abrirArhivo.print(html);
                }else{
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

    public String leerArchivo(vista1JFrame jframe) {
        String res = "";
        try {
            FileReader fr = new FileReader(OpenSelectFile(jframe));
            int valor = fr.read();

            while (valor != -1) {
                char c = (char) (valor);
                res += c + "";
                String tag = "";
                int tagValor = 0, i = 0;
                int num1 = 0, num2 = 0;
                while (tagValor != -1 && i < 2) {
                    tagValor = fr.read();
                    tag += (char) (tagValor) + "";
                    tagValor = fr.read();
                    tag += (char) (tagValor) + "";
                    if (tag.equals("id")) {
                        break;
                    } else {
                        tag = "";
                    }
                }
                Ficha nuevaFicha = new Ficha(Integer.parseInt(obtenerPalabra(fr)), Integer.parseInt(obtenerPalabra(fr)));
                valor = fr.read();
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
