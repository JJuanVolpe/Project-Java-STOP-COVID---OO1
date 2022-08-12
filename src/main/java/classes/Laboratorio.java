package classes;

import java.time.LocalDate;
import java.util.List;

public class Laboratorio extends Control<Double>{
    public Laboratorio(LocalDate fecha, Double dato) {
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