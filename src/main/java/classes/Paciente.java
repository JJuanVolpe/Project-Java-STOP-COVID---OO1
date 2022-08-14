package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Paciente extends Voluntario{

    private List<Control> controles;

    public Paciente(Voluntario voluntario) {
        super(voluntario.getNombre(), voluntario.getDni(), voluntario.getSexo(), voluntario.getEdad(), voluntario.getId());
        this.controles = new ArrayList<>();
    }
    public Paciente(String name, String dni, String m, int edad, String id) {
        super(name, dni, m, edad, id);
        this.controles = new ArrayList<>();
    }

    private String getControlesToString() {
        if (this.controles.isEmpty())
            return "-";
        return controles.stream().map(Control::toString).collect((Collectors.joining("\t")));
    }

    public String getLastControlResult() {
        if (this.controles.isEmpty())
            return "-";
        return controles.get(this.controles.size()-1).getResultado().toString();
    }


    public List<Control> getControles(){ return this.controles;}

    public void agregarControl(Control control) {
        if (!this.poseePcrPositivo() && addByTimeOfControl(control)){
            this.controles.add(control);
        }
    }

    private boolean addByTimeOfControl(Control c){
        return  this.getControles().isEmpty() || this.getControles().get(getControles().size() - 1).passedAWeek(c.getFecha());
    };
    public boolean poseeClinicoPositivo(){
        return this.controles.stream().filter(Control::isClinico).anyMatch(Control::isPositive);
    }
    public boolean poseePcrPositivo(){
        return this.controles.stream().filter(Control::isPcr).anyMatch(Control::isPositive);
    }
    public boolean poseeControlLaboratorio() {
        return this.controles.stream().anyMatch(Control::isLaboratorio);
    }


    public boolean noTieneControlesPositivos() {
        //Note : If the stream is empty then true is returned and the predicate is not evaluated.
        //Es decir si no posee controles se considera que no tiene sintomas positivos y se realiza opera con el correspondiente...
        return this.controles.stream().noneMatch(Control::isPositive);  //Devuelve si no hay positivo
    }

    public List<Control> getControlsLabByTime(boolean OnlyFirstWeeks) {
        List<Control> controls = new ArrayList<>(this.controles.stream().filter(Control::isLaboratorio).toList());
        if (!controls.isEmpty() && OnlyFirstWeeks){
            while (controls.size() > 3){  //Elimina los controles por laboratorio para que solamente queden los 3 primeros.
                controls.remove((controls.size() - 1));
            }
        }
    return controls;
    }

    @Override
    public String toString() {
        return  super.toString() +
                ", controles:" + getControlesToString();
    }

}
