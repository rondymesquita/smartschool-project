package br.com.async.util;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class HttpUtils {
	
	public static String generateToken(){
		return UUID.randomUUID().toString();
	}

	public static Cookie getCookieFromRequest(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();

		if (cookies == null)
			return null;

		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			if (name.equals(cookieName)) {
				return cookie;
			}
		}
		return null;
	}

}
