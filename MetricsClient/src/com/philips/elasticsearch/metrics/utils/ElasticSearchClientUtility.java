/*
 * Copyright of Philips, 2019
 *
 * This file is subject to the terms and conditions defined by Philips,
 * visit https://www.ip.philips.com/licensing/ for more details.
 *
 */
package com.philips.elasticsearch.metrics.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.mapper.DateFieldMapper;
import org.elasticsearch.index.mapper.DateFieldMapper.DateFieldType;

import com.philips.elasticsearch.metrics.model.PropertiesPojo;

/**
 * The Class ElasticSearchClientUtility.
 */
public class ElasticSearchClientUtility {

  /** The elastic search instance map. */
  private static Map<String, RestHighLevelClient> elasticSearchInstanceMap = new ConcurrentHashMap<>();

  /** The field type. */
  private static DateFieldType fieldType = new DateFieldMapper.Builder("timestamp").fieldType();

  /**
   * Gets the elastic search client.
   *
   * @param attributes the attributes
   * @return the elastic search client
   */
  public static RestHighLevelClient getElasticSearchClient(final PropertiesPojo attributes) {
    final String mapKey = attributes.toString();
    synchronized (mapKey) {
      if (!elasticSearchInstanceMap.containsKey(mapKey)) {
        synchronized (mapKey) {
          final RestHighLevelClient elasticSearchRestHighLevelClient = new RestHighLevelClient(
              RestClient.builder(new HttpHost(attributes.getHost(), attributes.getPort())));
          elasticSearchInstanceMap.put(mapKey, elasticSearchRestHighLevelClient);
        }
      }
    }

    return elasticSearchInstanceMap.get(mapKey);

  }

  /**
   * Gets the date field type.
   *
   * @return the date field type
   */
  public static DateFieldType getDateFieldType() {
    return fieldType;
  }

  /**
   * Sets the elastic search instance map.
   *
   * @param mockedElasticSearchInstanceMap the mocked elastic search instance map
   */
  protected static void setElasticSearchInstanceMap(final Map<String, RestHighLevelClient> mockedElasticSearchInstanceMap) {
    elasticSearchInstanceMap = mockedElasticSearchInstanceMap;
  }
}
