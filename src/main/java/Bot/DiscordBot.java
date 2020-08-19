package Bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class DiscordBot {
    public static final String PREFIX = "$";
    private final JDA API;
    private final String TOKEN = "ADD_BOT_CLIENT_SECRET_KEY_HERE"; // Keep token secured, use System.getenv("$TOKEN_VARIABLE")

    public DiscordBot() throws LoginException {
        // 1. Setup a EnumSet of GatewayIntent and load all permissions to list
        EnumSet<GatewayIntent> gatewayIntents = initializeGateways();
        // 2. Build JDA Api configuration with api token and permission list
        API = JDABuilder.create(TOKEN, gatewayIntents).build();
    }

    // 1. Create method with return type of EnumSet<GatewayIntent> with no params
    private EnumSet<GatewayIntent> initializeGateways() {
        return EnumSet.allOf(GatewayIntent.class);
    }

    // 2. Create dynamic method that allows us to add eventListeners from application runtime
    public void addEventListener(Object listener) {
        API.addEventListener(listener);
    }

    public JDA getAPI() {
        return API;
    }
}
