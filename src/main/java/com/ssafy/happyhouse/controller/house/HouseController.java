package com.ssafy.happyhouse.controller.house;

import com.ssafy.happyhouse.dto.house.HouseDetailDto;
import com.ssafy.happyhouse.entity.house.HouseDeal;
import com.ssafy.happyhouse.request.AddressName;
import com.ssafy.happyhouse.response.MapGugunMarkerInfo;
import com.ssafy.happyhouse.response.MapMarkerInfo;
import com.ssafy.happyhouse.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    /**
     * 동 번호로 아파트 거래 내역을 조회
     * @param dongCode
     * @return
     */
    @GetMapping("/list/code/{code}")
    public ResponseEntity<?> findApartListByDongCode(@PathVariable("code") String dongCode){

        List<HouseDetailDto.ByDong> findList = houseService.findHouseDealByDongCode(dongCode);

        if (findList.isEmpty())
            return ResponseEntity.ok(Collections.emptyList());

        return ResponseEntity.ok(findList);
    }

    /**
     * 아파트명으로 아파트 정보를 검색
     * @param apartName
     * @return
     */
    @GetMapping("/list/name/{name}")
    public ResponseEntity<?> findApartListByName(@PathVariable("name") String apartName) {

        List<HouseDetailDto.ByAptName> findList = houseService.findHouseDealByName(apartName);

        if (findList.isEmpty())
            return ResponseEntity.ok(Collections.emptyList());

        return ResponseEntity.ok(findList);
    }

    /**
     * 주소를 기반으로 모든 아파트 정보를 조회
     * @param addressName
     * @return
     */
    @GetMapping("/list/apart")
    public ResponseEntity<?> findApartListByAddressName(@ModelAttribute AddressName addressName) {

        List<MapMarkerInfo> findList = houseService.findApartListByAddressName(addressName);

        return ResponseEntity.ok(findList);
    }

    /**
     * 아파트 정보 조회
     * @param aptCode 아파트 id
     * @return
     */
    @GetMapping("/detail/{code}")
    public ResponseEntity<?> findDetail(@PathVariable("code") Long aptCode){

        List<HouseDetailDto.ByAptcode> findDetail = houseService.findHouseDealByAptCode(aptCode);

        if (findDetail.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(findDetail);
    }

    /**
     * 구군 5년치 평균 실거래가 정보
     * @return
     */
    @GetMapping("/markers/gugun-avg-deal")
    public ResponseEntity<?> findGugunAvgDealAmount() {
        List<MapGugunMarkerInfo> findList = houseService.findGugunAvgDealAmount();
        if(findList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(findList);
    }


}
