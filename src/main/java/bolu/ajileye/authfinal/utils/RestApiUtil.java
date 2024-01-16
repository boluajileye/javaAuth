package bolu.ajileye.authfinal.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Slf4j
@Service
public class RestApiUtil {

    @Value("${api.consumption}")
    private String BaseUrl;

    @Value("${api.consumption_2}")
    private String BaseUrl2;

    private final RestTemplate restTemplate;

    public RestApiUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T get(String path, Class<T> responseClass) {
        return restTemplate.getForObject(formatUrl(path), responseClass);
    }


    public <T> T post(String path, Object data, Class<T> responseClass) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<T> requestEntity = (HttpEntity<T>) new HttpEntity<>(data,httpHeaders);

        return restTemplate.postForObject(formatUrl2(path), requestEntity, responseClass);
    }

    private String formatUrl(String path) {
        String url = (BaseUrl + path);

        log.info(url);
        return url;
    }
    private String formatUrl2(String path) {
        String url = (BaseUrl2 + path);

        log.info(url);
        return url;
    }
}
