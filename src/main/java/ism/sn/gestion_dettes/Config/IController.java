package ism.sn.gestion_dettes.Config;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IController<T, ID> {

    @GetMapping("")
    ResponseEntity<Map<String, Object>> selectAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size);

    @GetMapping("/{id}")
    ResponseEntity<Map<String, Object>> selectById(@PathVariable ID id);

    @PutMapping("/update/{id}")
    ResponseEntity<Map<String, Object>> update(@PathVariable ID id, @RequestBody T request);

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Map<String, Object>> delete(@PathVariable ID id);
}

