package com.back.websters.utils;

import com.back.websters.domain.script.Script;
import com.back.websters.service.DataService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ScriptMaker {

    private final DataService dataService;

    public void convertJsonToScript(String scriptData, long videoId) throws JSONException {

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

                Script script = Script.builder()
                        .startTime(startTime)
                        .endTime(endTime)
                        .content(content)
                        .build();

                dataService.saveScript(videoId, script);

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

    }
}
