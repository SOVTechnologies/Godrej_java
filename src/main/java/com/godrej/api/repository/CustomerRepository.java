package com.godrej.api.repository;

import com.godrej.api.model.TransCustomer;
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
public interface CustomerRepository extends JpaRepository<TransCustomer, Integer> {
    @Query("SELECT tc FROM TransCustomer tc WHERE tc.customer_id = :customer_id")
    Optional<TransCustomer> findById(@Param("customer_id") String customer_id);

    @Modifying
    @Query("DELETE FROM TransCustomer e WHERE e.customer_id = ?1")
    void deleteByCustomerId(String customer_id);


    @Query("SELECT tc FROM TransCustomer tc WHERE tc.mobile = :mobile")
    List<TransCustomer> findByMobile(@Param("mobile") String mobile);

    @Query("SELECT tc FROM TransCustomer tc WHERE tc.mobile = :mobile")
    TransCustomer findByMobileNo(@Param("mobile") String mobile);


    @Query("SELECT tc FROM TransCustomer tc WHERE tc.customer_id = :customer_id")
    TransCustomer findByCID(@Param("customer_id") String customer_id);

}
