package com.github.dudekmat.usereventcollectservice.event;

public interface UserEventProducer {

  void sendUserEvent(UserEvent userEvent);

}
