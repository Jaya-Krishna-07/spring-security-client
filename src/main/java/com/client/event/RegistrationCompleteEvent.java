package com.client.event;

import com.client.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

@Data
public class RegistrationCompleteEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;
    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;

    }
}
