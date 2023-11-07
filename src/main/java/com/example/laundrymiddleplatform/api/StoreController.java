package com.example.laundrymiddleplatform.api;

import com.example.laundrymiddleplatform.dto.CreateStore;
import com.example.laundrymiddleplatform.dto.UpdateUser;
import com.example.laundrymiddleplatform.entities.Product;
import com.example.laundrymiddleplatform.entities.Store;
import com.example.laundrymiddleplatform.entities.User;
import com.example.laundrymiddleplatform.response.ApiResponse;
import com.example.laundrymiddleplatform.services.interfaces.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/store")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createStore(@RequestBody CreateStore createStore) throws Exception {
        try{
            Store store = storeService.createStore(createStore.getStoreName(), createStore.getLocation(), createStore.getOwnerId(), createStore.getOwnerName());
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Create Store!")
                    .data(store)
                    .build());
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Create Store Fail! Error: " + e.getMessage())
                    .build());
        }
    }
    @GetMapping("/{Id}")
    public ResponseEntity<ApiResponse> getStoreId(@PathVariable Long Id) throws Exception {
        Optional<Store> store = storeService.getStoreId(Id);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Get Store ID")
                .data(store)
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        if(!storeService.getStoreId(id).isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Delete fail! Can't find this store!")
                    .build());
        }else {
            storeService.deleteStore(id);
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Store deleted successfully")
                    .build());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id, @RequestBody Store store) {
        if(!storeService.getStoreId(id).isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Update fail! Can't find this store!")
                    .build());
        }else{
            store.setStoreId(id);
            Store updatedStore = storeService.saveStore(store);
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Store updated successfully")
                    .data(updatedStore)
                    .build());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllStores() {
        List<Store> stores = storeService.getAllStores();
        if(stores.isEmpty()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("List of stores is empty!")
                    .build());
        }else {
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("List of stores")
                    .data(stores)
                    .build());
        }
    }
}
