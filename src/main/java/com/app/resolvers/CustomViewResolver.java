/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.resolvers;

import com.app.utils.LayoutResolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class CustomViewResolver extends UrlBasedViewResolver {

	private List<String> viewsToIgnore = new ArrayList<String>();

	public org.springframework.web.servlet.View createView(String viewName,
			Locale locale) throws Exception {
		// If this resolver is not supposed to handle the given view,
		// return null to pass on to the next resolver in the chain.
		return super.createView(LayoutResolver.resolveLayout(viewName), locale);
	}

	@Override
	protected Object getCacheKey(String viewName, Locale locale) {
		return super.getCacheKey(LayoutResolver.resolveLayout(viewName), locale);
	}

	public List<String> getViewsToIgnore() {
		return viewsToIgnore;
	}

	public void setViewsToIgnore(List<String> viewsToIgnore) {
		this.viewsToIgnore = viewsToIgnore;
	}
	
}
