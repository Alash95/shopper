package com.alash.shopper.dto.response;

import com.alash.shopper.dto.request.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
public class CustomerResponse {
    private String responseCode;
    private String responseMessage;
    private Data data;
}
