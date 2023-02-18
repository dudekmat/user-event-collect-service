package com.github.dudekmat.usereventcollectservice.event;

import lombok.Builder;

@Builder
public record UserSearchEvent(String eventType, String userId, String sessionId, Long eventTime,
                       String productId, String query, String searchParams,
                       String platform) implements UserEvent {

  @Override
  public String key() {
    return userId;
  }
}
