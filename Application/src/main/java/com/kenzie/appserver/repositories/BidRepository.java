package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.BidRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface BidRepository extends CrudRepository<BidRecord, String> {
    List<BidRecord> findByBuyerId(String buyerId);
}