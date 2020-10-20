package com.feedaggregator.back.services;

import com.feedaggregator.back.boundary.Item;
import com.feedaggregator.back.boundary.YouTubeChannelBoundary;
import com.feedaggregator.back.boundary.YouTubeVideoBoundary;
import com.feedaggregator.back.controllers.SocialMediaPost;
import com.feedaggregator.back.entity.YouTubeChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
public class YouTubeClientService {

    @Value("${app.google.access-key}")
    private String key;
    private RestTemplate restTemplate;
    private YouTubeChannelService youTubeChannelService;
    private static final String YOUTUBE_DEFAULT_VIDEO_URL = "https://www.youtube.com/watch?v=";

    @Autowired
    public YouTubeClientService (RestTemplate restTemplate, YouTubeChannelService youTubeChannelService){
        this.restTemplate = restTemplate;
        this.youTubeChannelService = youTubeChannelService;
    }

    public YouTubeVideoBoundary getVideosFromChannel (String channelId, int maxResults){

        return restTemplate.getForObject("https://www.googleapis.com/youtube/v3/playlistItems?playlistId="+ youTubeChannelService.getById(channelId).getUploadsId() +
                "&key=" + key + "&maxResults=" + maxResults + "&part=snippet", YouTubeVideoBoundary.class);

    }

    public List<SocialMediaPost> getVideosFromSubscriptions (List<YouTubeChannel> channels) {

        LinkedList<SocialMediaPost> videos = new LinkedList<>();
        YouTubeVideoBoundary video;

        for (YouTubeChannel channel: channels) {
            video = getVideosFromChannel(channel.getChannelId(), 5);
            videos.addAll(toSocialMediaPosts(video));
        }

        return  videos;
    }

    public YouTubeChannel addChannel (String channelName) {
        YouTubeChannelBoundary channel;
        YouTubeChannel newChannel;

        channel = restTemplate.getForObject("https://www.googleapis.com/youtube/v3/channels?part=snippet&part=contentDetails&forUsername=" + channelName +
                "&key=" + key + "&maxResults=" + 1, YouTubeChannelBoundary.class);

        newChannel = new YouTubeChannel(
                channel.getItems().get(0).getId(), //channel id
                channelName,
                channel.getItems().get(0).getSnippet().getThumbnails().getDefaultThumb().getUrl(), //avatar link
                channel.getItems().get(0).getContentDetails().getRelatedPlaylists().getUploads()); //uploads link
        youTubeChannelService.save(newChannel);

        return newChannel;

    }

    public List<SocialMediaPost> toSocialMediaPosts(YouTubeVideoBoundary video) {
        List<SocialMediaPost> posts = new LinkedList<SocialMediaPost>();
        List<YouTubeChannel> channels = youTubeChannelService.findAll();
        String avatarLink = "";

        for (Item item: video.getItems()) {
            for (YouTubeChannel channel: channels) {
                if (channel.getChannelId().equals(item.getSnippet().getChannelId())) {
                    avatarLink = channel.getAvatarLink();
                    break;
                }
            }
            posts.add(new SocialMediaPost(1L,
                    item.getSnippet().getChannelTitle(),
                    item.getSnippet().getPublishedAt(),
                    item.getSnippet().getTitle(),
                    avatarLink,
                    YOUTUBE_DEFAULT_VIDEO_URL + item.getSnippet().getResourceId().getVideoId(),
                    item.getSnippet().getDescription(),
                    new String[] { item.getSnippet().getThumbnails().getMedium().getUrl()}, //source avatar
                    new String[] {},
                    new String[] {}));
        }

        return posts;
    }

}
