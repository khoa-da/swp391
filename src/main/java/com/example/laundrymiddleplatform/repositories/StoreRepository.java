package com.example.laundrymiddleplatform.repositories;

import com.example.laundrymiddleplatform.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

}
