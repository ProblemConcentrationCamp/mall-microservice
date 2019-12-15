package com.mall.web.undertow;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

/**
 * <pre>
 * customized the config fro undertow
 * </pre>
 *
 * @author LCN
 * @date 2019-12-15 11:22
 */
@Deprecated
public class UndertowConfig implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

    /**
     * the buffer size
     */
    private final static int POOL_BUFFER__SIZE = 1024;

    @Override
    public void customize(UndertowServletWebServerFactory factory) {
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
            webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, POOL_BUFFER__SIZE));
        });
    }
}
