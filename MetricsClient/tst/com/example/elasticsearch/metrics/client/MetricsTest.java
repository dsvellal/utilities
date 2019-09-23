/*
 * Copyright of Philips, 2019
 *
 * This file is subject to the terms and conditions defined by Philips,
 * visit https://www.ip.philips.com/licensing/ for more details.
 *
 */
package com.philips.elasticsearch.metrics.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.philips.elasticsearch.metrics.utils.PropertiesUtilityTest;

import junit.framework.AssertionFailedError;

/**
 * The Class MetricsTest.
 */
public class MetricsTest {

  /** The Constant EXPECTED_HOST. */
  private static final String EXPECTED_HOST = "localhost";

  /** The Constant EXPECTED_PORT. */
  private static final int EXPECTED_PORT = 9200;

  /** The Constant EXPECTED_INDEX. */
  private static final String EXPECTED_INDEX = "metricsdemoindex";

  /** The Constant EXPECTED_TYPE. */
  private static final String EXPECTED_TYPE = "metrics";

  /** The dummy clazz. */
  private Class<PropertiesUtilityTest> dummyClazz;

  /**
   * Test get clazz.
   */
  @Test
  public void testGetClazz() {
    try {
      final Metrics metrics = getMetricsInstance();
      assertEquals(dummyClazz, metrics.getClazz());
    } catch (final AssertionFailedError e) {
      assertFalse("Something went wrong", true);
    }
  }

  /**
   * Test get properties.
   */
  @Test
  public void testGetProperties() {
    try {
      final Metrics metrics = getMetricsInstance();
      assertNotNull(metrics.getPropertiesPojo());
      assertEquals(EXPECTED_HOST, metrics.getPropertiesPojo().getHost());
      assertEquals(EXPECTED_TYPE, metrics.getPropertiesPojo().getType());
      assertEquals(EXPECTED_INDEX, metrics.getPropertiesPojo().getIndex());
      assertEquals(EXPECTED_PORT, metrics.getPropertiesPojo().getHost());
    } catch (final AssertionFailedError e) {
      assertFalse("Something went wrong", true);
    }

  }


  /**
   * Gets the metrics instance.
   *
   * @return the metrics instance
   */
  private Metrics getMetricsInstance() {
    Metrics metrics = null;
    dummyClazz = PropertiesUtilityTest.class;
    try {
      metrics = new Metrics(dummyClazz);
    } catch (final IOException e) {
      throw new AssertionFailedError("Unable to create Metrics instance");
    }

    return metrics;
  }

}
