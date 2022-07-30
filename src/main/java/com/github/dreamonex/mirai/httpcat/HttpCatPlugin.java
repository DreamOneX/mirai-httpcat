// Mirai HTTP Cat
// This file is part of Mirai HTTP Cat
//  Copyright (C) 2022 DreamOneX <me@dreamonex.eu.org> and contributors
//
//  Mirai HTTP Cat is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Affero General Public License as
//  published by the Free Software Foundation, either version 3 of the
//  License, or any later version.
//
//  Mirai HTTP Cat is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Affero General Public License for more details.
//
//  You should have received a copy of the GNU Affero General Public License
//  along with this program.  If not, see <https://www.gnu.org/licenses/>.

package com.github.dreamonex.mirai.httpcat;

import com.github.dreamonex.mirai.httpcat.config.HttpCatConfig;
import com.github.dreamonex.mirai.httpcat.handlers.MessageHandler;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.MessageEvent;

public final class HttpCatPlugin extends JavaPlugin {
    public static final HttpCatPlugin INSTANCE = new HttpCatPlugin();

    private HttpCatPlugin() {
        super(new JvmPluginDescriptionBuilder("com.github.dreamonex.mirai.httpcat", "0.1.0")
                .name("HttpCat")
                .author("DreamOneX")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("HTTP Cat loaded");
        reloadPluginConfig(HttpCatConfig.INSTANCE);

        GlobalEventChannel.INSTANCE.subscribeAlways(
            MessageEvent.class,
            MessageHandler::handle
        );
    }
}
