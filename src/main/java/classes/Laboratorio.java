package classes;

import java.time.LocalDate;
import java.util.List;

public class Laboratorio extends Control<Double>{
    public Laboratorio(LocalDate fecha, Double dato) {
        super(fecha, dato);
    }

    @Override
    public Double getResult() {
        return this.resultado;
    }

    @Override
     boolean isPositive() {
        return false;
    }


    @Override
    LocalDate getFecha() {
        return this.fecha;
    }

    protected void agregarPorLaboratorio(List<Control> controlesLaboratorio){
        controlesLaboratorio.add(this);
    };
}
