# Proyecto desarrollado en la asignatura Orientación a Objetos I. 

En la carpeta exercise_data se encuentra el Diagrama UML, el problema detallado a resolver y los requerimientos para la ejecución del programa :) 


Se genera la satisfacción de casos de uso, aplicando un diseño de software imperativo respecto a los principios de la POO, 
de forma tal que la destribución de comportamientos y responsabilidades entre los objetos sea equitativa o propia del objeto.
Generando como resultado un software simple y moldeado a los CU requeridos.

Una instancia de la clase Ensayo permite satisfacer los CU: 1, 2, 6, 7 y 8., 
A partir de la creación de un grupo de Voluntarios permite generar una instancia de la clase Estudio que permite satisfacer los CU: 3, 4 y 5. 
A su vez los algoritmos de resolución del C.U: 6, 7 y 8 están implementados en la clase:SystemAvg, para aislar los algoritmos de estadística proyectando
una clase dedicada al calculo de estadísticas .

Los Pacientes por su parte, (ex-Voluntarios) permiten almacenar controles por semana,
si es que no tienen un control pcr positivo o ha pasado una semana del último control (si hay más de uno).
Los Controles se representan cómo una estructura de datos abstracta y genérica, que permite adaptar y generalizar los tipos: Pcr, Clinico y Laboratorio.

                ######################################################################################################

Cualquier cambio respecto a un diseño más genérico/legible o enfocado a optimización es bien recibido. 
Se percibe un diseño posible como resolucion al enunciado descripto y asignado.
Trabajo final desarrollado como oportunidad para aprobar la asignatura correspondiente en el Redictado de OO1.
