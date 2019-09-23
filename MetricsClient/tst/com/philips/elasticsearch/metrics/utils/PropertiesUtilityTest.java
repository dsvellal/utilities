/*
 * Copyright of Philips, 2019
 *
 * This file is subject to the terms and conditions defined by Philips,
 * visit https://www.ip.philips.com/licensing/ for more details.
 *
 */
package com.philips.elasticsearch.metrics.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.philips.elasticsearch.metrics.model.PropertiesPojo;

import junit.framework.AssertionFailedError;

public class PropertiesUtilityTest {

  private static final String DUMMY_HOST = "dummyHost";

  /** The Constant DUMMY_PORT. */
  private static final int DUMMY_PORT = 9200;
  private static final String DUMMY_INDEX = "dummyIndex";
  private static final String DUMMY_TYPE = "dummyType";

  @Test
  public void testDefaultPath() {
    try {
      final PropertiesUtility propertiesUtility = getPropertiesUtility();
      assertNotNull(propertiesUtility.getPropertiesPojo());
    } catch (final AssertionFailedError e) {
      assertFalse("Unable to create properties utility", true);
    }
  }

  @Test
  public void testCustomPathHappyCase() {
    try {
      final PropertiesUtility propertiesUtility = new PropertiesUtility("tst/test_properties/happy_case_properties.txt");
      final PropertiesPojo pojo = propertiesUtility.getPropertiesPojo();
      assertEquals(DUMMY_HOST, pojo.getHost());
      assertEquals(DUMMY_PORT, pojo.getPort());
      assertEquals(DUMMY_INDEX, pojo.getIndex());
      assertEquals(DUMMY_TYPE, pojo.getType());

    } catch (final IOException e) {
      assertFalse("Unable to create properties utility", true);
    }
  }

  @Test
  (expected = IllegalArgumentException.class)
  public void testCustomPathEmptyPropertiesFile() throws IOException {
    final PropertiesUtility propertiesUtility = new PropertiesUtility("tst/test_properties/empty_properties.txt");
    propertiesUtility.getPropertiesPojo();
  }

  @Test
  (expected = IllegalArgumentException.class)
  public void testCustomPathInvalidKey() throws IOException {
    final PropertiesUtility propertiesUtility = new PropertiesUtility("tst/test_properties/invalid_key.txt");
    propertiesUtility.getPropertiesPojo();
  }

  @Test
  (expected = IllegalArgumentException.class)
  public void testCustomPathEmptyKey() throws IOException {
    final PropertiesUtility propertiesUtility = new PropertiesUtility("tst/test_properties/empty_key.txt");
    propertiesUtility.getPropertiesPojo();
  }

  @Test
  (expected = IllegalArgumentException.class)
  public void testCustomPathEmptyValue() throws IOException {
    final PropertiesUtility propertiesUtility = new PropertiesUtility("tst/test_properties/empty_value.txt");
    propertiesUtility.getPropertiesPojo();
  }

  @Test
  (expected = IOException.class)
  public void testCustomInvalidPath() throws IOException {
    new PropertiesUtility("some_invalid_path");
  }

  private PropertiesUtility getPropertiesUtility() {
    try {
      return new PropertiesUtility();
    } catch (final IOException e) {
      throw new AssertionFailedError();
    }
  }

}
