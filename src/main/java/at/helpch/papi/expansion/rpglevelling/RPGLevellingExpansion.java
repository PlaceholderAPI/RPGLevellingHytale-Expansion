package at.helpch.papi.expansion.rpglevelling;

import at.helpch.placeholderapi.PlaceholderAPI;
import at.helpch.placeholderapi.expansion.PlaceholderExpansion;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zuxaw.plugin.api.RPGLevelingAPI;

public class RPGLevellingExpansion extends PlaceholderExpansion {
    private RPGLevelingAPI api;

    @Override
    public @NotNull String getIdentifier() {
        return "rpglevelling";
    }

    @Override
    public @NotNull String getAuthor() {
        return "HelpChat";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @Nullable String getRequiredPlugin() {
        return "Zuxaw:RPGLeveling";
    }

    @Override
    public boolean canRegister() {
        final boolean available = RPGLevelingAPI.isAvailable();

        if (available) {
            api = RPGLevelingAPI.get();
        }

        return available;
    }

    @Override
    public @Nullable String onPlaceholderRequest(final PlayerRef player, @NotNull final String params) {
        switch (params) {
            case "xp":
                return String.valueOf(api.getPlayerXP(player.getUuid()));
            case "level":
                return String.valueOf(api.getPlayerLevel(player.getUuid()));
            case "is_max":
                return PlaceholderAPI.booleanValue(api.isPlayerMaxLevel(player.getUuid()));
        }

        final RPGLevelingAPI.PlayerLevelInfo info = api.getPlayerLevelInfo(player.getUuid());

        if (info != null) {
            switch (params) {
                case "maxlevel":
                    return String.valueOf(info.getMaxLevel());
                case "xpnextlevel":
                    return String.valueOf(info.getXpNeededForNext());
            }
        }

        return null;
    }
}
