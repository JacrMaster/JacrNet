package com.jacrnet.repository;

import com.jacrnet.entity.PlanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlanRepository extends CrudRepository<PlanEntity,Long> {

}
