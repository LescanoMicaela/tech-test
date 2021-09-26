package com.tech.test.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Joke data transfer object
 * for IJokeService
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JokeDTO {

    /**
     * Joke unique ide
     */
    @Schema(description = "Joke unique id", example = "jpWRorhwSSmVh4EzW00oXQ")
    String id;

    /**
     * Joke text with it's description
     */
    @Schema(ref = "Joke description", example = "There are no Chuck Norris Jokes. Only Chuck Norris Facts.")
    String text;

    /**
     * Getter method for id
     *
     * @return String the id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for id
     *
     * @param id joke id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for joke text
     * It maps the key value from JSON
     * to key text
     *
     * @return String the joke
     */
    @JsonProperty("text")
    public String getText() {
        return text;
    }

    /**
     * Setter for joke text
     * It maps th key text from JSON
     * to value
     *
     * @param text the joke
     */
    @JsonProperty("value")
    @Schema(hidden = true)
    public void setText(String text) {
        this.text = text;
    }


}
