package com.eazybytes.gatewayserver.filters;


import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "eazybank-correlation-id";

    public String getCorrelationId(HttpHeaders httpHeaders){
        if(httpHeaders.get(CORRELATION_ID) != null){
            List<String> requestHeaderList = httpHeaders.get(CORRELATION_ID);
            return requestHeaderList.stream().findFirst().get();
        }else {
            return  null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange serverWebExchange , String name , String value){
        return serverWebExchange.mutate().request(serverWebExchange.getRequest().mutate().header(name,value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange serverWebExchange , String correlationId){
        return this.setRequestHeader(serverWebExchange, CORRELATION_ID,correlationId);
    }
}
