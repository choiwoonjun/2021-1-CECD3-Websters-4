package com.back.websters.component.utils;

import com.back.websters.domain.script.Script;
import com.back.websters.domain.video.Video;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ScriptMaker {

    public List<Script> convertJsonToScript(String scriptData, Video video) throws JSONException {
        List<Script> scripts = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(scriptData);
        JSONArray items = jsonObject.getJSONObject("results").getJSONArray("items");
        String startTime = "";
        String endTime = "";
        String content = "";
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            if (item.getString("type").equals("punctuation")) {
                content += ".";
                content = content.trim();

                scripts.add(Script.builder()
                        .startTime(startTime)
                        .endTime(endTime)
                        .content(content)
                        .video(video)
                        .build());

                startTime = "";
                endTime = "";
                content = "";
            } else {
                if (startTime.equals("")) {
                    startTime = item.getString("start_time");
                }
                endTime = item.getString("end_time");
                content += " " + item.getJSONArray("alternatives").getJSONObject(0).getString("content");
            }
        }
        return scripts;
    }

}
