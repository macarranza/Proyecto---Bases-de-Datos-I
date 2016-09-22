/**  * Created by Brayan Fajardo on 22/06/2014.  */
import java.sql.*;

public class Datos {
    private Conexion conexion;
    private String tuplas;      //Guarda la consulta de la base de datos como string
    private ResultSet rs;       //Variable resultado de la conexion con la base de datos
    private Statement s;        //Variable que guarda la consulta de la base de datos
    private String tabla;

    public Datos() {
        conexion = new Conexion("Copamundialfifa2014");
        //conexion.conectarBaseDatos();
        tuplas = "";
        rs = null;
        s = null;
        tabla = "";
    }

    public void setTuplas() {
        if (tabla.equals("equipos")) {
            try {
                while (rs.next()) {
                    tuplas += rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5) + "," + rs.getString(6) + "," + rs.getString(7) + "," + rs.getString(8) + "," + rs.getString(9) + "," + rs.getString(10) + "," + rs.getString(11) + "/n";
                }

                //System.out.println("Tuplas obtenidas con éxito");
            } catch (SQLException e) {
                //System.out.println("No se han podido obtener las tuplas de la tabla " + this.tabla);
            }
        }

        if (tabla.equals("calendariopartidos")) {
            try {
                while (rs.next()) {
                    tuplas += rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5) + "," + rs.getString(6) + "," + rs.getString(7) + "/n";
                    //System.out.println("Tuplas obtenidas con éxito");
                }
            } catch (SQLException e) {
                System.out.println("No se han podido obtener las tuplas de la tabla " + this.tabla);
            }
        }
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public void setS(Statement s) {
        this.s = s;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getTuplas() {
        return tuplas;
    }

    public ResultSet getRs() {
        return rs;
    }

    public Statement getS() {
        return s;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public String getTabla() {
        return tabla;
    }

    public void consultarDatos(String tabla) {
        conexion.conectarBaseDatos();
        try {
            s = conexion.getConn().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.tabla = tabla;
            rs = s.executeQuery("select * from " + tabla + " ;");
            setTuplas();
        } catch (SQLException e) {
            System.out.println("No se ha podido realizar la consulta en la tabla " + getTabla());
        }

    }

    public void actualizarDatos(String tabla, String columnaGol, String columnaEquipo1, int valor1, String valor2, String columnaEquipo2, String valor3) {
        this.tabla = tabla;
        PreparedStatement registro;
        try {
            registro = conexion.getConn().prepareStatement("UPDATE " + this.tabla + " set " + columnaGol + " = ? where " + columnaEquipo1 + " = ? AND "
                    + columnaEquipo2 + " = ? ;");
            registro.setInt(1, valor1);
            registro.setString(2, valor2);
            registro.setString(3, valor3);
            registro.executeUpdate();
            //System.out.println("Se ha realizado la actualización de la tabla  " + tabla + "con éxito");
        } catch (SQLException e) {
            System.out.println("No se ha podido actualizar la tabla" + this.tabla);
        }
    }

    public void actualizarDatos(String tabla, String columnaEquipo, String columnaPartidosJugados, String columnaPartidosGanados,
                                String columnaPartidosEmpatados, String columnaPartidosPerdidos, String columnaGolesAFavor,
                                String columnaGolesEnContra, String columnaGolesDiferencia, String columnaPuntos, String valorEquipo,
                                int valorPartidosJugados, int valorPartidosGanados, int valorPartidosEmpatados, int valorPartidosPerdidos,
                                int valorGolesAFavor, int valorGolesEnContra, int valorGolesDiferencia, int valorPuntos) {
        this.tabla = tabla;
        PreparedStatement registro;
        try {
            registro = conexion.getConn().prepareStatement("UPDATE " + this.tabla + " set " + columnaPartidosJugados + " = ?, " +
                    columnaPartidosGanados + " = ?, " + columnaPartidosEmpatados + " = ?, " + columnaPartidosPerdidos + " = ?, " +
                    columnaGolesAFavor + " = ?, " + columnaGolesEnContra + " = ?, " +
                    columnaGolesDiferencia + " = ?, " + columnaPuntos + " = ? " + "where " + columnaEquipo + " =  ? ;");
            registro.setInt(1, valorPartidosJugados);
            registro.setInt(2, valorPartidosGanados);
            registro.setInt(3, valorPartidosEmpatados);
            registro.setInt(4, valorPartidosPerdidos);
            registro.setInt(5, valorGolesAFavor);
            registro.setInt(6, valorGolesEnContra);
            registro.setInt(7, valorGolesDiferencia);
            registro.setInt(8, valorPuntos);
            registro.setString(9, valorEquipo);
            registro.executeUpdate();

            //System.out.println("Se ha realizado la actualización de la tabla  " + tabla + "con éxito");
        } catch (SQLException E) {
            System.out.println("No se ha podido actualizar la tabla" + this.tabla);
        }
    }

    public static void actualizarBitacora(String tipoEvento, String resultado) throws SQLException {
        Conexion con = new Conexion("Copamundialfifa2014");
        con.conectarBaseDatos();
        Connection c = con.getConn();
        Statement s = con.getConn().createStatement();
        Statement cmd = null;
        /*int id = 1;
        ResultSet resultSet = s.executeQuery("select max(id) from bitácora;");
        while (resultSet.next()) {
            id = resultSet.getInt(1);
            id++;
        }*/
        String stmt = "insert into bitácora values ('" + tipoEvento + "' ,(select current_timestamp), '" + resultado + "');";
        try {
            cmd = c.createStatement();
            cmd.executeUpdate(stmt);
            System.out.println("ACTUALIZACION DE LA BITACORA EXITOSA!");
        } catch (Exception ex) {
            System.out.println("ACTUALIZACION DE LA BITACORA FALLIDA!");
        }
    }
}
