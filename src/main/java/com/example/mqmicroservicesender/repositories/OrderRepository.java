package com.example.mqmicroservicesender.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.mqmicroservicesender.entities.CompletedOrder;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<CompletedOrder, Long> {

	public List<CompletedOrder> findAll();
}
