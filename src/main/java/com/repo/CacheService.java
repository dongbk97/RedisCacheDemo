package com.repo;

import com.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CacheService {


    @CachePut(value = "cacheSt", key = "#st.id")
    public Student addCache(Student st) {
        log.info("Set student to Cache !");
        return st;
    }

}
