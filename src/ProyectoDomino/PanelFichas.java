/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoDomino;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import ppi_sistemico_v1.juego.Juego;
import ppi_sistemico_v1.juego.Lista;

public class PanelFichas extends JFrame implements ActionListener {

    JButton b1, b2;
    Ficha x, y;
    Image image;// IMAGEN QUE SERA MOSTRADA COMO FONDO
    Juego j = null;

    public PanelFichas(Juego j) {
        setLayout(null);
        this.j = j;
        init();
        show();
    }

    public PanelFichas() {
        setLayout(null);
        init();
        show();
    }

    public void init() {
        System.out.println();
        resize(700, 500);
        if (j == null) {
            image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Imagenes\\ficha1-1.png"));
            x = new Ficha(1, 1, image);
            y = new Ficha(2, 3, image);
            add(x);
            add(y);
            x.move(40, 60);
            y.move(80, 100);
            b1 = new JButton("mover");
            add(b1);
            b1.reshape(100, 300, 300, 30);
            b1.addActionListener(this);
            b2 = new JButton("detener");
            add(b2);
            b2.reshape(100, 350, 300, 30);
            b2.addActionListener(this);
        } else {
            pintarJuego();
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            x.start(1, 3);
            y.start(2, -3);
        } else {
            x.stop();
            y.stop();
        }
    }

    private void pintarJuego() {
        Lista[] vec = j.getVec();
        int x = 40, y = 60;
        for (int i = 0; i < vec.length; i++) {
            ppi_sistemico_v1.bean.Ficha ficha = vec[i].getPunta();
            while (ficha != null) {
                Image imageFicha = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Imagenes\\" + ficha.getNum1() + "" + ficha.getNum2() + ".png"));
                Ficha viewFicha = new Ficha(ficha.getNum1(), ficha.getNum2(), imageFicha);
                add(viewFicha);
                viewFicha.move(x, y);
                x += 50;
                ficha = ficha.getLiga();
            }
            x = 40;
            y += 60;
        }

    }

}
