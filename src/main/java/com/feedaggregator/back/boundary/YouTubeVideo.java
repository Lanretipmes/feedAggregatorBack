package com.feedaggregator.back.boundary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.feedaggregator.back.controllers.SocialMediaPost;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeVideo {

    private List<Item> items;

    public List<SocialMediaPost> toSocialMediaPosts() {
        List<SocialMediaPost> posts = new LinkedList<SocialMediaPost>();

        for (Item item: items) {
            posts.add(new SocialMediaPost(1L,
                    item.getSnippet().getChannelTitle(),
                    item.getSnippet().getPublishedAt(),
                    "",
                    "",
                    item.getSnippet().getDescription(),
                    new String[] { item.getSnippet().getThumbnails().getMedium().getUrl()},
                    new String[] {},
                    new String[] {}));
        }

        return posts;
    }
}
