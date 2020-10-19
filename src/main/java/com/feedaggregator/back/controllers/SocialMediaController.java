package com.feedaggregator.back.controllers;

import com.feedaggregator.back.boundary.YouTubeVideoBoundary;
import com.feedaggregator.back.entity.YouTubeChannel;
import com.feedaggregator.back.services.YouTubeChannelService;
import com.feedaggregator.back.services.YouTubeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


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

    @GetMapping("/subscriptions/youtube")
    public ResponseEntity YouTubeChannels() {
        List<YouTubeChannel> channels = youTubeChannelService.findAll();

        if (channels != null)
        return ResponseEntity.ok(channels);
        else return ResponseEntity.ok("No channels found");

    }

    @GetMapping("/test")
    public ResponseEntity test() {
        YouTubeVideoBoundary video = youTubeClientService.getVideosFromChannel("UCq19-LqvG35A-30oyAiPiqA", 5);
        return ResponseEntity.ok(video);
    }

    @GetMapping("/posts")
    public ResponseEntity post(){
        ArrayList<SocialMediaPost> posts = new ArrayList<>(10);
        channels.addAll(youTubeChannelService.findAll());

        posts.addAll(youTubeClientService.getVideosFromSubscriptions(channels));

        return ResponseEntity.ok(posts);
    }

    @RequestMapping(value = "/subscriptions/youtube", method = RequestMethod.POST)
    public String addYouTubeChannel(@RequestBody String title) {
        channels.add(youTubeClientService.addChannel(title));
        return "success";
    }

}
