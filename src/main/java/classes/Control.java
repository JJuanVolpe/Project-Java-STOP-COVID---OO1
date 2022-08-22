package classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Control<T> {

    public LocalDate getFecha() {
        return fecha;
    }

    public T getResultado() {
        return resultado;
    }

    protected LocalDate fecha;
    protected T resultado;

    @Override
    public String toString() {
        return "Control:" +
                "fecha=" + fecha +
                ", resultado=" + resultado.toString();
    }

    public Control(LocalDate fecha, T dato) {
        this.fecha = fecha;
        this.resultado = dato;
    }

    public abstract boolean isPositive();
    protected boolean isLaboratorio(){return false;}
    protected boolean isClinico(){return false;}
    protected boolean isPcr(){return false;}

    public boolean passedAWeek(LocalDate otherDate) {
        return ChronoUnit.DAYS.between(this.getFecha(), otherDate) >= 7;
    }

}
