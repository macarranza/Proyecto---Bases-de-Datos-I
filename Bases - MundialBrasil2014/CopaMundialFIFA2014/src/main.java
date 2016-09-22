import java.sql.SQLException;

/**  * Created by Brayan Fajardo on 19/06/2014.  */
public class main {
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
        try {
            Datos.actualizarBitacora("Fin Simulación", null);
        } catch (SQLException e) {
            System.out.println("Error durante la simulación");
        }
    }
}