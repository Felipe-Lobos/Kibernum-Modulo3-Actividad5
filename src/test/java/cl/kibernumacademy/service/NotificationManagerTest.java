package cl.kibernumacademy.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.kibernumacademy.model.NotificationHistory;

@ExtendWith(MockitoExtension.class)
public class NotificationManagerTest {

    @Mock
    private NotificationChannel smsChannelMock;
    @Mock
    private NotificationChannel emailChannelMock;


    private NotificationManager notificationManager;
    @BeforeEach
    void setUp(){
        notificationManager = new NotificationManager(smsChannelMock,emailChannelMock);
    }

    @ParameterizedTest
    @CsvSource({
        "Mensaje 1: hola buenas tardes por correo , felipe@mail.com , EMAIL",
        "Mensaje 1: hola buenas dias por sms , +56911223355 , SMS",
    })
    void enviarNotificacionEmail(String mensaje, String destinatario, String canal ){
        boolean notificationSent =  notificationManager.sendNotification(mensaje,destinatario,canal);
        assertTrue(notificationSent);
        List<NotificationHistory> notifications =  notificationManager.getNotifications();
        assertEquals(notifications.size(), 1);
    }


}
