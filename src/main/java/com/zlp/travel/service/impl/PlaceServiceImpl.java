package com.zlp.travel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlp.travel.Response.PlaceResponse;
import com.zlp.travel.Response.ProvinceResponse;
import com.zlp.travel.dao.PlaceDao;
import com.zlp.travel.dao.ProvinceDao;
import com.zlp.travel.entity.Place;
import com.zlp.travel.entity.Province;
import com.zlp.travel.service.PlaceService;
import com.zlp.travel.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: zlp
 * Date: 2020-08-08 20:03
 * Description:张立朋，写点注释吧!!
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private ProvinceService provinceService;
    /**
     * 通过省份id查找旅游景点
     * @param pageNumber
     * @param pageSize
     * @param provinceId
     * @return
     */
    @Override
    public PlaceResponse findPlaceByPage(Integer pageNumber, Integer pageSize,Long provinceId) {

        Page<Place> page = new Page<>(pageNumber,pageSize);
        QueryWrapper<Place> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("province_id",provinceId);
        //以门票的数量进行排序
        queryWrapper.orderByDesc("hotticket");
        IPage<Place> provinceIPage = placeDao.selectPage(page,queryWrapper);
        //获取记录总条数
        Long total = provinceIPage.getTotal();
        //获取记录
        List<Place> PlaceList =provinceIPage.getRecords();
        PlaceResponse PlaceResponse = new PlaceResponse();
        PlaceResponse.setTotal(total);
        PlaceResponse.setPlaces(PlaceList);
        return PlaceResponse;
    }

    /**
     * 添加旅游景点
     * @param place
     * @return
     */
    @Override
    public Integer addPlace(Place place) {
        Province province = provinceService.findProvinceById(place.getProvinceId());
        //更新省份景点的数量
        province.setPlacecounts(province.getPlacecounts()+1);
        provinceService.updateProvinceById(province);
        return placeDao.insert(place);
    }

    /**
     * 根据id删除景点
     * @param id
     * @return
     */
    @Override
    public Integer deletePlaceById(Long id) {
        //查找当前id的景点信息
        Place place = placeDao.selectById(id);
        Province province = provinceService.findProvinceById(place.getProvinceId());
        //更新省份景点的数量
        province.setPlacecounts(province.getPlacecounts()-1);
        provinceService.updateProvinceById(province);
        return placeDao.deleteById(id);
    }


    /**
     * 根据id查找景点
     * @param id
     * @return
     */
    @Override
    public Place findPlaceById(Long id) {
        return placeDao.selectById(id);
    }

    /**
     *更新景点
     * @param place
     * @return
     */
    @Override
    public Integer updatePlace(Place place) {
        return placeDao.updateById(place);
    }
}
