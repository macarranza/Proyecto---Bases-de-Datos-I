import java.sql.SQLException;

/**  * Created by Brayan Fajardo on 19/06/2014.  */
public class Hilo extends Thread {
    Partidos partido;
    int orden;
    int TE;
    String grupo;

    public Hilo(String nombreHilo, int num, int TIM, int TEM) {
        super(nombreHilo);
        grupo=nombreHilo;
        partido = new Partidos(TIM);
        this.orden = num;
        TE = (int) (Math.random() * TEM);
    }

    public void run() {
        try {
            Datos.actualizarBitacora("Inicio Proceso "+grupo, null);
        } catch (SQLException e) {
            System.out.println("Error al iniciar proceso en "+grupo);
        }
        for (int p = 0; p<6 ;p++){
            try {
                partido.generarPartido(orden);
                sleep(TE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            orden++;
        }
        try {
            Datos.actualizarBitacora("Fin Proceso "+grupo, null);
        } catch (SQLException e) {
            System.out.println("Error en proceso en "+grupo);
        }
    }
}