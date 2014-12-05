/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi_sistemico_v1.utils;

import ppi_sistemico_v1.bean.Ficha;

/**
 *
 * @author ronal_000
 */
public class CRUD {
    public static Ficha asignarFicha(Ficha f,int n){
        Ficha p = f;
        int cont = 0;
        while(p !=null && p.getLiga() != null && cont < n){
            p = p.getLiga();
            cont++;
        }
        return p;
    }
    public static int contarFichas(Ficha f){
        int totalFichas = 0;
        Ficha n = f;
        while (n != null) {
            totalFichas++;
            n = n.getLiga();
        }
       return totalFichas;
    }
}
