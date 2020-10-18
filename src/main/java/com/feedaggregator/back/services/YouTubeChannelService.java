package com.feedaggregator.back.services;

import com.feedaggregator.back.entity.YouTubeChannel;
import com.feedaggregator.back.repositories.YouTubeChannelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YouTubeChannelService {

    private YouTubeChannelRepository youTubeChannelRepository;

    public YouTubeChannelService (YouTubeChannelRepository youTubeChannelRepository) {
        this.youTubeChannelRepository = youTubeChannelRepository;
    }

    public void save(YouTubeChannel channel){
        if (channel != null)
            youTubeChannelRepository.save(channel);
    }

    public void delete(YouTubeChannel channel){
        youTubeChannelRepository.delete(channel);
    }

    public void update(Long id, YouTubeChannel channel){

    }

    public List<YouTubeChannel> findAll() {
        return youTubeChannelRepository.findAll();
    }

}
