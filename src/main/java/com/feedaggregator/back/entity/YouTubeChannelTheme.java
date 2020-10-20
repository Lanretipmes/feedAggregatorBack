package com.feedaggregator.back.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "youtubechannelthemes")
@NoArgsConstructor
@RequiredArgsConstructor
public class YouTubeChannelTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "themename")
    @NonNull private String themeName;
    @ManyToMany
    @ToString.Exclude
    @PrimaryKeyJoinColumn(name = "channelid")
    private List<YouTubeChannel> channels;

}
