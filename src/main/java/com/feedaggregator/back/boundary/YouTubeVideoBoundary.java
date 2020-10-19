package com.feedaggregator.back.boundary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeVideoBoundary {

    private List<Item> items;

}
