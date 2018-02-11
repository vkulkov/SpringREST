package com.client;

import com.domain.Account;
import com.domain.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

@Component
public class RestApplicationClient {

    private RestTemplate restTemplate;

    @Inject
    public RestApplicationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Account getAccount(Long id) {
        return restTemplate.getForObject("http://localhost:8080/MoneyFlow/accounts/{id}", Account.class, id);
    }

    public void updateAccount(Account account) {
        restTemplate.put("http://localhost:8080/MoneyFlow/accounts/{id}", account, account.getId());
    }

    public void deleteAccount(Long id) {
        restTemplate.delete("http://localhost:8080/MoneyFlow/accounts/{id}", id);
    }

    public Account createAccount(Account account) {
        return restTemplate.postForObject("http://localhost:8080/MoneyFlow/accounts", account, Account.class);
    }

    public Transfer getTransfer(Long id) {
        return restTemplate.getForObject("http://localhost:8080/MoneyFlow/transfers/{id}", Transfer.class, id);
    }

    public Transfer createTransfer(Transfer transfer) {
        return restTemplate.postForObject("http://localhost:8080/MoneyFlow/transfers", transfer, Transfer.class);
    }

    public Account exchangeResources(Long id) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Account> response = restTemplate.exchange(
                "http://localhost:8080/MoneyFlow/accounts/{id}", HttpMethod.GET, httpEntity, Account.class, id);
        return response.getBody();
    }
}
