package com.feedaggregator.back.services;

import com.feedaggregator.back.dto.Item;
import com.feedaggregator.back.dto.SocialMediaPost;
import com.feedaggregator.back.dto.YouTubeVideoDTO;
import com.feedaggregator.back.entity.YouTubeChannel;
import com.feedaggregator.back.entity.YouTubeChannelTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SocialMediaService {

    private static final String YOUTUBE_DEFAULT_VIDEO_URL = "https://www.youtube.com/watch?v=";
    private YouTubeChannelService channelService;
    private YouTubeClientService clientService;
    private YouTubeChannelThemeService themeService;

    @Autowired
    public SocialMediaService (YouTubeChannelService channelService, YouTubeClientService clientService, YouTubeChannelThemeService themeService){
        this.channelService = channelService;
        this.clientService = clientService;
        this.themeService = themeService;
    }

    public List<SocialMediaPost> toSocialMediaPosts(YouTubeVideoDTO video) {
        List<SocialMediaPost> posts = new LinkedList<SocialMediaPost>();
        List<YouTubeChannel> channels = channelService.findAll();
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

    public List<YouTubeChannel> getYouTubeChannels(){
        return channelService.findAll();
    }

    public List<SocialMediaPost> getVideosFromCollection(List<YouTubeChannel> channels) {

        LinkedList<SocialMediaPost> videos = new LinkedList<>();
        YouTubeVideoDTO video;

        for (YouTubeChannel channel: channels) {
            video = clientService.getVideosFromChannel(channel.getChannelId(), 5);
            videos.addAll(toSocialMediaPosts(video));
        }

        return  videos;
    }

    public List<SocialMediaPost> getThemedVideos(String themeName) {

        YouTubeChannelTheme theme = themeService.getByName(themeName);

        return getVideosFromCollection(theme.getChannels());
    }

}
