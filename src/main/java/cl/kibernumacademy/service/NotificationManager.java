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


    public boolean sendNotification(String mensaje, String destinatario, String canal) {
        if (mensaje.isEmpty() || mensaje == null || destinatario == null) {
            return false;
        }
        NotificationChannel channel;
        Notification notification = new Notification(mensaje, destinatario);
        // String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
        // String smsRegex = "^[\\\\+]?[(]?[0-9]{3}[)]?[-\\\\s\\\\.]?[0-9]{3}[-\\\\s\\\\.]?[0-9]{4,6}$";
        if(canal.equalsIgnoreCase("SMS")){
            channel = smsChannel;
        }else if (canal.equalsIgnoreCase("EMAIL")){
            channel = emailChannel;
        }else{
            return false;
        }
        channel.send(notification);
        saveNotification(notification, canal);
        return true;
    }


    private void saveNotification(Notification notification, String canal){
        notifications.add(new NotificationHistory(notification, canal));
    }
    public List<NotificationHistory> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

}
