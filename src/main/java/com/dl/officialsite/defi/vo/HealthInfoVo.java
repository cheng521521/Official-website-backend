package com.dl.officialsite.defi.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName HealthInfo
 * @Author jackchen
 * @Date 2023/11/2 20:29
 * @Description HealthInfo
 **/
@Data
@Builder
public class HealthInfoVo {

    //健康系数
    private String healthFactor;

    //总借款
    private String totalBorrows;

    //总存款
    private String totalCollateralETH;

    //LTV数据获取
    private String totalLiquidity;
}
