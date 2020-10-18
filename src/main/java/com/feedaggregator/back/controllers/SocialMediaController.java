package com.feedaggregator.back.controllers;

import com.feedaggregator.back.boundary.YouTubeVideo;
import com.feedaggregator.back.entity.YouTubeChannel;
import com.feedaggregator.back.services.YouTubeChannelService;
import com.feedaggregator.back.services.YouTubeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;


@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.POST, RequestMethod.GET})
public class SocialMediaController {

    private YouTubeClientService youTubeClientService;
    private YouTubeChannelService youTubeChannelService;
    private LinkedList<YouTubeChannel> channels = new LinkedList<>();

    @Autowired
    public SocialMediaController (YouTubeClientService youTubeClientService, YouTubeChannelService youTubeChannelService) {
        this.youTubeClientService = youTubeClientService;
        this.youTubeChannelService = youTubeChannelService;
    }

    @GetMapping("/")
    public ResponseEntity main() {

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/subscription/youtube")
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

    @RequestMapping(value = "/subscription/youtube", method = RequestMethod.POST)
    public String addYouTubeChannel(@RequestBody String channelId) {
        YouTubeChannel newChannel = new YouTubeChannel(channelId);
        youTubeChannelService.save(newChannel);
        channels.add(newChannel);
        return "success";
    }

}
