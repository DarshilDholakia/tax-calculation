package com.teamC.calculation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Payload {
    private String authorizationnHeader;
    //value from postman when make request
    private String personId;
}
