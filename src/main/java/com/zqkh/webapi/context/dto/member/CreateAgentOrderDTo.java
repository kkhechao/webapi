package com.zqkh.webapi.context.dto.member;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAgentOrderDTo {

    public enum Level {
        GOLD,
        DIAMOND,
        CROWN;
    }

    private Level level;
}
