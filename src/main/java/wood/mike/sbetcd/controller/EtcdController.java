package wood.mike.sbetcd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wood.mike.sbetcd.model.GetResponse;
import wood.mike.sbetcd.model.PutRequest;
import wood.mike.sbetcd.model.PutResponse;
import wood.mike.sbetcd.service.EtcdService;

@Slf4j
@RestController
public class EtcdController {

    private final EtcdService etcdService;

    public EtcdController(EtcdService etcdService) {
        this.etcdService = etcdService;
    }

    @PostMapping("/put")
    public ResponseEntity<PutResponse> put(@RequestBody PutRequest putRequest) {
        try{
            log.info("PutRequest: {}", putRequest);
            etcdService.put(putRequest);
            return new ResponseEntity<>(PutResponse.success(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(PutResponse.failure(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<GetResponse> get(@RequestParam String key) {
        try{
            log.info("GetRequest: {}", key);
            String value = etcdService.get(key);
            return new ResponseEntity<>(GetResponse.success(value), HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(GetResponse.failure(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
