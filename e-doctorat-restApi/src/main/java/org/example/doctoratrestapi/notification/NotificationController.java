package org.example.doctoratrestapi.notification;

import org.example.doctoratrestapi.mappers.notification.NotificationMapper;
import org.example.doctoratrestapi.models.NotificationModel;
import org.example.doctoratrestapi.dtos.notification.NotificationCreationDTO;
import org.example.doctoratrestapi.dtos.notification.NotificationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    public NotificationController(NotificationService notificationService,
                                  NotificationMapper notificationMapper) {
        this.notificationService = notificationService;
        this.notificationMapper = notificationMapper;
    }

    // Endpoint : "api/notifications/notification/add"
    @PostMapping("/notification/add")
    public ResponseEntity<Void> addNotification(@RequestBody NotificationCreationDTO request) {
        NotificationModel entity = notificationMapper.toEntity(request);
        notificationService.createNotification(entity, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Endpoint : "api/notifications/{candidatId}/getNotifications"
    @GetMapping("/{candidatId}/getNotifications")
    public ResponseEntity<List<NotificationDTO>> getNotifications(@PathVariable Long candidatId) {
        List<NotificationModel> entities =
                notificationService.getNotificationsByCandidatId(candidatId);

        List<NotificationDTO> dtos = entities.stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
