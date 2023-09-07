package com.xxx.xcx01_server.config;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.meilisearch.sdk.model.TaskInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MeiliSearchConfig {

    @Bean
    public Index index() throws MeilisearchException {

        Client client = new Client(new Config("http://127.0.0.1:7700", "123456"));

        TaskInfo taskInfo = client.createIndex("goods", "id");
        String indexUid = taskInfo.getIndexUid();

        Index index = client.index(indexUid);
        return index;
    }
}
