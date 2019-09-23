/*
 * Copyright of Philips, 2019
 *
 * This file is subject to the terms and conditions defined by Philips,
 * visit https://www.ip.philips.com/licensing/ for more details.
 *
 */
package com.philips.elasticsearch.metrics.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * The Class PropertiesPojoTest.
 */
public class PropertiesPojoTest {

  /** The mocked index. */
  private String mockedIndex = null;

  /** The mocked type. */
  private String mockedType = null;

  /** The mocked host. */
  private String mockedHost = null;

  /** The mocked port. */
  private int mockedPort = -1;

  /**
   * Happy properties pojo instantiation.
   */
  @Test
  public void happyPropertiesPojoInstantiation() {
    final PropertiesPojo properties = getPropertiesInstance("mockedIndex", "mockedType", "mockedHost", 100);
    assertEquals(mockedIndex, properties.getIndex());
    assertEquals(mockedType, properties.getType());
    assertEquals(mockedHost, properties.getHost());
    assertEquals(mockedPort, properties.getPort());
  }

  /**
   * Exception null host value.
   */
  @Test(expected = NullPointerException.class)
  public void exceptionNullHostValue() {
    final PropertiesPojo properties = getPropertiesInstance("mockedIndex", "mockedType", null, 100);
  }

  /**
   * Exception null index value.
   */
  @Test(expected = NullPointerException.class)
  public void exceptionNullIndexValue() {
    final PropertiesPojo properties = getPropertiesInstance(null, "mockedType", "mockedLocalhost", 100);
  }

  /**
   * Exception null type value.
   */
  @Test(expected = NullPointerException.class)
  public void exceptionNullTypeValue() {
    final PropertiesPojo properties = getPropertiesInstance("mockedIndex", null, "mockedLocalhost", 100);
  }

  /**
   * Exception invalid port value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void exceptionInvalidPortValue() {
    final PropertiesPojo properties = getPropertiesInstance("mockedIndex", "mockedType", "mockedLocalhost", -1);
  }

  /**
   * Happy to string test.
   */
  @Test
  public void happyToStringTest() {
    final String JOINER = ":";
    final PropertiesPojo properties = getPropertiesInstance("mockedIndex", "mockedType", "mockedHost", 100);
    final String expectedToStringValue = this.mockedHost + JOINER + String.valueOf(this.mockedPort) + JOINER
        + this.mockedIndex + JOINER + this.mockedType;
    assertEquals(expectedToStringValue, properties.toString());
  }

  /**
   * Gets the properties instance.
   *
   * @param index the index
   * @param type the type
   * @param host the host
   * @param port the port
   * @return the properties instance
   */
  private PropertiesPojo getPropertiesInstance(final String index, final String type, final String host, final int port) {
    this.mockedIndex = index;
    this.mockedType = type;
    this.mockedHost = host;
    this.mockedPort = port;
    return (new PropertiesPojo(host, port, index, type));
  }

}
