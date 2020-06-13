
package run;

import modelo.dao.DaoNotas;
import modelo.dao.impl.DaoNotasImpl;


public class TestNotas {
    
    
    public static void main(String[] args) {
        
        DaoNotas daoNotas = new DaoNotasImpl();
       String pc = "PC1";
       double nota= 10;
        
        String res=daoNotas.insertarNotasDatos("5", "9", pc, nota);
        System.out.println(res);
    }
}
