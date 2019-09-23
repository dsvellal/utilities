/*
 * Copyright
 * @author: dsvellal
 *
 */
package com.sample.unit.testing.restapi.client.mocking;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.VersionType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.unit.testing.samples.pojo.CustomObject;
import com.sample.unit.testing.samples.pojo.ElasticSearchClientRequestContext;

public class ElasticSearchClient {
  private final ElasticSearchClientRequestContext requestContext;

  public ElasticSearchClient(final ElasticSearchClientRequestContext requestContext) {
    this.requestContext = requestContext;
  }

  public Result indexDocument(final CustomObject document, final IndexRequest indexRequest) throws IOException {
    final ObjectMapper objectMapper = requestContext.getObjectMapper();
    final Map<String, Object> documentMapper = objectMapper.convertValue(document, Map.class);
    final RestHighLevelClient restHighLevelClient = requestContext.getRestHighLevelClient();
    final IndexRequest request = indexRequest.source(documentMapper);
    final IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
    return indexResponse.getResult();
  }


  public Result deleteId(final String id) throws IOException {
    final DeleteRequest deleteRequest = new DeleteRequest(requestContext.getIndex(), requestContext.getType(), id);
    deleteRequest.versionType(VersionType.INTERNAL);
    final DeleteResponse response = requestContext.getRestHighLevelClient().delete(deleteRequest, RequestOptions.DEFAULT);
    return response.getResult();
  }

}
