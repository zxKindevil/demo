package com.es.ch_1_lianjie;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.LocalTransportAddress;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 *
 * @author zhangxin.zhang created on 16-1-24.
 */
public class MAIN {
    public static void main(String[] args) {
//        Settings settings = ImmutableSettings.settingsBuilder()
//                .put("cluster.name", "elasticsearch").build();
        TransportClient transportClient = new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9200));
        System.out.println("done");
    }
}
