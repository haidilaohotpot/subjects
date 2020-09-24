package com.wonder4work.repository;

import com.wonder4work.model.ESObject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author xiezengcheng
 * @date 2020-09-24
 */
@Component
public interface ESRepository extends ElasticsearchRepository<ESObject, String> {

}
