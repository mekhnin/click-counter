package com.example.clickcounter.service;

import com.example.clickcounter.entity.Count;
import com.example.clickcounter.repository.CountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountServiceImpl implements CountService {

    private final CountRepository countRepository;

    @Autowired
    public CountServiceImpl(CountRepository countRepository) {
        this.countRepository = countRepository;
    }

    @Override
    public synchronized long add() {
        Count current = get();
        current.setValue(current.getValue() + 1);
        return countRepository.save(current).getValue();
    }

    @Override
    public Count get() {
        return countRepository.findById(1L).orElseThrow(RuntimeException::new);
    }

}
