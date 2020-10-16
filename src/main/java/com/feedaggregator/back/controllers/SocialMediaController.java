package com.feedaggregator.back.controllers;

import com.feedaggregator.back.boundary.YouTubeVideo;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SocialMediaController {

    @GetMapping("/")
    public ResponseEntity main() {

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        YouTubeVideo video = restTemplate.getForObject("https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCq19-LqvG35A-30oyAiPiqA&key=AIzaSyBNl2SBp8Gw_0UXhchOQT2U0N3mJyEku44&maxResults=1",
                YouTubeVideo.class);
        return ResponseEntity.ok(video);
    }

    @GetMapping("/posts")
    public ResponseEntity post(){
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        YouTubeVideo video = restTemplate.getForObject("https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCq19-LqvG35A-30oyAiPiqA&key=AIzaSyBNl2SBp8Gw_0UXhchOQT2U0N3mJyEku44&maxResults=1",
                YouTubeVideo.class);

        return ResponseEntity.ok(new SocialMediaPost[] {
                new SocialMediaPost(1L, "asd", "15.10.2020", "https://i.pinimg.com/originals/5e/c5/01/5ec50175139d39c8a69f0f8ce3233ea9.jpg", "", "sample",
                        new String[] {"https://i.pinimg.com/originals/5e/c5/01/5ec50175139d39c8a69f0f8ce3233ea9.jpg",
                                "https://i.pinimg.com/originals/5e/c5/01/5ec50175139d39c8a69f0f8ce3233ea9.jpg",
                                "https://i.pinimg.com/originals/5e/c5/01/5ec50175139d39c8a69f0f8ce3233ea9.jpg"},new String[] {},new String[] {}),
                new SocialMediaPost(2L, "dddaaasd", "16.10.2020", "https://i.pinimg.com/originals/5e/c5/01/5ec50175139d39c8a69f0f8ce3233ea9.jpg", "", "ksldfjghlksdfhl",
                        new String[] {"https://i.pinimg.com/originals/5e/c5/01/5ec50175139d39c8a69f0f8ce3233ea9.jpg",
                                "https://i.pinimg.com/originals/5e/c5/01/5ec50175139d39c8a69f0f8ce3233ea9.jpg",
                                "https://i.pinimg.com/originals/5e/c5/01/5ec50175139d39c8a69f0f8ce3233ea9.jpg"},new String[] {},new String[] {}),
                video.toSocialMediaPosts().get(0)
        });
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
