package com.test.model;

public class CardConnectRequestEntity {

    private String merchid;
    private long account;
    private int expiry;
    private long orderid;
    private long batchid;
    private String batchsource;
    private String currency;
    private String name;
    private String capture;
    private String receipt;

    public String getMerchid() {
        return merchid;
    }

    public void setMerchid(String merchid) {
        this.merchid = merchid;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public int getExpiry() {
        return expiry;
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public long getBatchid() {
        return batchid;
    }

    public void setBatchid(long batchid) {
        this.batchid = batchid;
    }

    public String getBatchsource() {
        return batchsource;
    }

    public void setBatchsource(String batchsource) {
        this.batchsource = batchsource;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapture() {
        return capture;
    }

    public void setCapture(String capture) {
        this.capture = capture;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    @Override
    public String toString() {
        return "CardConnectRequestEntity{" +
                "merchid='" + merchid + '\'' +
                ", account=" + account +
                ", expiry=" + expiry +
                ", orderid=" + orderid +
                ", batchid=" + batchid +
                ", batchsource='" + batchsource + '\'' +
                ", currency='" + currency + '\'' +
                ", name='" + name + '\'' +
                ", capture='" + capture + '\'' +
                ", receipt='" + receipt + '\'' +
                '}';
    }
}
