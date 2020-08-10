package com.zlp.travel.Response;

import com.zlp.travel.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author: zlp
 * Date: 2020-08-08 20:01
 * Description:张立朋，写点注释吧!!
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResponse {
    private Long total;
    private Long totalPage;
    private Integer page;
    private List<Place> places;
}
