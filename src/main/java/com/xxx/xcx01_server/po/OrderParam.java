package com.xxx.xcx01_server.po;

import java.util.List;

public class OrderParam {

    private ReviceAddressParam reviceAddress;

    private String payWay;

    private List<OrderGoodsParam> goodsList;

    public ReviceAddressParam getReviceAddress() {
        return reviceAddress;
    }

    public void setReviceAddress(ReviceAddressParam reviceAddress) {
        this.reviceAddress = reviceAddress;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public List<OrderGoodsParam> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsParam> goodsList) {
        this.goodsList = goodsList;
    }
}
