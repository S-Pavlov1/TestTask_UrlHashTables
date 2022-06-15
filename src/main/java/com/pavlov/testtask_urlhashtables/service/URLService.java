package com.pavlov.testtask_urlhashtables.service;

import com.pavlov.testtask_urlhashtables.entity.TodayURL;
import com.pavlov.testtask_urlhashtables.entity.YesterdayURL;
import com.pavlov.testtask_urlhashtables.repository.TodayURLRepository;
import com.pavlov.testtask_urlhashtables.repository.YesterdayURLRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class URLService {
    private final TodayURLRepository todayURLRepository;

    private final YesterdayURLRepository yesterdayURLRepository;
    public URLService(TodayURLRepository todayURLRepository, YesterdayURLRepository yesterdayURLRepository) {
        this.todayURLRepository = todayURLRepository;
        this.yesterdayURLRepository = yesterdayURLRepository;
    }

    public List<String> getDeletedURLs () {
        List<String> yesterdayURLS = yesterdayURLRepository.findAll().stream().map(YesterdayURL::getUrl).toList();
        List<String>todayURLS = todayURLRepository.findAll().stream().map(TodayURL::getUrl).toList();

        return checkListForRepeat(yesterdayURLS,todayURLS);
    }

    public List<String> getNewURLs () {
        List<String> yesterdayURLS = yesterdayURLRepository.findAll().stream().map(YesterdayURL::getUrl).collect(Collectors.toList());
        List<String>todayURLS = todayURLRepository.findAll().stream().map(TodayURL::getUrl).collect(Collectors.toList());

        return checkListForRepeat(todayURLS,yesterdayURLS);
    }

    public List<String> getChangedURLs () {
        List<YesterdayURL> yesterdayURLS = yesterdayURLRepository.findAll();
        List<TodayURL> todayURLS = todayURLRepository.findAll();

        return yesterdayURLS.stream().filter(y -> todayURLS.stream()
                .anyMatch(t -> t.getUrl().equals(y.getUrl()) && !t.getHtmlCode().equals(y.getHtmlCode())))
                .map(YesterdayURL::getUrl)
                .collect(Collectors.toList());
    }

    private List<String> checkListForRepeat(List<String> list, List<String> checkList) {
        return list.stream().filter(l -> !checkList.contains(l)).collect(Collectors.toList());
    }
}
