package cn.ffcs.util;

import cn.ffcs.model.ElasticEntity;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class ESUtils {

    private static RestHighLevelClient restHighLevelClient;

    static {
        //这里如果要用client去访问其他节点，就添加进去
        restHighLevelClient = new RestHighLevelClient(RestClient.builder
                (new HttpHost("localhost", 9200, "http")));
    }

    /**
     * 创建索引
     * @param idxName 索引名称
     * @param idxSQL  索引描述
     * @return void
     * @throws
     * @since
     */
    public static CreateIndexResponse createIndex(String idxName, Map<String, Object> idxSQL) {


        try {
            if (isExistsIndex(idxName)) {
                log.error(" idxName={} 已经存在,idxSql={}", idxName, idxSQL);
                return null;
            }
            CreateIndexRequest request = new CreateIndexRequest(idxName);
            buildSetting(request);
            request.mapping(idxSQL);
//            request.settings() 手工指定Setting
            CreateIndexResponse res = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            if (!res.isAcknowledged()) {
                throw new RuntimeException("初始化失败");
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    /**
     * 判断某个index是否存在
     *
     * @param idxName index名
     * @return boolean
     * @throws
     * @since
     */
    public static boolean isExistsIndex(String idxName) throws Exception {
        return restHighLevelClient.indices().exists(new GetIndexRequest(idxName), RequestOptions.DEFAULT);
    }

    /**
     * 设置分片
     *
     * @param request
     * @return void
     * @throws
     * @since
     */
    public static void buildSetting(CreateIndexRequest request) {
        request.settings(Settings.builder().put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2));
    }

    /**
     * 新增和编辑数据
     * @param idxName
     * @param id
     * @param data
     * @return
     */
    public static IndexResponse insertOrUpdateOne(String idxName, String id, Map<String, Object> data) {

        IndexRequest request = new IndexRequest(idxName);
        log.error("Data : id={},entity={}",id, JSON.toJSONString(data));
        request.id(id);
        request.source(data, XContentType.JSON);
        try {
            return restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 批量插入数据
     *
     * @param idxName index
     * @param list    带插入列表
     * @return void
     * @throws
     * @since
     */
    public static BulkResponse insertBatch(String idxName, List<ElasticEntity> list) {
        BulkRequest request = new BulkRequest();

        list.forEach(item -> request.add(new IndexRequest(idxName).id(item.getId())
                .source(item.getData(), XContentType.JSON)));
        try {
            return restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 批量删除
     *
     * @param idxName index
     * @param idList  待删除列表
     * @return void
     * @throws
     * @since
     */
    public static BulkResponse deleteBatch(String idxName, Collection<Object> idList) {
        BulkRequest request = new BulkRequest();
        idList.forEach(item -> request.add(new DeleteRequest(idxName, item.toString())));
        try {
            return restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除
     *
     * @param idxName index
     * @param id      待删除ID
     * @return void
     * @throws
     * @since
     */
    public static DeleteResponse deleteOne(String idxName, String id) {
        DeleteRequest request = new DeleteRequest(idxName, id);
        try {
            return restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param idxName index
     * @param builder 查询参数
     * @param c       结果类对象
     * @return java.util.List<T>
     * @throws
     * @since
     */
    public static <T> List<T> search(String idxName, SearchSourceBuilder builder, Class<T> c) {
        SearchRequest request = new SearchRequest(idxName);
        request.source(builder);
        try {
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<T> res = new ArrayList<>(hits.length);
            log.info(response.toString());

            for (SearchHit hit : hits) {
                res.add(JSON.parseObject(hit.getSourceAsString(), c));
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除index
     *
     * @param idxName
     * @return void
     * @throws
     * @since
     */
    public static AcknowledgedResponse deleteIndex(String idxName) {
        try {
            if (isExistsIndex(idxName)) {
                log.error(" idxName={} 已经存在", idxName);
                throw new RuntimeException("idxName已经存在");
            }
            return restHighLevelClient.indices().delete(new DeleteIndexRequest(idxName), RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param idxName
     * @param builder
     * @return void
     * @throws
     * @since
     */
    public static BulkByScrollResponse deleteByQuery(String idxName, QueryBuilder builder) {

        DeleteByQueryRequest request = new DeleteByQueryRequest(idxName);
        request.setQuery(builder);
        //设置批量操作数量,最大为10000
        request.setBatchSize(10000);
        request.setConflicts("proceed");
        try {
            return restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
