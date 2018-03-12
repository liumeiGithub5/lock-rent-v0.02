package com.yifushidai.service;

import com.yifushidai.entity.TokenEntity;
import com.yifushidai.mapper.TokenEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class TokenService {
	@Autowired
	private TokenEntityMapper tokenMapper;
	//12小时后过期  1年
	private final static long EXPIRE = (3600 * 24 * 365 );

	public TokenEntity queryByMobile(String mobile) {
		return tokenMapper.selectByPrimaryKey(mobile);
	}


	public TokenEntity queryByToken(String token) {
		return tokenMapper.queryByToken(token);
	}


	public void save(TokenEntity token){
		tokenMapper.insertSelective(token);
	}
	
	public void update(TokenEntity token){
		tokenMapper.updateByPrimaryKeySelective(token);
	}

	public Map<String, Object> createToken(String mobile /*long userId*/) {
		//生成一个token
		String token = UUID.randomUUID().toString();
		//当前时间
		Date now = new Date();

		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		TokenEntity tokenEntity = queryByMobile(mobile);
		if(tokenEntity == null){
			tokenEntity = new TokenEntity();
			tokenEntity.setMobile(mobile);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//保存token
			save(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);
			//更新token
			update(tokenEntity);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		map.put("expire", EXPIRE);
		return map;
	}
}
