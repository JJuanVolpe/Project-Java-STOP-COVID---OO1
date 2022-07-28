package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Paciente {

    private Voluntario voluntario;
    private List<Control> controles;
    public Paciente(Voluntario voluntario) {
        this.voluntario = voluntario;
        this.controles = new ArrayList<>();
    }


    private String getControlesToString() {
        if (this.controles == null || this.controles.isEmpty())
            return "-";
        return controles.stream().map(Control::toString).collect((Collectors.joining("\n")));
    }


    public List<Control> getControles(){ return this.controles;}

    public void setControles(List<Control> controles){ this.controles = controles;}

    public void realizarControl(Control control) {
        if (this.getSinSintomasPositivos()) {
            this.controles.add(control);
        }
    }

    public void ralizarControl(Control c) {
        this.controles.add(c);
    }


    public boolean poseePcrPositivo(){
        List<Control> controls = new ArrayList<>();
        this.controles.forEach(c -> c.agregarPorPcr(controls));
        return controls.stream().anyMatch(Control::isPositive);
    }

    public boolean poseeLaboratorioPositivo(){
        List<Control> controls = new ArrayList<>();
        this.controles.forEach(c -> c.agregarPorLaboratorio(controls));
        return controls.stream().anyMatch(Control::isPositive);
    }

    public boolean poseeClinicoPositivo(){
        List<Control> controls = new ArrayList<>();
        this.controles.forEach(c -> c.agregarPorClinico(controls));
        return controls.stream().anyMatch(Control::isPositive);
    }
    public boolean getSinSintomasPositivos() {

        if (this.controles != null) {       //Note : If the stream is empty then true is returned and the predicate is not evaluated.
            return true;                   //Es decir si no posee controles se considera que no tiene sintomas positivos y se realiza opera con el correspondiente...
        }
        return this.controles.stream().noneMatch(Control::isPositive);
    }

    public boolean getByAge(int minus, int plus) {
        return this.voluntario.getByAge(minus, plus);
    }

    public List<Control> getControlsByLab(boolean firstWeeks) {
        List<Control> controls = new ArrayList<>();
        this.controles.forEach(c -> c.agregarPorLaboratorio(controls));
        if (firstWeeks){
            while (controls.size() > 3){  //Elimina los controles por laboratorio para que solamente queden los 3 primeros.
                controls.remove(controls.size());
            }
        }
    return controls;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "voluntario=" + voluntario.toString() +
                ", controles=" + getControlesToString();
    }

    public boolean poseeControlLaboratorio() {
        List<Control> controls = new ArrayList<>();
        this.controles.forEach(c -> c.agregarPorClinico(controls));
        return !controls.isEmpty();
    }
}
