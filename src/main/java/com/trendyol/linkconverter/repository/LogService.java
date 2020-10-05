package com.trendyol.linkconverter.repository;

import com.couchbase.client.java.Collection;
import com.trendyol.linkconverter.domain.log.Log;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogService {

    private final Collection linkLogCollection;

    public void saveLog(Log log) {
        linkLogCollection.insert(log.getId(), log);
    }
}
