package com.godrej.api.repository;

import com.godrej.api.model.SourceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceTypeRepository extends CrudRepository<SourceType, Long> {


}
