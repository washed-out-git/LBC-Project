package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.SellerRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SellerRepository extends CrudRepository<SellerRecord, String> {
}
