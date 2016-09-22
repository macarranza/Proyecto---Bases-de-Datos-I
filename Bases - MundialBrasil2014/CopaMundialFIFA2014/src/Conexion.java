/**  * Created by Brayan Fajardo on 19/06/2014.  */

import java.sql.*; // Se importa la librería
    public class Conexion {
        private Connection conn;
        private String nombreBaseDatos;

        public Conexion(String nombreBaseDatos) {
            conn  = null;
            this.nombreBaseDatos = nombreBaseDatos;
        }

        public void setConn(Connection conn) {
            this.conn = conn;
        }

        public void setNombreBaseDatos(String nombreBaseDatos) {
            this.nombreBaseDatos = nombreBaseDatos;
        }

        public Connection getConn() {
            return conn;
        }

        public String getNombreBaseDatos() {
            return nombreBaseDatos;
        }

        public void conectarBaseDatos() {    //Método que permite conectar a la base de datos
            String driver = "org.postgresql.Driver";//Nombre del primer postgres
            String connectString = "jdbc:postgresql://localhost:5432/" + nombreBaseDatos; // Se llama a la base de datos
            String user = "postgres";               //Se usa el usuario definido en la base de datos
            String password = "postgres";      //Guarda el password de la base de datos
            // Se inicializa la conexion
            try {
                try {
                    Class.forName(driver);          //Se invoca al driver que conecta a la base de datos
                } catch (ClassNotFoundException e) {
                    System.out.println("Error a la hora de utilizar el driver de postgres");
                }             //Se conecta a la base de datos

                conn = DriverManager.getConnection(connectString, user, password);             //Muestra que la conexion se ha realizado con éxito
                //System.out.println("Conexion a la base de datos " + nombreBaseDatos + " realizada con exito! ");
            }         //Se returna el siguiente error si no se logra conectar con la base de datos
            catch (SQLException e) {
                System.out.println("Se ha producido un error en la conexion con la base de datos");
            }
        }

        public void cerrarConexion(){
            try {
                conn.close();
                //System.out.println("Se ha cerrado la conexion con la base de datos");
            } catch (SQLException e) {
                System.out.println("No se ha podido cerrar la conexion con la base de datos");
            }
        }
    }