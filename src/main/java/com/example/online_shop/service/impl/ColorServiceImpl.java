package com.example.online_shop.service.impl;

import com.example.online_shop.exception.ResourceNotFoundException;
import com.example.online_shop.model.Color;
import com.example.online_shop.repository.ColorRepository;
import com.example.online_shop.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;


    public void save(Color color) {
        if (color.getColor() != null)
            if (colorRepository.findByColor(color.getColor()) == null) {
                colorRepository.save(color);
            }
    }

    public void findAllCollor() {
        colorRepository.findAll();
    }

    public Color findColorById(int id) {
        Optional<Color> byId = colorRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        throw new ResourceNotFoundException("Collor not found"+byId.get().getColor());
    }
}
