package com.feedaggregator.back.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "youtubechannels")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class YouTubeChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "channelid")
    @NonNull private String channelId;
    @Column(name = "channelname")
    @NonNull private String channelName;
    @Column(name = "avatarlink")
    @NonNull private String avatarLink;
    @Column(name = "uploadsid")
    @NonNull private String uploadsId;
    @Column(name = "themes")
    @ManyToMany
    @ToString.Exclude
    @PrimaryKeyJoinColumn(name = "themename")
    private List<YouTubeChannelTheme> themes;

}
