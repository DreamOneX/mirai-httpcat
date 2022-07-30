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

package com.github.dreamonex.mirai.httpcat.handlers;

import java.io.IOException;

import com.github.dreamonex.mirai.httpcat.HttpCatPlugin;
import com.github.dreamonex.mirai.httpcat.utils.ConfigManager;
import com.github.dreamonex.mirai.httpcat.utils.HttpHelper;

import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;

public final class MessageHandler {
    public static void handle(MessageEvent e) {
        if (e.getMessage().toString().startsWith("http.cat/")) {
            String url = ConfigManager.getHttpCatUrl() 
                + e.getMessage().toString().split("/")[1];
            new Thread() {
                @Override
                public void run() {
                    try {
                        ExternalResource res = ExternalResource
                            .create(HttpHelper.getHttpInputStream(url));
                        Image image = e.getSubject().uploadImage(res);
                        res.close();
                        e.getSender().sendMessage(image);
                    } catch (IOException e) {
                        HttpCatPlugin.INSTANCE.getLogger()
                            .error("呜呜呜，怎么连不上", e);
                    }
                }
            }.start();
        }
    }
}
