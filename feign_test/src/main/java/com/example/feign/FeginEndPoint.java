package com.example.feign;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;

/**
 * @author sml
 * created on  2018/10/30
 */

@FeignClient(value = "user", url = "${addr.url}")
public interface FeginEndPoint {

    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Result> contributors(@Param("owner") String owner, @Param("repo") String repo);


}
