package tema2;

import java.io.IOException;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class CarreraController implements ChangeListener<Object>{

    @FXML
    ProgressBar progresoCaballo1;
    @FXML
    ProgressBar progresoCaballo2;
    @FXML
    ProgressBar progresoCaballo3;
    @FXML
    ProgressBar progresoCaballo4;

    @FXML
    Label mensajes;

    @FXML
    Button iniciarButton;


    Thread tc1;
    Thread tc2;
    Thread tc3;
    Thread tc4;
    
    boolean carreraTerminada=false;

    @FXML
    private void iniciarCarrera() throws IOException {
        // Enlazada... y como probamos quien ha ganado?
        // Y si en vez de enlarlo, observamos su valor
        iniciarButton.setDisable(true);

        carreraTerminada=false;

        Caballo caballo=new Caballo("1");
        caballo.getTerminadoProperty().addListener(this);
        progresoCaballo1.progressProperty().bind(caballo.getValorProperty());
        tc1=new Thread(caballo);
        tc1.start();

    
        Caballo caballo2=new Caballo("2");
        caballo2.getTerminadoProperty().addListener(this);
        progresoCaballo2.progressProperty().bind(caballo2.getValorProperty());
        tc2 = new Thread(caballo2);
        tc2.start();

        
        Caballo caballo3=new Caballo("3");
        caballo3.getTerminadoProperty().addListener(this);
        progresoCaballo3.progressProperty().bind(caballo3.getValorProperty());
        tc3=new Thread(caballo3);
        tc3.start();

        
        Caballo caballo4=new Caballo("4");
        caballo4.getTerminadoProperty().addListener(this);
        progresoCaballo4.progressProperty().bind(caballo4.getValorProperty());
        tc4=new Thread(caballo4);
        tc4.start();
        
    }
    @Override
    public void changed(ObservableValue<? extends Object>  observable, Object oldValue, Object newValue) {

        //if(!carreraTerminada){
            //carreraTerminada = true;

            //que caballo termina
            //Caballo ganador= (Caballo)((ObservableValue<?>) observable).getValue();
            //mensajes.setText("Caballo "+ ganador.getNombre()+" ha ganado ");

            //if(tc1 !=null && tc1.isAlive()){
                tc1.interrupt();
            //}
            //if(tc2 !=null && tc2.isAlive()){
                tc2.interrupt();
           // }
            //if(tc3 !=null && tc3.isAlive()){
                tc3.interrupt();
           // }
            //if(tc4 !=null && tc4.isAlive()){
                tc4.interrupt();
           // }

            iniciarButton.setDisable(false);

            Platform.runLater(() -> {
                mensajes.setText("Final");
            });
            
       // }
        
    }
    
}
