package classes;

import java.time.LocalDate;

public class ControlClinico extends Control<Boolean>{

    public ControlClinico(LocalDate fecha, Boolean dato) {
        super(fecha, dato);
    }

    @Override
    public boolean isPositive() {
        return this.resultado;
    }

    protected boolean isClinico(){ return true;};
}
