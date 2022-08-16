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

package com.github.dreamonex.mirai.httpcat.commands;

import com.github.dreamonex.mirai.httpcat.HttpCatPlugin;
import com.github.dreamonex.mirai.httpcat.utils.ConfigManager;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JCompositeCommand;

public final class HttpCatCommand extends JCompositeCommand {
    public static final HttpCatCommand INSTANCE = new HttpCatCommand();
    private HttpCatCommand() {
        super(HttpCatPlugin.INSTANCE, "httpcat");
        this.setDescription("httpcat相关指令");
    }

    @SubCommand("setBaseUrl")
    public void setBaseUrl(CommandSender context, String url) {
        if (url == null || url.isEmpty()) {
            context.sendMessage("URL不能为空");
            return;
        }
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            context.sendMessage("非法URL");
            return;
        }
        ConfigManager.setHttpCatUrl(url);
        context.sendMessage("已成功设置URL为" + url);
    }

    @SubCommand("setSuffixName")
    public void setSuffixName(CommandSender context, String suffix) {
        if (suffix == "null") {
            ConfigManager.setSuffixName("");
            context.sendMessage("已成功设置后缀名为空")
        }
        if (!suffix.startsWith(".")) {
            context.sendMessage("非法后缀名");
            return;
        }
        ConfigManager.setSuffixName(suffix);
        context.sendMessage("已成功设置后缀名为" + suffix);
    }
}
