package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Estudio {


    private LocalDate fechaInicio;
    private LocalDate fechaInoculacion;
    private List<Paciente> vacuna;
    private List<Paciente> placebo;

    private List<Paciente> suspendidos; //Mutuamente excluyente de  (Placebo o Vacuna)

    public Estudio(Set<Voluntario> voluntarios) {
        this.fechaInicio = LocalDate.now();
        this.generarGrupoPlaceboYVacuna(voluntarios);
    }


    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public List<Paciente> getSuspendidos() {
        return this.suspendidos;
    }

    //Registramos fecha en que se genera la inoculación de ambos grupos, se asume el mismo tiempo para todos
    private void registrarInoculacion() {
        this.fechaInoculacion = LocalDate.now();
    }
    private void generarGrupoPlaceboYVacuna(Set<Voluntario> set) {

        List<Voluntario> list = new ArrayList<>(set);
        int midIndex = (list.size() - 1) / 2;

        List<List<Voluntario>> lists = new ArrayList<>(
                list.stream()
                        .collect(Collectors.partitioningBy(s -> list.indexOf(s) > midIndex))
                        .values()
        );

        lists.get(0).forEach(v -> this.vacuna.add((new Paciente(v))));
        lists.get(1).forEach(v -> this.placebo.add((new Paciente(v))));

    }


    private void suspenderConSintomas(List<Paciente> list){

        list.stream().filter(Paciente::poseePcrPositivo).forEach(p -> {
            String strName = p.toString().substring(0, p.toString().indexOf(','));// jej el nombre del paciente.
            System.out.println("Lo sentimos ha sido suspendido por detección de control PCR positivo:" + strName);
            this.suspendidos.add(p);
            if (this.vacuna.contains(p)){
                this.vacuna.remove(p);
            } else {
                this.placebo.remove(p);
            }
        });
    }

    //Inciso 4. Se registra el control sobre el paciente.
    public void realizarControlEnPaciente(Paciente p, Control control) {
        if (p.getSinSintomasPositivos()){
            p.realizarControl(control);
        }
    }

    //Inciso 5. Informar resultado de control.
    public void informarResultadoControl(List<Control> controles) {
        List<Paciente> list = this.vacuna;
        list.addAll(this.placebo);
        list.stream().forEach(System.out::println);
        suspenderConSintomas(list);
    }

    public List<Paciente> getAllVoluntarios() {
        List<Paciente> list = this.vacuna;
        list.addAll(this.placebo);
        if (this.suspendidos != null && !this.suspendidos.isEmpty()) {
            list.addAll(this.suspendidos);
        }
        return list;
    }

    public List<Paciente> getGrupoVacunaPorLaboratorio() {
        return this.vacuna.stream().filter(Paciente::poseeControlLaboratorio).collect(Collectors.toList());
    }


    /*
    public void realizarControlesGrupoPlacebo(Control control) {
        this.placebo.stream().filter(Paciente::getSinSintomasPositivos).forEach(P -> P.realizarControl(control));
    }
    public void realizarControlesGrupoVacuna(Control control) {
        this.vacuna.stream().filter(Paciente::getSinSintomasPositivos).forEach(P -> P.realizarControl(control));
    }
    */

}
