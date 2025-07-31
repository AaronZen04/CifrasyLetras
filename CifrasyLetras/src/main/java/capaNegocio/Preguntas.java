package capaNegocio;

import java.io.*;
import java.util.*;

public class Preguntas {

    private List<Pregunta> listaPreguntas;

    public Preguntas() {
        listaPreguntas = cargarPreguntas("Preguntas.txt");
    }

    private List<Pregunta> cargarPreguntas(String nombreArchivo) {
        List<Pregunta> preguntas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(nombreArchivo)))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 2) {
                    preguntas.add(new Pregunta(partes[0], partes[1]));
                }
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Error al cargar preguntas: " + e.getMessage());
        }
        return preguntas;
    }

    public void ejercicioPreguntas() {
        if (listaPreguntas.isEmpty()) {
            System.out.println("No hay preguntas disponibles.");
            return;
        }

        Scanner in = new Scanner(System.in);
        Random random = new Random();

        System.out.println("🧠 Tienes 30 segundos para responder a una pregunta de cultura general.");
        System.out.println("Escribe tu respuesta (una única palabra):\n");

        Pregunta pregunta = listaPreguntas.get(random.nextInt(listaPreguntas.size()));
        System.out.println("❓ Pregunta: " + pregunta.getEnunciado());

        final String[] respuestaJugador = {null};

        Thread inputThread = new Thread(() -> {
            respuestaJugador[0] = in.nextLine();
        });

        inputThread.start();

        try {
            inputThread.join(30_000);
        } catch (InterruptedException e) {
            System.out.println("Temporizador interrumpido.");
        }

        if (respuestaJugador[0] == null) {
            System.out.println("⏱️ Tiempo agotado. La respuesta era: " + pregunta.getRespuestaCorrecta());
            inputThread.interrupt();
        } else if (pregunta.esCorrecta(respuestaJugador[0])) {
            System.out.println("✅ ¡Correcto!");
        } else {
            System.out.println("❌ Incorrecto. La respuesta era: " + pregunta.getRespuestaCorrecta());
        }
    }
}
