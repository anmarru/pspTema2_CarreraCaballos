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
       
        Caballo caballo2=new Caballo("2");
        caballo2.getTerminadoProperty().addListener(this);
        progresoCaballo2.progressProperty().bind(caballo2.getValorProperty());
        tc2 = new Thread(caballo2);
        
        Caballo caballo3=new Caballo("3");
        caballo3.getTerminadoProperty().addListener(this);
        progresoCaballo3.progressProperty().bind(caballo3.getValorProperty());
        tc3=new Thread(caballo3);
        
        Caballo caballo4=new Caballo("4");
        caballo4.getTerminadoProperty().addListener(this);
        progresoCaballo4.progressProperty().bind(caballo4.getValorProperty());
        tc4=new Thread(caballo4);

        tc3.start();
        tc2.start();
        tc1.start();
        tc4.start();

        
    }
    @Override
    public void changed(ObservableValue<? extends Object>  observable, Object oldValue, Object newValue) {

            if(!carreraTerminada){
                carreraTerminada=true;
            }

                tc1.interrupt();
                tc2.interrupt();
                tc3.interrupt();
                tc4.interrupt();
            
                String nombreCaballoGanador="";
                
                if (progresoCaballo1.getProgress() == 1.0) {
                    nombreCaballoGanador = "Caballo 1";
                } else if (progresoCaballo2.getProgress() == 1.0) {
                    nombreCaballoGanador = "Caballo 2";
                } else if (progresoCaballo3.getProgress() == 1.0) {
                    nombreCaballoGanador = "Caballo 3";
                } else if (progresoCaballo4.getProgress() == 1.0) {
                    nombreCaballoGanador = "Caballo 4";
                }

                String caballoGanador= nombreCaballoGanador;

            Platform.runLater(() -> {
                mensajes.setText("ganador : "+ caballoGanador);
            });
            
            iniciarButton.setDisable(false);

        
    }
    
}
