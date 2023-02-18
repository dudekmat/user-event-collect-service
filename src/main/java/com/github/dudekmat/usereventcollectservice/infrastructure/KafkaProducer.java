package com.github.dudekmat.usereventcollectservice.infrastructure;

import static java.util.Objects.nonNull;

import com.github.dudekmat.usereventcollectservice.event.UserEvent;
import com.github.dudekmat.usereventcollectservice.event.UserEventProducer;
import com.github.dudekmat.usereventcollectservice.event.UserProductEvent;
import com.github.dudekmat.usereventcollectservice.event.UserSearchEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class KafkaProducer implements UserEventProducer {

  private final KafkaTemplate<String, UserEvent> kafkaTemplate;

  public static final String USER_PRODUCT_EVENT_TOPIC = "user-product-event";
  public static final String USER_SEARCH_EVENT_TOPIC = "user-search-event";

  @Override
  public void sendUserEvent(UserEvent userEvent) {
    if (userEvent instanceof UserProductEvent) {
      sendToKafka(USER_PRODUCT_EVENT_TOPIC, userEvent);
    }
    if (userEvent instanceof UserSearchEvent) {
      sendToKafka(USER_SEARCH_EVENT_TOPIC, userEvent);
    }
  }

  private void sendToKafka(String topic, UserEvent userEvent) {
    kafkaTemplate.send(topic, userEvent.key(), userEvent)
        .thenAccept(message -> {
          if (nonNull(message)) {
            log.info("Sent message={} with offset=[{}]", message,
                message.getRecordMetadata().offset());
          }
        }).exceptionally(ex -> {
          log.error("Unable to send message due to: {}", ex.getMessage());
          return null;
        });
  }
}
