# MonitorDormilon_Sis-Operativos

## Descripción
El proyecto "Monitor Dormilón" es una simulación del problema de sincronización utilizando hilos y semáforos en Java. Representa una situación donde un monitor de algoritmos en una universidad ayuda a estudiantes con sus tareas de programación. El monitor duerme cuando no hay estudiantes esperando y se despierta cuando un estudiante llega a pedir ayuda.

### Comportamiento del Sistema
- Hay **3 sillas** en el corredor para que los estudiantes esperen si el monitor está ocupado.
- Si un estudiante llega y el monitor está dormido, lo despierta.
- Si no hay sillas disponibles, el estudiante se va a la sala de cómputo y regresa después de un tiempo.
- El monitor atiende a los estudiantes en orden de llegada (cola FIFO).
- Después de ayudar a un estudiante, el monitor revisa si hay más estudiantes esperando antes de volver a dormirse.

## Tecnologías Utilizadas
- **Java** para la implementación del programa.
- **Semáforos** y **synchronized** para la sincronización de hilos.

## Estructura del Proyecto
- **MonitorDormilon.java**: Contiene la lógica completa del monitor y los estudiantes.
- **Monitor**: Hilo que representa al monitor, atiende estudiantes y duerme cuando no hay nadie.
- **Estudiante**: Hilo que representa a los estudiantes que alternan entre programar y buscar ayuda.

## Ejecución del Proyecto
1. Clona o descarga el repositorio.
2. Compila el archivo **MonitorDormilon.java**:
   ```
   javac MonitorDormilon.java
   ```
3. Ejecuta el programa:
   ```
   java MonitorDormilon
   ```

## Ejemplo de Salida
```
[Monitor]: No hay estudiantes. Me voy a dormir.
[Estudiante 3]: Necesito ayuda del monitor.
[Estudiante 3]: Me senté en una silla del corredor. (Sillas ocupadas: 1)
[Monitor]: Atendiendo al estudiante 3
[Monitor]: Terminé con el estudiante 3
[Monitor]: No hay estudiantes. Me voy a dormir.
```

**Autores:** [Davide Flamini](https://github.com/davidone007) - [Andrés Cabezas](https://github.com/andrescabezas26)-
[Nicolas Cuellar](https://github.com/Nicolas-CM)  

