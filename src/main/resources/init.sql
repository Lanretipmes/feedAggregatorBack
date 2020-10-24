create table YouTubeChannels
(
    id         SERIAL PRIMARY KEY,
    channelId  VARCHAR(100) not null,
    channelName VARCHAR(100) not null,
    avatarLink VARCHAR(100) not null,
    uploadsId VARCHAR(100) not null,
    themes VARCHAR[]
);

create table YouTubeChannelThemes
(

    themeName VARCHAR(100) PRIMARY KEY,
    channelIds INT[]

)
