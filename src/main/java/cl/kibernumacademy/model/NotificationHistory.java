package cl.kibernumacademy.model;

import java.time.LocalDateTime;

public class NotificationHistory {

    private Notification notification;
    private String channel;
    private LocalDateTime sentAt;

    public NotificationHistory(Notification notification, String channel) {
        this.notification = notification;
        this.channel = channel;
        this.sentAt = LocalDateTime.now();
    }

    public Notification getNotification() {
        return notification;
    }

    public String getChannel() {
        return channel;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    @Override
    public String toString() {
        return "NotificationHistory [notification=" + notification + ", channel=" + channel + ", sentAt=" + sentAt
                + "]";
    }

}
