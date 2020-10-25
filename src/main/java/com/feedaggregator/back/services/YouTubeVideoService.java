package com.feedaggregator.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YouTubeVideoService {

    private YouTubeClientService clientService;
    private SocialMediaService mediaService;

    @Autowired
    public YouTubeVideoService (YouTubeClientService clientService, SocialMediaService mediaService){
        this.clientService = clientService;
        this.mediaService = mediaService;
    }

    // Looks pretty dead to me?


}
