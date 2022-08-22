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
        if (!this.poseePcrPositivo()){
            if (this.addByTimeOfControl(control) || this.needBeTested(control)){
                this.controles.add(control);
            }
        }else {
            System.out.println("No se ha podido agregar el control");
        }
    }

    //Satisface  Caso de Uso 4 Parte C: A cualquier voluntario (Placebo o Vacuna) que registre
    //un Control Clínico con síntomas, se le practica un Control PCR.
    private boolean needBeTested(Control c) {
        return this.getControles().get(getControles().size()-1).isClinico() &&
                getControles().get(getControles().size()-1).isPositive()  && c.isPcr();
    }

    private boolean addByTimeOfControl(Control c){
        return  this.getControles().isEmpty() || this.getControles().get(getControles().size() - 1).passedAWeek(c.getFecha());
    };
    public boolean poseeClinicoPositivo(){
        return this.getControles().stream().filter(Control::isClinico).anyMatch(Control::isPositive);
    }
    public boolean poseePcrPositivo(){
        return this.getControles().stream().filter(Control::isPcr).anyMatch(Control::isPositive);
    }
    public boolean poseeControlLaboratorio() {
        return this.getControles().stream().anyMatch(Control::isLaboratorio);
    }


    public boolean noTieneControlesPositivos() {
        //Note : If the stream is empty then true is returned and the predicate is not evaluated.
        //Es decir si no posee controles se considera que no tiene sintomas positivos y se realiza opera con el correspondiente...
        return this.getControles().stream().noneMatch(Control::isPositive);  //Devuelve si no hay positivo
    }

    public List<Control> getControlsLabByTime(boolean onlyFirstWeeks) {
        List<Control> controls = new ArrayList<>(this.getControles().stream().filter(Control::isLaboratorio).toList());
        int weeks = (onlyFirstWeeks) ? 3 : 6;
        while (controls.size() > weeks){  //Elimina los controles por laboratorio para que solamente queden los 3 primeros.
            controls.remove((controls.size() - 1));
        }
    return controls;
    }
    @Override
    public String toString() {
        return  super.toString() +
                ", controles:" + getControlesToString();
    }
}
