/*
 * Copyright of Philips, 2019
 *
 * This file is subject to the terms and conditions defined by Philips,
 * visit https://www.ip.philips.com/licensing/ for more details.
 *
 */
package com.philips.elasticsearch.metrics.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.philips.elasticsearch.metrics.model.PropertiesPojo;

/**
 * The Class ElasticSearchClientUtilityTest.
 */
public class ElasticSearchClientUtilityTest {

  /** The Constant DUMMY_HOST. */
  private static final String DUMMY_HOST = "dummyHost";

  /** The Constant DUMMY_PORT. */
  private static final int DUMMY_PORT = 9200;

  /** The Constant DUMMY_INDEX. */
  private static final String DUMMY_INDEX = "dummyIndex";

  /** The Constant DUMMY_TYPE. */
  private static final String DUMMY_TYPE = "dummyType";

  /** The mocked rest high level client. */
  private RestHighLevelClient mockedRestHighLevelClient;

  /** The mocked elastic search instance map. */
  private Map<String, RestHighLevelClient> mockedElasticSearchInstanceMap;

  /** The pojo. */
  private PropertiesPojo pojo;

  /**
   * Sets the up.
   */
  @Before
  public void setUp() {
    pojo = new PropertiesPojo(DUMMY_HOST, DUMMY_PORT, DUMMY_INDEX, DUMMY_TYPE);
    mockedRestHighLevelClient = Mockito.mock(RestHighLevelClient.class);
    mockedElasticSearchInstanceMap = new HashMap<>();
    mockedElasticSearchInstanceMap.put(pojo.toString(), mockedRestHighLevelClient);
    ElasticSearchClientUtility.setElasticSearchInstanceMap(mockedElasticSearchInstanceMap);
  }

  /**
   * Test get of existing elastic search client.
   */
  @Test
  public void testGetOfExistingElasticSearchClient() {
    final RestHighLevelClient actualRestHighLevelClient = ElasticSearchClientUtility.getElasticSearchClient(pojo);
    assertEquals(mockedRestHighLevelClient, actualRestHighLevelClient);
  }

  /**
   * Test get of non existing elastic search client.
   */
  @Test
  public void testGetOfNonExistingElasticSearchClient() {
    final PropertiesPojo pojo = new PropertiesPojo(DUMMY_HOST, 1234, DUMMY_INDEX, DUMMY_TYPE);
    final RestHighLevelClient actualRestHighLevelClient = ElasticSearchClientUtility.getElasticSearchClient(pojo);
    assertNotNull(actualRestHighLevelClient);
  }

  /**
   * Test get date field type.
   */
  @Test
  public void testGetDateFieldType() {
    assertNotNull(ElasticSearchClientUtility.getDateFieldType());
  }


}
