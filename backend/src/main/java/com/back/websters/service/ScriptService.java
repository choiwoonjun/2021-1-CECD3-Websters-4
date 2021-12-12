package com.back.websters.service;

import com.back.websters.component.utils.ScriptMaker;
import com.back.websters.domain.script.Script;
import com.back.websters.domain.script.ScriptDTO;
import com.back.websters.domain.script.ScriptRepository;
import com.back.websters.domain.video.Video;
import com.back.websters.domain.video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScriptService {

    private final ScriptRepository scriptRepository;
    private final VideoRepository videoRepository;
    private final ScriptMaker scriptMaker;

    public void createScripts(String resultString, long videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 동영상이 존재하지 않습니다."));
        scriptRepository.saveAll(scriptMaker.convertJsonToScript(resultString, video));
    }

    public String findScripts(String videoName) {
        Video video = videoRepository.findByName(videoName)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 동영상이 존재하지 않습니다."));
        List<ScriptDTO> result = scriptRepository.findAllByVideo(video)
                .stream().map(ScriptDTO::new).collect(Collectors.toList());

        JSONArray scripts = new JSONArray();
        for (ScriptDTO script : result) {
            scripts.put(new JSONObject()
                    .put("start_time", script.getStartTime())
                    .put("end_time", script.getEndTime())
                    .put("content", script.getContent()));
        }
        return new JSONObject().put("results", scripts).toString();
    }

}
