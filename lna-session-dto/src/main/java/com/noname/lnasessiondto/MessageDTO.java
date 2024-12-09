package com.noname.lnasessiondto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDTO {

    private String content;
    private String sender;

}