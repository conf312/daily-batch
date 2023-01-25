package com.daily.product.batch.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "livestock")
@Table(name = "livestock", catalog = "daily_product")
public class Livestock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long no;
    private String placeCode;
    private String placeName;
    private String productCode;
    private String productName;
    private String standard;
    private Long price;
    private String remarks;
    private String marketTypeCode;
    private String marketTypeName;
    private String autonomousCode;
    private String autonomousName;
    private String checkDate;

    @Builder
    public Livestock(Long id, Long no, String placeCode, String placeName, String productCode, String productName, String standard, Long price, String remarks, String marketTypeCode, String marketTypeName, String autonomousCode, String autonomousName, String checkDate) {
        this.id = id;
        this.no = no;
        this.placeCode = placeCode;
        this.placeName = placeName;
        this.productCode = productCode;
        this.productName = productName;
        this.standard = standard;
        this.price = price;
        this.remarks = remarks;
        this.marketTypeCode = marketTypeCode;
        this.marketTypeName = marketTypeName;
        this.autonomousCode = autonomousCode;
        this.autonomousName = autonomousName;
        this.checkDate = checkDate;
    }
}
