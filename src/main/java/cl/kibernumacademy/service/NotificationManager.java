package cl.kibernumacademy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.kibernumacademy.model.Notification;
import cl.kibernumacademy.model.NotificationHistory;

public class NotificationManager {
    private static final String SMS = "SMS";
    private static final String EMAIL = "EMAIL";

    // Lista donde se guardan las notificaciones enviadas
    private List<NotificationHistory> notifications = new ArrayList<>();
    // Aquí se guardan los canales disponibles (SMS, EMAIL, etc)
    private final Map<String, NotificationChannel> channelMap;

    // Constructor, recibe los canales y los guarda en el mapa
    public NotificationManager(NotificationChannel smsChannel, NotificationChannel emailChannel) {
        channelMap = new HashMap<>();
        channelMap.put(SMS, smsChannel);
        channelMap.put(EMAIL, emailChannel);
    }

    // Este método manda la notificación por el canal que le digas
    public boolean sendNotification(Notification notification, String canal) {
        String mensaje = notification.getMensaje();
        String destinatario = notification.getDestinatario();
        // Si falta el mensaje o el destinatario, no se envía nada
        if (mensaje == null || destinatario == null || mensaje.isEmpty() || destinatario.isEmpty()) {
            return false;
        }

        // Busca el canal en el mapa, si no existe lanza error
        NotificationChannel channel = channelMap.get(canal.toUpperCase());
        if (channel == null) {
            throw new IllegalArgumentException("ERROR EN EL CANAL ESPECIFICADO");
        }
        
        // Intenta enviar la notificación, si sale bien la guarda en el historial
        boolean result = channel.send(notification);
        if (result) {
            saveNotification(notification, canal);
        }
        return result;
    }

    // Guarda la notificación en la lista de historial
    private void saveNotification(Notification notification, String canal) {
        notifications.add(new NotificationHistory(notification, canal));
    }

    // Devuelve la lista de notificaciones enviadas (no se puede modificar desde fuera)
    public List<NotificationHistory> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }
}
