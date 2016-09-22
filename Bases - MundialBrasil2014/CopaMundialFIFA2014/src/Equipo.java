/**  * Created by Brayan Fajardo on 21/06/2014.  */
public class Equipo {
    String nombreEquipo;
    int posicion;
    int partidosJugados;
    int partidosGanados;
    int partidosEmpatados;
    int partidosPerdidos;
    int golesAFavor;
    int golesEnContra;
    int diferenciaGoles;
    int puntos;
    String grupo;
    public Equipo() {
        nombreEquipo = "";
        posicion = 0;
        partidosJugados = 0;
        partidosGanados = 0;
        partidosEmpatados = 0;
        partidosPerdidos = 0;
        golesAFavor = 0;
        golesEnContra = 0;
        diferenciaGoles = 0;
        puntos = 0;
        grupo = "";
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public void setDiferenciaGoles(int diferenciaGoles) {
        this.diferenciaGoles = diferenciaGoles;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public int getPosicion() {
        return posicion;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public int getDiferenciaGoles() {
        return diferenciaGoles;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getGrupo() {
        return grupo;
    }
}