package com.tech.test.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Joke implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    /**
     * Joke unique ide
     */
    String id;

    /**
     * Joke text with it's description
     */
    String text;

}
