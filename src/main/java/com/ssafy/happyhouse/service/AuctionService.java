package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.auction.AuctionProjection;
import com.ssafy.happyhouse.entity.auction.Auction;
import com.ssafy.happyhouse.global.error.ErrorCode;
import com.ssafy.happyhouse.global.error.exception.EntityNotFoundException;
import com.ssafy.happyhouse.mapper.AuctionMapper;
import com.ssafy.happyhouse.mapper.StoreMapper;
import com.ssafy.happyhouse.repository.AuctionRepository;
import com.ssafy.happyhouse.repository.DongCodeRepository;
import com.ssafy.happyhouse.repository.StoreRepository;
import com.ssafy.happyhouse.request.AddressName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final DongCodeRepository dongCodeRepository;
    private final AuctionRepository auctionRepository;

    public List<AuctionProjection> getAuctionsByGuGunCode(AddressName addressName) {

        String dongCode = dongCodeRepository.findDongCodeByDongName(addressName.getGugunName(), addressName.getDongName());
        String gugunCode = dongCode.substring(0,5) + "00000";

        return auctionRepository.getAuctionsByGuGunCode(gugunCode);
    }

    public Auction getAuctionById(Long id) {
        return auctionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_EXISTS_AUCTION));
    }
}
