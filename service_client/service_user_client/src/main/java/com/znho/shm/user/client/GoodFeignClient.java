package com.znho.shm.user.client;

import com.znho.shm.model.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user")
@Component
public interface GoodFeignClient {
    @GetMapping("/api/user/getUserInfoById/{id}")
    public User getUserInfoById(@PathVariable Long id);
}
