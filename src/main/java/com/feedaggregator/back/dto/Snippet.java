package com.feedaggregator.back.dto;

import lombok.Data;

@Data
public class Snippet {

    private String publishedAt;
    private String title;
    private String description;
    private Thumbnails thumbnails;
    private String channelTitle;
    private String channelId;
    private ResourceId resourceId;

}
