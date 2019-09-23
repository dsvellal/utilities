/*
 * Copyright of Philips, 2019
 *
 * This file is subject to the terms and conditions defined by Philips,
 * visit https://www.ip.philips.com/licensing/ for more details.
 *
 */
package com.philips.elasticsearch.metrics.model;

import java.util.UUID;

import com.philips.elasticsearch.metrics.utils.ElasticSearchClientUtility;

/**
 * The Class MetricAttributes.
 *
 * @param <T> the generic type
 */
public class MetricAttributes<T> {

  /** The key. */
  private final String key;

  /** The value. */
  private final T value;

  /** The clazz name. */
  private String clazzName;

  /** The id. */
  private final UUID id;

  /** The timestamp. */
  private final long timestamp;

  /** The timestamp str. */
  private final String timestampStr;

  /**
   * Instantiates a new metric attributes.
   *
   * @param key the key
   * @param value the value
   * @param timestamp the timestamp
   */
  public MetricAttributes(final String key, final T value, final long timestamp) {
    this.key = key;
    this.value = value;
    this.id = UUID.randomUUID();
    this.timestamp = timestamp;
    this.timestampStr = ElasticSearchClientUtility.getDateFieldType().dateTimeFormatter().formatMillis(timestamp);
  }

  /**
   * Gets the key.
   *
   * @return the key
   */
  public String getKey() {
    return key;
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public T getValue() {
    return value;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public UUID getId() {
    return id;
  }

  /**
   * Gets the clazz name.
   *
   * @return the clazz name
   */
  public String getClazzName() {
    return clazzName;
  }

  /**
   * Sets the clazz name.
   *
   * @param clazzName the new clazz name
   */
  public void setClazzName(final String clazzName) {
    this.clazzName = clazzName;
  }

  /**
   * Gets the timestamp string.
   *
   * @return the timestamp string
   */
  public String getTimestampString() {
    return this.timestampStr;
  }

  /**
   * Gets the timestamp.
   *
   * @return the timestamp
   */
  public long getTimestamp() {
    return this.timestamp;
  }

}
