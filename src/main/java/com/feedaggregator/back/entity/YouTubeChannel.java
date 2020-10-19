package com.feedaggregator.back.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "youtubechannels")
@NoArgsConstructor
@RequiredArgsConstructor
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

}
