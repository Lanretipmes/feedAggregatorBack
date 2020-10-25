package com.feedaggregator.back.services;

import com.feedaggregator.back.entity.YouTubeChannel;
import com.feedaggregator.back.entity.YouTubeChannelTheme;
import com.feedaggregator.back.repositories.YouTubeChannelThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class YouTubeChannelThemeService {

    private YouTubeChannelThemeRepository youTubeChannelThemeRepository;

    @Autowired
    public YouTubeChannelThemeService (YouTubeChannelThemeRepository youTubeChannelThemeRepository) {
        this.youTubeChannelThemeRepository = youTubeChannelThemeRepository;
    }

    @Transactional
    public void delete(YouTubeChannelTheme theme){
        youTubeChannelThemeRepository.delete(theme);
    }

    @Transactional
    public void update(String name, YouTubeChannelTheme newTheme){

    }

    public List<YouTubeChannelTheme> findAll(){
        return youTubeChannelThemeRepository.findAll();
    }

    public YouTubeChannelTheme getByName(String name){

        // This is fine for now, but keep in mind that getting all might take ages on a larger database
        // I will leave a suggestion on the repo
        for (YouTubeChannelTheme theme :youTubeChannelThemeRepository.findAll()) {
            if (theme.getThemeName().equals(name)) {
                return theme;
            }
        }
        return null;
    }

    public void createYouTubeChannelTheme(String name, List<YouTubeChannel> channels){
        if (channels == null)
            channels = new LinkedList<>();

        youTubeChannelThemeRepository.save(new YouTubeChannelTheme(name, channels));

    }

    public void addYouTubeChannelToTheme(YouTubeChannel channel, String themeName){

        YouTubeChannelTheme theme = getByName(themeName);
        theme.getChannels().add(channel);

    }
}
