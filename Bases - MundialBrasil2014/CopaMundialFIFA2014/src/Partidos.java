import java.sql.SQLException;

import static java.lang.Thread.sleep;

/**  * Created by Brayan Fajardo on 22/06/2014.  */
public class Partidos {
    int WaitInterno;
    String resultado;
    Datos datosPartido;
    Datos datosEquipos;
    String id;
    int orden;
    Equipo equipo1;
    Equipo equipo2;
    String equipo1Nombre;
    String equipo2Nombre;
    String grupo;
    int golesEquipo1;
    int golesEquipo2;
    String tablaCalendarioPartidos;
    String tablaEquipos;

    public Partidos(int TIM){
        WaitInterno = (int) (Math.random() * TIM);
        datosPartido = new Datos();
        datosEquipos = new Datos();
        id = "";
        orden = 0;
        equipo1 = new Equipo();
        equipo2 = new Equipo();
        equipo1Nombre = "";
        equipo2Nombre = "";
        grupo = "";
        resultado = "";
        golesEquipo1 = 0;
        golesEquipo2 = 0;
        tablaCalendarioPartidos = "calendariopartidos";
        tablaEquipos = "equipos";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public void setEquipo1Nombre(String equipo1) {
        this.equipo1Nombre = equipo1;
    }

    public void setEquipo2Nombre(String equipo2) {
        this.equipo2Nombre = equipo2;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public void setGolesEquipo1(int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public void setGolesEquipo2(int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public void setDatosPartido(Datos datosPartido) {
        this.datosPartido = datosPartido;
    }

    public void setDatosEquipos(Datos datosEquipos) {
        this.datosEquipos = datosEquipos;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public String getId() {
        return id;
    }

    public int getOrden() {
        return orden;
    }

    public String getEquipo1Nombre() {
        return equipo1Nombre;
    }

    public String getEquipo2Nombre() {
        return equipo2Nombre;
    }

    public Datos getDatosPartido() {
        return datosPartido;
    }

    public Datos getDatosEquipos() {
        return datosEquipos;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public String getGrupo() {
        return grupo;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public int generarPartido(int numeroPartido) throws InterruptedException {
        try {
            this.id = String.valueOf(numeroPartido);
            getId();
            iniciarTransaccion();
            datosPartido.getConexion().getConn().setAutoCommit(false);
            //System.out.println("Id " + id);
            // PROCESO PARTIDO //////////////////////////////////////////////////////////////////////////////////////////////////////////
            //generarGolRandom();
            generarGolRandom1();
            sleep(WaitInterno*1000);
            generarGolRandom2();
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println("Equipo1: " + equipo1.getNombreEquipo() + " " + getGolesEquipo1());
            datosPartido.actualizarDatos(this.tablaCalendarioPartidos, "golesequipo1", "equipo1", golesEquipo1, equipo1.getNombreEquipo(), "equipo2", equipo2.getNombreEquipo());
            System.out.println("Equipo2: " + equipo2.getNombreEquipo() + " " + getGolesEquipo2());
            datosPartido.actualizarDatos(this.tablaCalendarioPartidos, "golesequipo2", "equipo2", golesEquipo2, equipo2.getNombreEquipo(), "equipo1", equipo1.getNombreEquipo());
            //validarGoles();

            //if (validarGoles()){
                datosPartido.getConexion().getConn().commit();
                //System.out.println("Transacción realizada con éxito");
                actualizarTablaEquipos();
                resultado="";
                resultado+=equipo1.getNombreEquipo()+" - "+equipo2.getNombreEquipo()+": "+getGolesEquipo1()+" - "+getGolesEquipo2();
                datosPartido.actualizarBitacora("Partido Concluido",resultado);
                /*System.out.println("Actualización de la tabla " + tablaEquipos + " con el equipo " + equipo1.getNombreEquipo() + " y " +
                        equipo2.getNombreEquipo() + " realizada con éxito");  */
                datosPartido.getConexion().cerrarConexion();
            //}
            /*else{
                datosPartido.getConexion().getConn().rollback();
                //System.out.println("Se deshace la transacción");
                generarPartido(Integer.valueOf(id));
                resultado="";
                resultado+=equipo1.getNombreEquipo()+" - "+equipo2.getNombreEquipo()+": "+getGolesEquipo1()+" - "+getGolesEquipo2();
                datosPartido.actualizarBitacora("Partido Abortado",resultado);
            }*/
        } catch (SQLException e) {
            //System.out.println("Error a la hora de realizar la transacción");
        }
        return 0;
    }

    public void actualizarTablaEquipos() {
        int PJ1 = equipo1.getPartidosJugados() + 1;
        equipo1.setPartidosJugados(PJ1);
        if (comprobarGane()) {
            int PG1 = equipo1.getPartidosGanados() + 1;
            equipo1.setPartidosGanados(PG1);
            int P1 = equipo1.getPuntos() + 3;
            equipo1.setPuntos(P1);
        }

        if (comprobarEmpate()) {
            int PE1 = equipo1.getPartidosEmpatados() + 1;
            equipo1.setPartidosEmpatados(PE1);
            int P1 = equipo1.getPuntos() + 1;
            equipo1.setPuntos(P1);
        }

        if (comprobarDerrota()) {
            int PP1 = equipo1.getPartidosPerdidos() + 1;
            equipo1.setPartidosPerdidos(PP1);
        }

        int GF1 = equipo1.getGolesAFavor() + golesEquipo1;
        equipo1.setGolesAFavor(GF1);
        int GC1 = equipo1.getGolesEnContra() + golesEquipo2;
        equipo1.setGolesEnContra(GC1);
        int DG1 = equipo1.getDiferenciaGoles() + calcularDiferenciaGolesEquipo1();
        equipo1.setDiferenciaGoles(DG1);
        int PJ2 = equipo2.getPartidosJugados() + 1;
        equipo2.setPartidosJugados(PJ2);

        if (comprobarGane()) {
            int PP2 = equipo2.getPartidosPerdidos() + 1;
            equipo2.setPartidosPerdidos(PP2);
        }

        if (comprobarEmpate()) {
            int PE2 = equipo2.getPartidosEmpatados() + 1;
            equipo2.setPartidosEmpatados(PE2);
            int P2 = equipo2.getPuntos() + 1;
            equipo2.setPuntos(P2);
        }

        if (comprobarDerrota()) {
            int PG2 = equipo2.getPartidosGanados() + 1;
            equipo2.setPartidosGanados(PG2);
            int P2 = equipo2.getPuntos() + 3;
            equipo2.setPuntos(P2);
        }

        int GF2 = equipo2.getGolesAFavor() + golesEquipo2;
        equipo2.setGolesAFavor(GF2);
        int GC2 = equipo2.getGolesEnContra() + golesEquipo1;
        equipo2.setGolesEnContra(GC2);
        int DG2 = equipo2.getDiferenciaGoles() + calcularDiferenciaGolesEquipo2();
        equipo2.setDiferenciaGoles(DG2);
        datosEquipos.actualizarDatos(this.tablaEquipos, "nombre", "partidosJugados", "victorias", "empates", "derrotas", "golesafavor",
                "golesencontra", "diferenciagoles", "puntos", this.equipo1.getNombreEquipo(), this.equipo1.getPartidosJugados(),
                this.equipo1.getPartidosGanados(), this.equipo1.getPartidosEmpatados(),this.equipo1.getPartidosPerdidos(), this.equipo1.getGolesAFavor(), this.equipo1.getGolesEnContra(),
                this.equipo1.getDiferenciaGoles(), this.equipo1.getPuntos());
        datosEquipos.actualizarDatos(this.tablaEquipos, "nombre", "partidosJugados", "victorias", "empates", "derrotas", "golesafavor",
                "golesencontra", "diferenciagoles", "puntos", this.equipo2.getNombreEquipo(), this.equipo2.getPartidosJugados(),
                this.equipo2.getPartidosGanados(), this.equipo2.getPartidosEmpatados(),this.equipo2.getPartidosPerdidos(), this.equipo2.getGolesAFavor(), this.equipo2.getGolesEnContra(),
                this.equipo2.getDiferenciaGoles(), this.equipo2.getPuntos());
    }

    public int calcularDiferenciaGolesEquipo1(){
        return getGolesEquipo1()-getGolesEquipo2();
    }

    public int calcularDiferenciaGolesEquipo2(){
        return getGolesEquipo2()-getGolesEquipo1();
    }

    public boolean comprobarEmpate(){
        return golesEquipo1 == golesEquipo2;
    }

    public boolean comprobarGane(){
        return golesEquipo1 > golesEquipo2;
    }

    public boolean comprobarDerrota(){
        return golesEquipo1 < golesEquipo2;
    }

    public void iniciarTransaccion(){
        consultarDatosTablaPartidos();
        establecerAtributosConsultados();
        asociarPartidosAEquipos();
    }

    public void consultarDatosTablaPartidos(){
        datosPartido.consultarDatos("calendariopartidos");
    }

    public void establecerAtributosConsultados(){
        String [] datosPartidoLista = datosPartido.getTuplas().split("/n");
        //System.out.println("Este es el arraylist de datosPartidoLista");
        //System.out.println(getId());
        for (int i = 0; i < datosPartidoLista.length; i++) {
            //System.out.println(datosPartidoLista[i]);
        }
        for(int i = 0; i < datosPartidoLista.length; i++) {
            String [] partido  = datosPartidoLista[i].split(",");
            String ordenEnLista = partido[0];
            if ((ordenEnLista).equals(id)){
                setId(partido[0]);
                setOrden(Integer.parseInt(partido[1]));
                setEquipo1Nombre(partido[2]);
                setEquipo2Nombre(partido[3]);
                setGrupo(partido[4]);
                setGolesEquipo1(Integer.parseInt(partido[5]));
                setGolesEquipo2(Integer.parseInt(partido[6]));
            }
        }
        //System.out.println("Atributos consultados establecidos con exito");
    }

    public void asociarPartidosAEquipos() {
        datosEquipos.consultarDatos(tablaEquipos);
        String datosEquiposLista[] = datosEquipos.getTuplas().split("/n");
        //System.out.println("Este es el arraylist de datosEquiposLista");
        for (int i = 0; i < datosEquiposLista.length; i++) {
            //System.out.println(datosEquiposLista[i]);
        }

        for (int i = 0; i < datosEquiposLista.length; i++) {
            String equipo[] = datosEquiposLista[i].split(",");
            if (equipo[0].equals(this.equipo1Nombre)) {
                equipo1.setNombreEquipo(equipo[0]);
                //System.out.println("equipo1.setNombreEquipo " + equipo1.getNombreEquipo());
                equipo1.setPosicion(Integer.parseInt(equipo[1]));
                equipo1.setPartidosJugados(Integer.parseInt(equipo[2]));
                equipo1.setPartidosGanados(Integer.parseInt(equipo[3]));
                equipo1.setPartidosEmpatados(Integer.parseInt(equipo[4]));
                equipo1.setPartidosPerdidos(Integer.parseInt(equipo[5]));
                equipo1.setGolesAFavor(Integer.parseInt(equipo[6]));
                equipo1.setGolesEnContra(Integer.parseInt(equipo[7]));
                equipo1.setDiferenciaGoles(Integer.parseInt(equipo[8]));
                equipo1.setPuntos(Integer.parseInt(equipo[9]));
                equipo1.setGrupo(equipo[10]);
            }
        }

        for (int i = 0; i < datosEquiposLista.length; i++) {
            String equipo[] = datosEquiposLista[i].split(",");
            if (equipo[0].equals(this.equipo2Nombre)) {
                equipo2.setNombreEquipo(equipo[0]);
                //System.out.println("equipo2.setNombreEquipo " + equipo2.getNombreEquipo());
                equipo2.setPosicion(Integer.parseInt(equipo[1]));
                equipo2.setPartidosJugados(Integer.parseInt(equipo[2]));
                equipo2.setPartidosGanados(Integer.parseInt(equipo[3]));
                equipo2.setPartidosEmpatados(Integer.parseInt(equipo[4]));
                equipo2.setPartidosPerdidos(Integer.parseInt(equipo[5]));
                equipo2.setGolesAFavor(Integer.parseInt(equipo[6]));
                equipo2.setGolesEnContra(Integer.parseInt(equipo[7]));
                equipo2.setDiferenciaGoles(Integer.parseInt(equipo[8]));
                equipo2.setPuntos(Integer.parseInt(equipo[9]));
                equipo2.setGrupo(equipo[10]);
            }
        }
    }

    public void generarGolRandom1(){
        int gol1 = (int) (Math.random() * 6);
        //int gol2 = (int) (Math.random() * 6);
        setGolesEquipo1(gol1);
        //setGolesEquipo2(gol2);
        //System.out.println("Gol random " + golesEquipo1);
        //System.out.println("Gol random " + golesEquipo2);
    }

    public void generarGolRandom2(){
        //int gol1 = (int) (Math.random() * 6);
        int gol2 = (int) (Math.random() * 6);
        //setGolesEquipo1(gol1);
        setGolesEquipo2(gol2);
        //System.out.println("Gol random " + golesEquipo1);
        //System.out.println("Gol random " + golesEquipo2);
    }

    public boolean validarGoles(){
        return (golesEquipo1 + golesEquipo2) <= 8;
    }
}


