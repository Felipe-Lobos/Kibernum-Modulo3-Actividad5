package cl.kibernumacademy.service;

import cl.kibernumacademy.model.Notification;

public class SmsChannel implements NotificationChannel {
    @Override
    public void send(Notification notification) {
        System.out.println("Notificacion: " + notification + " Enviada desde " + this.getClass().getSimpleName());
    }

    @Override
    public String getChannelName() {
        return "SMS";
    }
}
