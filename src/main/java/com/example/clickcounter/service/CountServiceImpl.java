package com.example.clickcounter.service;

import com.example.clickcounter.entity.Count;
import com.example.clickcounter.repository.CountRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CountServiceImpl implements CountService {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Callable<Long> increment = new Callable<>() {
        @Override
        public Long call() {
            Count current = get();
            current.setValue(current.getValue() + 1);
            return countRepository.save(current).getValue();
        }
    };
    private final CountRepository countRepository;

    @Autowired
    public CountServiceImpl(CountRepository countRepository) {
        this.countRepository = countRepository;
    }

    @Override
    @SneakyThrows
    public long add()  {
        return executorService.submit(increment).get();
    }

    @Override
    public Count get() {
        return countRepository.findById(1L).orElseThrow(RuntimeException::new);
    }
}
