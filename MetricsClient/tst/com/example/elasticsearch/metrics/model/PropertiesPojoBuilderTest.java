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
 * The Class PropertiesPojoBuilderTest.
 */
public class PropertiesPojoBuilderTest {

  /** The Constant DUMMY_HOST. */
  private static final String DUMMY_HOST = "dummy_host";

  /** The Constant DUMMY_PORT. */
  private static final int DUMMY_PORT = 1080;

  /** The Constant DUMMY_TYPE. */
  private static final String DUMMY_TYPE = "dummy_type";

  /** The Constant DUMMY_INDEX. */
  private static final String DUMMY_INDEX = "dummy_index";

  /** The pojo. */
  private PropertiesPojo pojo;

  /**
   * Exception builder instance creation and immediately build.
   */
  @Test
  (expected = IllegalArgumentException.class)
  public void exceptionBuilderInstanceCreationAndImmediatelyBuild() {
    final PropertiesPojoBuilder pojoBuilder = new PropertiesPojoBuilder();
    pojoBuilder.build();
  }

  /**
   * Exception builder instance creation with null host.
   */
  @Test
  (expected = IllegalArgumentException.class)
  public void exceptionBuilderInstanceCreationWithNullHost() {
    final PropertiesPojoBuilder pojoBuilder = new PropertiesPojoBuilder();
    pojoBuilder.setIndex(DUMMY_INDEX);
    pojoBuilder.setPort(DUMMY_PORT);
    pojoBuilder.setType(DUMMY_TYPE);
    pojoBuilder.build();
  }

  /**
   * Exception builder instance creation with no port.
   */
  @Test
  (expected = IllegalArgumentException.class)
  public void exceptionBuilderInstanceCreationWithNoPort() {
    final PropertiesPojoBuilder pojoBuilder = new PropertiesPojoBuilder();
    pojoBuilder.setHost(DUMMY_HOST);
    pojoBuilder.setIndex(DUMMY_INDEX);
    pojoBuilder.setType(DUMMY_TYPE);
    pojoBuilder.build();
  }

  /**
   * Exception builder instance creation with null type.
   */
  @Test
  (expected = IllegalArgumentException.class)
  public void exceptionBuilderInstanceCreationWithNullType() {
    final PropertiesPojoBuilder pojoBuilder = new PropertiesPojoBuilder();
    pojoBuilder.setHost(DUMMY_HOST);
    pojoBuilder.setIndex(DUMMY_INDEX);
    pojoBuilder.setPort(DUMMY_PORT);
    pojoBuilder.build();
  }

  /**
   * Exception builder instance creation with null index.
   */
  @Test
  (expected = IllegalArgumentException.class)
  public void exceptionBuilderInstanceCreationWithNullIndex() {
    final PropertiesPojoBuilder pojoBuilder = new PropertiesPojoBuilder();
    pojoBuilder.setHost(DUMMY_HOST);
    pojoBuilder.setPort(DUMMY_PORT);
    pojoBuilder.setType(DUMMY_TYPE);
    pojoBuilder.build();
  }

  /**
   * Exception builder instance creation with negetive port.
   */
  @Test
  (expected = IllegalArgumentException.class)
  public void exceptionBuilderInstanceCreationWithNegetivePort() {
    final PropertiesPojoBuilder pojoBuilder = new PropertiesPojoBuilder();
    pojoBuilder.setPort(-100);
  }

  /**
   * Happy case builder instance creation.
   */
  @Test
  public void HappyCaseBuilderInstanceCreation() {
    final PropertiesPojoBuilder pojoBuilder = new PropertiesPojoBuilder();
    pojoBuilder.setHost(DUMMY_HOST);
    pojoBuilder.setIndex(DUMMY_INDEX);
    pojoBuilder.setPort(DUMMY_PORT);
    pojoBuilder.setType(DUMMY_TYPE);
    pojo = pojoBuilder.build();

    assertEquals(DUMMY_HOST, pojo.getHost());
    assertEquals(DUMMY_PORT, pojo.getPort());
    assertEquals(DUMMY_INDEX, pojo.getIndex());
    assertEquals(DUMMY_TYPE, pojo.getType());
  }



}
