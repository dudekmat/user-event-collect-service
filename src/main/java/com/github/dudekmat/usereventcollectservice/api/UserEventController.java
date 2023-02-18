package com.github.dudekmat.usereventcollectservice.api;

import com.github.dudekmat.usereventcollectservice.event.UserEvent;
import com.github.dudekmat.usereventcollectservice.event.UserEventProducer;
import com.github.dudekmat.usereventcollectservice.event.UserProductEvent;
import com.github.dudekmat.usereventcollectservice.event.UserSearchEvent;
import jakarta.validation.Valid;
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
  void collectProductEvents(@RequestBody @Valid UserProductEventPayload userProductEventPayload) {
    publishUserEvent(UserProductEvent.builder()
        .eventType(userProductEventPayload.eventType())
        .userId(userProductEventPayload.userId())
        .sessionId(userProductEventPayload.sessionId())
        .eventTime(userProductEventPayload.eventTime())
        .productId(userProductEventPayload.productId())
        .pageName(userProductEventPayload.pageName())
        .boxName(userProductEventPayload.boxName())
        .boxPosition(userProductEventPayload.boxPosition())
        .platform(userProductEventPayload.platform())
        .build());
  }

  @PostMapping("/search-events")
  void collectSearchEvents(@RequestBody @Valid UserSearchEventPayload userSearchEventPayload) {
    publishUserEvent(UserSearchEvent.builder()
        .eventType(userSearchEventPayload.eventType())
        .userId(userSearchEventPayload.userId())
        .sessionId(userSearchEventPayload.sessionId())
        .eventTime(userSearchEventPayload.eventTime())
        .productId(userSearchEventPayload.productId())
        .query(userSearchEventPayload.query())
        .searchParams(userSearchEventPayload.searchParams())
        .platform(userSearchEventPayload.platform())
        .build());
  }

  private void publishUserEvent(UserEvent userEvent) {
    userEventProducer.sendUserEvent(userEvent);
  }
}
