package com.example.javautils.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(language = "java", collection = "ConnectionInf")
public class ConnectionInf {
    @Id
    private String id;

    @Indexed
    private String host;

    @Indexed
    private String port;

    @Indexed
    private String username;

    @Indexed
    private String dbName;

    @Indexed
    private String password;

}
