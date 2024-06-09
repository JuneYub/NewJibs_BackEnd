package com.ssafy.happyhouse.mapper;

import com.ssafy.happyhouse.entity.news.News;
import com.ssafy.happyhouse.entity.news.NewsLetter;

import java.util.List;

public interface NewsMapper {

    // 부동산 연관 뉴스 조회
    List<News> getRelatedNews();

    // 뉴스레터 구독자 조회
    List<String> getRecipients();

    // 뉴스레터 저장
    void writeNewsLetter(NewsLetter newsLetter);

    // 뉴스레터 구독 취소
    void unsubscribe(String orgEmail);

    // 구독 여부 조회
    int checkSubscribe(Long memberId);

    // 구독 갱신
    void updateIsSubscribe(Long memberId);
}
