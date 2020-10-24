package com.feedaggregator.back.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "youtubechannelthemes")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class YouTubeChannelTheme {

    @Column(name = "themename")
    @NonNull private String themeName;
    @ManyToMany
    @ToString.Exclude
    @PrimaryKeyJoinColumn(name = "channelids")
    //@JoinTable()
    @NonNull private List<YouTubeChannel> channels;

}
