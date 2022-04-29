package kz.iitu.itse1908.murzaliev.controllers;

import io.swagger.annotations.ResponseHeader;
import io.swagger.v3.oas.annotations.headers.Header;
import kz.iitu.itse1908.murzaliev.database.Provider;
import kz.iitu.itse1908.murzaliev.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    private ProviderRepository providerRepository;

    @Autowired
    public ProviderController(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @GetMapping("head")
    public HttpEntity<String> getHeader () {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("test-header", Arrays.asList("test-header-value"));

        HttpEntity<String> responseEntity = new HttpEntity<>("test", headers);
        return responseEntity;
    }

    @RequestMapping(value="", method = RequestMethod.OPTIONS)
    ResponseEntity<?> collectionOptions()
    {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.OPTIONS)
                .build();
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> defaultP () {
        return ResponseEntity.ok("Welcome to the providers page!");
    }

    @GetMapping("getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Provider> getAll () {
        return providerRepository.findAll();
    }

    @GetMapping("get/{num}")
    public Page<Provider> getNumber (@PathVariable Integer num) {
        return providerRepository.findAll(PageRequest.of(1,num));
    }

    @PostMapping(path = "add",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Provider> addProvider (@RequestBody Provider newProvider) {
        Provider provider = providerRepository.save(new Provider(newProvider.getProviderName()));
        if (provider == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<>(provider, HttpStatus.CREATED);
        }
    }

    @PutMapping(path = "edit",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Provider> editProvider (@RequestBody Provider newProvider) {
        Provider provider = new Provider();
        if (newProvider.getProviderId() == null) {
            return ResponseEntity.badRequest().build();
        }
        else {
            provider = providerRepository.findByProviderId(newProvider.getProviderId());
            provider.setProviderName(newProvider.getProviderName());
            provider = providerRepository.save(provider);
        }

        if (provider == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<>(provider, HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping(path = "delete",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Provider> deleteProvider (@RequestBody Provider deleteProvider) {
        Provider provider = new Provider();
        if (deleteProvider.getProviderId() == null) {
            return ResponseEntity.badRequest().build();
        }
        else {
            provider = providerRepository.findByProviderId(deleteProvider.getProviderId());
            providerRepository.delete(provider);
        }

        return new ResponseEntity<>(provider, HttpStatus.OK);
    }
}
