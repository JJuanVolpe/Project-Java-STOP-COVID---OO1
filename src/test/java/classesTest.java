import classes.Ensayo;
import classes.Estudio;
import classes.Voluntario;
import org.junit.jupiter.api.BeforeEach;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.*;

public class classesTest {

    public classesTest(){
        //
        //
        //
        System.out.println();
    }

    private Ensayo ensayo;



    public Set<Voluntario> getVoluntarios(){

        Set<Voluntario> voluntarios = new HashSet<>(
                List.of(new Voluntario("gabriel", "3353121", "222222", 15, "00000"),
                        new Voluntario("anibal", "43678910", "222222", 21, "00001"),
                        new Voluntario("facundo", "16782912", "222222", 39, "00002"),
                        new Voluntario("ariel", "18210112", "222222", 60, "00003")
                        )
        );
    return voluntarios;
    }

    @BeforeEach
    public void getTestEnsayo() {
        ensayo = new Ensayo(getVoluntarios());
    }

    @Test
    public void registroDeVoluntario(){
        Voluntario v = new Voluntario("enz1o", "18210112", "333333", 71, "00006");

        this.ensayo.registrarVoluntario("enzo", "18210112", "333333", 71, "00004");
    }

}
