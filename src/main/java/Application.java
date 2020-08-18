import Bot.DiscordBot;

import javax.security.auth.login.LoginException;
import static net.dv8tion.jda.api.OnlineStatus.ONLINE;
import static net.dv8tion.jda.api.entities.Activity.watching;

public class Application {
    public static void main(String[] args) {
        try {
        // 1. Instantiate Discord bot configuration class
            DiscordBot discordBot = new DiscordBot();

        // 2. Setup global configuration for Discord bot at startup
            discordBot.getAPI().getPresence().setActivity(watching("Over my kids"));
            discordBot.getAPI().getPresence().setStatus(ONLINE);

        // 3. Add event classes to Discord bot object ( All event classes need to extend ListenerAdapter )
            // 3.1 Let's create events and add them to the bot :)
        } catch (LoginException e) {
            e.printStackTrace();
        }

        //Trying to add something I'm not allowed to without pull request
    }
}
