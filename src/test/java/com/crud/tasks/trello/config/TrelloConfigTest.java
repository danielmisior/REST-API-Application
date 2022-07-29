package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class TrelloConfigTest {

    private TrelloConfig trelloConfig;

    @Test
    void gettersShouldBeNullTest() {
        //Given
        trelloConfig = new TrelloConfig();
        //When
        String token = trelloConfig.getTrelloToken();
        String key = trelloConfig.getTrelloAppKey();
        String username = trelloConfig.getTrelloUsername();
        String endpoint = trelloConfig.getTrelloApiEndpoint();
        //Then
        assertNull(token);
        assertNull(key);
        assertNull(username);
        assertNull( endpoint);
    }
}
