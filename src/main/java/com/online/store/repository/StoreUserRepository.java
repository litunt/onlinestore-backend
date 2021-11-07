package com.online.store.repository;

import com.online.store.model.StoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreUserRepository extends JpaRepository<StoreUser, Long> {

}
