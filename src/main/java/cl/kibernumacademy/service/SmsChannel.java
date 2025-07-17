package cl.kibernumacademy.service;

import cl.kibernumacademy.model.Notification;

public class SmsChannel implements NotificationChannel {


    @Override
    public String getChannelName() {
        return "SMS";
    }

    @Override
    public boolean send(Notification notification) {
        System.out.println("Notificacion: " + notification + " Enviada desde " + this.getClass().getSimpleName());
        return true;
    }
}
