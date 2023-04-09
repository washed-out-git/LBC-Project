package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.BuyerRecord;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface BuyerRepository extends CrudRepository<BuyerRecord, String> {
}
