package com.botola.agentapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Builder
@Getter
@Setter
public class AlertMessage {
    List<?> messages;
}