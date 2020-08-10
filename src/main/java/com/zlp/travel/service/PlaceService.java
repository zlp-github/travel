package com.zlp.travel.service;

import com.zlp.travel.Response.PlaceResponse;
import com.zlp.travel.entity.Place;

/**
 * Author: zlp
 * Date: 2020-08-08 19:33
 * Description:张立朋，写点注释吧!!
 */
public interface PlaceService {

    PlaceResponse findPlaceByPage(Integer pageNumber,Integer pageSize,Long provinceId);

    Integer addPlace(Place place);

    Integer deletePlaceById(Long id);

    Place findPlaceById(Long id);

    Integer updatePlace(Place place);
}
