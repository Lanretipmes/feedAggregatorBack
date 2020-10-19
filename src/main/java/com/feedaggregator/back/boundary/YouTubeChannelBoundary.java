package com.feedaggregator.back.boundary;

import lombok.Data;

import java.util.List;

@Data
public class YouTubeChannelBoundary {

    private List<YouTubeChannelItem> items;

}
