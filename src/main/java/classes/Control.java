package classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public abstract class Control<T> {

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

    public T getResult() {
        return null;
    }

    abstract boolean isPositive();

    abstract LocalDate getFecha();

    protected boolean isLaboratorio(){
        return false;
    };
    protected boolean isClinico(){
        return false;
    };
    protected boolean isPcr(){
        return false;
    };

    protected boolean passedAWeek(LocalDate otherDate) {
        return ChronoUnit.DAYS.between(this.getFecha(), otherDate) >= 7;
    }

}
