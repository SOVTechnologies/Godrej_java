package com.godrej.api.repository;

import com.godrej.api.model.TbBroker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Component
public interface TbBrokerRepository extends JpaRepository<TbBroker, Long> {
    @Query("SELECT tc FROM TbBroker tc WHERE tc.id = :id")
    Optional<TbBroker> findById(@Param("id") Integer customerid);


    @Query("SELECT tc FROM TbBroker tc WHERE tc.name LIKE %?1%")
    List<TbBroker> findByName(@Param("name") String name);

    @Modifying
    @Query("DELETE FROM TbBroker e WHERE e.id = ?1")
    void deletById(Integer id);

}
