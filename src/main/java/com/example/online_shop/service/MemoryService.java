package com.example.online_shop.service;


import com.example.online_shop.model.Memory;


public interface MemoryService {


    void save(Memory memory);

    void findAllMemory();

    Memory findMemoryById(int id);

}
