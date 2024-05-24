package com.godrej.api.repository;

import com.godrej.api.model.Topologies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopologiesRepository extends JpaRepository<Topologies, Integer> {

    @Query("SELECT tc FROM Topologies tc WHERE tc.id = :id")
    Optional<Topologies> findByid(@Param("id") String id);

    @Query("SELECT tc FROM Topologies tc")
    List<Topologies> findAll( );

    @Modifying
    @Query("DELETE FROM Topologies e WHERE e.id = ?1")
    void deleteByName(Integer id);
}
