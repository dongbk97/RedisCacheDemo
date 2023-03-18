package com.repo;

import com.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Slf4j
public class StudentRepo {
    @Autowired
    CacheManager cacheManager;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CacheService cacheService;
    private final String HASH_KEY = "student1";

    //    @CachePut(value = "cacheSt", key = "#st.id")
    public Student saveStudent(Student st) {
        redisTemplate.opsForHash().put(HASH_KEY, st.getId(), st);

        return st;
    }

    @Cacheable(value = "cacheSt", key = "#id")
    public Student getStudent(int id) {
        log.info("Get student " + id + " from database inmemory");
        return (Student) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public List<Student> getAllStudent() {
        log.info("Get all student from database inmemory");
        List<Student> listSt = redisTemplate.opsForHash().values(HASH_KEY);

        listSt.forEach(st -> {
            cacheService.addCache(st);
        });

        return listSt;
    }


    public String clearAllCache() {
        cacheManager.getCacheNames().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
        return "All Cache is cleared";
    }

}
