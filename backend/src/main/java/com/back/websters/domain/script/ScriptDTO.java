package com.back.websters.domain.script;

import lombok.Data;

@Data
public class ScriptDTO {
    private float startTime;
    private float endTime;
    private String content;

    public ScriptDTO(Script script) {
        this.startTime = Float.parseFloat(script.getStartTime());
        this.endTime = Float.parseFloat(script.getEndTime());
        this.content=script.getContent();
    }
}
