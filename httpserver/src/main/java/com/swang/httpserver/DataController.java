package com.swang.httpserver;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/data")
public class DataController {
    @PostMapping(value = "/upload", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> handleData(@RequestBody Mono<DataRequest> request) {
        return request.map(data -> {
            String id = data.getId();
            byte[] content = data.getContent();
            // Process the byte array as needed
            int length = (content != null) ? content.length : 0;
            return "Received id:  " + id + ", with byte[] of length: " + length + ", utf8 content: " + new String(content);
        });
    }
}
