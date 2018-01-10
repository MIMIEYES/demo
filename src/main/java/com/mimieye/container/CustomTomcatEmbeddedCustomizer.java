package com.mimieye.container;




import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




/**
 * 编程方式自定义内嵌容器
 *
 * @author fz
 *
 */
@Configuration
@ConfigurationProperties(prefix = "tomcat")
public class CustomTomcatEmbeddedCustomizer {
    private int maxThreads;
    private int minSpareThreads;
    private int acceptCount;
    private int connectionTimeout;
    private String URIEncoding = "UTF-8";
    private boolean disableUploadTimeout;
    private boolean enableLookups;
    private String compression;
    private int compressionMinSize;
    private String compressableMimeType;




    /**
     * 订制内嵌tomcat容器
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());
        return factory;
    }




    class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
        public void customize(Connector connector) {
            Http11NioProtocol protocol = (Http11NioProtocol) connector
                    .getProtocolHandler();
// 设置最大连接数
            protocol.setMaxThreads(maxThreads);
            protocol.setConnectionTimeout(connectionTimeout);
            protocol.setMinSpareThreads(minSpareThreads);
            protocol.setAcceptorThreadCount(acceptCount);
            protocol.setDisableUploadTimeout(disableUploadTimeout);
            protocol.setCompression(compression);
            protocol.setCompressionMinSize(compressionMinSize);
            protocol.setCompressableMimeType(compressableMimeType);
// connector.setURIEncoding(URIEncoding);
            connector.setEnableLookups(enableLookups);
        }
    }




    public int getMaxThreads() {
        return maxThreads;
    }




    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }




    public int getMinSpareThreads() {
        return minSpareThreads;
    }




    public void setMinSpareThreads(int minSpareThreads) {
        this.minSpareThreads = minSpareThreads;
    }




    public int getAcceptCount() {
        return acceptCount;
    }




    public void setAcceptCount(int acceptCount) {
        this.acceptCount = acceptCount;
    }




    public int getConnectionTimeout() {
        return connectionTimeout;
    }




    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }




    public String getURIEncoding() {
        return URIEncoding;
    }




    public void setURIEncoding(String uRIEncoding) {
        URIEncoding = uRIEncoding;
    }




    public boolean isDisableUploadTimeout() {
        return disableUploadTimeout;
    }




    public void setDisableUploadTimeout(boolean disableUploadTimeout) {
        this.disableUploadTimeout = disableUploadTimeout;
    }




    public boolean isEnableLookups() {
        return enableLookups;
    }




    public void setEnableLookups(boolean enableLookups) {
        this.enableLookups = enableLookups;
    }




    public String getCompression() {
        return compression;
    }




    public void setCompression(String compression) {
        this.compression = compression;
    }




    public int getCompressionMinSize() {
        return compressionMinSize;
    }




    public void setCompressionMinSize(int compressionMinSize) {
        this.compressionMinSize = compressionMinSize;
    }




    public String getCompressableMimeType() {
        return compressableMimeType;
    }




    public void setCompressableMimeType(String compressableMimeType) {
        this.compressableMimeType = compressableMimeType;
    }
}