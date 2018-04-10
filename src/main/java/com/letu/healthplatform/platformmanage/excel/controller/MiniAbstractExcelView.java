package com.letu.healthplatform.platformmanage.excel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.AbstractView;

/**
 * 基础抽象Excel View
 * @author JueYue
 * @date 2018年2月5日 上午9:27:22
 */
public abstract class MiniAbstractExcelView extends AbstractView {

    private static final String   CONTENT_TYPE = "application/vnd.ms-excel";

    protected static final String HSSF         = ".xls";
    protected static final String XSSF         = ".xlsx";

    public MiniAbstractExcelView() {
        setContentType(CONTENT_TYPE);
    }

    protected boolean isIE(HttpServletRequest request) {
        return (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 || request
            .getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0) ? true : false;
    }

}
