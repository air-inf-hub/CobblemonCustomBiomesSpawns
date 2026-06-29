package com.cobblemoncustom.biomes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import com.mojang.brigadier.arguments.*;
import static net.minecraft.server.command.CommandManager.*;

public class CobblemonCustomBiomes implements ModInitializer {

    public static final String MOD_ID = "cobblemoncustombiomes";

    @Override
    public void onInitialize() {
        registerBiomes();
        registerCommands();
        System.out.println("[CobblemonCustomBiomes] Mod cargado correctamente!");
    }

    private void registerBiomes() {
        // Los biomas se crean con archivos JSON (siguiente paso)
    }

    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("cobblemonbiome")
                .requires(source -> source.hasPermissionLevel(2))
                .then(literal("add")
                    .then(argument("pokemon", StringArgumentType.string())
                    .then(argument("biome_id", StringArgumentType.string())
                    .then(argument("spawn_rate", DoubleArgumentType.doubleArg(0.0, 1.0))
                    .then(argument("min_level", IntegerArgumentType.integer(1))
                    .then(argument("max_level", IntegerArgumentType.integer(1))
                    .then(argument("jefe_rate", DoubleArgumentType.doubleArg(0.0, 1.0))
                    .executes(ctx -> {
                        ServerCommandSource source = ctx.getSource();
                        String pokemon = StringArgumentType.getString(ctx, "pokemon");
                        String biome = StringArgumentType.getString(ctx, "biome_id");
                        source.sendFeedback(() -> Text.literal("§aSpawn de " + pokemon + " en " + biome + " registrado."), true);
                        return 1;
                    }))))))))));
        });
    }
}
