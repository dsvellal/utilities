/*
 * Copyright of Philips, 2019
 *
 * This file is subject to the terms and conditions defined by Philips,
 * visit https://www.ip.philips.com/licensing/ for more details.
 *
 */
package com.philips.elasticsearch.metrics.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.philips.elasticsearch.metrics.utils.ElasticSearchClientUtility;

/**
 * The Class MetricAttributesTest.
 */
public class MetricAttributesTest {

  /** The attributes. */
  private MetricAttributes<Double> attributes = null;

  /** The mocked key. */
  private String mockedKey = null;

  /** The mocked value. */
  private Double mockedValue = null;

  /** The mocked timestamp. */
  private long mockedTimestamp = 0L;

  /**
   * Setup.
   */
  @Before
  public void setup() {
    mockedKey = "mockedKey";
    mockedValue = 1.01;
    mockedTimestamp = 1556668800034L;
    attributes = new MetricAttributes<>(mockedKey, mockedValue, mockedTimestamp);
  }

  /**
   * Happy metric attributes instance creation.
   */
  @Test
  public void happyMetricAttributesInstanceCreation() {
    assertEquals(mockedKey, attributes.getKey());
    assertEquals(mockedValue, attributes.getValue());
    assertEquals(mockedTimestamp, attributes.getTimestamp());
    assertNotNull(attributes.getId());
    assertEquals(UUID.class, attributes.getId().getClass());
    final String expectedTimestampStr = ElasticSearchClientUtility.getDateFieldType().dateTimeFormatter()
        .formatMillis(mockedTimestamp);
    assertEquals(expectedTimestampStr, attributes.getTimestampString());
  }

  /**
   * Happy metric attributes class get and set.
   */
  @Test
  public void happyMetricAttributesClassGetAndSet() {
    final Class<MetricAttributesTest> mockedClazz = MetricAttributesTest.class;
    final String expectedClassName = mockedClazz.getName();
    attributes.setClazzName(expectedClassName);
    assertEquals(expectedClassName, attributes.getClazzName());
  }

}
