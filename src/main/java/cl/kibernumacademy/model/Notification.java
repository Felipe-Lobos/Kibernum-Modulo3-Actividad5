package cl.kibernumacademy.model;



import cl.kibernumacademy.service.NotificationChannel;

public class Notification {
    private String mensaje;
    private String destinatario;

    public Notification(String mensaje, String destinatario) {
        this.mensaje = mensaje;
        this.destinatario = destinatario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getDestinatario() {
        return destinatario;
    }

    // public NotificationChannel getCanalUtilizado() {
    //     return canalUtilizado;
    // }

    // @Override
    // public String toString() {
    //     return "Notification [mensaje=" + mensaje + ", destinatario=" + destinatario + ", canalUtilizado="
    //             + canalUtilizado + "]";
    // }

}
