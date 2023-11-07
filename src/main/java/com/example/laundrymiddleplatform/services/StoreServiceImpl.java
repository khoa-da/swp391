package com.example.laundrymiddleplatform.services;

import com.example.laundrymiddleplatform.entities.Store;
import com.example.laundrymiddleplatform.repositories.StoreRepository;
import com.example.laundrymiddleplatform.services.interfaces.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StoreServiceImpl implements StoreService {

    @Autowired
    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store createStore(String storeName, String location, Long ownerId, String ownerName) {
        Store saveStore = storeRepository.save(Store.builder()
                .storeName(storeName)
                .location(location)
                .ownerId(ownerId)
                .ownerName(ownerName)
                .build());
        return saveStore;
    }

    @Override
    public Optional<Store> getStoreId(Long Id) {
        Optional<Store> storeId = storeRepository.findById(Id);
        return storeId;
    }

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }
}
