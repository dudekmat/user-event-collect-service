package com.github.dudekmat.usereventcollectservice.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

record UserSearchEventPayload(@NotBlank String eventType, @NotBlank String userId,
                              @NotBlank String sessionId, @NotNull Long eventTime,
                              @NotBlank String productId, @NotBlank String query,
                              String searchParams, String platform) {

}
