package com.project.railway.data.entity;

import javax.persistence.*;

@Entity
public class DiscountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentType;

    private String documentTypeBG;

    private int discountValue;

    @OneToMany(mappedBy = "discounttype", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)


    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentTypeBG() {
        return documentTypeBG;
    }

    public void setDocumentTypeBG(String documentTypeBG) {
        this.documentTypeBG = documentTypeBG;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    @Override
    public String toString() {
        return "DiscountType{" +
                "id=" + id +
                ", documentType='" + documentType + '\'' +
                ", documentTypeBG='" + documentTypeBG + '\'' +
                ", discountValue=" + discountValue +
                '}';
    }
}
