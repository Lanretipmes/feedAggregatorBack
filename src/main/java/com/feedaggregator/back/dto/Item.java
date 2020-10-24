package com.feedaggregator.back.dto;

import lombok.Data;

@Data
public class Item {

    private Snippet snippet;
    private String id;
    private ContentDetails contentDetails;

}
