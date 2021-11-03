package com.back.websters.domain.script;

import com.back.websters.domain.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScriptRepository extends JpaRepository<Script, Long> {

    List<Script> findAllByVideo(Video video);

}
