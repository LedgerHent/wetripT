package com.viptrip.wetrip.dao.ext;

import org.springframework.stereotype.Repository;

import com.viptrip.base.dao.BaseDaoForJPA;
import com.viptrip.wetrip.dao.GetSmsCheckCodeDao;

@Repository("GetSmsCheckCodeDao")
public class GetSmsCheckCodeDaoExt extends BaseDaoForJPA implements GetSmsCheckCodeDao{

}
