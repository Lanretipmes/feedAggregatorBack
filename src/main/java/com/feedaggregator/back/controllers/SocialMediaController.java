package com.feedaggregator.back.controllers;

import com.feedaggregator.back.dto.SocialMediaPost;
import com.feedaggregator.back.dto.YouTubeVideoDTO;
import com.feedaggregator.back.entity.YouTubeChannel;
import com.feedaggregator.back.services.SocialMediaService;
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
    private SocialMediaService mediaService;
    private LinkedList<YouTubeChannel> channels = new LinkedList<>();
    private ArrayList<SocialMediaPost> posts;

    @Autowired
    public SocialMediaController (YouTubeClientService youTubeClientService, SocialMediaService mediaService) {
        this.youTubeClientService = youTubeClientService;
        this.mediaService = mediaService;
    }

    @GetMapping("/")
    public ResponseEntity main() {

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/subscriptions/youtube")
    public ResponseEntity YouTubeChannels() {
        List<YouTubeChannel> channels = mediaService.getYouTubeChannels();

        if (channels != null)
        return ResponseEntity.ok(channels);
        else return ResponseEntity.ok("No channels found");

    }

    @GetMapping("/test")
    public ResponseEntity test() {
        YouTubeVideoDTO video = youTubeClientService.getVideosFromChannel("UCq19-LqvG35A-30oyAiPiqA", 5);
        return ResponseEntity.ok(video);
    }

    @GetMapping("/posts/all")
    public ResponseEntity post(){
        posts = new ArrayList<>(10);
        channels.addAll(mediaService.getYouTubeChannels());

        posts.addAll(mediaService.getVideosFromCollection(channels));

        return ResponseEntity.ok(posts);
    }

    @RequestMapping(value = "/subscriptions/youtube", method = RequestMethod.POST)
    public String addYouTubeChannel(@RequestBody String title) {
        channels.add(youTubeClientService.addChannel(title));
        return "success";
    }

    @GetMapping("/posts/youtube/themes/{themeName}")
    public ResponseEntity getThemedYouTubeVideos(@RequestBody String themeName){

        posts = new ArrayList<>(10);
        posts.addAll(mediaService.getThemedVideos(themeName));

        return ResponseEntity.ok(posts);

    }

}
