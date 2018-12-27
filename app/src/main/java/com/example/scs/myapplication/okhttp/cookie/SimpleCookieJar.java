package com.example.scs.myapplication.okhttp.cookie;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public  class SimpleCookieJar implements CookieJar {
	Context context;
	PersistentCookieStore cookieStore;
	public SimpleCookieJar(Context context) {
		this.context = context;
		cookieStore = new PersistentCookieStore(context);
	}
//	private final List<Cookie> allCookies = new ArrayList<>();
	@Override
	public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//		allCookies.addAll(cookies);
		for (Cookie item : cookies) {
			cookieStore.add(url, item);
		}
	}

	@Override
	public synchronized List<Cookie> loadForRequest(HttpUrl url) {
		List<Cookie> cookies = cookieStore.get(url);
		return cookies;
//		List<Cookie> result = new ArrayList<>();
//		for (Cookie cookie : allCookies) {
//			if (cookie.matches(url)) {
//				result.add(cookie);
//			}
//		}
//		return result;
	}
}
