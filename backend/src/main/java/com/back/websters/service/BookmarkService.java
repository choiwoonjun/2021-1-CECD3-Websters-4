package com.back.websters.service;

import com.back.websters.domain.bookmark.Bookmark;
import com.back.websters.domain.bookmark.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    List<Bookmark> getBookmarks() {

        return null;
    }

}
