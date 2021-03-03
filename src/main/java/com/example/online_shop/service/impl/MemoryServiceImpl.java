package com.example.online_shop.service.impl;

import com.example.online_shop.model.Memory;
import com.example.online_shop.repository.MemoryRepository;
import com.example.online_shop.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoryServiceImpl implements MemoryService {

    private final MemoryRepository memoryRepository;


    public void save(Memory memory) {

        if (memoryRepository.findByMemory(memory.getMemory()) == null) {

            memoryRepository.save(memory);
        }

    }



    public void findAllMemory() {
        memoryRepository.findAll();
    }

    public Memory findMemoryById(int id) {
        Optional<Memory> byId = memoryRepository.findById(id);
        if (byId.isPresent()) {

            return byId.get();
        }
        return null;
    }
}
