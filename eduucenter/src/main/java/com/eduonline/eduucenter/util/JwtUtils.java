package com.eduonline.eduucenter.util;


import com.eduonline.eduucenter.entity.UcenterMember;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;


import java.util.Date;

/**
 * @author helen
 * @since 2019/3/16
 */
public class JwtUtils {


	public static final String APPSECRET = "guli123456";

	/**
	 * 1 生成jwt令牌的方法
	 * @param member
	 * @return
	 */
	public static String genJsonWebToken(UcenterMember member){

	if(member == null
			|| StringUtils.isEmpty(member.getId())
			|| StringUtils.isEmpty(member.getNickname())
			|| StringUtils.isEmpty(member.getAvatar())){
		return null;
	}

		String token = Jwts.builder().setSubject("guli")
				.claim("id", member.getId())
				.claim("nickname", member.getNickname())
				.claim("avatar", member.getAvatar())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(SignatureAlgorithm.HS256, APPSECRET).compact();

		return token;
	}

	/**
	 * 2 根据jwt令牌获取里面用户信息
	 * @param token
	 * @return
	 */
	public static Claims checkJwt(String token){
		Claims claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
		return claims;
	}


}
