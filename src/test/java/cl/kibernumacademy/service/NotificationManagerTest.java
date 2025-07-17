package cl.kibernumacademy.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.kibernumacademy.model.Notification;
import cl.kibernumacademy.model.NotificationHistory;

@ExtendWith(MockitoExtension.class)
public class NotificationManagerTest {

    @Mock
    private NotificationChannel smsChannelMock;
    @Mock
    private NotificationChannel emailChannelMock;

    @Captor
    private ArgumentCaptor<Notification> notificationCaptor;

    @InjectMocks
    private NotificationManager notificationManager;

    @BeforeEach
    void setUp() {
        notificationManager = new NotificationManager(smsChannelMock, emailChannelMock);
    }

    @ParameterizedTest
    @CsvSource({
            "Mensaje 1: hola buenas tardes por correo , felipe@mail.com , EMAIL"
    })
    void sentNotificationByEmail_successTest(String mensaje, String destinatario, String canal) {
        Notification emailNotification = new Notification(mensaje, destinatario);
        given(emailChannelMock.send(emailNotification)).willReturn(true);
        boolean notificationSent = notificationManager.sendNotification(emailNotification, canal);
        assertTrue(notificationSent);
        verify(emailChannelMock).send(emailNotification);
        List<NotificationHistory> notifications = notificationManager.getNotifications();
        assertEquals(notifications.size(), 1);

        verify(emailChannelMock, times(1)).send(notificationCaptor.capture());
        assertEquals(notificationCaptor.getValue().getMensaje(), mensaje);
        assertEquals(notificationCaptor.getValue().getDestinatario(), destinatario);
    }

    @ParameterizedTest
    @CsvSource({
            "Mensaje 1: hola buenas tardes por correo , +1122336655 , SMS"
    })
    void sentNotificationBySMS_successTest(String mensaje, String destinatario, String canal) {
        Notification smsNotification = new Notification(mensaje, destinatario);
        given(smsChannelMock.send(smsNotification)).willReturn(true);
        boolean notificationSent = notificationManager.sendNotification(smsNotification, canal);
        assertTrue(notificationSent);
        verify(smsChannelMock).send(smsNotification);
        List<NotificationHistory> notifications = notificationManager.getNotifications();
        assertEquals(notifications.size(), 1);

        verify(smsChannelMock, times(1)).send(notificationCaptor.capture());
        assertEquals(notificationCaptor.getValue().getMensaje(), mensaje);
        assertEquals(notificationCaptor.getValue().getDestinatario(), destinatario);
    }

    @ParameterizedTest
    @CsvSource({
            "Mensaje 1: hola buenas tardes por correo , felipe@mail.com , EMAIL"
    })
    void sentNotificationByEmail_failureTest(String mensaje, String destinatario, String canal) {
        Notification emailNotification = new Notification(mensaje, destinatario);
        given(emailChannelMock.send(emailNotification)).willReturn(false);
        boolean notificationSent = notificationManager.sendNotification(emailNotification, canal);
        assertFalse(notificationSent);
        List<NotificationHistory> notifications = notificationManager.getNotifications();
        assertEquals(notifications.size(), 0);
        // verify(smsChannelMock).send(emailNotification);
    }

    @ParameterizedTest
    @CsvSource({
            "Mensaje 1: hola buenas tardes por correo , +1122336655 , SMS"
    })
    void sentNotificationBySMS_failureTest(String mensaje, String destinatario, String canal) {
        Notification smsNotification = new Notification(mensaje, destinatario);
        given(smsChannelMock.send(smsNotification)).willReturn(false);
        boolean notificationSent = notificationManager.sendNotification(smsNotification, canal);
        assertFalse(notificationSent);
        List<NotificationHistory> notifications = notificationManager.getNotifications();
        assertEquals(notifications.size(), 0);
        // verify(smsChannelMock).send(emailNotification);
    }

    @Test
    void sentEmailNotification_invalidChannelException() {
        Notification notification = new Notification("mensaje", "destinatario");
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> notificationManager.sendNotification(notification, "canalInvalido")
            );

        assertEquals("ERROR EN EL CANAL ESPECIFICADO", exception.getMessage());

        List<NotificationHistory> notifications = notificationManager.getNotifications();
        assertEquals(notifications.size(), 0);

    }

}
