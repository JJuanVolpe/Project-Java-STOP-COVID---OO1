package classes;

import java.time.LocalDate;

public class ControlLaboratorio extends Control<Double>{
    public ControlLaboratorio(LocalDate fecha, Double dato) {
        super(fecha, dato);
    }

    @Override
     public boolean isPositive() {
        return false;
    }
    protected boolean isLaboratorio(){
        return true;
    }
}