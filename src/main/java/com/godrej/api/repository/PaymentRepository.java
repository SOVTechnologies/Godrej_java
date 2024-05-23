package com.godrej.api.repository;

import com.godrej.api.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {

    @Query("SELECT tc FROM PaymentDetails tc WHERE tc.statusCode = :statusCode")
    List<PaymentDetails> findBystatusCode(String statusCode);



    @Query("SELECT tc FROM PaymentDetails tc WHERE tc.customer_id = :customer_id")
    List<PaymentDetails> findByCID(String customer_id);

}
