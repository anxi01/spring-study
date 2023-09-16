package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.wishlist.WishListRepository;
import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.entity.WishListEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListDto search(String query){

        // 지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        // 지역 검색 결과(개수)가 있을 때
        if(searchLocalRes.getTotal() > 0){
            var localItem = searchLocalRes.getItems().stream().findFirst().get();

            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            // 이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal() > 0){
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                // 결과를 리턴
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }
        return new WishListDto();
    }

    public WishListDto add(WishListDto wishListDto) {
        var entity = dtoToEntity(wishListDto);
        var saveEntity = wishListRepository.save(entity);
        return entityToDto(saveEntity);
    }


    private WishListEntity dtoToEntity(WishListDto wishListDto){
        var wishListEntity = new WishListEntity();
        wishListEntity.setIndex(wishListDto.getIndex());
        wishListEntity.setTitle(wishListDto.getTitle());
        wishListEntity.setCategory(wishListDto.getCategory());
        wishListEntity.setAddress(wishListDto.getAddress());
        wishListEntity.setRoadAddress(wishListDto.getRoadAddress());
        wishListEntity.setHomePageLink(wishListDto.getHomePageLink());
        wishListEntity.setImageLink(wishListDto.getImageLink());
        wishListEntity.setVisit(wishListDto.isVisit());
        wishListEntity.setVisitCount(wishListDto.getVisitCount());
        wishListEntity.setLastVisitDate(wishListDto.getLastVisitDate());

        return wishListEntity;
    }

    private WishListDto entityToDto(WishListEntity wishListEntity){
        var wishListDto = new WishListDto();
        wishListDto.setIndex(wishListEntity.getIndex());
        wishListDto.setTitle(wishListEntity.getTitle());
        wishListDto.setCategory(wishListEntity.getCategory());
        wishListDto.setAddress(wishListEntity.getAddress());
        wishListDto.setRoadAddress(wishListEntity.getRoadAddress());
        wishListDto.setHomePageLink(wishListEntity.getHomePageLink());
        wishListDto.setImageLink(wishListEntity.getImageLink());
        wishListDto.setVisit(wishListEntity.isVisit());
        wishListDto.setVisitCount(wishListEntity.getVisitCount());
        wishListDto.setLastVisitDate(wishListEntity.getLastVisitDate());

        return wishListDto;
    }

    public List<WishListDto> findAll() {
        return wishListRepository.findAll()
                .stream()
                .map(it -> entityToDto(it))
                .collect(Collectors.toList());
    }

    public void delete(int index) {
        wishListRepository.deleteById(index);
    }

    public void addVisit(int index){
        var wishItem = wishListRepository.findById(index);
        if(wishItem.isPresent()){
            var item = wishItem.get();

            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);
        }
    }
}
