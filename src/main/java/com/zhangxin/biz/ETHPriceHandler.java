package com.zhangxin.biz;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Files;
import com.zhangxin.utils.Constant;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangxin on 2018/1/14.
 */
@Service
public class ETHPriceHandler {
    public static File file = new File("price.txt");

    public void handle(Document sellpage, Document buypage) throws IOException {
        BigDecimal sellPrice = dealSellPrice(sellpage);
        BigDecimal marketPrice = dealMarketPrice(sellpage);
        String topSeller = dealTopper(sellpage);

        BigDecimal bugPrice = dealBuyPrice(buypage);
        String topBuyer = dealTopper(buypage);

        BigDecimal subprice = sellPrice.subtract(bugPrice);

        String format = Constant.YYMMDD.format(new Date());
        String show = Joiner.on(",").join(format, sellPrice, topSeller, bugPrice, topBuyer, marketPrice, subprice) + "\n";
        Files.append(show, file, Charsets.UTF_8);

        //===
        this.notityPrice(sellPrice, bugPrice);
    }

    private void notityPrice(BigDecimal sellPrice, BigDecimal bugPrice) {
        if (sellPrice.compareTo(new BigDecimal(Configs.getString("eth.sellprice.gt.notify"))) >= 0) {
            SoundPlayer.playMie();
        }
        if (bugPrice.compareTo(new BigDecimal(Configs.getString("eth.buyprice.lt.notify"))) <= 0) {
            SoundPlayer.playMie();
        }
        if (sellPrice.subtract(bugPrice).compareTo(new BigDecimal(Configs.getString("switch.eth.open.price.diff"))) >= 0) {
            SoundPlayer.playMie();
        }
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
        BigDecimal minprice = new BigDecimal(1000000);
        for (Element element : elements) {
            String text = element.text();
            text = text.replaceAll(",", "");
            if (new BigDecimal(text).compareTo(minprice) < 0) {
                minprice = new BigDecimal(text);
            }
        }

        return minprice;
    }

    private String dealTopper(Document document) {

        Elements remcard = document.getElementsByClass("recommend-card");
        Elements select = remcard.select("a[href^=/users]");
        return select.get(0).text().trim();
    }
}
