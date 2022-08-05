Defensa respectiva del programa StopCovid:

Bueno para empezar vamos a tener una instancia que conste de voluntarios 
(Ensayo), desde allí podremos generar una instancia "Estudio", 
la cuál posee Pacientes separados en Vacuna y Placebo, 
fecha de inoculación general para c/u. (?).
Los pacientes pueden tener controles, se registra la fecha y resultado 
del mismo. Los Pacientes contienen Voluntarios en orden al azar x grupo.
Se utiliza, ademas una clase auxiliar, "SystemAvg" para permitir generalizar
calculos de estadisticas que se deseen agregar a priori.


requires org.junit.jupiter.api v 53.0;