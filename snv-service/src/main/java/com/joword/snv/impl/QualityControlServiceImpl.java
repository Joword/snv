package com.joword.snv.impl;

import com.joword.snv.QualityControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Joword
 * @date: 2023/1/29 11:02
 * @version: 1.0
 * @description: 质控数据实现类
 */
@Service
public class QualityControlServiceImpl implements QualityControlService {

    @Autowired
    private QualityControlService qualityControlService;

}
