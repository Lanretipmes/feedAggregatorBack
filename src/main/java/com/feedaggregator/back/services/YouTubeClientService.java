package com.feedaggregator.back.services;

import com.feedaggregator.back.boundary.YouTubeVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YouTubeClientService {

    @Value("${app.google.access-key}")
    private String key;
    private RestTemplate restTemplate;

    @Autowired
    public YouTubeClientService (RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public YouTubeVideo getVideosFromChannel (String channelId, int maxResults){

        return restTemplate.getForObject("https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&channelId="+ channelId +
                "&key=" + key + "&maxResults=" + maxResults, YouTubeVideo.class);

    }

}
