package com.xxx.xcx01_server.po;


public class ReviceAddressParam {

//    cityName: "广州市"
//    countyName: "海珠区"
//    detailInfo: "新港中路397号"
//    errMsg: "chooseAddress:ok"
//    nationalCode: "510000"
//    postalCode: "510000"
//    provinceName: "广东省"
//    telNumber: "020-81167888"
//    userName: "张三"

    private String cityName;
    private String countyName;
    private String detailInfo;
    private String nationalCode;
    private String postalCode;
    private String provinceName;
    private String telNumber;
    private String userName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return
                cityName + ',' +
                countyName + ',' +
                detailInfo + ',' +
                nationalCode + ',' +
                postalCode + ',' +
                provinceName + ',' +
                telNumber + ',' +
                userName;
    }
}
