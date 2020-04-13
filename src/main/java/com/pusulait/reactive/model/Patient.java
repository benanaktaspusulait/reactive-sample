package com.pusulait.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @Indexed
    private String id;

    private String forename;
    private String surname;
}
