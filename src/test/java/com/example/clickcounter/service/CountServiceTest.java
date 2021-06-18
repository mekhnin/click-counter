package com.example.clickcounter.service;

import com.example.clickcounter.entity.Count;
import com.example.clickcounter.repository.CountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CountServiceTest {

    @MockBean
    CountRepository repository;

    @Autowired
    CountService service;

    @Test
    void whenGetThenReturnCount() {
        long current = 28L;
        Count count = new Count();
        count.setValue(current);
        Mockito.when(repository.findById(Mockito.eq(1L))).thenReturn(Optional.of(count));
        assertEquals(count, service.get());
        Mockito.verify(repository, Mockito.times(1)).findById(Mockito.any());
    }

    @Test
    void whenAddThenReturnIncrementedCount() {
        long before = 10_000_000_000L;
        long after = before + 1;
        Count countBefore = new Count();
        countBefore.setValue(before);
        Count countAfter = new Count();
        countAfter.setValue(after);
        Mockito.when(repository.findById(Mockito.eq(1L))).thenReturn(Optional.of(countBefore));
        Mockito.when(repository.save(Mockito.argThat(arg -> arg.getValue().equals(after)))).thenReturn(countAfter);
        assertEquals(after, service.add());
        Mockito.verify(repository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }

}
