package Lombok;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public abstract  class FilterRestTemplate implements RestOperations {

    protected RestTemplate restTemplate;

}

/*
    上述代码相当于


    public abstract class FilterRestTemplate implements RestOperations {

        protected volatile RestTemplate restTemplate;

        protected FilterRestTemplate(RestTemplate restTemplate) {
                this.restTemplate = restTemplate;
        }

        @Override
        public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
                return restTemplate.getForObject(url,responseType,uriVariables);
        }

        @Override
        public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
                return restTemplate.getForObject(url,responseType,uriVariables);
        }

        @Override
        public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
                return restTemplate.getForObject(url,responseType);
        }

        @Override
        public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
                return restTemplate.getForEntity(url,responseType,uriVariables);
        }
        //其他实现代码略。。。
    }


 */