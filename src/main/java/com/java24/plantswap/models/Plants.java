package com.java24.plantswap.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Document(collection = "plants")
public class Plants {
    @Id
    private String id;
    private String name;
    private String trivialName;
    private int age;

    @Field(targetType = FieldType.STRING) //This ensures that PlantSize is stored as a String in MongoDB.

    private PlantSize plantSize;

    @Field(targetType = FieldType.STRING)
    private PlantType plantType;
    @Field(targetType = FieldType.STRING)
    private WaterRequirement waterRequirement;
    @Field(targetType = FieldType.STRING)
    private LightRequirement lightRequirement;
    @Field(targetType = FieldType.STRING)
    private DifficultyLevel difficultyLevel;

    private String photo;
    @Field(targetType = FieldType.STRING)
    private PlantStatus plantStatus;
    @Field(targetType = FieldType.STRING)
    private PayMethod payMethod;
    private BigDecimal price;

    public LightRequirement getLightRequirement() {
        return lightRequirement;
    }

    public void setLightRequirement(LightRequirement lightRequirement) {
        this.lightRequirement = lightRequirement;
    }

    public Plants() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrivialName() {
        return trivialName;
    }

    public void setTrivialName(String trivialName) {
        this.trivialName = trivialName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PlantSize getPlantSize() {
        return plantSize;
    }

    public void setPlantSize(PlantSize plantSize) {
        this.plantSize = plantSize;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }

    public WaterRequirement getWaterRequirement() {
        return waterRequirement;
    }

    public void setWaterRequirement(WaterRequirement waterRequirement) {
        this.waterRequirement = waterRequirement;
    }



    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public PlantStatus getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(PlantStatus plantStatus) {
        this.plantStatus = plantStatus;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
