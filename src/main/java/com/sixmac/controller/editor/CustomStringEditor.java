package com.sixmac.controller.editor;

import com.sixmac.utils.ConfigUtil;
import org.apache.commons.lang.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * Created by wangbin on 2015/7/7.
 */
public class CustomStringEditor extends PropertyEditorSupport {


    @Override
    public void setAsText(String text) {
        if (text == null) {
            setValue(null);
        } else {
            String value = text;
            value = value.replace("<","&lt").replace(">","&gt");
            setValue(value);
        }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        if(value != null) {
            String valueStr = value.toString();
            if(StringUtils.isNotBlank(valueStr) && valueStr.contains("files/upload")) {
                return ConfigUtil.getString("upload.url") + valueStr;
            }
        }

        return value != null ? value.toString() : "";
    }
}
