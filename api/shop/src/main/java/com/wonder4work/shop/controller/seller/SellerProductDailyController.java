package com.wonder4work.shop.controller.seller;

import com.wonder4work.shop.domain.ProductSellDaily;
import com.wonder4work.shop.dto.EchartSeries;
import com.wonder4work.shop.dto.EchartXAxis;
import com.wonder4work.shop.service.ProductSellDailyService;
import com.wonder4work.shop.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xiezengcheng
 * @date 2020-09-21
 */
@RestController
@RequestMapping("/seller/product/daily")
public class SellerProductDailyController {

    @Autowired
    private ProductSellDailyService productSellDailyService;

    /*
     * 列出销量统计
     * */
    @RequestMapping(value = "/listproductselldailyinfo", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult listProductSellDailyInfobSshop(HttpServletRequest request) {


        Map<String, Object> modelMap = new HashMap<String, Object>();

        ProductSellDaily productSellDaily = new ProductSellDaily();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Long endTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DATE, -6);
        Long beginTime = calendar.getTimeInMillis();

        List<ProductSellDaily> productSellDailyList = productSellDailyService.listProductSellDaily(productSellDaily, beginTime, endTime);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        HashSet<String> legendData = new HashSet<>();
        HashSet<String> xData = new HashSet<>();

        List<EchartSeries> series = new ArrayList<>();
        List<Integer> totalList = new ArrayList<>();

        String currentProductName = "";

        for (int i = 0; i < productSellDailyList.size(); i++) {

            ProductSellDaily productSellDaily1 = productSellDailyList.get(i);

            //去重
            legendData.add(productSellDaily1.getProduct().getProductName());

            xData.add(sdf.format(productSellDaily1.getCreateTime()));

            if (!currentProductName.equals(productSellDaily1.getProduct().getProductName()) && !currentProductName.isEmpty()) {

                EchartSeries es = new EchartSeries();
                es.setName(currentProductName);

                es.setData(totalList.subList(0, totalList.size()));

                series.add(es);

                totalList = new ArrayList<>();

                currentProductName = productSellDaily1.getProduct().getProductName();

                totalList.add(productSellDaily1.getTotal());

            } else {
                totalList.add(productSellDaily1.getTotal());
                currentProductName = productSellDaily1.getProduct().getProductName();
            }

            if (i == productSellDailyList.size() - 1) {
                EchartSeries es = new EchartSeries();
                es.setName(currentProductName);
                es.setData(totalList.subList(0, totalList.size()));
                series.add(es);
            }

        }

        modelMap.put("series", series);
        modelMap.put("legendData", legendData);
        List<EchartXAxis> xAxes = new ArrayList<>();
        EchartXAxis echartXAxis = new EchartXAxis();
        echartXAxis.setData(xData);
        xAxes.add(echartXAxis);
        modelMap.put("xAxis", xAxes);
        modelMap.put("success", true);


        return JSONResult.ok(modelMap);

    }
}
