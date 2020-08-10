package com.zlp.travel.controller;

import com.zlp.travel.Response.PlaceResponse;
import com.zlp.travel.entity.Place;
import com.zlp.travel.entity.Result;
import com.zlp.travel.service.PlaceService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: zlp
 * Date: 2020-08-08 19:32
 * Description:张立朋，写点注释吧!!
 */
@RestController
@RequestMapping("place")
@CrossOrigin
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Value("${upload.dir}")
    private String realPath;

    /**
     * 根据省份id查询所有景点
     * @param pageNumber
     * @param pageSize
     * @param provinceId
     * @return
     */
    @GetMapping("getOnePlaces")
    public Map<String,Object> getAllPlaces(@RequestParam(value = "pageNumber") Integer pageNumber,
                                           @RequestParam(value = "pageSize") Integer pageSize,
                                           @RequestParam(value = "provinceId") Long provinceId){
        pageNumber = pageNumber == null?1:pageNumber;
        pageSize = pageSize == null?5:pageSize;
        HashMap<String,Object> map = new HashMap<>();
        PlaceResponse response = placeService.findPlaceByPage(pageNumber,pageSize,provinceId);
        map.put("places",response.getPlaces());
        map.put("total",response.getTotal());
        map.put("totalPage",response.getTotalPage());
        map.put("page",response.getPage());
        return map;
    }

    /**
     * 添加旅游景点
     * @param place
     * @return
     */
    @PostMapping("save")
    public Result addPlace(MultipartFile pic, Place place) throws IOException {
        Result result =new Result();
        try{
            // 文件上传
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
            // base64编码处理(注意, 这一步必须放在 transferTo 操作前面!)
            place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
            // 文件上传
            File file = new File(realPath);
            pic.transferTo(new File(file,newFileName));

            //保存place对像
            placeService.addPlace(place);
            result.setMsg("添加旅游景点成功！！");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("添加旅游景点失败！！").setState(false);
        }
        return result;
    }

    /**
     * 根据id删除景点
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result deletePlaceById(@RequestParam(value = "id") Long id){
        Result result =new Result();
        try{
            placeService.deletePlaceById(id);
            result.setMsg("删除景点成功！！");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("删除景点失败！！").setState(false);
        }
        return result;
    }

    /**
     * 通过id查找景点
     * @param id
     * @return
     */
    @GetMapping("findPlaceById")
    public Place findPlaceById(@RequestParam(value = "id") Long id){
        return placeService.findPlaceById(id);
    }

    @PostMapping("update")
    public Result updatePlace(MultipartFile pic, Place place){
        Result result =new Result();
        try{
            //文件上传
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ extension;
            //base64编码
            place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
            //文件上传
            File file = new File(realPath);
            pic.transferTo(new File(file,newFileName));
            //更新place对像
            placeService.updatePlace(place);
            result.setMsg("修改旅游景点成功！！");
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("修改旅游景点失败！！").setState(false);
        }
        return result;
    }
}
