create table YouTubeChannels
(
    id         SERIAL PRIMARY KEY,
    channelId  VARCHAR(100) not null,
    channelName VARCHAR(100) not null,
    avatarLink VARCHAR(100) not null
);
