package capaNegocio;

import java.util.Random;
import java.util.Scanner;

public class Cifras {

	public void ejercicioCifras() {
		Scanner in = new Scanner(System.in);

		int[] numerosGenericos = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 25, 50, 75, 100 };
		int[] numerosAleatorios = new int[6];
		int numeroPropuesto = 0;
		int respuesta = 0;
		Random random = new Random();
		final int[] tiempoRestante = { 60 };
		boolean tiempoAgotado = false;

		System.out.println(
				"En este juego se te darán 6 números aleatorios y tendrás que conseguir la cifra exacta o mas aproximada posible en 1 minuto");
		System.out.println("Números: ");
		for (int i = 0; i < numerosAleatorios.length; i++) {
			int indice = random.nextInt(numerosGenericos.length);
			numerosAleatorios[i] = numerosGenericos[indice];
			System.out.print(numerosAleatorios[i] + " ");
		}
		System.out.println("");
		numeroPropuesto = random.nextInt(900) + 100;
		System.out.println("Objetivo: " + numeroPropuesto);
		
		// Hilo que funciona aparte para contabilizar el tiempo
		Thread temporizador = new Thread(() -> {
			while (tiempoRestante[0] > 0) {
				System.out.print("\rTiempo restante: " + tiempoRestante[0] + " segundos   ");
				tiempoRestante[0]--;

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					break;
				}
			}
		});

		temporizador.start();
		// permitir responder mientras haya tiempo
		try {
			while (tiempoRestante[0] > 0) {
				if (in.hasNextInt()) {
					respuesta = in.nextInt();
					break;
				}
			}
		} finally {
			tiempoAgotado = tiempoRestante[0] == 0;
			temporizador.interrupt();
		}

		System.out.println();

		if (tiempoAgotado) {
			System.out.println("Se acabó el tiempo");
		} else {
			System.out.println("Tu respuesta fue: " + respuesta);
			if (respuesta == numeroPropuesto) {
				System.out.println("¡Enhorabuena! Lo has logrado.");
			} else {
				System.out.println("El número objetivo era: " + numeroPropuesto);
				System.out.println("Te has quedado a: " + Math.abs(numeroPropuesto - respuesta));
			}
		}
		System.out.println();
	}
}
