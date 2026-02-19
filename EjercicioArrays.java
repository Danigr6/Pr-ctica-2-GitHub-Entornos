import java.util.Arrays;
import java.util.List;

public class EjercicioArrays {
	 public static void main(String[] args) {
	        int numAlumnos = 40;
	        // vector con las notas generadas
	        Integer[] control = new Integer[numAlumnos];
	        int[] listaClase, practicas;
	        float[] calificaciones, estadistica;
	        int[] aprobados, suspensos;
	        int maxNota = 0, minNota = 0;
	        int indMaxNota, indMinNota;
	        int postEval;
	        double[] calif;
	        
	        generarNotasAleatorias(control);

	        minNota = obtenerMinimo(control);
	        maxNota = obtenerMaximo(control);
	        indMinNota = obtenerIndiceNota(control, minNota) + 1; 
	        indMaxNota = obtenerIndiceNota(control, maxNota) + 1;
	        
	        System.out.println("Mínimo es: " + minNota);
	        System.out.println("Máximo es: " + maxNota);
	        System.out.println("Indice del mínimo es: " + indMinNota);
	        System.out.println("Indice del máximo es: " + indMaxNota);

	        // Crear el array de prácticas y generar notas aleatorias
	        practicas = new int[numAlumnos];
	        generarNotasAleatorias(practicas);

	        // Crear el array de calificaciones y calcularlas
	        calificaciones = new float[numAlumnos];
	        calcularCalificaciones(control, practicas, calificaciones);

	        System.out.println("Calificaciones: " + Arrays.toString(calificaciones));

	        // Crear el array de estadística
	        estadistica = new float[10];
	        calcularEstadistica(calificaciones, estadistica);

	        // Crear los arrays de aprobados y suspensos
	        aprobados = new int[numAlumnos];
	        suspensos = new int[numAlumnos];
	        
	        calcularAprobadosYSuspensos(calificaciones, aprobados, suspensos);

	        mostrarResumenAprobadosYSuspensos(aprobados, suspensos);

	        calif = new double[40];
	        for (int j = 0; j < 31; j++) {
	            calif[j] = Math.random() * 10;
	        }
	        System.out.println("Nota antigua alumno nº4: " + calif[3]);
	        calif[3] = 6; // Cambiar la calificación del alumno nº 4
	        System.out.println("Nota nueva alumno nº4: " + calif[3]);
	    }
	
    // Generar notas aleatorias recibiendo int[]
    public static void generarNotasAleatorias(int[] practicas) {
        for (int i = 0; i < practicas.length; i++) {
            practicas[i] = (int) (Math.random() * 11);
        }
    }
    // Generar notas aleatorias recibiendo Integer[]
    public static void generarNotasAleatorias(Integer[] control) {
        for (int i = 0; i < control.length; i++) {
        	control[i] = (int) (Math.random() * 11);
        }
    }

    // Método para encontrar el mínimo
    public static int obtenerMinimo(Integer[] control) {
        int min = control[0];
        for (int i = 1; i < control.length; i++) {
            if (control[i] < min) {
                min = control[i];
            }
        }
        return min;
    }

    // Método para encontrar el máximo
    public static int obtenerMaximo(Integer[] control) {
        int max = control[0];
        for (int i = 1; i < control.length; i++) {
            if (control[i] > max) {
                max = control[i];
            }
        }
        return max;
    }

    // Método para obtener el índice de una nota
    public static int obtenerIndiceNota(Integer[] control, int nota) {
        for (int i = 0; i < control.length; i++) {
            if (control[i] == nota) {
                return i;
            }
        }
        return -1; // En caso de no encontrar la nota
    }

    // Método para calcular las calificaciones finales
    public static void calcularCalificaciones(Integer[] control, int[] practicas, float[] calificaciones) {
        for (int i = 0; i < control.length; i++) {
            calificaciones[i] = (control[i] + practicas[i]) / 2.0f;
        }
    }

    // Método para calcular la estadística
    public static void calcularEstadistica(float[] calificaciones, float[] estadistica) {
        for (int i = 0; i < 10; i++) {
            int count = 0;
            for (int j = 0; j < calificaciones.length; j++) {
                if (calificaciones[j] > i && calificaciones[j] <= (i + 1)) {
                    count++;
                }
            }
            estadistica[i] = (float) count / calificaciones.length;
            System.out.println("Estadística nota tramo <= " + (i + 1) + " = " + Math.round(estadistica[i] * 100) / 100.0 + "%");
        }
    }

    // Método para calcular los aprobados y suspensos
    public static void calcularAprobadosYSuspensos(float[] calificaciones, int[] aprobados, int[] suspensos) {
        int countAprobados = 0;
        int countSuspensos = 0;

        for (int i = 0; i < calificaciones.length; i++) {
            if (calificaciones[i] < 5) {
                aprobados[countAprobados++] = i + 1;
            } else {
                suspensos[countSuspensos++] = i + 1;
            }
        }
    }

    // Mostrar el resumen de aprobados y suspensos
    public static void mostrarResumenAprobadosYSuspensos(int[] aprobados, int[] suspensos) {
        System.out.println("Resumen de aprobados por nº de lista: " + Arrays.toString(aprobados));
        System.out.println("Resumen de suspensos por nº de lista: " + Arrays.toString(suspensos));
    }
    
}
