package com.xxx.xcx01_server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Index;
import com.xxx.xcx01_server.entity.GoodsEntity;
import com.xxx.xcx01_server.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndexSearchCommandLineRunner implements CommandLineRunner {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private Index index;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        List<GoodsEntity> list = goodsService.list();
        String s = objectMapper.writeValueAsString(list);
        index.addDocuments(s);
    }
}
