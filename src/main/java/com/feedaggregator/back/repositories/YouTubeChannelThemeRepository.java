package com.feedaggregator.back.repositories;

import com.feedaggregator.back.entity.YouTubeChannelTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YouTubeChannelThemeRepository extends JpaRepository<YouTubeChannelTheme, String> {



}
