package com.example.online_shop.repository;

import com.example.online_shop.model.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemoryRepository extends JpaRepository<Memory, Integer> {
    Memory findByMemory(String memory);

}
