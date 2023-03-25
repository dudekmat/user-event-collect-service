package com.github.dudekmat.usereventcollectservice.api;

import com.github.dudekmat.usereventcollectservice.event.UserEvent;
import com.github.dudekmat.usereventcollectservice.event.UserEventProducer;
import com.github.dudekmat.usereventcollectservice.event.UserProductEvent;
import com.github.dudekmat.usereventcollectservice.event.UserSearchEvent;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Validated
@RequiredArgsConstructor
class UserEventController {

  private final UserEventProducer userEventProducer;

  @PostMapping("/product-events")
  void collectProductEvents(@RequestBody @Valid List<UserProductEventPayload> userProductEvents) {
    userProductEvents.forEach(event -> publishUserEvent(UserProductEvent.builder()
        .eventType(event.eventType())
        .userId(event.userId())
        .sessionId(event.sessionId())
        .eventTime(event.eventTime())
        .productId(event.productId())
        .pageName(event.pageName())
        .boxName(event.boxName())
        .boxPosition(event.boxPosition())
        .platform(event.platform())
        .build()));
  }

  @PostMapping("/search-events")
  void collectSearchEvents(@RequestBody @Valid List<UserSearchEventPayload> userSearchEvents) {
    userSearchEvents.forEach(event -> publishUserEvent(UserSearchEvent.builder()
        .eventType(event.eventType())
        .userId(event.userId())
        .sessionId(event.sessionId())
        .eventTime(event.eventTime())
        .productId(event.productId())
        .query(event.query())
        .searchParams(event.searchParams())
        .platform(event.platform())
        .build()));
  }

  private void publishUserEvent(UserEvent userEvent) {
    userEventProducer.sendUserEvent(userEvent);
  }
}
