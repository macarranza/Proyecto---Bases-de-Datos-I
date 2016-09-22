/**
 * Created by Brayan Fajardo on 21/06/2014.
 */
public class Grupo {

    Equipo equipo1;
    Equipo equipo2;
    Equipo equipo3;
    Equipo equipo4;
    String letraGrupo;

    public Grupo(){

        equipo1 = new Equipo();
        equipo2 = new Equipo();
        equipo3 = new Equipo();
        equipo4 = new Equipo();
        letraGrupo = "";
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public void setEquipo3(Equipo equipo3) {
        this.equipo3 = equipo3;
    }

    public void setEquipo4(Equipo equipo4) {
        this.equipo4 = equipo4;
    }

    public void setLetraGrupo(String letraGrupo) {
        this.letraGrupo = letraGrupo;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public Equipo getEquipo3() {
        return equipo3;
    }

    public Equipo getEquipo4() {
        return equipo4;
    }

    public String getLetraGrupo() {
        return letraGrupo;
    }
}





