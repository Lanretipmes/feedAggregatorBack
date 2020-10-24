package com.feedaggregator.back.services;

import com.feedaggregator.back.dto.YouTubeChannelDTO;
import com.feedaggregator.back.dto.YouTubeVideoDTO;
import com.feedaggregator.back.entity.YouTubeChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YouTubeClientService {

    @Value("${app.google.access-key}")
    private String key;
    private RestTemplate restTemplate;
    private YouTubeChannelService youTubeChannelService;
    private YouTubeChannelThemeService youTubeChannelThemeService;

    @Autowired
    public YouTubeClientService (RestTemplate restTemplate, YouTubeChannelService youTubeChannelService, YouTubeChannelThemeService youTubeChannelThemeService){
        this.restTemplate = restTemplate;
        this.youTubeChannelService = youTubeChannelService;
        this.youTubeChannelThemeService = youTubeChannelThemeService;
    }

    public YouTubeVideoDTO getVideosFromChannel (String channelId, int maxResults){

        return restTemplate.getForObject("https://www.googleapis.com/youtube/v3/playlistItems?playlistId="+ youTubeChannelService.getById(channelId).getUploadsId() +
                "&key=" + key + "&maxResults=" + maxResults + "&part=snippet", YouTubeVideoDTO.class);

    }

    public YouTubeChannel addChannel (String channelName) {
        YouTubeChannelDTO channel;
        YouTubeChannel newChannel;

        channel = restTemplate.getForObject("https://www.googleapis.com/youtube/v3/channels?part=snippet&part=contentDetails&forUsername=" + channelName +
                "&key=" + key + "&maxResults=" + 1, YouTubeChannelDTO.class);

        newChannel = new YouTubeChannel(
                channel.getItems().get(0).getId(), //channel id
                channelName,
                channel.getItems().get(0).getSnippet().getThumbnails().getDefaultThumb().getUrl(), //avatar link
                channel.getItems().get(0).getContentDetails().getRelatedPlaylists().getUploads()); //uploads link
        youTubeChannelService.save(newChannel);

        return newChannel;

    }

}
