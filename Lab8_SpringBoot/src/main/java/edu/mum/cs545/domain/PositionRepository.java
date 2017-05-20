package edu.mum.cs545.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<Position, Long>{
	List<Position> findByName(String name);
}
