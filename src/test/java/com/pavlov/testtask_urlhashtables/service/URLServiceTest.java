package com.pavlov.testtask_urlhashtables.service;

import com.pavlov.testtask_urlhashtables.entity.TodayURL;
import com.pavlov.testtask_urlhashtables.entity.YesterdayURL;
import com.pavlov.testtask_urlhashtables.repository.TodayURLRepository;
import com.pavlov.testtask_urlhashtables.repository.YesterdayURLRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({SpringExtension.class})
public class URLServiceTest {

    @TestConfiguration
    static class URLServiceTestConfiguration {
        @Bean
        public URLService urlService (TodayURLRepository todayURLRepository, YesterdayURLRepository yesterdayURLRepository) {
            return new URLService(todayURLRepository,yesterdayURLRepository);
        }
    }
    @MockBean
    private YesterdayURLRepository yesterdayURLRepository;

    @MockBean
    private TodayURLRepository todayURLRepository;

    private List<YesterdayURL> yesterdayURLs;
    private List<TodayURL> todayURLs;
    private URLService urlService;

    @BeforeEach
    void init () {
        urlService = new URLService(todayURLRepository,yesterdayURLRepository);

        YesterdayURL yesterdayURLFirst  = new YesterdayURL("vk.com","vk html");
        YesterdayURL yesterdayURLSecond  = new YesterdayURL("ya.ru","yandex html");
        yesterdayURLs = new ArrayList<>();
        yesterdayURLs.add(yesterdayURLFirst);
        yesterdayURLs.add(yesterdayURLSecond);

        todayURLs = new ArrayList<>();

        Mockito.when(todayURLRepository.findAll()).thenReturn(todayURLs);
        Mockito.when(yesterdayURLRepository.findAll()).thenReturn(yesterdayURLs);
    }

    @Test
    public void getNewURLsTest () {
        TodayURL todayURLFirst  = new TodayURL("vk.com","vk html");
        TodayURL todayURLSecond  = new TodayURL("ya.ru","yandex html");
        TodayURL todayURLThird = new TodayURL("youtube.com","youtube html");
        todayURLs.add(todayURLFirst);
        todayURLs.add(todayURLSecond);
        todayURLs.add(todayURLThird);

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(todayURLs.get(2).getUrl());

        List<String> newUrls = urlService.getNewURLs();
        assertEquals(expectedResult, newUrls);
    }

    @Test
    public void getEmptyListOfNewURLsTest () {
        TodayURL todayURLFirst  = new TodayURL("vk.com","vk html");
        TodayURL todayURLSecond  = new TodayURL("ya.ru","yandex html");
        todayURLs.add(todayURLFirst);
        todayURLs.add(todayURLSecond);

        List<String> expectedResult = new ArrayList<>();

        List<String> newUrls = urlService.getNewURLs();
        assertEquals(expectedResult, newUrls);
    }

    @Test
    public void getDeletedURLsTest () {
        TodayURL todayURLFirst  = new TodayURL("vk.com","vk html");
        todayURLs.add(todayURLFirst);

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(yesterdayURLs.get(1).getUrl());

        List<String> deletedURLs = urlService.getDeletedURLs();
        assertEquals(expectedResult, deletedURLs);
    }

    @Test
    public void getEmptyListOfDeletedURLsTest () {
        TodayURL todayURLFirst  = new TodayURL("vk.com","vk html");
        TodayURL todayURLSecond  = new TodayURL("ya.ru","yandex html");
        todayURLs.add(todayURLFirst);
        todayURLs.add(todayURLSecond);

        List<String> expectedResult = new ArrayList<>();

        List<String> deletedURLs = urlService.getDeletedURLs();
        assertEquals(expectedResult, deletedURLs);
    }

    @Test
    public void getChangedURLsTest () {
        TodayURL todayURLFirst  = new TodayURL("vk.com","vk html");
        TodayURL todayURLSecond  = new TodayURL("ya.ru","new yandex html");
        todayURLs.add(todayURLFirst);
        todayURLs.add(todayURLSecond);

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add(todayURLs.get(1).getUrl());

        List<String> changedUrls = urlService.getChangedURLs();
        assertEquals(expectedResult, changedUrls);
    }

    @Test
    public void getEmptyListOfChangedURLsTest () {
        TodayURL todayURLFirst  = new TodayURL("vk.com","vk html");
        TodayURL todayURLSecond  = new TodayURL("ya.ru","yandex html");
        todayURLs.add(todayURLFirst);
        todayURLs.add(todayURLSecond);

        List<String> expectedResult = new ArrayList<>();

        List<String> changedUrls = urlService.getChangedURLs();
        assertEquals(expectedResult, changedUrls);
    }


}
