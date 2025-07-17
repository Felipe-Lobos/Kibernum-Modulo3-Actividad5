package cl.kibernumacademy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.kibernumacademy.model.Notification;
import cl.kibernumacademy.model.NotificationHistory;

public class NotificationManager {
    private NotificationChannel smsChannel;
    private NotificationChannel emailChannel;
    private List<NotificationHistory> notifications = new ArrayList<>();
    

    public NotificationManager(NotificationChannel smsChannel, NotificationChannel emailChannel) {
        this.smsChannel = smsChannel;
        this.emailChannel = emailChannel;
    }


    public boolean sendNotification(Notification notification, String canal) {
        String mensaje = notification.getMensaje();
        String destinatario = notification.getDestinatario();
        if (mensaje.isEmpty() || mensaje == null || destinatario == null) {
            return false;
        }
        NotificationChannel channel;
        // Notification notification = new Notification(mensaje, destinatario);
        // String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
        // String smsRegex = "^[\\\\+]?[(]?[0-9]{3}[)]?[-\\\\s\\\\.]?[0-9]{3}[-\\\\s\\\\.]?[0-9]{4,6}$";
        if(canal.equalsIgnoreCase("SMS")){
            channel = smsChannel;
        }else if (canal.equalsIgnoreCase("EMAIL")){
            channel = emailChannel;
        }else{
            throw new IllegalArgumentException("ERROR EN EL CANAL ESPECIFICADO");
        }
        boolean result = channel.send(notification);
        if (result) {
            saveNotification(notification, canal);
        }
        System.out.println("Result=="+result);
        return result;
    }


    private void saveNotification(Notification notification, String canal){
        notifications.add(new NotificationHistory(notification, canal));
    }
    public List<NotificationHistory> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

}
