package com.wonder4work.controller;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.wonder4work.Enums.ScenarioCodeEnum;
import com.wonder4work.model.ESObject;
import com.wonder4work.model.ScenarioCode;
import com.wonder4work.repository.ESRepository;
import com.wonder4work.util.ESUtils;
import com.wonder4work.util.JsonResult;
import com.wonder4work.util.PagedGridResult;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.script.mustache.MultiSearchTemplateResponse;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiezengcheng
 * @date 2020-09-24
 */
@RestController
@RequestMapping("/es")
public class ESObjectController {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ESRepository repository;

    @GetMapping("/query/order")
    public JsonResult queryOrder(@RequestParam(required = false) String orderId,
                                 @RequestParam(required = false) String scenarioCode,
                                 @RequestParam(required = false) String startTime,
                                 @RequestParam(required = false) String endTime,
                                 Pageable pageable) {

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        Pageable requestPageable = new PageRequest(pageNumber, pageSize, new Sort(Sort.Direction.DESC, "timestamp"));
        QueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(orderId)) {
            ((BoolQueryBuilder) queryBuilder).must(QueryBuilders.queryStringQuery(orderId).field("orderId"));
        }

        if (!StringUtils.isEmpty(scenarioCode)) {
            ((BoolQueryBuilder) queryBuilder).must(QueryBuilders.queryStringQuery(scenarioCode).field("scenarioCode"));
        }

/*        if (!StringUtils.isEmpty(startTime)) {
            //大于等于
            ((BoolQueryBuilder) queryBuilder).must(QueryBuilders.rangeQuery("timestamp").gte(startTime));
        }


        if (!StringUtils.isEmpty(endTime)) {
            //小于等于
            ((BoolQueryBuilder) queryBuilder).must(QueryBuilders.rangeQuery("timestamp").lte(endTime));

        }*/

        Page<ESObject> page = repository.search(queryBuilder, requestPageable);

        PagedGridResult pagedGridResult = new PagedGridResult();
        pagedGridResult.setPage(page.getNumber());
        pagedGridResult.setTotal(page.getTotalPages());
        pagedGridResult.setRecords(page.getTotalElements());
        pagedGridResult.setRows(page.getContent());

        return JsonResult.success(pagedGridResult);
    }


    @GetMapping("/query/scenarios")
    public JsonResult queryScenarios(@RequestParam(required = false) String scenarioCode,
                                    Pageable pageable) {

        List<Object> esObjectList = new ArrayList<>();
        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        if (!StringUtils.isEmpty(scenarioCode)) {

            String valueForMsg = ScenarioCodeEnum.getValueForMsg(scenarioCode);

            matchAllQuery = QueryBuilders.
                    queryStringQuery(valueForMsg).field("scenarioCode");
        }

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(matchAllQuery);
        nativeSearchQueryBuilder.withSearchType(SearchType.QUERY_THEN_FETCH);
        nativeSearchQueryBuilder.withIndices("bc_traces").withTypes("trace");
        TermsAggregationBuilder termsAggregation = AggregationBuilders.terms("group_scenarioCode").field("scenarioCode").order(BucketOrder.count(false));;
        nativeSearchQueryBuilder.addAggregation(termsAggregation);
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();

        //查询并返回带聚合结果
        AggregatedPage<MultiSearchTemplateResponse.Item> result = elasticsearchTemplate.queryForPage(nativeSearchQuery, MultiSearchTemplateResponse.Item.class);
        //解析聚合
        Aggregations aggregations = result.getAggregations();
        //获取指定名称的聚合
        StringTerms terms = aggregations.get("group_scenarioCode");
        //获取桶
        List<StringTerms.Bucket> buckets = terms.getBuckets();
        //遍历打印
        List<ScenarioCode> scenarioCodes = new ArrayList<>();
        for (StringTerms.Bucket bucket : buckets) {
            ScenarioCode code = new ScenarioCode();
            code.setCount(bucket.getDocCount());
            if (ScenarioCodeEnum.contains(bucket.getKeyAsString())) {
                code.setScenarioCode(ScenarioCodeEnum.getMsgForValue(bucket.getKeyAsString()));
                code.setCode(bucket.getKey());

            }else{
                code.setScenarioCode(bucket.getKey());
                code.setCode(bucket.getKey());
            }
            scenarioCodes.add(code);
        }

        PagedGridResult pagedGridResult = new PagedGridResult();
        pagedGridResult.setPage(result.getNumber());
        pagedGridResult.setTotal(1);
        pagedGridResult.setRecords(buckets.size());
        pagedGridResult.setRows(scenarioCodes);

        return JsonResult.success(pagedGridResult);
    }
}
