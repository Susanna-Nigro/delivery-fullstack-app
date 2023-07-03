package com.sn.deliveryserver.exceptions;

public class UnmodifiableException extends Exception {
  public UnmodifiableException(String errorMsg) {
    super(errorMsg);
  }
}
