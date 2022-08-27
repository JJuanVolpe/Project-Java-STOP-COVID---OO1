package classes;

import java.time.LocalDate;

public class ControlLaboratorio extends Control<Double>{
   
    public ControlLaboratorio(LocalDate fecha, Double dato) {
        super(fecha, dato);
    }
   
    protected boolean isLaboratorio(){
        return true;
    }
    
    @Override
     public boolean isPositive() {
        return false;
    }
}