package com.dl.officialsite.defi.service;

import com.dl.officialsite.defi.vo.HealthInfoVo;
import com.dl.officialsite.defi.vo.TokenAPYInfoAllVo;
import com.dl.officialsite.config.ChainInfo;
import java.util.List;

/**
 * @ClassName AbstractTokenApy
 * @Author jackchen
 * @Date 2024/3/6 20:30
 * @Description AbstractTokenApy
 **/
public abstract class AbstractTokenAPY {

    /**
     * get token apy group by defi provider
     */
    public abstract List<TokenAPYInfoAllVo> queryTokenApy();

    public abstract HealthInfoVo getHealthInfo(ChainInfo chainInfo, String address);


}
