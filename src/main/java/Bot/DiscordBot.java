package Bot;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;

@Getter
public class DiscordBot {
    private JDA API;
    public static final String prefix = "$";
    private final String token = "ADD_BOT_CLIENT_SECRET_KEY_HERE";

    public DiscordBot() {
        // 1. Setup a EnumSet of GatewayIntent and load all permissions to list
        // 2. Build JDA Api configuration with api token and permission list
    }

    // 1. Create method with return type of EnumSet<GatewayIntent> with no params
    // 2. Create dynamic method that allows us to add eventListeners from application runtime
}
