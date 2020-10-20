package com.feedaggregator.back.boundary;

import lombok.Data;

@Data
public class Item {

    private Snippet snippet;
    private String id;
    private ContentDetails contentDetails;

}
