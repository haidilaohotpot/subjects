package com.wonder4work.kafka.repository;

import com.wonder4work.kafka.domain.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author xiezengcheng
 * @date 2020-08-25
 */
public interface LogRepository extends JpaRepository<Log, Integer> , JpaSpecificationExecutor<Log> {

}
