package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import capaNegocio.Cifras;
import capaNegocio.Letras;
import capaNegocio.Preguntas;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);  

			System.out.println("Bienvenido a Cifras y Letras, selecciona que juego quieres practicar: ");
	        boolean salir = false;
	        int opcion;
	 
	        while (!salir) {
	 
	            System.out.println("1. Cifras");
	            System.out.println("2. Letras");
	            System.out.println("3. Preguntas");
	            System.out.println("4. Salir");
	 
	            try {
	 
	                System.out.println("Escribe una de las opciones:");
	                opcion = in.nextInt();
	 
	                switch (opcion) {
	                    case 1:
	                    	Cifras cifra = new Cifras();
	                        System.out.println("Has seleccionado Cifras");
	                        cifra.ejercicioCifras();
	                        break;
	                    case 2:
	                        System.out.println("Has seleccionado Letras");
	                        Letras letras = new Letras();
	                        letras.ejercicioLetras();
	                        break;
	                    case 3:
	                        System.out.println("Has seleccionado Preguntas");
	                        Preguntas preguntas = new Preguntas();
	                        preguntas.ejercicioPreguntas();
	                        break;
	                    case 4:
	                        salir = true;
	                        break;
	                    default:
	                        System.out.println("Solo números entre 1 y 4");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Debes insertar un número");
	                in.next();
	            }
	        }
	 
	    }


}
