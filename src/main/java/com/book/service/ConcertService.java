package com.book.service;

import com.book.concert.Performance;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by jotaiwan on 3/08/2017.
 */
@Service
public class ConcertService implements Performance {
    private static final Logger logger = LoggerFactory.logger(ConcertService.class);

    @Override
    public void perform() {
        logger.info("Concert starts performing now");
    }
}
