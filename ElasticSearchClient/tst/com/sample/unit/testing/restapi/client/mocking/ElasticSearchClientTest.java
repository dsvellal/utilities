/*
 * Copyright
 * @author: dsvellal
 *
 */
package com.sample.unit.testing.restapi.client.mocking;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.uid.Versions;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.index.seqno.SequenceNumbers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.unit.testing.samples.pojo.CustomObject;
import com.sample.unit.testing.samples.pojo.ElasticSearchClientRequestContext;

@RunWith(MockitoJUnitRunner.class)
public class ElasticSearchClientTest {
  private ElasticSearchClientRequestContext mockedRequestContext;
  private RestHighLevelClient mockedRestHighLevelClient;
  private ObjectMapper mockedObjectMapper;

  private Map<String, Object> mockedDocumentMapper;
  private IndexRequest mockedIndexRequest;
  private IndexRequest mockedTransformedIndexRequest;
  private IndexResponse mockedIndexResponse;

  private String mockedIndex;
  private String mockedType;
  private String mockedUniqueId;

  private CustomObject mockedCustomObject;
  private DeleteResponse mockedDeleteResponse;
  private Result mockedResult;

  private ElasticSearchClient elasticSearchClient;

  @Before
  public void setup() {

    mockedRestHighLevelClient = Mockito.mock(RestHighLevelClient.class);
    mockedObjectMapper = Mockito.mock(ObjectMapper.class);
    mockedIndex = "mockedIndex";
    mockedType = "mockedType";

    mockedRequestContext = new ElasticSearchClientRequestContext(mockedRestHighLevelClient, mockedObjectMapper,
        mockedIndex, mockedType);

    mockedDocumentMapper = Mockito.mock(Map.class);
    mockedIndexRequest = Mockito.mock(IndexRequest.class);
    mockedTransformedIndexRequest = Mockito.mock(IndexRequest.class);
    mockedIndexResponse = Mockito.mock(IndexResponse.class);

    mockedUniqueId = UUID.randomUUID().toString();

    mockedCustomObject = new CustomObject(Arrays.asList("ONE"));
    mockedDeleteResponse = Mockito.mock(DeleteResponse.class);
    mockedResult = Result.DELETED;
  }

  @Test
  public void testElasticSearchIndexMethod() throws IOException {
    Mockito.when(mockedObjectMapper.convertValue(mockedCustomObject, Map.class)).thenReturn(mockedDocumentMapper);
    Mockito.when(mockedIndexRequest.source(Mockito.anyMapOf(String.class, Object.class)))
    .thenReturn(mockedTransformedIndexRequest);

    Mockito.when(mockedTransformedIndexRequest.routing()).thenReturn("");
    Mockito.when(mockedTransformedIndexRequest.timeout()).thenReturn(TimeValue.MINUS_ONE);
    Mockito.when(mockedTransformedIndexRequest.version()).thenReturn(Versions.MATCH_ANY);
    Mockito.when(mockedTransformedIndexRequest.versionType()).thenReturn(VersionType.INTERNAL);
    Mockito.when(mockedTransformedIndexRequest.ifSeqNo()).thenReturn(SequenceNumbers.UNASSIGNED_SEQ_NO);
    Mockito.when(mockedTransformedIndexRequest.ifPrimaryTerm()).thenReturn(SequenceNumbers.UNASSIGNED_PRIMARY_TERM);
    Mockito.when(mockedTransformedIndexRequest.getPipeline()).thenReturn("");
    Mockito.when(mockedTransformedIndexRequest.getRefreshPolicy()).thenReturn(RefreshPolicy.NONE);
    Mockito.when(mockedTransformedIndexRequest.waitForActiveShards()).thenReturn(ActiveShardCount.DEFAULT);

    Mockito.when(mockedRestHighLevelClient.index(mockedTransformedIndexRequest, RequestOptions.DEFAULT))
    .thenReturn(mockedIndexResponse);
    Mockito.when(mockedIndexResponse.getResult()).thenReturn(mockedResult);

    elasticSearchClient = new ElasticSearchClient(mockedRequestContext);
    final Result outcomeResult = elasticSearchClient.indexDocument(mockedCustomObject, mockedIndexRequest);

    Assert.assertEquals(mockedResult, outcomeResult);
  }

  @Test
  public void testElasticSearchDeleteMethod() throws IOException {
    Mockito.when(mockedRequestContext.getIndex()).thenReturn(mockedIndex);
    Mockito.when(mockedRequestContext.getType()).thenReturn(mockedType);
    Mockito.when(mockedRequestContext.getRestHighLevelClient()).thenReturn(mockedRestHighLevelClient);
    Mockito.when(mockedRestHighLevelClient.delete(Mockito.any(DeleteRequest.class), Mockito.any(RequestOptions.class)))
    .thenReturn(mockedDeleteResponse);
    Mockito.when(mockedDeleteResponse.getResult()).thenReturn(mockedResult);

    elasticSearchClient = new ElasticSearchClient(mockedRequestContext);
    final Result outcomeResult = elasticSearchClient.deleteId(mockedUniqueId);

    Assert.assertEquals(mockedResult, outcomeResult);
  }

  @Test
  public void dummy() {
    Assert.assertTrue(true);
  }
}
