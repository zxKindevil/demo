package com.zhangxin.test;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author zhangxin
 *         Created on 17/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class EsTest {
    @Resource
    private TransportClient client;

    @Before
    public void init() throws Exception {
//        Settings settings = Settings.builder()
//                .put("cluster.name", "mts.elastic.index.center.beta1").build();
//        client = new PreBuiltTransportClient(settings)
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.32.64.13"), 8300));
    }

    @Test
    public void test() throws IOException {
//        IndexResponse response = client.prepareIndex("test", "orde11", "2")
//                .setSource(jsonBuilder()
//                        .startObject()
//                        .field("id", 2)
//                        .field("aaa", "fuck测试你妹的aaaa")
//                        .field("bbb", "bbb")
//                        .field("ccc", "sdfdsfssdfsdf水电费2234水电费水电费是放水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费水电费我只是一个测试束带结发克里斯多夫级科室老地方级科室对方级科室对方就上课了打飞机是考虑到房间卡死了打飞机卡死了代缴费莱克斯顿及父类卡数据的福克斯来得及发可怜世纪东方考虑数据的反馈 ")
//                        .field("updateTime", new Date().getTime())
//                        .endObject()
//                )
//                .get();
//
//        client.close();

        System.out.println(client.connectedNodes());
        System.out.println("here");
    }

    @Test
    public void query() {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("*").requireFieldMatch(false);

        SearchRequestBuilder test = client.prepareSearch("test")
                .highlighter(highlightBuilder)
                .setQuery(QueryBuilders.queryStringQuery("我只是"));


        SearchResponse searchHits = test.get();
        System.out.println(searchHits);

        for (SearchHit hit : searchHits.getHits()) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();

            for (Map.Entry<String, HighlightField> entry : highlightFields.entrySet()) {
                HighlightField value = entry.getValue();
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        }


    }

    @Test
    public void delete() {
        DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .source("test")
                .filter(QueryBuilders.boolQuery().must(QueryBuilders.typeQuery("orde11")).filter(QueryBuilders.rangeQuery("id").lt(new Date().getTime())))
                .get();
    }


//    @Test
//    public void query() {
//        SearchResponse searchResponse = client.prepareSearch()
//                .setIndices("test")
//                .setTypes("order")
//                .setQuery(QueryBuilders.queryStringQuery("妹"))
//                .get();
//        System.out.println(searchResponse);
//    }
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
