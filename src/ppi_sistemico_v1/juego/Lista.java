package ppi_sistemico_v1.juego;

import ppi_sistemico_v1.bean.Ficha;

/**
 *
 * @author lina
 */
public class Lista {

    private Ficha punta;

    public Lista() {
        punta = null;
    }

    public Ficha getPunta() {
        return punta;
    }

    public void setPunta(Ficha punta) {
        this.punta = punta;
    }

    public Boolean insertarAdelante(Ficha ficha) {
        Boolean res = false;
        try {
            if (ficha != null) {
                Ficha n = new Ficha(ficha.getNum1(), ficha.getNum2());
                if (punta == null) {
                    punta = n;
                } else {
                    n.setLiga(punta);
                    punta = n;
                }
            }
            res = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return res;
        }
    }

    public Boolean insertarFinal(Ficha ficha) {
        Boolean res = false;
        try {
            if (ficha != null) {
                Ficha n = new Ficha(ficha.getNum1(), ficha.getNum2());
                if (punta == null) {
                    punta = n;
                } else {
                    Ficha p = this.punta;
                    while (p.getLiga() != null) {
                        p = p.getLiga();
                    }
                    p.setLiga(n);
                }
            }
            res = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return res;
        }
    }

    
    public Boolean eliminarFicha(Ficha ficha) {
        Boolean res = false;
        try {
            if (ficha != null && punta != null) {
                if (ficha == punta) {
                    if (punta.getLiga() == null) {
                        punta = null;
                    } else {
                        Ficha e = punta;
                        punta = punta.getLiga();
                        e.setLiga(null);
                        e = null;
                    }
                } else {
                    Ficha p = punta, a = null;
                    while (p != null && p != ficha) {
                        a = p;
                        p = p.getLiga();
                    }
                    if (p == null) {
                        res = false;
                    } else {
                        a.setLiga(p.getLiga());
                        p = null;
                        
                        res = true;
                    }
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return res;
        }
    }

    public Boolean eliminarFicha(Integer num1, Integer num2) {
        Boolean res = false;
        try {
            if (num1 != null && num2 != null && punta != null) {
                if (punta.getNum1() == num1 && punta.getNum2() == num2) {
                    punta = null;
                } else {
                    Ficha p = punta, a = null;
                    while (p != null && p.getNum1() != num1 && p.getNum2() != num2) {
                        a = p;
                        p = p.getLiga();
                    }
                    if (p == null) {
                        res = false;
                    } else {
                        a.setLiga(p.getLiga());
                        p = null;
                        res = true;
                    }
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {//El finally sirve uanque haya un error o no siempre retorne el res 
            return res;
        }
    }

    public Ficha obtenerFicha(int num) {
        Ficha p = punta;
        try {
            while (p != null) {
                if (num == p.getNum1() || num == p.getNum2()) {
                    return p;
                }
                p = p.getLiga();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Ficha obtenerFicha(int num1, int num2) {
        Ficha p = punta;
        try {
            while (p != null) {
                if (num1 == p.getNum1() && num2 == p.getNum2()) {
                    return p;
                }
                p = p.getLiga();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Ficha obtenerFichaConNumeroMaximo(int numeroMaximo) {
        Ficha p = punta;
        try {
            int cont = 1;
            while (p != null) {
                if (cont == numeroMaximo) {
                    return p;
                }
                cont++;
                p = p.getLiga();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return null;
    }

    public String mostrar() {
        String r = "";
        Ficha p = punta;
        while (p != null) {
            r = r + "[" + p.getNum1() + ":" + p.getNum2() + "]";
            //r = r + "[x:x]";
            p = p.getLiga();
        }
        return r;
    }

    public String mostrarJugar() {
        String r = "";
        Ficha p = punta;
        while (p != null) {
            r = r + "" + p.getNum1() + ":" + p.getNum2() + "";
            //r = r + "[x:x]";
            p = p.getLiga();
        }
        return r;
    }

    public String mostrarMask() {
        String r = "";
        Ficha p = punta;
        while (p != null) {
            //r = r + "[" + p.getNum1() + ":" + p.getNum2() + "]";
            r = r + "[x:x]";
            p = p.getLiga();
        }
        return r;
    }

    public int obtenernumeroDerecha() {
        if (punta != null) {
            Ficha p = punta;
            while (p.getLiga() != null) {
                p = p.getLiga();
            }
            return (p.getNum2());
        }
        return 0;
    }

    public int obtenernumeroIzquierda() {
        if (punta != null) {
            return (punta.getNum1());
        }
        return 0;
    }

    public Ficha fichaMayor() {
        Ficha p = punta;
        Integer may = 0;
        Ficha igual = null, aux = null;
        try {
            while (p != null) {
                if (p.getNum1() == p.getNum2()) {
                    igual = p;
                    if (igual.getNum1() + igual.getNum2() > may) {
                        aux = igual;
                        may = igual.getNum1() + igual.getNum2();
                    }
                }
                p = p.getLiga();
            }
            return aux;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Integer fichaMayorNumero() {
        Ficha p = punta;
        Integer may = 0;
        Ficha igual = null, aux = null;
        try {
            while (p != null) {
                igual = p;
                if (igual.getNum1() + igual.getNum2() > may) {
                    aux = igual;
                    may = igual.getNum1() + igual.getNum2();
                }
                p = p.getLiga();
            }
            return may;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Integer fichaMenor() {
        Ficha p = punta;
        Integer men = 0;
        try {
            while (p != null) {
                men += p.getNum1() + p.getNum2();
                p = p.getLiga();
            }
            return men;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void inspeccionar() {
    }
}
