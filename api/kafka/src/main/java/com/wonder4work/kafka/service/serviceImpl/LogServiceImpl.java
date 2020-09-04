package com.wonder4work.kafka.service.serviceImpl;

import com.wonder4work.kafka.controller.PagedGridResult;
import com.wonder4work.kafka.domain.Log;
import com.wonder4work.kafka.repository.LogRepository;
import com.wonder4work.kafka.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;

/**
 * @author xiezengcheng
 * @date 2020-08-25
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;


    @Override
    public PagedGridResult query(String queryText, Integer page, Integer pageSize) {

        PagedGridResult pagedGridResult = new PagedGridResult();

        //查询条件存在这个对象中
        Specification<Log> specification = new Specification<Log>() {
            //重新Specification的toPredicate方法
            @Override
            public Predicate toPredicate(Root<Log> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //我要模糊查询的字段是adminName
                Path msg = root.get("msg");
                //criteriaBuilder.like模糊查询，第一个参数是上一行的返回值，第二个参数是like('%xxx%')中，xxx的值
                Predicate predicate = criteriaBuilder.like(msg, "%" + queryText + "%");
                return predicate;
            }
        };

        Sort.Order order=new Sort.Order(Sort.Direction.DESC, "id");
        //分页条件存在这个对象中
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,Sort.by(order));

        //进行查询操作，第一个参数是查询条件对象，第二个参数是分页对象
        Page<Log> pages = logRepository.findAll(specification, pageRequest);
        pagedGridResult.setPage(page);
        pagedGridResult.setRecords(pages.getTotalElements());
        pagedGridResult.setRows(pages.getContent());
        pagedGridResult.setTotal(pages.getTotalPages());

        return pagedGridResult;
    }

    @Override
    public Log create(Log log) {
        Log save = logRepository.save(log);
        return save;
    }

}
