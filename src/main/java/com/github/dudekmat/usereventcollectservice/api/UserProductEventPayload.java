package com.github.dudekmat.usereventcollectservice.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

record UserProductEventPayload(@NotBlank String eventType, @NotBlank String userId,
                               @NotBlank String sessionId, @NotNull Long eventTime,
                               @NotBlank String productId, String pageName, String boxName,
                               String boxPosition, String platform) {

}
