/**
 * Copyright (c) 2016-2019, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress;

import com.jfinal.config.Constants;
import com.jfinal.config.Interceptors;
import io.jboot.aop.jfinal.JfinalHandlers;
import io.jboot.server.listener.JbootAppListenerBase;
import io.jpress.core.menu.SystemMenuManager;
import io.jpress.core.wechat.WechatAddonManager;
import io.jpress.web.captcha.JPressCaptchaCache;
import io.jpress.web.handler.JPressHandler;
import io.jpress.web.interceptor.UTMInterceptor;
import io.jpress.web.render.JPressRenderFactory;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: JPress 初始化工具
 * @Package io.jpress
 */
public class JPressInitializer extends JbootAppListenerBase {

    @Override
    public void onJfinalConstantConfig(Constants constants) {
        constants.setRenderFactory(new JPressRenderFactory());
        constants.setCaptchaCache(new JPressCaptchaCache());
    }

    @Override
    public void onHandlerConfig(JfinalHandlers handlers) {
        handlers.add(0, new JPressHandler());
    }

    @Override
    public void onInterceptorConfig(Interceptors interceptors) {
        interceptors.add(new UTMInterceptor());
//        interceptors.add(new CSRFInterceptor());
    }

    @Override
    public void onJFinalStarted() {

        SystemMenuManager.me().init();
        WechatAddonManager.me().init();

    }


}
