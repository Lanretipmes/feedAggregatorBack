package com.feedaggregator.back.controllers;

import com.feedaggregator.back.boundary.YouTubeVideo;
import com.feedaggregator.back.entity.YouTubeChannel;
import com.feedaggregator.back.services.YouTubeChannelService;
import com.feedaggregator.back.services.YouTubeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SocialMediaController {

    private YouTubeClientService youTubeClientService;
    private YouTubeChannelService youTubeChannelService;
    private LinkedList<YouTubeChannel> channels = new LinkedList<>();

    @Autowired
    public SocialMediaController (YouTubeClientService youTubeClientService) {
        this.youTubeClientService = youTubeClientService;
    }

    @GetMapping("/")
    public ResponseEntity main() {

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/YTchannels")
    public ResponseEntity YouTubeChannels() {
        return ResponseEntity.ok(youTubeChannelService.findAll());
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        YouTubeVideo video = youTubeClientService.getVideosFromChannel("UCq19-LqvG35A-30oyAiPiqA", 5);
        return ResponseEntity.ok(video);
    }

    @GetMapping("/posts")
    public ResponseEntity post(){
        ArrayList<SocialMediaPost> posts = new ArrayList<>(10);

        YouTubeVideo video = youTubeClientService.getVideosFromChannel("UCq19-LqvG35A-30oyAiPiqA", 10);

        posts.addAll(video.toSocialMediaPosts());

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/addYTchannel")
    public String addYouTubeChannel(@RequestParam(name = "channelId", required = true) String channelId) {
        YouTubeChannel newChannel = new YouTubeChannel(channelId);
        youTubeChannelService.save(newChannel);
        channels.add(newChannel);
        return "success";
    }

}
