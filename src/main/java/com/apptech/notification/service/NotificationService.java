package com.apptech.notification.service;

import com.apptech.notification.dto.NotificationDTO;

public interface NotificationService {

	public boolean sendNotification(NotificationDTO dto);
}
