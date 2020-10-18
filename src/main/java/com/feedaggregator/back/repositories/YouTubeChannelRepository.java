package com.feedaggregator.back.repositories;

import com.feedaggregator.back.entity.YouTubeChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YouTubeChannelRepository extends JpaRepository <YouTubeChannel, Long> {

}
