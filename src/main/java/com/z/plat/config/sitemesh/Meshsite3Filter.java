package com.z.plat.config.sitemesh;

import com.z.plat.core.tagrules.CssTagRuleBundle;
import com.z.plat.core.tagrules.ScriptTagRuleBundle;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class Meshsite3Filter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/WEB-INF/views/plat/decorators/decorator.jsp")//拦截规则该路径会被转发
                .addExcludedPath("/login.htm")
                .addExcludedPath("/sys/user/searchUserLogin.htm")//白名单
                .addTagRuleBundles(new CssTagRuleBundle(),new ScriptTagRuleBundle());
    }
}
