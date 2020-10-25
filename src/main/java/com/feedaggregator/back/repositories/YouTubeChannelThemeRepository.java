package com.feedaggregator.back.repositories;

import com.feedaggregator.back.entity.YouTubeChannelTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface YouTubeChannelThemeRepository extends JpaRepository<YouTubeChannelTheme, String> {


    // The suggestions
    // You can do it with @Query or use the spring magic to generate the query
    @Query("select theme from YouTubeChannelTheme theme where theme.themeName = :name")
    Collection<YouTubeChannelTheme> findByName(@Param("name") String name);

}
