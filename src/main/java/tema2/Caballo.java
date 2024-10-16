package tema2;

import java.util.concurrent.ThreadLocalRandom;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Caballo implements Runnable {

    private String nombre;

    private SimpleDoubleProperty valor;
    private SimpleBooleanProperty terminado;

    

    public Caballo(String nombre) {
        this.nombre = nombre;
        valor= new SimpleDoubleProperty(0);
        terminado =new SimpleBooleanProperty(false);
    }



    @Override
    public void run() {
        double porcentaje=0;
        int numAleatorio;

        try {
            
            while (!Thread.currentThread().interrupted() && porcentaje < 1.0) {
                numAleatorio = ThreadLocalRandom.current().nextInt(0, 14);
                System.out.println("Caballo " + nombre + " ha aumentado en " + numAleatorio);
                porcentaje += (numAleatorio/100.0);
                System.out.println(porcentaje);
        		//pcs.firePropertyChange("porcentaje", porcentaje-1, porcentaje);
                valor.set(porcentaje);
                
            
                //if(valor.get() >=1)
                if(porcentaje >=1.0){
                    terminado.set(true);
                    break;
                }
                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 500) );

            }
        } catch (InterruptedException ex) {
            System.out.println("Hilo interrumpido");
        }
    
    }



    public Double getValor() {
        return valor.get();
    }

    public SimpleDoubleProperty getValorProperty(){
        return valor;
    }

    public SimpleBooleanProperty getTerminadoProperty(){
        return terminado;
    }



    public String getNombre() {
        return nombre;
    }
    
}
