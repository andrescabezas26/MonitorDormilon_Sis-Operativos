import java.util.concurrent.Semaphore;
import java.util.LinkedList;
import java.util.Queue;

public class MonitorDormilon {

    private static final int NUM_SILLAS = 3;
    private static final int NUM_ESTUDIANTES = 4;

    private static Semaphore monitor = new Semaphore(1, true);
    private static Queue<Integer> sillas = new LinkedList<>();

    public static void main(String[] args) {
        // Crear el hilo del monitor
        Thread monitorThread = new Thread(new Monitor());
        monitorThread.start();

        // Crear los hilos de los estudiantes
        for (int i = 1; i <= NUM_ESTUDIANTES; i++) {
            new Thread(new Estudiante(i)).start();
        }
    }

    static class Monitor implements Runnable {
        @Override
        public void run() {
            try {
                boolean isSleeping = false;
                while (true) {
                    if (sillas.isEmpty() && !isSleeping) {
                        System.out.println("[Monitor]: No hay estudiantes. Me voy a dormir.");
                        isSleeping = true;
                        monitor.acquire(); // El monitor duerme
                    }else if(sillas.isEmpty() && isSleeping){
                        monitor.acquire(); // El monitor duerme
                    } else {
                        isSleeping = false;
                        int estudianteId;
                        synchronized (sillas) {
                            estudianteId = sillas.poll(); // Atiende al siguiente en la cola
                            System.out.println("[Monitor]: Atendiendo al estudiante " + estudianteId);
                        }
                        Thread.sleep((long) (Math.random() * 5000)); // Simula el tiempo de atención
                        System.out.println("[Monitor]: Terminé con el estudiante " + estudianteId);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Estudiante implements Runnable {
        private int id;

        public Estudiante(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep((long) (Math.random() * 10000 + 5000)); // Simula el tiempo programando (mayor que el tiempo de atención)
                    System.out.println("[Estudiante " + id + "]: Necesito ayuda del monitor.");

                    synchronized (sillas) {
                        if (sillas.size() < NUM_SILLAS) {
                            sillas.add(id);
                            System.out.println("[Estudiante " + id + "]: Me senté en una silla del corredor. (Sillas ocupadas: " + sillas.size() + ")");
                            monitor.release(); // Despierta al monitor si está dormido
                        } else {
                            System.out.println("[Estudiante " + id + "]: No hay sillas disponibles. Voy a la sala de computo y vuelvo más tarde.");
                        }
                    }

                    Thread.sleep((long) (Math.random() * 15000 + 5000)); // Cooldown antes de volver a pedir ayuda
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
