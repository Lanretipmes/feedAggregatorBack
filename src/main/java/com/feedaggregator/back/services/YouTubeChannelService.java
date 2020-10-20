package com.feedaggregator.back.services;

import com.feedaggregator.back.entity.YouTubeChannel;
import com.feedaggregator.back.repositories.YouTubeChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class YouTubeChannelService {

    private YouTubeChannelRepository youTubeChannelRepository;

    @Autowired
    public YouTubeChannelService (YouTubeChannelRepository youTubeChannelRepository) {
        this.youTubeChannelRepository = youTubeChannelRepository;
    }

    @Transactional
    public void save(YouTubeChannel channel){
        if (channel != null)
            youTubeChannelRepository.save(channel);
    }

    @Transactional
    public void delete(YouTubeChannel channel){
        youTubeChannelRepository.delete(channel);
    }

    public void update(Long id, YouTubeChannel channel){

    }

    public List<YouTubeChannel> findAll() {
        return youTubeChannelRepository.findAll();
    }

    public YouTubeChannel getById(String channelId) {

        for (YouTubeChannel channel: findAll()) {
            if (channel.getChannelId().equals(channelId))
                return channel;
        }

        return null;
    }

}
