package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrelloMapperTestSuite {
    private TrelloMapper trelloMapper;
    private TrelloCardDto trelloCardDto;
    private TrelloCard trelloCard;
    private TrelloBoard trelloBoard;
    private TrelloBoardDto trelloBoardDto;
    private TrelloListDto trelloListDto;
    private TrelloList trelloList;
    private List<TrelloListDto> trelloListDtos;
    private List<TrelloList> trelloLists;
    private List<TrelloBoard> trelloBoardList;
    private List<TrelloBoardDto> trelloBoardDtoList;

    @BeforeEach
    void beforeEveryTest() {
        trelloMapper = new TrelloMapper();
        trelloListDtos = new ArrayList<>();
        trelloLists = new ArrayList<>();
        trelloBoardDtoList = new ArrayList<>();
        trelloBoardList = new ArrayList<>();

        trelloCard = new TrelloCard("testCard", "testDescription", "testPos", "111");
        trelloCardDto = new TrelloCardDto("testCard", "testDesc", "testPos", "111");
        trelloBoardDto = new TrelloBoardDto("1", "testBoard", trelloListDtos);
        trelloBoard = new TrelloBoard("1", "testBoard", trelloLists);
        trelloListDto = new TrelloListDto("1", "testList", false);
        trelloList = new TrelloList("1", "testList", true);
    }

    @Test
    void mapToCardDtoTest() {
        //Given&When
        trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("testCard", trelloCardDto.getName());
        assertEquals("111", trelloCardDto.getListId());
    }

    @Test
    void mapToCardTest() {
        //Given&When
        trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("testCard", trelloCard.getName());
        assertEquals("testPos", trelloCard.getPos());
    }

    @Test
    void mapToBoardTest() {
        //Given&When
        trelloBoard = trelloMapper.mapToBoard(trelloBoardDto);
        //Then
        assertEquals(0, trelloBoard.getLists().size());
        assertEquals("1", trelloBoard.getId());
    }

    @Test
    void mapToBoardsTest() {
        //Given
        trelloBoardDtoList.add(trelloBoardDto);
        //When
        trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals(1, trelloBoardList.size());
        assertEquals("testBoard", trelloBoardList.get(0).getName());
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        trelloBoardList.add(trelloBoard);
        //When
        trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertEquals("testBoard", trelloBoardDtoList.get(0).getName());
        assertEquals(1, trelloBoardDtoList.size());
    }

    @Test
    void mapToListTest() {
        //Given
        trelloListDtos.add(trelloListDto);
        //When
        trelloLists = trelloMapper.mapToList(trelloListDtos);
        //Then
        assertFalse(trelloLists.get(0).isClosed());
        assertEquals(1, trelloLists.size());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        trelloLists.add(trelloList);
        //When
        trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertTrue(trelloListDtos.get(0).isClosed());
        assertEquals(1, trelloListDtos.size());
    }
}