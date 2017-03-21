package com.zhangxin.test;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author zhangxin
 *         Created on 17/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class EsTest {
    //    @Resource
    private TransportClient client;

    @Before
    public void init() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "my-application").build();

        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
    }

    @Test
    public void test() throws IOException {
        Settings settings = Settings.builder()
                .put("cluster.name", "my-application").build();

        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        Order order = new Order();

        IndexResponse response = client.prepareIndex("test", "order", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("id", 1)
                        .field("aaa", "fuck测试你妹的")
                        .field("bbb", "bbb")
                        .field("ccc", "ccc")
                        .endObject()
                )
                .get();

        client.close();

        System.out.println("here");
    }

    @Test
    public void query() {
        SearchResponse searchResponse = client.prepareSearch()
                .setIndices("test")
                .setTypes("order")
                .setQuery(QueryBuilders.queryStringQuery("妹"))
                .get();
        System.out.println(searchResponse);
    }
}

class Order implements Serializable {
    private int id;
    private String aaa;
    private String bbb;
    private String ccc;

    public Order() {
    }

    public Order(int id, String aaa, String bbb, String ccc) {
        this.id = id;
        this.aaa = aaa;
        this.bbb = bbb;
        this.ccc = ccc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    public String getBbb() {
        return bbb;
    }

    public void setBbb(String bbb) {
        this.bbb = bbb;
    }

    public String getCcc() {
        return ccc;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }
}
