package com.pusulait.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "patient")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends BaseEntity {

    @Field
    private String forename;

    @Field
    private String surname;

    @Field
    private Integer age;

    @Field
    private IllnessType illnessType;

}


