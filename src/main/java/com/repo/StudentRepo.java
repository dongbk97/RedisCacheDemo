package com.repo;

import com.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class StudentRepo {

    @Autowired
    private RedisTemplate redisTemplate;

    private final String HASH_KEY = "student1";

    @CachePut(value = "cacheSt", key = "#st.id")
    public Student saveStudent(Student st) {
        redisTemplate.opsForHash().put(HASH_KEY, st.getId(), st);

        return st;
    }

    @Cacheable(value = "cacheSt", key = "#id")
    public Student getStudent(int id) {
        log.info("Get student " + id + " from database inmemory");
        return (Student) redisTemplate.opsForHash().get(HASH_KEY, id);
    }


}
