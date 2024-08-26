package com.emailsender.models;

import java.sql.*;
import java.util.Date;


public class coupon_details {
    private String couponCode;
    private String description;
    private double discountPercentage;
    private Date validFrom;
    private Date validTo;

    protected static Connection con = null;
    protected static PreparedStatement pstm = null;
    protected static ResultSet rs = null;

    
    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

}