package com.back.websters.service;

import com.back.websters.domain.bookmark.Bookmark;
import com.back.websters.domain.bookmark.BookmarkRepository;
import com.back.websters.domain.video.Video;
import com.back.websters.domain.video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final VideoRepository videoRepository;

    public void saveBookmarks(List<Bookmark> bookmarks, long videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 동영상이 존재하지 않습니다."));
        for (Bookmark bookmark : bookmarks) {
            bookmark.setVideo(video);
        }
        bookmarkRepository.saveAll(bookmarks);
    }

    public String findBookmarks(String videoName) {
        Video video = videoRepository.findByName(videoName)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 동영상이 존재하지 않습니다."));
        List<Bookmark> bookmarks = bookmarkRepository.findAllByVideo(video);
        JSONArray result = new JSONArray();
        for (Bookmark bookmark : bookmarks) {
            result.put(new JSONObject()
                    .put("bookmark_id", bookmark.getId())
                    .put("video_id", bookmark.getVideo().getId())
                    .put("time", bookmark.getTime()));
        }
        return new JSONObject().put("results", result).toString();
    }

}
