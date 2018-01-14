package com.zhangxin.biz;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;

/**
 * @author zhangxin on 2018/1/14.
 */
public class ETHPriceHandler {

    public void handle(Document sellpage, Document buypage) {
        BigDecimal sellPrice = dealSellPrice(sellpage);
        BigDecimal marketPrice = dealMarketPrice(sellpage);
        String topSeller = dealTopper(sellpage);

        BigDecimal bugPrice = dealBuyPrice(buypage);
        String topBuyer = dealTopper(buypage);

        BigDecimal subprice = sellPrice.subtract(bugPrice);
    }

    private BigDecimal dealBuyPrice(Document buypage) {
        Elements elements = buypage.getElementsByClass("recommend-card__price");
        BigDecimal max = BigDecimal.ZERO;
        for (Element element : elements) {
            String text = element.text();
            text = text.replaceAll(",", "");
            if (new BigDecimal(text).compareTo(max) > 0) {
                max = new BigDecimal(text);
            }
        }

        return max;
    }

    private BigDecimal dealMarketPrice(Document sellpage) {
        Elements box = sellpage.getElementsByClass("box");
        Elements select = box.select("span[class=price]");
        String shijia = select.get(0).text();
        shijia = shijia.replaceAll("CNY", "").replaceAll(" ", "").replaceAll(",", "");
        return new BigDecimal(shijia);
    }

    private BigDecimal dealSellPrice(Document doc) {
        Elements elements = doc.getElementsByClass("recommend-card__price");
        BigDecimal maxSellPrice = BigDecimal.ZERO;
        for (Element element : elements) {
            String text = element.text();
            text = text.replaceAll(",", "");
            if (new BigDecimal(text).compareTo(maxSellPrice) > 0) {
                maxSellPrice = new BigDecimal(text);
            }
        }

        return maxSellPrice;
    }

    private String dealTopper(Document document) {

        Elements remcard = document.getElementsByClass("recommend-card");
        Elements select = remcard.select("a[href^=/users]");
        return select.get(0).text().trim();
    }
}
