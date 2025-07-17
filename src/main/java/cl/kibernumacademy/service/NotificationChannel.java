package cl.kibernumacademy.service;

import cl.kibernumacademy.model.Notification;

public interface NotificationChannel {
    public boolean send(Notification notification);
    public String getChannelName();
}
