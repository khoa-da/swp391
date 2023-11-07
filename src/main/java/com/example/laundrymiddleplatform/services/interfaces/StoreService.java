package com.example.laundrymiddleplatform.services.interfaces;

import com.example.laundrymiddleplatform.entities.Store;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StoreService {
    Store createStore(String storeName, String location, Long ownerId, String ownerName);

    Optional<Store> getStoreId(Long storeId);

    Store saveStore(Store store);

    List<Store> getAllStores();

    void deleteStore(Long id);
}
