package com.alash.shopper.dto.request;

import com.alash.shopper.dto.response.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Data {

    private String username;
    private String email;


}
