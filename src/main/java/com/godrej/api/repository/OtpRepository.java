package com.godrej.api.repository;

import com.godrej.api.model.Otp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtpRepository extends CrudRepository<Otp, Long> {
    //@Query("SELECT count(*) FROM trans_otp WHERE mobileno = ?1 AND otp = ?2")
    List<Otp> findByMobilenoAndOtp(String mobileno,String otp);
}
