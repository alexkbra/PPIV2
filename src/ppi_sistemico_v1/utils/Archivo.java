
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi_sistemico_v1.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
            PrintStream abrirArhivo = new PrintStream(archivo);
            FileOutputStream archivoJson = new FileOutputStream(new File(".").getAbsolutePath() + "/Datos/juego.json");
            PrintStream abrirArhivoJson = new PrintStream(archivoJson);
            if (juego != null) {
                String json = "{\"Vector\":{";
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
                    html += " <table  border=\"2\"> <tr> <td> <div id='Vec" + i + "'><h1>" + getNombre(i) + "</h1></div></td>";
                    json += "  \"Vec" + i + "\":[ ";
                    while (ficha != null) {
                        Ficha f = ficha;
                        html += "    <td><table border=\"2\"><tr><td>";
                        html += "    <span class=\"" + f.getNum1() + "\" >" + f.getNum1() + "</span></td>";
                        html += "    <td><span class=\"" + f.getNum2() + "\">" + f.getNum2() + "</span></td>";
                        html += "    <tr></table></td>";
                        json += "{\"Num1\":\"" + f.getNum1() + "\",\"Num2\":\"" + f.getNum2() + "\"},";
                        ficha = ficha.getLiga();
                    }
                    json = json.substring(0, json.length() - 1);
                    json += "],";
                    html += "</tr></table><br/>";
                }
                json = json.substring(0, json.length() - 1);
                json += "}}";
                html += "</body>";
                html += "</html>";
                abrirArhivoJson.print(json);
                abrirArhivo.print(html);
            } else {
                return false;
            }
            OpenWin.muestraURL(url, "mozilla");
            abrirArhivoJson.close();
            abrirArhivo.close();
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
                valor = fr.read();
            }
            fr.close();
            construir(res, jframe);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return res;
    }

    public void construir(String contentText, vista1JFrame frame) {
        try {
            JsonParser parser = new JsonParser();
            JsonElement datos = parser.parse(contentText);
            JsonObject jsonObject = datos.getAsJsonObject();
            JsonElement vec = jsonObject.get("Vector");
            Lista[] listas = new Lista[5];
            for (int i = 0; i < listas.length; i++) {
                listas[i] = new Lista();
            }
            for (int i = 0; i < listas.length; i++) {
                JsonObject vector = vec.getAsJsonObject();
                JsonElement vecNumber = vector.get("Vec" + i);
                JsonArray fichas = vecNumber.getAsJsonArray();
                for (int j = 0; j < fichas.size(); j++) {
                    JsonElement fichaE = fichas.get(j);
                    JsonObject ficha = fichaE.getAsJsonObject();
                    listas[i].insertarFinal(new Ficha(Integer.parseInt(ficha.get("Num1").getAsString()), Integer.parseInt(ficha.get("Num2").getAsString())));
                }
            }
            Juego j = frame.getJuego();
            if (j == null) {
                j = (new Juego());
            }
            j.setVec(listas);
            frame.setJuego(j);
            frame.pintar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
    }

    public String OpenSelectFile(vista1JFrame jframe) {
        String res = "";
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File(new File(".").getAbsolutePath() + "/Datos/juego.json"));
        int respuesta = fc.showOpenDialog(jframe);
        if (respuesta == JFileChooser.APPROVE_OPTION) {
            File archivoElegido = fc.getSelectedFile();
            res = archivoElegido.getPath();
        }
        return res;
    }
}
