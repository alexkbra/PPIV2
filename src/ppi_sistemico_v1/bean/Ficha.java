
package ppi_sistemico_v1.bean;

public class Ficha {
    
    private int Num1;
    private int Num2;
    private Ficha Liga ;
 
    public Ficha(int N1, int N2){
        
        this.Num1=N1;
        this.Num2=N2;
        this.Liga=null;
        
    }
   
    public int getNum1() {
        return Num1;
    }

    public int getNum2() {
        return Num2;
    }

    public Ficha getLiga() {
        return Liga;
    }

    public void setNum1(int Num1) {
        this.Num1 = Num1;
    }

    public void setNum2(int Num2) {
        this.Num2 = Num2;
    }

    public void setLiga(Ficha Liga) {
        this.Liga = Liga;
    }
    
    
    
    
            
    
}
