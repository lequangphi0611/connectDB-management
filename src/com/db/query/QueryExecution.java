package com.db.query;

import java.util.List;
import java.util.Optional;

public interface QueryExecution<T> {

	List<T> getResultList();
	
	Optional<T> getSingleResult();
	
}
