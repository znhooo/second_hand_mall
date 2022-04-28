package com.znho.shm.good.client;

import com.znho.shm.model.good.Good;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-good")
@Component
public interface OrderFeignClient {
    @RequestMapping("/api/good/updateGoodStatus")
    public boolean updateGoodStatus(@RequestBody Good good);
}
