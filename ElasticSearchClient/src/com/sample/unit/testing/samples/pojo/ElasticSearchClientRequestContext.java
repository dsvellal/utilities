/*
 * Copyright
 * @author: dsvellal
 *
 */
package com.sample.unit.testing.samples.pojo;

import java.util.UUID;

import org.elasticsearch.client.RestHighLevelClient;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ElasticSearchClientRequestContext {
  private final RestHighLevelClient restHighLevelClient;
  private final ObjectMapper objectMapper;
  private final String index;
  private final String type;

  public ElasticSearchClientRequestContext(final RestHighLevelClient restHighLevelClient, final ObjectMapper objectMapper, final String index, final String type) {
    this.restHighLevelClient = restHighLevelClient;
    this.objectMapper = objectMapper;
    this.index = index;
    this.type = type;
  }

  public RestHighLevelClient getRestHighLevelClient() {
    return restHighLevelClient;
  }

  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  public String getIndex() {
    return index;
  }

  public String getType() {
    return type;
  }

  public String getUniqueId() {
    return UUID.randomUUID().toString();
  }

}
