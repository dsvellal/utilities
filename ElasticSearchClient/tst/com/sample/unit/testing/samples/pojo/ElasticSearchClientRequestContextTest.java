/*
 * Copyright
 * @author: dsvellal
 *
 */
package com.sample.unit.testing.samples.pojo;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ElasticSearchClientRequestContextTest {
  private RestHighLevelClient mockedRestHighLevelClient;
  private ObjectMapper mockedObjectMapper;
  private ElasticSearchClientRequestContext requestContext;
  private String mockedIndex;
  private String mockedType;

  @Before
  public void setup() {
    mockedRestHighLevelClient = Mockito.mock(RestHighLevelClient.class);
    mockedObjectMapper = Mockito.mock(ObjectMapper.class);
    mockedIndex = "MockedIndex";
    mockedType = "MockedType";
    requestContext = new ElasticSearchClientRequestContext(mockedRestHighLevelClient, mockedObjectMapper, mockedIndex, mockedType);
  }

  @Test
  public void testElasticSearchClientIndex() {
    Assert.assertEquals(mockedRestHighLevelClient, requestContext.getRestHighLevelClient());
    Assert.assertEquals(mockedObjectMapper, requestContext.getObjectMapper());
    Assert.assertEquals(mockedIndex, requestContext.getIndex());
    Assert.assertEquals(mockedType, requestContext.getType());
    Assert.assertNotNull(requestContext.getUniqueId());
  }

}
