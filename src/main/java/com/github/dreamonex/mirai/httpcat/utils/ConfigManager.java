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

package com.github.dreamonex.mirai.httpcat.utils;

import com.github.dreamonex.mirai.httpcat.config.HttpCatConfig;

import net.mamoe.mirai.console.data.Value;

public final class ConfigManager {
    public final static String getHttpCatUrl() {
        Value<String> pluginconfig = HttpCatConfig.INSTANCE.httpCatURL;
        return pluginconfig.get();
    }

    public final static void setHttpCatUrl(String url) {
        Value<String> pluginconfig = HttpCatConfig.INSTANCE.httpCatURL;
        pluginconfig.set(url);
    }

    public final static String getSuffixName() {
        Value<String> pluginconfig = HttpCatConfig.INSTANCE.suffixName;
        return pluginconfig.get();
    }

    public final static void setSuffixName(String suffix) {
        Value<String> pluginconfig = HttpCatConfig.INSTANCE.suffixName;
        pluginconfig.set(suffix);
    }
}
