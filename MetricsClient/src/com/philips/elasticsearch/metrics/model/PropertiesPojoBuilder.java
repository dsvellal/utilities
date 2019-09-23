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
 * The Class PropertiesPojoBuilder.
 */
public class PropertiesPojoBuilder {

  /** The host. */
  private String host;

  /** The port. */
  private int port;

  /** The index. */
  private String index;

  /** The type. */
  private String type;

  /**
   * Instantiates a new properties pojo builder.
   */
  public PropertiesPojoBuilder() {
    this.port = -1;
  }

  /**
   * Sets the host.
   *
   * @param host the host
   * @return the properties pojo builder
   */
  public PropertiesPojoBuilder setHost(final String host) {
    this.host = Objects.requireNonNull(host, "Specified host is null or empty");
    return this;
  }

  /**
   * Sets the port.
   *
   * @param port the port
   * @return the properties pojo builder
   */
  public PropertiesPojoBuilder setPort(final int port) {
    if (port <= 0) {
      throw new IllegalArgumentException("Specified port: " + port + " does not belong to the exposed port range");
    }
    this.port = port;
    return this;
  }

  /**
   * Sets the index.
   *
   * @param index the index
   * @return the properties pojo builder
   */
  public PropertiesPojoBuilder setIndex(final String index) {
    this.index = Objects.requireNonNull(index, "Specified index is null or empty");
    return this;
  }

  /**
   * Sets the type.
   *
   * @param type the type
   * @return the properties pojo builder
   */
  public PropertiesPojoBuilder setType(final String type) {
    this.type = Objects.requireNonNull(type, "Specified type is null or empty");
    return this;
  }

  /**
   * Builds the.
   *
   * @return the properties pojo
   */
  public PropertiesPojo build() {
    if (host == null || port == -1 || index == null || type == null) {
      throw new IllegalArgumentException("Can not construct PropertiesPojo because attributes are not correct");
    }

    return new PropertiesPojo(host, port, index, type);
  }

}
