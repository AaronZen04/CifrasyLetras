package capaNegocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Letras {
	@SuppressWarnings("deprecation")
	public void ejercicioLetras() {
		Scanner in = new Scanner(System.in);

		char[] vocales = { 'A', 'E', 'I', 'O', 'U' };
		char[] abecedario = { 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'V', 'X',
				'Y', 'Z' };
		String palabra;
		int cantidadVocales = 0;
		int cantidadConsonantes = 10;
		Random random = new Random();
		System.out.println(
				"En este juego tendrás que formar una palabra de mínimo 5 letras y un máximo de 10, escogiendo una cantidad de vocales entre 2-5 en 45 segundos");
		do {
			System.out.print("Número de vocales (2-5): ");
			cantidadVocales = in.nextInt();

			if (cantidadVocales < 2 || cantidadVocales > 5) {
				System.out.println("Número de vocales inválido. Introduce un número entre 2 y 5.");
				System.out.println("");
			}
		} while (cantidadVocales < 2 || cantidadVocales > 5);

		cantidadConsonantes -= cantidadVocales;

		char[] vocalesSeleccionadas = new char[cantidadVocales];
		for (int i = 0; i < cantidadVocales; i++) {
			vocalesSeleccionadas[i] = vocales[random.nextInt(vocales.length)];
		}
		System.out.print("Vocales: ");
		for (char vocal : vocalesSeleccionadas) {
			System.out.print(vocal + " ");
		}
		System.out.println("");

		char[] consonantesSeleccionadas = new char[cantidadConsonantes];
		for (int i = 0; i < cantidadConsonantes; i++) {
			consonantesSeleccionadas[i] = abecedario[random.nextInt(abecedario.length)];
		}
		System.out.print("Consonantes: ");
		for (char Consonante : consonantesSeleccionadas) {
			System.out.print(Consonante + " ");
		}
		System.out.println("");

		char[] letrasDisponibles = new char[vocalesSeleccionadas.length + consonantesSeleccionadas.length];
		System.arraycopy(vocalesSeleccionadas, 0, letrasDisponibles, 0, vocalesSeleccionadas.length);
		System.arraycopy(consonantesSeleccionadas, 0, letrasDisponibles, vocalesSeleccionadas.length, consonantesSeleccionadas.length);
		
		// Iniciar el temporizador en un hilo separado
		Thread timerThread = new Thread(() -> temporizador(45));
		timerThread.start();

		System.out.print(" Escribe tu palabra: ");
		palabra = in.next();
		timerThread.interrupt();

		System.out.println("\n Palabra registrada: " + palabra);
		
		if (esPalabraValida(palabra, letrasDisponibles)) {
		    System.out.println("✅ ¡Palabra válida con las letras!");
		    
		    if (existePalabraEnDiccionario(palabra)) {
		        System.out.println("✅ La palabra existe en el diccionario.");
		    } else {
		        System.out.println("❌ La palabra NO existe en el diccionario.");
		    }

		} else {
		    System.out.println("❌ La palabra no se puede formar con las letras dadas.");
		}

	}
	// Método para saber si la palabra se puede formar con las letras dadas
	public static boolean esPalabraValida(String palabra, char[] letrasDisponibles) {
	    // Creamos una lista modificable con las letras disponibles
	    List<Character> letras = new ArrayList<>();
	    for (char letra : letrasDisponibles) {
	        letras.add(letra);
	    }

	    // Recorremos cada letra de la palabra del usuario
	    for (char letra : palabra.toUpperCase().toCharArray()) {
	        if (letras.contains(letra)) {
	            letras.remove((Character) letra); // Quitamos una aparición
	        } else {
	            return false; // Letra no disponible o ya usada todas las veces posibles
	        }
	    }

	    return true; // Si pudo formar toda la palabra
	}
	// REVISAR pero funciona
	public boolean existePalabraEnDiccionario(String palabra) {
	    try (BufferedReader reader = new BufferedReader(
	            new InputStreamReader(getClass().getClassLoader().getResourceAsStream("diccionario.txt")))) {

	        String linea;
	        palabra = palabra.toLowerCase();
	        while ((linea = reader.readLine()) != null) {
	            if (linea.trim().equals(palabra)) {
	                return true;
	            }
	        }
	    } catch (IOException | NullPointerException e) {
	        System.out.println("Error al leer el diccionario: " + e.getMessage());
	    }
	    return false;
	}


	// MÉTODO TEMPORIZADOR
	public void temporizador(int segundos) {
	    for (int i = segundos; i >= 0; i--) {
	        String texto = "⏳ Tiempo restante: " + i + " segundos";
	        int longitudMax = 40; // asegúrate de que sobreescribe bien la línea
	        String lineaLimpia = String.format("\r%-" + longitudMax + "s", texto);
	        System.out.print(lineaLimpia);
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            return; // Si se interrumpe (por pulsar intro), se sale del bucle
	        }
	    }
	    System.out.print("\r🔔 ¡Tiempo agotado!                     \n");
	}


}

// formatear y documentar bien.