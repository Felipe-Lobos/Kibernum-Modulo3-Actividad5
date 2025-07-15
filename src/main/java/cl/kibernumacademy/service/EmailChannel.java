package cl.kibernumacademy.service;

import cl.kibernumacademy.model.Notification;

public class EmailChannel implements NotificationChannel {

    @Override
    public void send(Notification notification) {
        System.out.println("Notificacion: "+notification+" Enviada desde "+ this.getClass().getSimpleName());
    }

    @Override
    public String getChannelName() {
        return "EMAIL";
    }

    
}
