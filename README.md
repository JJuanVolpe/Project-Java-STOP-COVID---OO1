#
# <p align="center">Ejercicio final Orientación a Objetos 1</p>

Un laboratorio está realizando ensayos para demostrar la eficacia de su vacuna StopCovid. Un
ensayo consiste en reclutar grupos de personas que serán inoculadas y luego realizarles controles
para determinar la inmunización obtenida a lo largo del tiempo. El laboratorio desea construir un
software que permita registrar toda la actividad del ensayo y recopilar la información necesaria
para estudios estadísticos.
El software debe satisfacer los siguientes Casos de Uso y todos los que crea necesario para ello.



## Casos de uso breves

En el actual repositorio se encuentra el desarrollo del Frontend, pero la aplicación cuenta con otros componentes de uso común, siendo los siguientes:

- 1. Registro de voluntario. De cada voluntario de los que se registra nombre, dni, sexo, edad y
     se le asigna un identificador de 4 dígitos que no puede repetirse dentro del estudio en el
     que se integra.


- 2. Iniciar estudio. Se registra la fecha de inicio y se divide a los voluntarios registrados en dos
     grupos, llamados Vacuna y Placebo con la misma cantidad de integrantes cada uno. La
     asignación es al azar, no puede responder sólo al orden de registro del voluntario.

- 3. Registrar inoculación. Para cada voluntario se registra la fecha en que recibe la
     inoculación inicial (que no siempre coincide con el inicio del estudio).

- 4. Realizar control. Durante el estudio se pueden practicar tres tipos de controles: Clínico,
- Laboratorio, PCR. En los tres se registra la fecha y el resultado que arroja. Los resultados
- posibles son Control Clínico: con o sin síntomas, Control PCR: positivo o negativo y Control
- Laboratorio: cantidad de anticuerpos (número real de 0 a 100). Los controles se realizan
- una vez por semana de la siguiente manera:

-a. A cada miembro del grupo Placebo se le realiza un Control Clínico.
-b. A cada miembro del grupo Vacuna se le realiza un Control Clínico y un Control.
Laboratorio
-c. A cualquier voluntario (Placebo o Vacuna) que registre un Control Clínico con síntomas, se le practica un Control PCR. El sistema debe verificar que el voluntario está en condiciones de recibir un Control.


- 5. Informar resultado de control. A todos los voluntarios se les informa el resultado de su
     control. Cuando un voluntario resulta con un Control PCR con resultado positivo, además
     se le informa que es suspendido del estudio y no recibe más controles. El sistema debe
     verificar que el voluntario está en condiciones de recibir un Control.

- 6. Reportar síntomas. En cualquier momento de un estudio el laboratorio quiere poder
     extraer la siguiente información el porcentaje de integrantes de ese grupo que registraron
     algún Control Clínico con síntomas divididos en tres rangos de edades: 18-40, 41-60,
     mayores de 60.

- 7. Reportar suspendidos. En cualquier momento de un estudio el laboratorio quiere poder
     extraer el porcentaje de voluntarios que debieron ser suspendidos por control PCR
     Positivo los mismos rangos de edades.

- 8. Reporte grupo Vacuna. En cualquier momento de un estudio el laboratorio quiere obtener
     el promedio de cantidad anticuerpos generados por los miembros del grupo Vacuna hasta
     la semana 3 y luego hasta la semana 6.


### Actividades

- **1**:  Diseñar un modelo orientado a objetos para el problema planteado arriba expresándolo en un diagrama de clases.

- **2**: Implementar en Java la aplicación considerando las historias de usuario enunciadas y las que considere necesarias para que pueda funcionar la solución.

- **3**:  (obligatorio para trabajo en pareja, opcional para individual) Implementar tests de unidad para todos los métodos públicos de sus clases.