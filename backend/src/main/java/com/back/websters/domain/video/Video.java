package com.back.websters.domain.video;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Video {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VIDEO_ID")
    private long id;
    @Column(nullable = false , name = "VIDEO_NAME")
    private String name;
    @Column(nullable = false, name = "VIDEO_URI")
    private String uri;

}
