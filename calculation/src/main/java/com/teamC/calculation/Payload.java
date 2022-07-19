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
    //get information from feign client, it could use thses information to access other microserivces.
    //value from postman when make request(username password will become string )
    private String personId;
}
