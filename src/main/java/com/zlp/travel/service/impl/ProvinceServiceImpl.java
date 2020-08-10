package com.zlp.travel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlp.travel.Response.ProvinceResponse;
import com.zlp.travel.dao.ProvinceDao;
import com.zlp.travel.entity.Province;
import com.zlp.travel.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: zlp
 * Date: 2020-08-08 19:40
 * Description:张立朋，写点注释吧!!
 */
@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;

    @Override
    public ProvinceResponse findProvinceByPage(Integer pageNumber, Integer pageSize) {
        Page<Province> page = new Page<>(pageNumber,pageSize);
        QueryWrapper<Province> queryWrapper = new QueryWrapper<>();
        IPage<Province> provinceIPage = provinceDao.selectPage(page,queryWrapper);
        //获取记录总条数
        Long total = provinceIPage.getTotal();
        //获取记录
        List<Province> provinceList =provinceIPage.getRecords();
        //获取页数
        Long totalPage = total%pageSize == 0 ? total/pageSize:(total/pageSize)+1;
        ProvinceResponse provinceResponse = new ProvinceResponse();
        provinceResponse.setTotal(total);
        provinceResponse.setProvinces(provinceList);
        provinceResponse.setTotalPage(totalPage);
        provinceResponse.setPage(pageNumber);
        return provinceResponse;
    }

    /**
     * 添加省份
     * @param province
     * @return
     */
    @Override
    public Integer addProvince(Province province) {
        return provinceDao.insert(province);
    }

    /**
     * 删除省份
     * @param id
     * @return
     */
    @Override
    public Integer deleteProvinceById(Long id) {
        return provinceDao.deleteById(id);
    }

    /**
     * 根据id查找省份
     * @param id
     * @return
     */
    @Override
    public Province findProvinceById(Long id) {
        return provinceDao.selectById(id);
    }

    @Override
    public Integer updateProvinceById(Province province) {
        return provinceDao.updateById(province);
    }
}
