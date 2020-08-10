package com.zlp.travel.service;

import com.zlp.travel.Response.ProvinceResponse;
import com.zlp.travel.entity.Province;

/**
 * Author: zlp
 * Date: 2020-08-08 19:33
 * Description:张立朋，写点注释吧!!
 */
public interface ProvinceService {

    ProvinceResponse findProvinceByPage(Integer pageNumber, Integer pageSize);

    Integer addProvince(Province province);

    Integer deleteProvinceById(Long id);

    Province findProvinceById(Long id);

    Integer updateProvinceById(Province province);
}
