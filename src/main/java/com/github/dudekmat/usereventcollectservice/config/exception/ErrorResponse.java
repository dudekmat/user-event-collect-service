package com.github.dudekmat.usereventcollectservice.config.exception;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static java.util.Collections.emptyList;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
@JsonInclude(NON_EMPTY)
public class ErrorResponse {

  private final String message;
  private final List<Object> errors;

  private ErrorResponse(String message) {
    this.message = message;
    errors = emptyList();
  }

  private ErrorResponse(ErrorResponseBuilder builder) {
    this.message = builder.message;
    this.errors = builder.errors;
  }

  public static ErrorResponse fromMessage(String message) {
    return new ErrorResponse(message);
  }

  public static ErrorResponseBuilder builder() {
    return new ErrorResponseBuilder();
  }

  public static class ErrorResponseBuilder {

    private String message;
    private List<Object> errors;

    private ErrorResponseBuilder() {
      this.errors = new ArrayList<>();
    }

    public ErrorResponseBuilder message(String message) {
      this.message = message;
      return this;
    }

    public ErrorResponseBuilder addError(Object error) {
      this.errors.add(error);
      return this;
    }

    public ErrorResponseBuilder addErrors(List<Object> errors) {
      this.errors.addAll(errors);
      return this;
    }

    public ErrorResponse build() {
      return new ErrorResponse(this);
    }
  }
}
