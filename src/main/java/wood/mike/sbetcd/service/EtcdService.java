package wood.mike.sbetcd.service;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wood.mike.sbetcd.model.PutRequest;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class EtcdService {
    private final Client client;

    public EtcdService(@Value("${ETCD_ENDPOINTS:http://localhost:2379}") String endpoint) {
        log.info("Etcd service initializing endpoint: {}", endpoint);
        this.client = Client.builder()
                .endpoints(endpoint)
                .build();
    }

    public void put(PutRequest putRequest) throws Exception {
        client.getKVClient()
                .put(ByteSequence.from(putRequest.key(), StandardCharsets.UTF_8),
                        ByteSequence.from(putRequest.value(), StandardCharsets.UTF_8))
                .get();
    }

    public String get(String key) throws Exception {
        var response = client.getKVClient()
                .get(ByteSequence.from(key, StandardCharsets.UTF_8))
                .get();

        if (response.getKvs().isEmpty()) return null;
        return response.getKvs().getFirst().getValue().toString(StandardCharsets.UTF_8);
    }
}