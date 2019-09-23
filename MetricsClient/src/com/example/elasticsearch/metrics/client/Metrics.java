/*
 * Copyright of Philips, 2019
 *
 * This file is subject to the terms and conditions defined by Philips,
 * visit https://www.ip.philips.com/licensing/ for more details.
 *
 */
package com.philips.elasticsearch.metrics.client;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.philips.elasticsearch.metrics.model.MetricAttributes;
import com.philips.elasticsearch.metrics.model.PropertiesPojo;
import com.philips.elasticsearch.metrics.utils.ElasticSearchClientUtility;
import com.philips.elasticsearch.metrics.utils.PropertiesUtility;

/**
 * The Class Metrics.
 */
public class Metrics {

  /** The Constant OBJECT_MAPPER. */
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  /** The clazz. */
  private final Class clazz;

  /** The properties pojo. */
  private final PropertiesPojo propertiesPojo;

  /**
   * Instantiates a new metrics.
   *
   * @param clazz the clazz
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public Metrics(final Class clazz) throws IOException {
    this.clazz = clazz;
    final PropertiesUtility propertiesUtility = new PropertiesUtility();
    propertiesPojo = propertiesUtility.getPropertiesPojo();
  }

  /**
   * Gets the clazz.
   *
   * @return the clazz
   */
  public Class getClazz() {
    return clazz;
  }

  /**
   * Gets the properties pojo.
   *
   * @return the properties pojo
   */
  public PropertiesPojo getPropertiesPojo() {
    return propertiesPojo;
  }

  /**
   * Put metric.
   *
   * @param <T> the generic type
   * @param key the key
   * @param value the value
   */
  public <T> void putMetric(final String key, final T value) {
    putMetric(key, value, new Date().getTime());
  }

  /**
   * Put metric.
   *
   * @param <T> the generic type
   * @param key the key
   * @param value the value
   * @param timestamp the timestamp
   */
  public <T> void putMetric(final String key, final T value, final long timestamp) {
    final MetricAttributes<T> attributes = new MetricAttributes<>(key, value, timestamp);
    attributes.setClazzName(clazz.getName());

    try {
      final Map<String, Object> documentMapper = OBJECT_MAPPER.convertValue(attributes, Map.class);
      final RestHighLevelClient elasticSearchClient = ElasticSearchClientUtility.getElasticSearchClient(propertiesPojo);

      final IndexRequest indexRequest = new IndexRequest(propertiesPojo.getIndex(), propertiesPojo.getType(),
          attributes.getId().toString()).source(documentMapper);
      final IndexResponse indexResponse = elasticSearchClient.index(indexRequest, RequestOptions.DEFAULT);
      System.out.println("Index for key: " + key + " value: " + value + " timestamp: " + timestamp
          + " is created, with the following details: " + indexResponse.toString() + " with status: "
          + indexResponse.status());
    } catch (final IOException e) {
      e.printStackTrace(System.out);
    }
  }

  /**
   * Publish success metrics.
   *
   * @param metricPrefix the metric prefix
   * @param totalTimeOfExecution the total time of execution
   */
  public void publishSuccessMetrics(final String metricPrefix, final long totalTimeOfExecution) {
    publishCommonMetrics(metricPrefix, 1, 0, totalTimeOfExecution);
  }

  /**
   * Publish failure metrics.
   *
   * @param metricPrefix the metric prefix
   * @param specificExceptionNames the specific exception names
   * @param totalTimeOfExecution the total time of execution
   */
  public void publishFailureMetrics(final String metricPrefix, final List<String> specificExceptionNames,
      final long totalTimeOfExecution) {
    publishCommonMetrics(metricPrefix, 0, 1, totalTimeOfExecution);
    for (final String exception : specificExceptionNames) {
      putMetric(metricPrefix + "_EXCEPTION_" + exception, 1);
    }
  }

  /**
   * Publish common metrics.
   *
   * @param metricPrefix the metric prefix
   * @param success the success
   * @param failure the failure
   * @param totalTimeOfExecution the total time of execution
   */
  private void publishCommonMetrics(final String metricPrefix, final int success, final int failure,
      final long totalTimeOfExecution) {
    putMetric(metricPrefix + "_COUNT", 1);
    putMetric(metricPrefix + "_SUCCESS", success);
    putMetric(metricPrefix + "_FAILURE", failure);
    putMetric(metricPrefix + "_EXCEPTION", failure);
    putMetric(metricPrefix + "_TOTAL_TIME", totalTimeOfExecution);
  }
}
