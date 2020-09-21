package easyexcel.service;


import easyexcel.dto.ColumnData;
import easyexcel.dto.ScoreDTO;

import java.util.List;

/**
 * DrivingBehaviorCompareService
 *
 * @author dongrui
 * @date 2020/7/29 11:20
 */
public interface DrivingBehaviorCompareService {
    /**
     * 获取业务数据
     *
     * @return 业务数据
     */
    List<ScoreDTO> getData();

    /**
     * 获取列格式的业务数据
     *
     * @return 列格式的数据
     */
    List<ColumnData> getDataByColumn();
}
