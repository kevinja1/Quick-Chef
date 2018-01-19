package com.fbla.quickchef.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbla.quickchef.model.UserPaymentInfo;

public interface UserPaymentInfoRepo extends JpaRepository<UserPaymentInfo, String> {

}
