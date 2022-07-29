package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloValidatorTestSuite {

    private TrelloValidator trelloValidator;

    @Test
    void validateTrelloBoardsTest() {
        //Given
        trelloValidator = new TrelloValidator();
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "board_name", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2", "test", new ArrayList<>()));
        //When
        List<TrelloBoard> resultList = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(1, resultList.size());
        assertEquals("board_name", resultList.get(0).getName());
    }
}
