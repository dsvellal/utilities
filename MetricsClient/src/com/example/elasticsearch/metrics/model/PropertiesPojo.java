/*
 * Copyright of Philips, 2019
 *
 * This file is subject to the terms and conditions defined by Philips,
 * visit https://www.ip.philips.com/licensing/ for more details.
 *
 */
package com.philips.elasticsearch.metrics.model;

import java.util.Objects;

/**
 * The Class PropertiesPojo.
 */
public class PropertiesPojo {

  /** The Constant JOINER. */
  private static final String JOINER = ":";

  /** The host. */
  private final String host;

  /** The port. */
  private final int port;

  /** The index. */
  private final String index;

  /** The type. */
  private final String type;

  /**
   * Instantiates a new properties pojo.
   *
   * @param host the host
   * @param port the port
   * @param index the index
   * @param type the type
   */
  public PropertiesPojo(final String host, final int port, final String index, final String type) {
    this.host = Objects.requireNonNull(host, "Specified host is null or empty");
    this.index = Objects.requireNonNull(index, "Specified index is null or empty");
    this.type = Objects.requireNonNull(type, "Specified type is null or empty");

    if (port <= 0) {
      throw new IllegalArgumentException("Specified port: " + port + " does not belong to the exposed port range");
    }
    this.port = port;
  }

  /**
   * Gets the host.
   *
   * @return the host
   */
  public String getHost() {
    return host;
  }

  /**
   * Gets the port.
   *
   * @return the port
   */
  public int getPort() {
    return port;
  }

  /**
   * Gets the index.
   *
   * @return the index
   */
  public String getIndex() {
    return index;
  }

  /**
   * Gets the type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  @Override
  public String toString() {
    return (host + JOINER + String.valueOf(port) + JOINER + index + JOINER + type);
  }

}
