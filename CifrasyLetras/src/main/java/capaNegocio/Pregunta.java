package capaNegocio;

public class Pregunta {
    private String enunciado;
    private String respuesta;

    public Pregunta(String enunciado, String respuesta) {
        this.enunciado = enunciado;
        this.respuesta = respuesta.toLowerCase().trim();
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getRespuestaCorrecta() {
        return respuesta;
    }

    public boolean esCorrecta(String entradaUsuario) {
        return respuesta.equals(entradaUsuario.toLowerCase().trim());
    }
}
