package at.helpch.papi.expansion.rpglevelling;

import at.helpch.placeholderapi.PlaceholderAPI;
import at.helpch.placeholderapi.expansion.PlaceholderExpansion;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
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
        return "1.0.1";
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
        final Ref<EntityStore> ref = player.getReference();

        if (ref == null) {
            return null;
        }

        final RPGLevelingAPI.PlayerLevelInfo info = api.getPlayerLevelInfo(player, ref.getStore());

        return switch (params) {
            case "xp" -> String.valueOf(info.getExperience());
            case "level" -> String.valueOf(info.getLevel());
            case "is_max" -> PlaceholderAPI.booleanValue(info.isMaxLevel());
            case "maxlevel" -> String.valueOf(info.getMaxLevel());
            case "xpnextlevel" -> String.valueOf(info.getXpNeededForNext());
            default -> null;
        };
    }
}
