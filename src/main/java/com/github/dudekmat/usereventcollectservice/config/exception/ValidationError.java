package com.github.dudekmat.usereventcollectservice.config.exception;

import lombok.Builder;

@Builder
record ValidationError(String object, String field, Object rejectedValue, String message,
                       String validationCode) {

}
