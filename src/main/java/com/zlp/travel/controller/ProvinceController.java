package com.zlp.travel.controller;

import com.zlp.travel.Response.ProvinceResponse;
import com.zlp.travel.entity.Province;
import com.zlp.travel.entity.Result;
import com.zlp.travel.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: zlp
 * Date: 2020-08-08 19:33
 * Description:张立朋，写点注释吧!!
 */
@RestController
@RequestMapping("province")
@CrossOrigin
@Slf4j
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    /**
     * 查找所有省份
     * @param pageNumber
     * @param pageSize
     * @return
     */

    @GetMapping("findProvinceByPage")
    public Map<String,Object> findProvinceByPage(Integer pageNumber, Integer pageSize){
        pageNumber = pageNumber == null?1:pageNumber;
        pageSize = pageSize == null?5:pageSize;
        HashMap<String,Object> map = new HashMap<>();
        ProvinceResponse response = provinceService.findProvinceByPage(pageNumber,pageSize);
        map.put("provinces",response.getProvinces());
        map.put("total",response.getTotal());
        map.put("totalPage",response.getTotalPage());
        map.put("page",response.getPage());
        return map;
    }


    /**
     * 添加省份信息
     * @param province
     * @return
     */
    @PostMapping("save")
    public Result addProvince(@RequestBody Province province){
        Result result =new Result();
        try{
            provinceService.addProvince(province);
            result.setMsg("添加省份成功！！");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("添加省份失败！！").setState(false);
        }
        return result;
    }

    /**
     * 删除省份
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result deleteProvinceById(@RequestParam(value = "id")Long id){
        Result result =new Result();
        try{
            provinceService.deleteProvinceById(id);
            result.setMsg("删除省份成功！！");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("删除省份失败！！").setState(false);
        }
        return result;
    }

    /**
     * 查找省份
     * @param id
     * @return
     */
    @GetMapping("findProvinceById")
    public Province findProvinceById(@RequestParam(value = "id")Long id){
        return provinceService.findProvinceById(id);
    }

    /**
     * 更新省份
     */
    @PostMapping("update")
    public Result updateProvinceById(@RequestBody Province province){
        Result result = new Result();
        try{
            provinceService.updateProvinceById(province);
            result.setMsg("更新成功！！");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("更新失败！！");
        }
        return result;
    }
}
