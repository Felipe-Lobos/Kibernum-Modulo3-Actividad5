package cl.kibernumacademy.service;

import cl.kibernumacademy.model.Notification;

public class EmailChannel implements NotificationChannel {

    @Override
    public boolean send(Notification notification) {
        System.out.println("Notificacion: "+notification+" Enviada desde "+ this.getClass().getSimpleName());
        return true;
    }

    @Override
    public String getChannelName() {
        return "EMAIL";
    }

    
}
