package com.github.dudekmat.usereventcollectservice.event;

import lombok.Builder;

@Builder
public record UserProductEvent(String eventType, String userId, String sessionId, Long eventTime,
                        String productId, String pageName, String boxName, String boxPosition,
                        String platform) implements UserEvent {

  @Override
  public String key() {
    return userId;
  }
}
