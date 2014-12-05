/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppi_sistemico_v1.juego;

import ppi_sistemico_v1.bean.Ficha;

/**
 *
 * @author ronal_000
 */
public class Juego {

    private Integer NF, Turnos;
    private Lista[] Vec;

    public Juego(Integer NF, Integer Turno) {
        this.NF = NF > 14 ? 0 : NF;   //Esto es un condicional de asignacion.
        this.Turnos = Turno;
        this.Vec = new Lista[5];
        for (int i = 0; i < 5; i++) {
            Vec[i] = new Lista();
        }
    }

    public Juego() {
    }

    public Integer getNF() {
        return NF;
    }

    public void setNF(Integer NF) {
        this.NF = NF;
    }

    public Integer getTurnos() {
        return Turnos;
    }

    public void setTurnos(Integer Turnos) {
        this.Turnos = Turnos;
    }

    public Lista[] getVec() {
        return Vec;
    }

    public Lista getinfovec(int pos) {
        return Vec[pos];
    }

    public void setVec(Lista[] Vec) {
        this.Vec = Vec;
    }

    public void crearFichas() {
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                Ficha x = new Ficha(i, j);
                Vec[0].insertarAdelante(x);

            }
        }
        System.out.println(Vec[0].mostrar());
    }

    public void repartirJuego() {
        Integer numeroFichasPorRepartir = 28;
        for (int i = 0; i < this.NF; i++) {
            Integer numeroDeFichaDelJugador = (int) (1 + Math.random() * numeroFichasPorRepartir);
            Ficha fichaJugador = Vec[0].obtenerFichaConNumeroMaximo(numeroDeFichaDelJugador);
            Vec[3].insertarAdelante(fichaJugador);
            Vec[0].eliminarFicha(fichaJugador);
            numeroFichasPorRepartir = numeroFichasPorRepartir - 1;
            Integer numeroDeFichaDelComputador = (int) (1 + Math.random() * numeroFichasPorRepartir);
            Ficha fichaComputador = Vec[0].obtenerFichaConNumeroMaximo(numeroDeFichaDelComputador);
            Vec[2].insertarAdelante(fichaComputador);
            Vec[0].eliminarFicha(fichaComputador);
            numeroFichasPorRepartir = numeroFichasPorRepartir - 1;
        }
        Vec[1] = Vec[0];
        System.out.println("Sobrantes");
        System.out.println(Vec[1].mostrar());
        System.out.println("Computador");
        System.out.println(Vec[2].mostrar());
        System.out.println("Jugador");
        System.out.println(Vec[3].mostrar());

    }

    public String jugarComputador() {
        String res = "";
        if (Vec[4].getPunta() != null) {
            Integer numeroDerechaDelJuego = Vec[4].obtenernumeroDerecha();
            Integer numeroIzquierdoDelJuego = Vec[4].obtenernumeroIzquierda();
            Ficha fichaDerecha = Vec[2].obtenerFicha(numeroDerechaDelJuego);
            if (fichaDerecha != null) {
                if (fichaDerecha.getNum1() == numeroDerechaDelJuego) {
                    Vec[4].insertarFinal(fichaDerecha);
                    Vec[2].eliminarFicha(fichaDerecha);
                } else {
                    Integer temp = fichaDerecha.getNum1();
                    fichaDerecha.setNum1(fichaDerecha.getNum2());
                    fichaDerecha.setNum2(temp);
                    Vec[4].insertarFinal(fichaDerecha);
                    Vec[2].eliminarFicha(fichaDerecha);
                }
                if (Vec[2].getPunta() == null) {
                    res = "Ganó Computador";
                }
            } else {
                Ficha fichaIzquierda = Vec[2].obtenerFicha(numeroIzquierdoDelJuego);
                if (fichaIzquierda != null) {
                    if (fichaIzquierda.getNum2() == numeroIzquierdoDelJuego) {
                        Vec[4].insertarAdelante(fichaIzquierda);
                        Vec[2].eliminarFicha(fichaIzquierda);
                    } else {
                        Integer temp = fichaDerecha.getNum2();
                        fichaDerecha.setNum2(fichaDerecha.getNum1());
                        fichaDerecha.setNum1(temp);
                        Vec[4].insertarFinal(fichaIzquierda);
                        Vec[2].eliminarFicha(fichaIzquierda);
                    }
                    if (Vec[2].getPunta() == null) {
                        res = "Ganó Computador";
                    }
                } else {
                    res += "Paso";
                }
            }
        }
        return res;
    }

    public String jugarJugador(int num1, int num2, String dir) {
        String res = "";
        if (Vec[4].getPunta() != null) {
            Ficha fichaJugador = Vec[3].obtenerFicha(num1, num2);
            if (fichaJugador != null) {
                if (dir.equals("I")) {
                    int numeroIzquierda = Vec[4].obtenernumeroIzquierda();
                    if (fichaJugador.getNum2() == numeroIzquierda) {
                        Vec[4].insertarAdelante(fichaJugador);
                        Vec[3].eliminarFicha(fichaJugador);
                    } else {
                        Integer temp = fichaJugador.getNum1();
                        fichaJugador.setNum1(fichaJugador.getNum2());
                        fichaJugador.setNum2(temp);
                        Vec[4].insertarAdelante(fichaJugador);
                        Vec[3].eliminarFicha(fichaJugador);
                    }
                    if (Vec[3].getPunta() == null) {
                        res = "Gane!!";
                    }
                } else {
                    if (dir.equals("D")) {
                        int numeDerecho = Vec[4].obtenernumeroDerecha();
                        if (fichaJugador.getNum1() == numeDerecho) {
                            Vec[4].insertarFinal(fichaJugador);
                            Vec[3].eliminarFicha(fichaJugador);
                        } else {
                            Integer temp = fichaJugador.getNum1();
                            fichaJugador.setNum1(fichaJugador.getNum2());
                            fichaJugador.setNum2(temp);
                            Vec[4].insertarFinal(fichaJugador);
                            Vec[3].eliminarFicha(fichaJugador);
                        }
                        if (Vec[3].getPunta() == null) {
                            res = "Gane!!";
                        }
                    } else {
                        res += "No Existe Dirección, Debe ser I ó D";
                    }
                }
            } else {
                res += "La Ficha No Existe";
            }
        } else {
            Ficha fichaJugador = Vec[3].obtenerFicha(num1, num2);
            if (fichaJugador != null) {
                Vec[4].insertarFinal(fichaJugador);
            } else {
                res += "La Ficha No Existe";
            }
        }
        return res;
    }

    public String repartirTurno() {
        String res = "";
        Ficha fichaMayorJugador = Vec[3].fichaMayor();
        Ficha fichaMayorCompador = Vec[2].fichaMayor();
        if (fichaMayorCompador == null) {
            //El jugador
            if (fichaMayorJugador != null) {
                res = "Turno del Jugador";
            } else {
                Integer numeroMayorComputador = Vec[2].fichaMayorNumero();
                Integer numeroMayorJuegador = Vec[3].fichaMayorNumero();
                if (numeroMayorComputador > numeroMayorJuegador) {
                    Vec[4].insertarFinal(fichaMayorCompador);
                    Vec[2].eliminarFicha(fichaMayorCompador);
                } else {
                    res = "Turno del Jugador";
                }
            }
        } else {
            //Computador
            if (fichaMayorJugador == null) {
                Vec[4].insertarFinal(fichaMayorCompador);
                Vec[2].eliminarFicha(fichaMayorCompador);
            } else {
                Integer numeroMayorComputador = Vec[2].fichaMayorNumero();
                Integer numeroMayorJuegador = Vec[3].fichaMayorNumero();
                if (numeroMayorComputador > numeroMayorJuegador) {
                    Vec[4].insertarFinal(fichaMayorCompador);
                    Vec[2].eliminarFicha(fichaMayorCompador);
                } else {
                    res = "Turno del Jugador";
                }
            }

        }
        return res;
    }

    public String inspeccionar() {
        String res = "";
        Integer numeroIzquerdo = Vec[4].obtenernumeroIzquierda();
        Integer numeroDerecho = Vec[4].obtenernumeroDerecha();
        Integer numeroDeJugador = Vec[3].fichaMenor();
        Integer numeroDeComputador = Vec[2].fichaMenor();
        Ficha fichaDelJugador = Vec[3].obtenerFicha(numeroDerecho);
        Ficha fichaDelComputador = Vec[2].obtenerFicha(numeroDerecho);
        if (fichaDelJugador == null) {
            fichaDelJugador = Vec[3].obtenerFicha(numeroIzquerdo);
        }
        if (fichaDelComputador == null) {
            fichaDelComputador = Vec[2].obtenerFicha(numeroIzquerdo);
        }
        if (fichaDelComputador == null && fichaDelJugador == null) {
            if (numeroDeJugador < numeroDeComputador) {
                res = "Gane!!";
            } else {
                res = "Ganá computador";
            }
        }
        return res;
    }

    public void insertarFicha(Ficha ficha) {
        for (int i = 0; i < Vec.length; i++) {
            Vec[i].insertarAdelante(ficha);
        }
    }
}
