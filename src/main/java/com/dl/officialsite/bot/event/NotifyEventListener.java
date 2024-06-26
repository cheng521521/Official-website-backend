package com.dl.officialsite.bot.event;

import com.dl.officialsite.bot.constant.BotEnum;
import com.dl.officialsite.bot.constant.ChannelEnum;
import com.dl.officialsite.bot.constant.GroupNameEnum;
import com.dl.officialsite.bot.discord.DiscordBotService;
import com.dl.officialsite.bot.model.Message;
import com.dl.officialsite.bot.telegram.TelegramBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Component
public class NotifyEventListener implements ApplicationListener<EventNotify> {
    @Autowired
    private TelegramBotService telegramBotService;
    @Autowired
    private DiscordBotService discordBotService;

    public static Set<ChannelEnum> ENABLE_CHANNELS = new HashSet<>();

    static {
        ENABLE_CHANNELS.add(ChannelEnum.WELCOME);
        ENABLE_CHANNELS.add(ChannelEnum.SHARING);
    }

    @Override
    public void onApplicationEvent(EventNotify event) {
        String sourceName = event.getSource().toString();
        log.info("event class：{}, message:{}", sourceName, event);

        Optional.of(event.getBotEnum())
            .map(botEnum -> event.getBotEnum() == BotEnum.ALL ? Arrays.asList(BotEnum.values()) : Arrays.asList(botEnum))
            .ifPresent(
                botList -> botList.forEach(bot -> sendMessage(bot, event.getGroupEnum(), event.getChannelEnum(), event.getMsg())));
    }

    private void sendMessage(BotEnum botEnum, GroupNameEnum group, ChannelEnum channelEnum, Message message) {
        if (!ENABLE_CHANNELS.contains(channelEnum)) {
            return;
        }

        switch (botEnum) {
            case DISCORD:
                if (discordBotService.isBotInitialized()) {
                    discordBotService.sendMessage(group, channelEnum, message);
                }
                break;
            case TELEGRAM:
                if (telegramBotService.isBotInitialized()) {
                    telegramBotService.sendMessage(group, channelEnum, message);
                }
                break;
            default:
                break;
        }
    }
}
