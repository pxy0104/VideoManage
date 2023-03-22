package com.pxy.ggkt.activity.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pxy.ggkt.activity.mapper.CouponInfoMapper;
import com.pxy.ggkt.activity.service.CouponInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxy.ggkt.activity.service.CouponUseService;
import com.pxy.ggkt.client.user.UserInfoFeignClient;
import com.pxy.ggkt.model.activity.CouponInfo;
import com.pxy.ggkt.model.activity.CouponUse;
import com.pxy.ggkt.model.user.UserInfo;
import com.pxy.ggkt.vo.activity.CouponUseQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author pxy
 * @since 2023-03-21
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    @Autowired
    private CouponUseService couponUseService;
    @Autowired
    private UserInfoFeignClient userInfoFeignClient;

    //get used coupon list
    @Override
    public IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo) {

        //获取条件值
        Long couponId = couponUseQueryVo.getCouponId();
        String couponStatus = couponUseQueryVo.getCouponStatus();
        String getTimeBegin = couponUseQueryVo.getGetTimeBegin();
        String getTimeEnd = couponUseQueryVo.getGetTimeEnd();

        //经典判空
        QueryWrapper<CouponUse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(couponId)) {
            wrapper.eq("coupon_id", couponId);
        }
        if (!StringUtils.isEmpty(couponStatus)) {
            wrapper.eq("coupon_status", couponStatus);
        }
        if (!StringUtils.isEmpty(getTimeBegin)) {
            wrapper.ge("get_time", getTimeBegin);
        }
        if (!StringUtils.isEmpty(getTimeEnd)) {
            wrapper.le("get_time", getTimeEnd);
        }

        //调用方法条件分页查询
        IPage<CouponUse> pageModel = couponUseService.page(pageParam, wrapper);

        //封装用户昵称和手机号
        List<CouponUse> couponUseList = pageModel.getRecords();
        couponUseList.stream().forEach(item -> {
            this.getUserInfoBycouponUse(item);
        });
        return pageModel;
    }

    //Feign
    private CouponUse getUserInfoBycouponUse(CouponUse couponUse) {
        Long userId = couponUse.getUserId();
        if (!StringUtils.isEmpty(userId)) {
            UserInfo userInfo = userInfoFeignClient.getById(userId);
            if (userInfo != null) {
                couponUse.getParam().put("nickName", userInfo.getNickName());
                couponUse.getParam().put("phone", userInfo.getPhone());
            }
        }
        return couponUse;
    }
}
