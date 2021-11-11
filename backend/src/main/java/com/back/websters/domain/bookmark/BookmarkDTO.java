package com.back.websters.domain.bookmark;


import lombok.Data;

@Data
public class BookmarkDTO {
    private int time;
    public BookmarkDTO(Bookmark bookmark) {
        String time = bookmark.getTime();
        this.time = Integer.parseInt(time.trim());
    }
}
