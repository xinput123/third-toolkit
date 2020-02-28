package com.dozer;

import com.dozer.pojo.Source;
import com.dozer.pojo.Target01;
import com.dozer.pojo.Target02;
import com.dozer.pojo.Target03;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * @Author: xinput
 * @Date: 2020-02-28 09:47
 */
public class Case {

    /**
     * Source 和 Target01 的字段一模一样
     */
    @Test
    public void test01() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<String> habbies = Arrays.asList("read", "game");
        Source source = new Source()
                .setName("xinput")
                .setAge(10)
                .setBirthday(new Date())
                .setHabbies(habbies);

        Target01 target01 = mapper.map(source, Target01.class);
        assertThat(target01.getName()).isNotEmpty();
        assertThat(target01.getName()).isEqualTo(source.getName());

    }

    /**
     * Source 和 Target02 通过配置文件
     */
    @Test
    public void test02() {
        Mapper mapper = DozerBeanMapperBuilder.create()
                .withMappingFiles("dozerBeanMapping.xml")
                .build();

        List<String> habbies = Arrays.asList("read", "game");
        Source source = new Source()
                .setName("xinput")
                .setAge(10)
                .setBirthday(new Date())
                .setHabbies(habbies);

        Target02 target02 = mapper.map(source, Target02.class);
        assertThat(target02.getUserName()).isNotEmpty();
        assertThat(target02.getUserName()).isEqualTo(source.getName());
    }

    /**
     * Source 和 Target03 通过注解的方式
     * dozer 是双向映射的，只需要配置一个类上即可
     */
    @Test
    public void test03() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<String> habbies = Arrays.asList("read", "game");
        Source source = new Source()
                .setName("xinput")
                .setAge(10)
                .setBirthday(new Date())
                .setHabbies(habbies);

        Target03 target03 = mapper.map(source, Target03.class);
        assertThat(target03.getUserName()).isNotEmpty();
        assertThat(target03.getUserName()).isEqualTo(source.getName());

        Source source2 = mapper.map(target03, Source.class);
        assertThat(source2.getName()).isNotEmpty();
        assertThat(target03.getUserName()).isEqualTo(source2.getName());
    }

}
