package com.feedaggregator.back.services;

import com.feedaggregator.back.entity.YouTubeChannelTheme;
import com.feedaggregator.back.repositories.YouTubeChannelThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class YouTubeChannelThemeService {

    private YouTubeChannelThemeRepository youTubeChannelThemeRepository;

    @Autowired
    public YouTubeChannelThemeService (YouTubeChannelThemeRepository youTubeChannelThemeRepository) {
        this.youTubeChannelThemeRepository = youTubeChannelThemeRepository;
    }

    @Transactional
    public void save(YouTubeChannelTheme theme){
        if (theme != null)
            youTubeChannelThemeRepository.save(theme);
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

        for (YouTubeChannelTheme theme :youTubeChannelThemeRepository.findAll()) {
            if (theme.getThemeName().equals(name)) {
                return theme;
            }
        }
        return null;
    }
}
