create table YouTubeChannels
(
    id         SERIAL PRIMARY KEY,
    channelId  VARCHAR(100) not null,
    channelName VARCHAR(100) not null,
    avatarLink VARCHAR(100) not null,
    uploadsId VARCHAR(100) not null,
    themeId SERIAL references YouTubeChannelThemes
);

create table YouTubeChannelThemes
(
    id  SERIAL PRIMARY KEY,
    themeName VARCHAR(100) not null,
    channelId SERIAL references YouTubeChannels
)
