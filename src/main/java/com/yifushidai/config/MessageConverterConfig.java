package com.yifushidai.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设置fastjson解析json数据
 * Created by hai on 2017/4/8.
 */
@Configuration
public class MessageConverterConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<MediaType>(2);
        //TODO 构造 mediaTypes时 不能直接使用new MediaType(MediaType.APPLICATION_JSON_UTF8_VALUE),会报错：
        //TODO Invalid token character '/' in token "application/json;charset=UTF-8"
        final Map<String, String> parameterMap = new HashMap<String, String>(4);
        parameterMap.put("charset", "utf-8");

        mediaTypes.add(new MediaType("text","html", parameterMap));
        mediaTypes.add(new MediaType("application","json", parameterMap));
        //TODO 设置支持的mediaType
        fastConverter.setSupportedMediaTypes(mediaTypes);
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //TODO 设置序列化feature
        fastJsonConfig.setSerializerFeatures
                (new SerializerFeature[]{
                        SerializerFeature.PrettyFormat,
                        //SerializerFeature.WriteMapNullValue, //TODO 序列化null，这里不序列null，浪费流量
                        SerializerFeature.QuoteFieldNames,
                        SerializerFeature.WriteDateUseDateFormat,//格式化日期，会转成字符串，否则是long时间
                        SerializerFeature.DisableCircularReferenceDetect //TODO 禁用fastjson循环引用检测
                });
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }

}
