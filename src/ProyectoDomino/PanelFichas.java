/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoDomino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelFichas extends JFrame implements ActionListener {

    JButton b1, b2;
    Ficha x, y;
    Image image;// IMAGEN QUE SERA MOSTRADA COMO FONDO

    public PanelFichas() {

        setLayout(null);
        image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Imagenes\\ficha1-1.png"));

        System.out.println();
        x = new Ficha(1, 1, image);
        y = new Ficha(2, 3, image);
        add(x);
        add(y);
        x.move(40, 60);

        y.move(80, 100);
        resize(500, 600);
        b1 = new JButton("mover");
        add(b1);
        b1.reshape(100, 300, 300, 30);
        b1.addActionListener(this);
        b2 = new JButton("detener");
        add(b2);
        b2.reshape(100, 350, 300, 30);
        b2.addActionListener(this);
        show();

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
}
