package com.feedaggregator.back.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Thumbnails {

    @JsonProperty("default")
    private Thumbnail defaultThumb;
    private Thumbnail medium;
    private Thumbnail high;


}
