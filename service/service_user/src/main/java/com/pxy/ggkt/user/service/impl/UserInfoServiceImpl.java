package com.pxy.ggkt.user.service.impl;


import com.pxy.ggkt.model.user.UserInfo;
import com.pxy.ggkt.user.mapper.UserInfoMapper;
import com.pxy.ggkt.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author pxy
 * @since 2023-03-21
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
