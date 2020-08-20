import Bot.DiscordBot;
import Events.*;

import javax.security.auth.login.LoginException;
import static net.dv8tion.jda.api.OnlineStatus.ONLINE;
import static net.dv8tion.jda.api.entities.Activity.watching;

public class Application {
    public static void main(String[] args) {
        try {
        // 1. Instantiate Discord bot configuration class
            DiscordBot discordBot = new DiscordBot();

        // 2. Setup global configuration for Discord bot at startup
            discordBot.getAPI().getPresence().setActivity(watching("over my kids"));
            discordBot.getAPI().getPresence().setStatus(ONLINE);

        // 3. Add event classes to Discord bot object ( All event classes need to extend ListenerAdapter )
            discordBot.addEventListener(new InvitationEvent());
            discordBot.addEventListener(new MathEvents());
            discordBot.addEventListener(new ChannelHistoryEvent());
            discordBot.addEventListener(new RandomEvent());
            discordBot.addEventListener(new HelperEvent());

        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
