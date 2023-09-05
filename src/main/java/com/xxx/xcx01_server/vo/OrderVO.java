package com.xxx.xcx01_server.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderVO {

    private String orderNo;

    private Integer orderStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private BigDecimal payMoney;

    private String reviceAddress;

    private String expressCompany;

    private String expressNO;

    private String payWay;

    private List<OrderGoodsVO> goodsList;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getReviceAddress() {
        return reviceAddress;
    }

    public void setReviceAddress(String reviceAddress) {
        this.reviceAddress = reviceAddress;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressNo() {
        return expressNO;
    }

    public void setExpressNo(String expressNO) {
        this.expressNO = expressNO;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public List<OrderGoodsVO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsVO> goodsList) {
        this.goodsList = goodsList;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getExpressNO() {
        return expressNO;
    }

    public void setExpressNO(String expressNO) {
        this.expressNO = expressNO;
    }
}
