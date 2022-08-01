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
import java.io.InputStream;

import com.github.dreamonex.mirai.httpcat.HttpCatPlugin;
import com.github.dreamonex.mirai.httpcat.utils.ConfigManager;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.UserMessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class MessageHandler {
    public static void sendHttpCat(Contact target, String content) {
        if (content.startsWith("http.cat/")) {
            if (content.split("/").length != 2) return;
            String url = ConfigManager.getHttpCatUrl()
                         + content.split("/")[1]
                         + ConfigManager.getSuffixName();
            HttpCatPlugin.INSTANCE.getLogger()
                .debug("url:" + url);
            
            new Thread() {
                @Override
                public void run() {
                    try {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                        .url(url)
                        .build();
                        InputStream stream;
                        try (Response response = client.newCall(request).execute()) {
                            stream = response.body().byteStream();
                            if (response.code() != 404) {
                                target.sendMessage("404 Not Found");
                                return;
                            } else if (response.code() != 200) {
                                target.sendMessage("奇怪的错误，检查你的url");
                                return;
                            }
                            ExternalResource res = ExternalResource
                                                   .create(stream);
                            Image image = target.uploadImage(res);
                            res.close();
                            target.sendMessage(image);
                        } catch (IllegalArgumentException e) {
                            HttpCatPlugin.INSTANCE.getLogger()
                                .verbose("wrong url");
                            target.sendMessage("错误的资源类型（请检查url地址）");
                        }
                    } catch (IOException e) {
                        HttpCatPlugin.INSTANCE.getLogger()
                        .error("呜呜呜，怎么连不上", e);
                    }
                }
            } .start();
        }
    }

    public static void handleGroupMessage(GroupMessageEvent e) {
        String content = e.getMessage().contentToString();
        sendHttpCat(e.getGroup(), content);
    }

    public static void handleUserMessage(UserMessageEvent e) {
        String content = e.getMessage().contentToString();
        sendHttpCat(e.getSender(), content);
    }
}
