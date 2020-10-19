package com.feedaggregator.back.controllers;

import lombok.Data;
import lombok.NonNull;

@Data
public class SocialMediaPost {
    @NonNull private long id;
    @NonNull private String sourceName;
    @NonNull private String date;
    @NonNull private String title;
    @NonNull private String sourceAvatarLink;
    @NonNull private String postLink;
    @NonNull private String description;
    @NonNull private String[] pictures;
    @NonNull private String[] videoLinks;
    @NonNull private String[] songLinks;
}
