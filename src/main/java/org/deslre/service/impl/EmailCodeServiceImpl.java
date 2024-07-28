package org.deslre.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.deslre.config.AppConfig;
import org.deslre.convert.EmailCodeConvert;
import org.deslre.entity.po.EmailCode;
import org.deslre.entity.po.UserInfo;
import org.deslre.entity.vo.EmailCodeVO;
import org.deslre.exception.DeslreException;
import org.deslre.mapper.EmailCodeMapper;
import org.deslre.mapper.UserInfoMapper;
import org.deslre.page.PageResult;
import org.deslre.query.EmailCodeQuery;
import org.deslre.result.Constants;
import org.deslre.service.EmailCodeService;
import org.deslre.utils.RedisComponent;
import org.deslre.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * ClassName: EmailCodeServiceImpl
 * Description: 邮箱验证码
 * Author: Deslrey
 * Date: 2024-07-28 20:50
 * Version: 1.0
 */
@Service
public class EmailCodeServiceImpl extends BaseServiceImpl<EmailCodeMapper, EmailCode> implements EmailCodeService {

    private static final Logger logger = LoggerFactory.getLogger(EmailCodeServiceImpl.class);

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private EmailCodeMapper emailCodeMapper;

    @Resource
    private AppConfig appConfig;

    @Resource
    private RedisComponent redisComponent;

    @Override
    public PageResult<EmailCodeVO> page(EmailCodeQuery query) {
        IPage<EmailCode> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(page.getTotal(), page.getSize(), page.getCurrent(), page.getPages(), EmailCodeConvert.INSTANCE.convertList(page.getRecords()));
    }

    private QueryWrapper<EmailCode> getWrapper(EmailCodeQuery query) {
        QueryWrapper<EmailCode> wrapper = new QueryWrapper<>();

        return wrapper;
    }

    @Override
    public void save(EmailCodeVO vo) {
        EmailCode entity = EmailCodeConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(EmailCodeVO vo) {
        EmailCode entity = EmailCodeConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendEmailCode(String email, Integer type) {
        if (Objects.equals(type, Constants.ZERO)) {
            QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("email", email);
            UserInfo userInfo = userInfoMapper.selectOne(wrapper);
            if (null != userInfo) {
                throw new DeslreException("邮箱已经存在");
            }
        }

        String code = StringUtil.getRandomNumber(Constants.LENGTH_5);

        // 发送验证码
//        sendMailCode(email, code);

        System.err.println("邮箱验证码  ------------ >  " + code);

        emailCodeMapper.disableEmailCode(email);

        EmailCode emailCode = new EmailCode();
        emailCode.setCode(code);
        emailCode.setEmail(email);
        emailCode.setStatus(Constants.ZERO);
        emailCode.setCreatTime(new Date());
        this.save(emailCode);
    }


    @Override
    public void checkCode(String email, String code) {
        EmailCode emailCode = this.getOne
                (new QueryWrapper<EmailCode>()
                        .eq("email", email)
                        .eq("code", code));
        if (null == emailCode) {
            throw new DeslreException("邮箱验证码错误");
        }

        if (emailCode.getStatus() == 1 || System.currentTimeMillis() - emailCode.getCreatTime().getTime() > Constants.LENGTH_15 * 1000 * 60) {
            throw new DeslreException("邮箱验证码已失效");
        }

        emailCodeMapper.disableEmailCode(null);
    }
}